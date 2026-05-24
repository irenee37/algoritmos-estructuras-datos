# Instrucciones de Ejecución

## Con Maven (Recomendado)

### Compilación

```bash
mvn clean compile
```

### Ejecución

```bash
mvn exec:java -Dexec.mainClass="LogAnalyzer" -Dexec.args="data/sample_logs.txt"
```

### Ejecutar Tests

```bash
mvn test
```

### Generar JAR ejecutable

```bash
mvn clean package
java -jar target/algoritmos-estructuras-datos-1.0-SNAPSHOT.jar data/sample_logs.txt
```

---

## Sin Maven (Compilación manual)

### Compilación

Compila los archivos Java:

```bash
javac -d target/classes src/main/java/*.java
```

### Ejecución

Ejecuta el programa pasando el archivo de logs como argumento:

```bash
java -cp target/classes LogAnalyzer data/sample_logs.txt
```

### Compilación y Ejecución de Tests (requiere JUnit en el CLASSPATH)

```bash
# Compilar tests
javac -cp "target/classes:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" \
       -d target/test-classes src/test/java/*.java

# Ejecutar tests
java -cp "target/classes:target/test-classes:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" \
     org.junit.runner.JUnitCore IpRecordTest LogAnalyzerTest
```

---

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

---

## Estructura del Proyecto

```
.
├── pom.xml                           # Configuración de Maven
├── README.md                          # Este archivo principal
├── INSTRUCCIONES.md                   # Guía de compilación y ejecución
├── .gitignore                         # Configuración de Git
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── IpRecord.java          # ⭐ TO DO: Implementar
│   │       └── LogAnalyzer.java       # ⭐ TO DO: Implementar
│   └── test/
│       └── java/
│           ├── IpRecordTest.java     # Tests para IpRecord
│           └── LogAnalyzerTest.java  # Tests para LogAnalyzer
├── data/
│   └── sample_logs.txt               # Archivo de logs para pruebas
├── lib/                              # Librerías (si no usas Maven)
├── target/                           # Directorio de compilación (generado)
└── docs/
    └── ANALISIS.md                   # Apartado 3: Tus conclusiones
```
