import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Tests para la clase IpRecord
 */
public class IpRecordTest {
    
    private IpRecord record1;
    private IpRecord record2;
    private IpRecord record3;
    
    @Before
    public void setUp() {
        record1 = new IpRecord("192.168.1.10", 5);
        record2 = new IpRecord("10.0.0.1", 3);
        record3 = new IpRecord("192.168.1.10", 10);
    }
    
    @Test
    public void testGetters() {
        assertEquals("192.168.1.10", record1.getIp());
        assertEquals(5, record1.getRequestCount());
    }
    
    @Test
    public void testIncrementRequestCount() {
        record1.incrementRequestCount();
        assertEquals(6, record1.getRequestCount());
        
        record1.incrementRequestCount();
        record1.incrementRequestCount();
        assertEquals(8, record1.getRequestCount());
    }
    
    @Test
    public void testComparableOrderByIpAlphabetical() {
        // 10.0.0.1 < 192.168.1.10 alfabéticamente
        assertTrue(record2.compareTo(record1) < 0);
        assertTrue(record1.compareTo(record2) > 0);
    }
    
    @Test
    public void testComparableEqualIps() {
        // Misma IP, diferente contador
        assertEquals(0, record1.compareTo(record3));
        assertEquals(0, record3.compareTo(record1));
    }
    
    @Test
    public void testToString() {
        String expected = "192.168.1.10 - Peticiones: 5";
        assertEquals(expected, record1.toString());
    }
    
    @Test
    public void testToStringAfterIncrement() {
        record1.incrementRequestCount();
        String expected = "192.168.1.10 - Peticiones: 6";
        assertEquals(expected, record1.toString());
    }
}
