/*
  NAME: Kacper Gęśla
  ID: 290168
*/

import java.util.Scanner;

public class JAVA_01_04 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number a: ");
        float a = scanner.nextFloat();

        System.out.println("Enter number b: ");
        float b = scanner.nextFloat();

        if (a == b){
            System.out.println("Both numbers are equal to " + a);
        }
        else {
            if (a > b) {
                System.out.println(a + " is greater");
            }
            else{
                System.out.println(b + " is greater");
            }
        }


    }
}