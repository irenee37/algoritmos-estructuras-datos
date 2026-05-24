# Algoritmos y Estructuras de Datos

## Práctica 10: Análisis de Tráfico de Red y Detección de Anomalías

### CONTEXTO

El objetivo de esta práctica es integrar los conceptos de **Tablas de Símbolos (Hash o BST)**, **Algoritmos de Ordenación Eficientes** y **Análisis de Complejidad**.

Imagina que trabajas como ingeniero de software para un servidor web. Tu tarea es procesar un archivo de *logs* (registros de actividad) del servidor para contabilizar cuántas peticiones realiza cada dirección IP y, posteriormente, ordenar esta información para identificar posibles ataques de denegación de servicio (DDoS) o usuarios muy activos.

Para ello, implementarás un programa que leerá los datos, los almacenará en una estructura de datos eficiente para búsquedas e inserciones rápidas, y finalmente los ordenará según distintos criterios.

---

## APARTADO 1: Procesamiento y Almacenamiento

**Archivo:** `src/main/java/LogAnalyzer.java`
**Archivo:** `src/main/java/IpRecord.java`

Escribe el código del programa `LogAnalyzer`. Este programa recibe un argumento en la línea de comandos que especifica el nombre de un fichero de *logs*.

El fichero contiene cuatro elementos separados por punto y coma (`;`) en cada línea:

1. Dirección IP (ej. `192.168.0.1`).
2. Fecha y hora (formato `MM/DD/YYYY-HH:MM:SS`).
3. Endpoint solicitado (ej. `/api/users`).
4. Código de estado HTTP (ej. `200` o `404`).

**Especificaciones:**

1. **Clase `IpRecord`:**
   * Crea esta clase para almacenar la información agregada de una IP. Debe contener la dirección IP (como `String`) y el contador total de peticiones (como `int`).
   * Esta clase debe implementar la interfaz `Comparable` definiendo su orden natural alfabéticamente por la dirección IP.

2. **Almacenamiento (Tabla de Símbolos):**
   * El programa debe leer el fichero línea por línea.
   * Utiliza una **Tabla Hash** (puedes basarte en *Separate Chaining* o *Linear Probing*) o un **Árbol de Búsqueda Binaria (BST)** para almacenar la información. La clave será la dirección IP y el valor será el objeto `IpRecord`.
   * Por cada línea válida leída, busca si la IP ya existe en la tabla. Si no existe, insértala con un contador de 1. Si ya existe, actualiza su valor incrementando el contador en 1.

3. **Validación:**
   * Una línea es válida si tiene exactamente los 4 campos separados por `;` y el código HTTP es un número entero.
   * Si una línea no cumple el formato, imprime el mensaje `Data source error` por la salida de error estándar y simplemente omite esa línea (no finalices el programa).

---

## APARTADO 2: Extracción y Ordenación

**Archivo:** `src/main/java/LogAnalyzer.java` (continuación)

Una vez procesado todo el fichero y poblada tu Tabla de Símbolos, debes extraer todos los valores (`IpRecord`) a un array clásico y proceder a ordenarlos.

**Especificaciones:**

1. **Ordenación por IP (Orden Natural):**
   * Ordena el array utilizando el método natural (alfabético por IP) apoyándote en la interfaz `Comparable`.
   * Imprime los 5 primeros resultados.

2. **Ordenación por Tráfico (Uso de Comparadores):**
   * Crea un `Comparator` que ordene los registros de **mayor a menor** según su contador de peticiones. En caso de empate en el número de peticiones, prevalece el orden alfabético de la IP.
   * Para esta ordenación, debes implementar un algoritmo **Quicksort** o **Mergesort**. Recuerda que Quicksort tiene un tiempo promedio de $O(N \log N)$ y es *in-place*, mientras que Mergesort garantiza $O(N \log N)$ pero requiere espacio extra.
   * Imprime el "Top 5" de IPs con más tráfico.

**Ejemplo de salida esperada:**

```txt
--- ORDEN ALFABÉTICO ---
1. 10.0.0.1 - Peticiones: 45
2. 10.0.0.2 - Peticiones: 12
3. 192.168.1.10 - Peticiones: 1500
4. 192.168.1.11 - Peticiones: 3
5. 8.8.8.8 - Peticiones: 330

--- TOP 5 TRÁFICO (DDoS Warning) ---
1. 192.168.1.10 - Peticiones: 1500
2. 8.8.8.8 - Peticiones: 330
3. 10.0.0.1 - Peticiones: 45
4. 10.0.0.2 - Peticiones: 12
5. 192.168.1.11 - Peticiones: 3
```

---

## APARTADO 3: Análisis y Conclusiones (README)

Añade a tu archivo `README.md` una breve sección de conclusiones donde justifiques teóricamente tus decisiones de diseño basándote en el análisis de algoritmos:

1. **Elección de la Tabla de Símbolos:**
   * Indica si elegiste Hash o BST.
   * Justifica tu elección en términos del coste de tiempo para las operaciones `put()` y `get()`. Por ejemplo, si usaste Hash, menciona cómo logra tiempo constante $O(1)$ en promedio. Si usaste BST, menciona su comportamiento logarítmico $O(\log N)$ y el riesgo del peor caso $O(N)$.

2. **Elección del Algoritmo de Ordenación:**
   * Indica qué algoritmo implementaste en el Apartado 2 (Quick o Merge).
   * Justifica por qué un algoritmo elemental como *Insertion Sort* (de coste cuadrático $O(N^2)$) no sería viable si el servidor registra millones de IPs distintas.

---

## ¿Por qué esta práctica es un buen ejemplo?

* **Integra varias prácticas:** Al igual que en la *Práctica 7* y *Práctica 9*, requiere leer ficheros, validar datos e instanciar objetos.
* **Usa la teoría de los PDFs:** Te obliga a programar una Tabla de Símbolos (PDF 3) para contabilizar frecuencias y a implementar algoritmos avanzados de ordenación (PDF 2) evaluando su eficiencia (PDF 1).
* **Usa Comparadores:** Requiere tanto `Comparable` (orden natural) como `Comparator` (orden por métricas secundarias), conceptos recurrentes en tus apuntes.

---

## Estructura del Proyecto

```
.
├── README.md                          # Este archivo
├── INSTRUCCIONES.md                   # Guía de compilación y ejecución
├── .gitignore                         # Configuración de Git
├── src/
│   └── main/
│       └── java/
│           ├── IpRecord.java          # ⭐ TO DO: Implementar
│           └── LogAnalyzer.java       # ⭐ TO DO: Implementar
├── data/
│   └── sample_logs.txt               # Archivo de logs para pruebas
└── test/
    └── java/
        ├── IpRecordTest.java         # Tests para IpRecord
        └── LogAnalyzerTest.java      # Tests para LogAnalyzer
```

---

## Cómo Empezar

1. **Lee el enunciado completo** (arriba) para entender qué debes implementar.
2. **Estudia los tests** en `test/` para ver qué comportamiento se espera.
3. **Implementa `IpRecord.java`** con la clase básica.
4. **Implementa `LogAnalyzer.java`** con toda la lógica de lectura, procesamiento y ordenación.
5. **Ejecuta los tests** para verificar tu solución.
6. **Escribe tus conclusiones** en el README.

---

## Entregas

- ✅ Código funcional en `src/main/java/`
- ✅ Tests pasando en `test/java/`
- ✅ Sección de conclusiones al final del README
