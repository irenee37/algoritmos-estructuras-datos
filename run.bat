@echo off
REM Script para compilar y ejecutar los tests en Windows

echo === Compilando proyecto ===
call mvn clean compile

echo.
echo === Ejecutando tests ===
call mvn test

echo.
echo === Ejecucion completa ===
echo.
call mvn exec:java -Dexec.mainClass="LogAnalyzer" -Dexec.args="data\sample_logs.txt"
