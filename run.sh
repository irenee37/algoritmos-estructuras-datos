#!/bin/bash
# Script para compilar y ejecutar los tests

echo "=== Compilando proyecto ==="
mvn clean compile

echo ""
echo "=== Ejecutando tests ==="
mvn test

echo ""
echo "=== Ejecución completa ==="
echo ""
mvn exec:java -Dexec.mainClass="LogAnalyzer" -Dexec.args="data/sample_logs.txt"
