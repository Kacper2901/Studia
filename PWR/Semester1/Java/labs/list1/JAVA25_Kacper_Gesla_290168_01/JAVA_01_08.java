/*
  NAME: Kacper Gęśla
  ID: 290168
*/

import java.util.Scanner;

public class JAVA_01_08 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a natural number n: ");
        int n = scanner.nextInt();

        for (int i = 2; i<=n; i += 2 ){
            System.out.println(i);
        }
    }
}