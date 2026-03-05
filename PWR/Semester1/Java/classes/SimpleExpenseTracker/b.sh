#!/bin/bash
cd src || exit
javac -d ../out Main.java
java -cp ../out Main
