/*
  NAME: Kacper Gęśla
  ID: 290168
*/

import java.util.Scanner;

public class JAVA_01_05 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a temperature in Celsius: ");
        float celsius = scanner.nextFloat();
        float Fahrenheit = celsius * 9 / 5 + 32;

        System.out.println(celsius + "C = " + Fahrenheit + "F" );
    }
}