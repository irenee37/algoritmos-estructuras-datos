# Instrucciones de Ejecución

## Compilación

Compila los archivos Java:

```bash
javac src/main/java/*.java
```

## Ejecución

Ejecuta el programa pasando el archivo de logs como argumento:

```bash
java -cp src/main/java LogAnalyzer data/sample_logs.txt
```

## Compilación y Ejecución de Tests

Si deseas compilar y ejecutar los tests (requiere JUnit 4):

```bash
# Compilar tests
javac -cp ".:test/lib/*" test/java/*.java src/main/java/*.java

# Ejecutar tests
java -cp ".:test/lib/*" org.junit.runner.JUnitCore IpRecordTest LogAnalyzerTest
```

## Descripción del archivo de logs

El archivo `data/sample_logs.txt` contiene registros de acceso con el siguiente formato:

```
IP;FECHA-HORA;ENDPOINT;CODIGO_HTTP
```

Ejemplo:
```
192.168.1.10;01/15/2026-08:30:45;/api/users;200
10.0.0.1;01/15/2026-09:00:00;/home;200
```

### Validación

El programa valida que:
- Cada línea tenga exactamente 4 campos separados por `;`
- El código HTTP sea un número entero válido
- Las líneas inválidas se ignoran y se imprime `Data source error` en stderr
