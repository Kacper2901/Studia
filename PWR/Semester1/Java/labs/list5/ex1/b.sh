#!/bin/bash
javac -cp src/term/jline.jar src/term/term.java
java --enable-native-access=ALL-UNNAMED -cp src/term/jline.jar src/Main.java
