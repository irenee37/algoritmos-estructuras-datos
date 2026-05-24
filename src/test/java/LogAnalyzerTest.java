import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import static org.junit.Assert.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Tests para la clase LogAnalyzer
 */
public class LogAnalyzerTest {
    
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    
    private String testLogFile;
    
    @Before
    public void setUp() throws IOException {
        // Crear un archivo de test temporal
        File file = tempFolder.newFile("test_logs.txt");
        testLogFile = file.getAbsolutePath();
    }
    
    private void writeLogsToFile(String... lines) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(testLogFile))) {
            for (String line : lines) {
                writer.println(line);
            }
        }
    }
    
    @Test
    public void testReadValidLogs() throws IOException {
        writeLogsToFile(
            "192.168.1.10;01/15/2026-08:30:45;/api/users;200",
            "192.168.1.10;01/15/2026-08:31:00;/api/users;200",
            "10.0.0.1;01/15/2026-09:00:00;/home;200"
        );
        
        Map<String, IpRecord> table = LogAnalyzer.readLogFile(testLogFile);
        
        assertNotNull(table);
        assertEquals(2, table.size());
        assertTrue(table.containsKey("192.168.1.10"));
        assertTrue(table.containsKey("10.0.0.1"));
        assertEquals(2, table.get("192.168.1.10").getRequestCount());
        assertEquals(1, table.get("10.0.0.1").getRequestCount());
    }
    
    @Test
    public void testInvalidLinesMissingFields() throws IOException {
        writeLogsToFile(
            "192.168.1.10;01/15/2026-08:30:45;/api/users;200",
            "invalid line without enough fields",
            "10.0.0.1;01/15/2026-09:00:00;/home;200"
        );
        
        Map<String, IpRecord> table = LogAnalyzer.readLogFile(testLogFile);
        
        // Debería leer solo las 2 líneas válidas
        assertEquals(2, table.size());
        assertTrue(table.containsKey("192.168.1.10"));
        assertTrue(table.containsKey("10.0.0.1"));
    }
    
    @Test
    public void testInvalidHttpCode() throws IOException {
        writeLogsToFile(
            "192.168.1.10;01/15/2026-08:30:45;/api/users;200",
            "192.168.1.10;01/15/2026-08:31:00;/api/users;not_a_number",
            "10.0.0.1;01/15/2026-09:00:00;/home;200"
        );
        
        Map<String, IpRecord> table = LogAnalyzer.readLogFile(testLogFile);
        
        // Debería leer solo las 2 líneas con código HTTP válido
        assertEquals(2, table.size());
        assertEquals(1, table.get("192.168.1.10").getRequestCount());
    }
    
    @Test
    public void testDuplicateIpsAggregation() throws IOException {
        writeLogsToFile(
            "192.168.1.10;01/15/2026-08:30:45;/api/users;200",
            "192.168.1.10;01/15/2026-08:31:00;/api/users;200",
            "192.168.1.10;01/15/2026-08:31:15;/api/products;200",
            "192.168.1.10;01/15/2026-08:31:30;/api/users;200",
            "192.168.1.10;01/15/2026-08:31:45;/api/orders;201"
        );
        
        Map<String, IpRecord> table = LogAnalyzer.readLogFile(testLogFile);
        
        assertEquals(1, table.size());
        assertEquals(5, table.get("192.168.1.10").getRequestCount());
    }
    
    @Test
    public void testEmptyFile() throws IOException {
        writeLogsToFile();
        
        Map<String, IpRecord> table = LogAnalyzer.readLogFile(testLogFile);
        
        assertNotNull(table);
        assertEquals(0, table.size());
    }
    
    @Test
    public void testMixedValidAndInvalidLines() throws IOException {
        writeLogsToFile(
            "192.168.1.10;01/15/2026-08:30:45;/api/users;200",
            "invalid",
            "10.0.0.1;01/15/2026-09:00:00;/home;200",
            "8.8.8.8;01/15/2026-10:00:00;/api/users;not_valid",
            "192.168.1.11;01/15/2026-11:00:00;/api/admin;403"
        );
        
        Map<String, IpRecord> table = LogAnalyzer.readLogFile(testLogFile);
        
        // Solo 3 líneas válidas
        assertEquals(3, table.size());
        assertTrue(table.containsKey("192.168.1.10"));
        assertTrue(table.containsKey("10.0.0.1"));
        assertTrue(table.containsKey("192.168.1.11"));
        assertFalse(table.containsKey("8.8.8.8"));
    }
    
    @Test
    public void testLargeIpCount() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            String ip = "192.168.1." + (i % 256);
            sb.append(ip).append(";01/15/2026-08:30:45;/api/users;200\n");
        }
        Files.write(Paths.get(testLogFile), sb.toString().getBytes());
        
        Map<String, IpRecord> table = LogAnalyzer.readLogFile(testLogFile);
        
        // Debería haber 256 IPs únicas
        assertEquals(256, table.size());
    }
}
