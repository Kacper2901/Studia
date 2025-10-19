/*
  NAME: Kacper Gęśla
  ID: 290168
*/

import java.util.Scanner;

public class JAVA_01_02 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a first number a: ");
        int a = scanner.nextInt();
        System.out.println("Enter a second number b: ");
        int b = scanner.nextInt();

        System.out.println("Sum: " + (a + b));
        System.out.println("Difference: " + (a - b));
        System.out.println("Product:  " + (a * b));
        System.out.println("Quotient: " + (a / b));

    }
}