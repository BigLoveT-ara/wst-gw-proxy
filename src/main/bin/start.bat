@echo off
java -XX:+UseG1GC -jar -Dlogging.config=%~dp0/config/logback-spring.xml %~dp0/${artifactId}.jar
pause
@echo on