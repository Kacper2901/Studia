/*
  NAME: Kacper Gęśla
  ID: 290168
*/

import java.util.Scanner;

public class JAVA_01_06 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a number n: ");
        int n = scanner.nextInt();
        int sum = 0;

        for (int i = 1; i <= n; ++i){
            sum += i;
        }

        System.out.println("Sum of the first n  natural numbers is " + sum);

    }
}