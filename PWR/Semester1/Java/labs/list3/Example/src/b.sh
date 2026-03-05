#!/bin/bash
javac -cp term/jline.jar Main.java term/term.java
java --enable-native-access=ALL-UNNAMED -cp .:term/jline.jar Main
