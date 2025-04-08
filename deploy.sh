BUILD_DIR="Build"
APP_NAME="test_1"
TOMCAT_WEBAPPS="C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps"
LIB_DIR="lib"
SRC_DIR="src/java"
WEB_DIR="src/main/webapp"
SERVLET_API_JAR="$LIB_DIR/servlet-api.jar"

rm -rf $BUILD_DIR
mkdir -p $BUILD_DIR/WEB-INF/classes

find $SRC_DIR -name "*.java" > sources.txt
javac -cp $SERVLET_API_JAR -d $BUILD_DIR/WEB-INF/classes @sources.txt
rm sources.txt

cp -r $WEB_DIR/* $BUILD_DIR/

cd $BUILD_DIR || exit
jar -cvf $APP_NAME.war *

cd ..

cp -f $BUILD_DIR/$APP_NAME.war $TOMCAT_WEBAPPS/

echo ""
echo "Deploiment reussi"
