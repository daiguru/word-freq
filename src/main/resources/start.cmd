echo Starting server for word frequency search...

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

set DEFAULT_JVM_OPTS="-ms256m" "-mx512m" "-Dfile.encoding=UTF-8" "-Djava.net.preferIPv4Stack=true"
set CLASSPATH=%APP_HOME%/lib/*;%APP_HOME%/config

echo %CLASSPATH%

java %DEFAULT_JVM_OPTS% %JAVA_OPTS% -classpath "%CLASSPATH%" "-Dspring.config.location=%APP_HOME%/config/application.properties" -jar "%APP_HOME%/lib/word-freq.jar"
