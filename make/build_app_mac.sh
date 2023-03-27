#!/bin/bash
# Script for packaging a Java application as a Mac installer.
# Designed to be used with Maven (see example pom.xml at
# https://github.com/torsteins/JavaInstallerForSwing)
#
# Inspired by a template made by dlemmermann at
# https://github.com/dlemmermann/JPackageScriptFX
#
# Simplified by Torstein Strømme to fit INF101 at the University of Bergen.


# ------ ENVIRONMENT ----------------------------------------------
# The script depends on environment variables to exist in order to
# run properly (think of them as input parameters to the script).
# We print them all here:

echo "required input environment variables..."
echo "  ABOUT_NAME: $ABOUT_NAME"           # name of colorgrid in menu
echo "  APP_PACKAGE: $APP_PACKAGE"         # e.g. "no.uib.inf101.colorgrid"
echo "  APP_VENDOR: $APP_VENDOR"           # info shown in ~about~ dialog
echo "  APP_VERSION: $APP_VERSION"         # version shown in ~about~ dialog
echo "  ICON_PATH: $ICON_PATH"             # path to .icns -file
echo "  INSTALLER_TYPE: $INSTALLER_TYPE"   # e.g. "dmg" or "pkg"
echo "  JAVA_HOME: $JAVA_HOME"             # path to java installation
echo "  JAVA_VERSION: $JAVA_VERSION"       # e.g. "17"
echo "  MAIN_CLASS: $MAIN_CLASS"           # e.g. "no.uib.inf101.gridview.Main"
echo "  MAIN_JAR: $MAIN_JAR"               # filename produced in package phase
echo "  PROJECT_NAME: $PROJECT_NAME"       # human-friendly name of application
echo "  PROJECT_VERSION: $PROJECT_VERSION" # version in pom, e.g. "1.0-SNAPSHOT"
echo "computed variables..."
CURRENT_YEAR=$(date +'%Y')
echo "  CURRENT_YEAR: $CURRENT_YEAR"
#PATH_TO_MAIN_CLASS="target/classes/${MAIN_CLASS//'.'//}.class"
PATH_TO_MAIN_CLASS="target/classes/${APP_PACKAGE//'.'//}/"
echo "  PATH_TO_MAIN_CLASS: $PATH_TO_MAIN_CLASS"
echo "  pwd: "
pwd

# ------ SETUP DIRECTORIES AND FILES ----------------------------------------
# Remove previously generated java runtime and installers. Copy all required
# jar files into the input/libs folder.

echo "setting up directories and files..."
rm -rfd ./target/java-runtime/
rm -rfd target/installer/

mkdir -p target/installer/input/libs/

if [[ -d target/libs ]]; then
  cp target/libs/* target/installer/input/libs
fi
cp "target/${MAIN_JAR}" target/installer/input/libs/

## ------ REQUIRED MODULES ---------------------------------------------------
## Use jlink to detect all modules that are required to run the application.
## Starting point for the jdep analysis is the set of jars being used by the
## application.

echo "detecting required modules.."
echo "$JAVA_HOME/bin/jdeps" \
       -q \
       --multi-release "${JAVA_VERSION}" \
       --ignore-missing-deps \
       --print-module-deps \
       --class-path "target/installer/input/libs/*" \
         "$PATH_TO_MAIN_CLASS"

detected_modules=$("$JAVA_HOME/bin/jdeps" \
  -q \
  --multi-release "${JAVA_VERSION}" \
  --ignore-missing-deps \
  --print-module-deps \
  --class-path "target/installer/input/libs/*" \
    "$PATH_TO_MAIN_CLASS")
echo "  detected modules: ${detected_modules}"

# Note: in the original version of this script by dlemmermann (reference
# above), there is a separate section on manual required modules. Please
# adapt from that script if you find that this is required. Only applies
# to certain modules, such as jdk.crypto.ec or jdk.localedata.

# ------ RUNTIME IMAGE ------------------------------------------------------
# Use the jlink tool to create a runtime image for our application. We are
# doing this in a separate step instead of letting jlink do the work as part
# of the jpackage tool. This approach allows for finer configuration and also
# works with dependencies that are not fully modularized, yet.

echo "creating java runtime image..."
echo "$JAVA_HOME/bin/jlink" \
       --strip-native-commands \
       --no-header-files \
       --no-man-pages  \
       --compress=2  \
       --strip-debug \
       --add-modules "${detected_modules}" \
       --output target/java-runtime
"$JAVA_HOME/bin/jlink" \
  --strip-native-commands \
  --no-header-files \
  --no-man-pages  \
  --compress=2  \
  --strip-debug \
  --add-modules "${detected_modules}" \
  --output target/java-runtime

# ------ PACKAGING ----------------------------------------------------------
# In the end we will find the package inside the target/installer directory.

echo "creating installer of type $INSTALLER_TYPE..."
echo "$JAVA_HOME/bin/jpackage" \
       --type "$INSTALLER_TYPE" \
       --dest target/installer \
       --input target/installer/input/libs \
       --name "${PROJECT_NAME}" \
       --main-class "${MAIN_CLASS}" \
       --main-jar "${MAIN_JAR}" \
       --runtime-image target/java-runtime \
       --icon "${ICON_PATH}" \
       --app-version "${APP_VERSION}" \
       --vendor "${APP_VENDOR}" \
       --copyright "Copyright © ${CURRENT_YEAR} ${APP_VENDOR}." \
       --mac-package-identifier "${APP_PACKAGE}"
     # --java-options -Xmx2048m \

"$JAVA_HOME/bin/jpackage" \
  --type "$INSTALLER_TYPE" \
  --dest target/installer \
  --input target/installer/input/libs \
  --name "${PROJECT_NAME}" \
  --main-class "${MAIN_CLASS}" \
  --main-jar "${MAIN_JAR}" \
  --runtime-image target/java-runtime \
  --icon "${ICON_PATH}" \
  --app-version "${APP_VERSION}" \
  --vendor "${APP_VENDOR}" \
  --copyright "Copyright © ${CURRENT_YEAR} ${APP_VENDOR}." \
  --mac-package-identifier "${APP_PACKAGE}"
# --java-options -Xmx2048m \
