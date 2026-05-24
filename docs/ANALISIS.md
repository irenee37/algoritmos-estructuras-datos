# Análisis y Conclusiones - Práctica 10

## APARTADO 3: Análisis y Justificación de Decisiones de Diseño

### 1. Elección de la Tabla de Símbolos

**Elegiste: [Hash/BST]** 

*Reemplaza con tu elección y justificación*

#### Justificación Teórica

- **Operaciones `put()` y `get()`**: Especifica la complejidad temporal de cada operación
- **Caso promedio vs. Peor caso**: 
- **Por qué elegiste esta sobre la alternativa**: 

#### Ventajas y Desventajas

| Aspecto | Hash | BST |
|--------|------|-----|
| get() promedio | O(1) | O(log N) |
| get() peor caso | O(N) | O(N) |
| put() promedio | O(1) | O(log N) |
| put() peor caso | O(N) | O(N) |
| Orden | No mantiene | Sí (in-order) |
| Espacio | Predecible | Variable |

---

### 2. Elección del Algoritmo de Ordenación

**Elegiste: [Quicksort/Mergesort]**

*Reemplaza con tu elección y justificación*

#### Comparación de Complejidades

| Algoritmo | Caso Promedio | Peor Caso | Espacio | In-place |
|-----------|--------------|-----------|---------|----------|
| **Quicksort** | O(N log N) | O(N²) | O(log N) | Sí |
| **Mergesort** | O(N log N) | O(N log N) | O(N) | No |
| Insertion Sort | O(N²) | O(N²) | O(1) | Sí |
| Bubble Sort | O(N²) | O(N²) | O(1) | Sí |

#### Por qué NO usar Insertion Sort

Si el servidor registra **millones de IPs distintas** (N >> 10^6):

- **Insertion Sort**: O(N²) = O(10^12) operaciones ≈ **Horas/Días de ejecución**
- **Quicksort/Mergesort**: O(N log N) ≈ O(2 × 10^7) operaciones ≈ **Milisegundos**

Esta es una **diferencia de órden de magnitud** inaceptable en producción.

#### Justificación de tu Elección

*Reemplaza con tu justificación. Considera:*

- Si elegiste **Quicksort**: ¿Por qué su eficiencia promedio y naturalidad in-place compensan el riesgo del peor caso?
- Si elegiste **Mergesort**: ¿Por qué prefieres garantía O(N log N) sobre eficiencia de espacio?

---

### 3. Complejidad Total del Programa

#### Fase 1: Lectura y Poblado de la Tabla

```
Leer N líneas → O(N) lécturas
Insertarlas en tabla → O(N) × O(operación tabla)

Complejidad total: O(N × O(operación tabla))
  - Con Hash: O(N) promedio
  - Con BST: O(N log N) promedio
```

#### Fase 2: Ordenación

```
Extraer M registros únicos → O(M)
Ordenar → O(M log M)

Complejidad total: O(M log M) donde M ≤ N
```

#### Complejidad Total

**Tu análisis:**

- Considera M (número de IPs únicas) vs. N (número total de registros)
- En casos realistas, M << N (muchos registros por IP)
- Por tanto, la fase de lectura domina si M ~ N, pero la ordenación es igual de importante

---

### 4. Reflexión Final

*Añade aquí:*

- **¿Qué aprendiste** de esta práctica sobre la importancia del análisis de complejidad?
- **¿Cuáles serían optimizaciones** adicionales si el servidor generara 1000 millones de registros diarios?
- **¿Qué estructura de datos** podrías usar en producción (p. ej., bases de datos especializadas)?
