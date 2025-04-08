@echo off
setlocal enabledelayedexpansion

set "BUILD_DIR=Build"
set applicationName=.
set "APP_NAME=ETU003085"
set "TOMCAT_WEBAPPS=C:\Program Files\apache-tomcat-10.1.28\webapps"
set "LIB_DIR=%applicationName%\lib\"
set "SRC_DIR=src\main\java"
set "WEB_DIR=src\main\webapp"

REM Supprimer le répertoire de build
if exist "%BUILD_DIR%" (
    rmdir /s /q "%BUILD_DIR%"
)
mkdir "%BUILD_DIR%\WEB-INF\classes"
mkdir "%BUILD_DIR%\WEB-INF\lib"  REM Créer le répertoire WEB-INF\lib

REM Construire le classpath avec tous les fichiers JAR dans lib
set "SERVLET_API_JAR="
for %%f in ("%LIB_DIR%\*.jar") do (
    set "SERVLET_API_JAR=!SERVLET_API_JAR!;%%f"
)
set "SERVLET_API_JAR=%SERVLET_API_JAR:~1%"

REM Trouver tous les fichiers .java et écrire leur chemin dans sources.txt
dir /b /s "%SRC_DIR%\*.java" > sources.txt

REM Compiler les fichiers .java
javac -cp "%SERVLET_API_JAR%" -d "%BUILD_DIR%\WEB-INF\classes" @"sources.txt"
if errorlevel 1 (
    echo Erreur lors de la compilation.
    del sources.txt
    pause
    exit /b 1
)
del sources.txt

REM Copier les fichiers JAR dans WEB-INF\lib
xcopy /y "%LIB_DIR%\*.jar" "%BUILD_DIR%\WEB-INF\lib\"

REM Copier les fichiers du répertoire web
xcopy /e /i "%WEB_DIR%\" "%BUILD_DIR%\"

REM Créer le fichier .war
cd "%BUILD_DIR%"
jar -cvf "%APP_NAME%.war" *
cd ..

REM Copier le fichier .war vers le dossier Tomcat
copy /y "%BUILD_DIR%\%APP_NAME%.war" "%TOMCAT_WEBAPPS%\"

echo.
echo Déploiement réussi
pause