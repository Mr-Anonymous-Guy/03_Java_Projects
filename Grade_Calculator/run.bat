@echo off
echo Building Grade Calculator...
if not exist bin mkdir bin

javac -d bin -sourcepath src src\Main.java src\model\*.java src\service\*.java src\utils\*.java src\report\*.java

if %ERRORLEVEL% EQU 0 (
    echo Build successful! Running application...
    echo ----------------------------------------
    java -cp bin Main
) else (
    echo Build failed. Please check for errors.
)

pause
