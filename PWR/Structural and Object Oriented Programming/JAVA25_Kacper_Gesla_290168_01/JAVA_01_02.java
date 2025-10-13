/*
  NAME: Kacper Gęśla
  ID: 290168
*/

import java.util.Scanner;

public class JAVA_01_02 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Wprowadz liczbe a: ");
        float a = scanner.nextFloat();
        System.out.println("Wprowadz liczbe b: ");
        float b = scanner.nextFloat();

        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a / b = " + (a / b));
        System.out.println("a * b = " + (a * b));

    }
}