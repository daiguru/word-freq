echo "Starting server for word frequency search..."

APP_BASE_NAME=`basename "$0"`
APP_HOME=`pwd`
APP_HOME_PARENT="$(dirname "$APP_HOME")"

DEFAULT_JVM_OPTS='"-ms256m" "-mx512m" "-Dfile.encoding=UTF-8" "-Djava.net.preferIPv4Stack=true"'
CLASSPATH=$APP_HOME_PARENT/lib/*:$APP_HOME_PARENT/config
JAVA_OPTS='"-classpath" "$CLASSPATH"'
JAR_OPTS='"-jar" "$APP_HOME_PARENT/lib/word-freq.jar"'

# Determine the Java command to use to start the JVM.
if [ -n "$JAVA_HOME" ] ; then
    if [ "$OS_NAME" == "OS400"  ]; then
        JAVACMD="$JAVA_HOME/bin/java"
        echo "iSeries OS, Setting Java Command to $JAVACMD"
    elif [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        # IBM's JDK on AIX uses strange locations for the executables
        JAVACMD="$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVACMD" ] ; then
        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
else
    JAVACMD="java"
    which java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
fi

echo "$CLASSPATH"

eval $JAVACMD $DEFAULT_JVM_OPTS $JAVA_OPTS "-Dspring.config.location=$APP_HOME_PARENT/config/application.properties" $JAR_OPTS "$@"

#Run the Command
eval "$0"