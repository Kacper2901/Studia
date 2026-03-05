/*
  NAME: Kacper Gęśla
  ID: 290168
*/

import java.util.Scanner;

public class JAVA_01_09 {
    public static void main(String[] args ){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter three numbers: ");
        int min = scanner.nextInt();
        int b = scanner.nextInt();
        if (b < min){
            min = b;
        }
        int c = scanner.nextInt();
        if (c < min){
            min = c;
        }

        System.out.println(min + " is the smallest");
    }
}