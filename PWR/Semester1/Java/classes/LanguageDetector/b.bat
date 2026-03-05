@echo off
cd src || exit /b
javac -d ..\out Main.java
java -cp ..\out Main
