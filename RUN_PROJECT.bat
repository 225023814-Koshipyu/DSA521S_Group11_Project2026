@echo off
setlocal EnableExtensions
cd /d "%~dp0"

set "JAVAC_EXE="
set "JAVA_EXE="

for %%I in (javac.exe) do set "JAVAC_EXE=%%~$PATH:I"
if defined JAVAC_EXE (
    for %%I in ("%JAVAC_EXE%") do set "JAVA_EXE=%%~dpIjava.exe"
)

if not defined JAVAC_EXE if exist "C:\Program Files\JetBrains\PyCharm 2026.1.1\jbr\bin\javac.exe" (
    set "JAVAC_EXE=C:\Program Files\JetBrains\PyCharm 2026.1.1\jbr\bin\javac.exe"
    set "JAVA_EXE=C:\Program Files\JetBrains\PyCharm 2026.1.1\jbr\bin\java.exe"
)

if not defined JAVAC_EXE (
    echo ERROR: A Java Development Kit was not found.
    echo Install JDK 8 or newer, then run this file again.
    pause
    exit /b 1
)

if not exist bin mkdir bin

echo Compiling the project...
"%JAVAC_EXE%" -encoding UTF-8 -d bin src\*.java
if errorlevel 1 (
    echo Compilation failed.
    pause
    exit /b 1
)

echo Running automated checks...
"%JAVA_EXE%" -cp bin ProjectTests
if errorlevel 1 (
    echo A test failed.
    pause
    exit /b 1
)

if /I "%~1"=="test" exit /b 0

echo.
echo Starting the Campus Service Centre...
"%JAVA_EXE%" -cp bin ServiceCentreApp
echo.
pause

