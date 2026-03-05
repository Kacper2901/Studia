/*
  NAME: Kacper Gęśla
  ID: 290168
*/

import java.util.Scanner;

public class JAVA_01_07 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a natural number n: ");
        int n = scanner.nextInt();

        int res = 1;
        if (n >0) {
            for (int i = 2; i <= n; i++) {
                res *= i;
            }
        }


        System.out.println(n + "! = " + res);
    }
}