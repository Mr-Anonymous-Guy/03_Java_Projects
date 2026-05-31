@echo off
echo Building Library Management System...
call mvn clean compile
if %ERRORLEVEL% EQU 0 (
    echo Build successful. Starting application...
    call mvn exec:java
) else (
    echo Build failed!
)
pause
