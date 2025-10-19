/*
  NAME: Kacper Gęśla
  ID: 290168
*/

import java.util.Scanner;

public class JAVA_01_10 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean active = true;

        while (active){
            System.out.println("Enter two numbers: ");
            float a = scanner.nextFloat();
            float b = scanner.nextFloat();

            System.out.println("Choose arithmetic operation: \n" +
                               "1. Addition\n" +
                               "2. Substraction\n" +
                               "3. Multiplication\n" +
                               "4. Division\n");

            int choice = scanner.nextInt();


            switch (choice){
                case 1:
                    System.out.println(a + " + " + b + " = " + (a + b));
                    break;
                case 2:
                    System.out.println(a + " - " + b + " = " + (a - b));
                    break;
                case 3:
                    System.out.println(a + " * " + b + " = " + (a * b));
                    break;
                case 4:
                    System.out.println(a + " / " + b + " = " + (a / b));
                    break;
                default:
                    System.out.println("The incorrect number was entered\n\n");
                    break;
            }

            System.out.println("\nDo you want to continue? \n" +
                               "1. Yes \n" +
                               "2. No\n");

            choice = scanner.nextInt();

            switch (choice){
                case 1:
                    active = true;
                    break;
                case 2:
                    active = false;
                    System.out.println("See you soon!");
                    break;
                default:
                    System.out.println("The incorrect number was entered. Error");
                    active = false;
                    break;

            }



        }




    }
}