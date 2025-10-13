/*
  NAME: Kacper Gęśla
  ID: 290168
*/

import java.util.Scanner;

public class JAVA_01_03 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number: ");
        int x = scanner.nextInt();

        if (x % 2 == 1){
            System.out.println(x + " is odd");
        }
        else{
            System.out.println(x + " is even");
        }
    }
}