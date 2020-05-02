@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  onuw-server startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and ONUW_SERVER_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\onuw-server.jar;%APP_HOME%\lib\javax.websocket-api-1.1.jar;%APP_HOME%\lib\conjure-java-jersey-server-5.7.1.jar;%APP_HOME%\lib\conjure-java-jackson-serialization-5.7.1.jar;%APP_HOME%\lib\jersey-container-servlet-2.26.jar;%APP_HOME%\lib\jersey-media-json-jackson-2.26.jar;%APP_HOME%\lib\onuw-server-api-objects.jar;%APP_HOME%\lib\conjure-lib-5.14.1.jar;%APP_HOME%\lib\tracing-jersey-4.4.0.jar;%APP_HOME%\lib\tracing-4.4.0.jar;%APP_HOME%\lib\jackson-datatype-guava-2.10.3.jar;%APP_HOME%\lib\jackson-module-afterburner-2.10.3.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.10.3.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.10.3.jar;%APP_HOME%\lib\jackson-jaxrs-cbor-provider-2.10.3.jar;%APP_HOME%\lib\jackson-module-jaxb-annotations-2.10.3.jar;%APP_HOME%\lib\jackson-jaxrs-base-2.10.3.jar;%APP_HOME%\lib\auth-tokens-3.6.1.jar;%APP_HOME%\lib\errors-2.12.0.jar;%APP_HOME%\lib\jackson-databind-2.10.3.jar;%APP_HOME%\lib\javax.servlet-api-3.1.0.jar;%APP_HOME%\lib\jackson-dataformat-cbor-2.10.3.jar;%APP_HOME%\lib\tritium-registry-0.16.8.jar;%APP_HOME%\lib\preconditions-1.13.0.jar;%APP_HOME%\lib\jackson-dataformat-smile-2.10.3.jar;%APP_HOME%\lib\jersey-bean-validation-2.29.1.jar;%APP_HOME%\lib\jersey-container-servlet-core-2.26.jar;%APP_HOME%\lib\jersey-server-2.29.1.jar;%APP_HOME%\lib\metrics-core-3.2.6.jar;%APP_HOME%\lib\jersey-hk2-2.29.1.jar;%APP_HOME%\lib\jzlib-1.1.3.jar;%APP_HOME%\lib\feign-core-8.18.0.jar;%APP_HOME%\lib\safe-logging-1.13.0.jar;%APP_HOME%\lib\jersey-client-2.29.1.jar;%APP_HOME%\lib\jersey-media-jaxb-2.29.1.jar;%APP_HOME%\lib\jersey-common-2.29.1.jar;%APP_HOME%\lib\jersey-entity-filtering-2.26.jar;%APP_HOME%\lib\javax.ws.rs-api-2.1.1.jar;%APP_HOME%\lib\resource-identifier-1.1.0.jar;%APP_HOME%\lib\jackson-annotations-2.10.3.jar;%APP_HOME%\lib\javax.annotation-api-1.3.1.jar;%APP_HOME%\lib\jackson-core-2.10.3.jar;%APP_HOME%\lib\guava-28.0-jre.jar;%APP_HOME%\lib\error_prone_annotations-2.3.4.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\slf4j-api-1.7.30.jar;%APP_HOME%\lib\jakarta.ws.rs-api-2.1.6.jar;%APP_HOME%\lib\hk2-locator-2.6.1.jar;%APP_HOME%\lib\hk2-api-2.6.1.jar;%APP_HOME%\lib\hk2-utils-2.6.1.jar;%APP_HOME%\lib\jakarta.annotation-api-1.3.5.jar;%APP_HOME%\lib\jakarta.inject-2.6.1.jar;%APP_HOME%\lib\jakarta.validation-api-2.0.2.jar;%APP_HOME%\lib\animal-sniffer-annotation-1.0.jar;%APP_HOME%\lib\tracing-api-4.4.0.jar;%APP_HOME%\lib\hibernate-validator-6.0.17.Final.jar;%APP_HOME%\lib\jakarta.el-api-3.0.3.jar;%APP_HOME%\lib\jakarta.el-3.0.2.jar;%APP_HOME%\lib\javax.inject-2.5.0-b42.jar;%APP_HOME%\lib\failureaccess-1.0.1.jar;%APP_HOME%\lib\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;%APP_HOME%\lib\checker-qual-2.8.1.jar;%APP_HOME%\lib\j2objc-annotations-1.3.jar;%APP_HOME%\lib\animal-sniffer-annotations-1.17.jar;%APP_HOME%\lib\osgi-resource-locator-1.0.3.jar;%APP_HOME%\lib\aopalliance-repackaged-2.6.1.jar;%APP_HOME%\lib\javassist-3.22.0-CR2.jar;%APP_HOME%\lib\jakarta.xml.bind-api-2.3.2.jar;%APP_HOME%\lib\jakarta.activation-api-1.2.1.jar;%APP_HOME%\lib\validation-api-2.0.1.Final.jar;%APP_HOME%\lib\jboss-logging-3.3.2.Final.jar;%APP_HOME%\lib\classmate-1.3.4.jar

@rem Execute onuw-server
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %ONUW_SERVER_OPTS%  -classpath "%CLASSPATH%" OnuwServer %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable ONUW_SERVER_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%ONUW_SERVER_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
