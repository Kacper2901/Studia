#!/bin/bash
javac -cp term/jline.jar term/term.java
java --enable-native-access=ALL-UNNAMED -cp term/jline.jar Main.java
