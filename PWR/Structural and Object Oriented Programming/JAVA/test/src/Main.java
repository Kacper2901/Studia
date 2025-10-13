import java.util.Scanner;

public class Main{
    static int AddTwoNumbers(int a, int b){
        return a + b;
    }
    static float AddTwoNumbers(float a, float b){
        return a + b;
    }

    static int Factorial(int n){
        if (n==0){
            return 1;
        }
        return n * Factorial(n - 1);
    }
    public static void main(String[] args){
        System.out.println(AddTwoNumbers(4, 5));
        System.out.println(AddTwoNumbers(4.5f, 5.0f));
        System.out.println(Factorial(5));
    }
}