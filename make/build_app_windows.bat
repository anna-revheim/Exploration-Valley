@ECHO OFF
REM Script for packaging a Java application as a Windows installer.
REM Designed to be used with Maven (see example pom.xml at
REM https://github.com/torsteins/JavaInstallerForSwing)
REM
REM Inspired by a template made by dlemmermann at
REM https://github.com/dlemmermann/JPackageScriptFX
REM
REM Simplified by Torstein Strømme to fit INF101 at the University of Bergen.

REM ------ ENVIRONMENT --------------------------------------------------------
REM The script depends on various environment variables to exist in order to
REM run properly. The location of the java binaries (java home), and the
REM project version as defined inside the pom.xml file, e.g. 1.0-SNAPSHOT.

ECHO "required input environment variables..."
ECHO "  APP_PACKAGE: %APP_PACKAGE%"
ECHO "  APP_VENDOR: %APP_VENDOR%"
REM  APP_VERSION: the application version, e.g. 1.0.0, shown in "about" dialog
ECHO "  APP_VERSION: %APP_VERSION%"
ECHO "  ICON_PATH: %ICON_PATH%"
REM  Set desired installer type: "msi" "exe" (or "app-image").
ECHO "  INSTALLER_TYPE: %INSTALLER_TYPE%"
ECHO "  JAVA_HOME: %JAVA_HOME%"
ECHO "  JAVA_VERSION: %JAVA_VERSION%"
ECHO "  MAIN_JAR: %MAIN_JAR%"
ECHO "  MAIN_CLASS: %MAIN_CLASS%"
ECHO "  PROJECT_NAME: %PROJECT_NAME%"
ECHO "  PROJECT_VERSION: %PROJECT_VERSION%"

ECHO "computed variables..."
@REM SET PATH_TO_MAIN="target\classes\%MAIN_CLASS:.=\%.class"
SET PATH_TO_MAIN="target\classes\%APP_PACKAGE:.=\%"
ECHO "  PATH_TO_MAIN: %PATH_TO_MAIN%"
SET YEAR=%DATE:~6,4%
ECHO "  YEAR: %YEAR%"

REM ------ SETUP DIRECTORIES AND FILES ----------------------------------------
REM Remove previously generated java runtime and installers. Copy all required
REM jar files into the input/libs folder.

ECHO "setting up directories and files..."
IF EXIST target\java-runtime RMDIR /S /Q  .\target\java-runtime
IF EXIST target\installer RMDIR /S /Q target\installer
MKDIR target\installer\input\libs

XCOPY /S /Q target\libs\* target\installer\input\libs\
COPY target\%MAIN_JAR% target\installer\input\libs\

REM ------ REQUIRED MODULES ---------------------------------------------------
REM Use jlink to detect all modules that are required to run the application.
REM Starting point for the jdep analysis is the set of jars being used by the
REM application.

ECHO "detecting required modules with jdeps..."

"%JAVA_HOME%\bin\jdeps" ^
  -q ^
  --multi-release %JAVA_VERSION% ^
  --ignore-missing-deps ^
  --class-path "target\installer\input\libs\*" ^
  --print-module-deps "%PATH_TO_MAIN%" > temp.txt

SET /p detected_modules=<temp.txt
DEL temp.txt
ECHO "  detected modules: %detected_modules%"

REM ------ RUNTIME IMAGE ------------------------------------------------------
REM Use the jlink tool to create a runtime image for our application. We are
REM doing this in a separate step instead of letting jlink do the work as part
REM of the jpackage tool. This approach allows for finer configuration and also
REM works with dependencies that are not fully modularized, yet.

ECHO "creating java runtime image with jlink..."

CALL "%JAVA_HOME%\bin\jlink" ^
  --strip-native-commands ^
  --no-header-files ^
  --no-man-pages ^
  --compress=2 ^
  --strip-debug ^
  --add-modules "%detected_modules%" ^
  --output target/java-runtime

REM ------ PACKAGING ----------------------------------------------------------
REM In the end we will find the package inside the target/installer directory.
ECHO "creating install package with jpackage..."

CALL "%JAVA_HOME%\bin\jpackage" ^
  --type %INSTALLER_TYPE% ^
  --dest target/installer ^
  --input target/installer/input/libs ^
  --name "%PROJECT_NAME%"  ^
  --main-class "%MAIN_CLASS%" ^
  --main-jar "%MAIN_JAR%" ^
  --runtime-image target/java-runtime ^
  --icon "%ICON_PATH%" ^
  --app-version %APP_VERSION% ^
  --vendor "%APP_VENDOR%" ^
  --copyright "Copyright © %YEAR% %APP_VENDOR%" ^
  --win-dir-chooser ^
  --win-per-user-install ^
  --win-shortcut ^
  --win-menu
REM  --java-options -Xmx2048m
