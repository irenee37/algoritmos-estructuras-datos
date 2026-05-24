import java.io.*;
import java.util.*;

/**
 * Programa para analizar logs de tráfico de red y detectar anomalías (DDoS).
 * 
 * ⭐ TODO: Implementar esta clase con los siguientes requisitos:
 * 
 * APARTADO 1: Procesamiento y Almacenamiento
 * ============================================
 * 
 * 1. Método readLogFile(String filename):
 *    - Lee el archivo línea por línea
 *    - Valida que cada línea tenga exactamente 4 campos separados por ';'
 *    - Valida que el 4º campo (código HTTP) sea un número entero
 *    - Si una línea es inválida: imprime "Data source error" en stderr y continúa
 *    - Almacena los datos en una Tabla de Símbolos (HashMap o TreeMap o implementación propia)
 *    - La clave es la IP y el valor es un IpRecord
 *    - Si la IP ya existe, incrementa su contador; si no, la añade con contador = 1
 *    - Retorna la tabla de símbolos poblada
 * 
 * APARTADO 2: Extracción y Ordenación
 * ====================================
 * 
 * 2. En el método main(String[] args):
 *    - Lee el archivo de logs usando readLogFile()
 *    - Convierte los valores de la tabla a un array de IpRecord
 * 
 * 3. Ordenación 1: Por IP (Orden Natural)
 *    - Ordena el array alfabéticamente usando Comparable
 *    - Imprime un título: "--- ORDEN ALFABÉTICO ---"
 *    - Imprime los 5 primeros registros (o menos si hay menos de 5)
 *    - Formato: "1. 10.0.0.1 - Peticiones: 45" (incluye número de línea)
 * 
 * 4. Ordenación 2: Por Tráfico (Usando Comparator)
 *    - Crea un Comparator que ordene de MAYOR a MENOR por peticiones
 *    - En caso de empate en peticiones, usa orden alfabético de IP
 *    - Implementa Quicksort o Mergesort (NO usar Arrays.sort directamente)
 *    - Imprime un título: "--- TOP 5 TRÁFICO (DDoS Warning) ---"
 *    - Imprime los 5 primeros registros (o menos si hay menos de 5)
 *    - Formato igual al anterior
 * 
 * NOTAS IMPORTANTES:
 * - Maneja excepciones de lectura de ficheros correctamente
 * - Valida que se proporcione exactamente 1 argumento en línea de comandos
 * - Usa StringBuilder o PrintWriter para optimizar la salida si es necesario
 */
public class LogAnalyzer {
    
    // TODO: Implementar
    
    public static void main(String[] args) {
        // TODO: Implementar
    }
}
