public class Main {
    static int sumOfNumbers(int n) {
        int res = 0;
        for (int i = 0; i <= n; i++) {
            res += i;
        }
        return res;
    }

    static int factorial(int n) {
        int res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    static boolean evenOrOdd(int number) {
        if (number % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    static int countDigits(int n) {
        if (n == 0) {
            return 1;
        }

        int res = 0;
        while (n != 0) {
            res += 1;
            n /= 10;
        }
        return res;
    }


    static int reverseNumber(int number) {
        int newNumber = 0;
        if (number == 0) return 0;

        while (number > 0) {
            newNumber *= 10;
            newNumber += number % 10;
            number /= 10;
        }
        return newNumber;
    }


    static boolean isPrime(int number) {
        if (number == 1 || number == 0) return false;
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }


    static int sumOfEvenNumbers(int n) {
        int res = 0;
        for (int i = 2; i <= n; i += 2) {
            res += i;
        }
        return res;
    }

    static int powerCalculator(int base, int exponent) {
        int res = 1;
        while (exponent > 0) {
            res *= base;
            exponent--;
        }
        return res;
    }

    static void multiplicationTable(int n) {
        for (int i = 0; i <= 10; i++) {
            System.out.println(n + " x " + i + " = " + n * i);
        }
    }


    static boolean isPalindrome(int n) {
        if (n == reverseNumber(n)) return true;
        else return false;
    }

    static void printFibonacci(int n) {
        int a = 0;
        int b = 1;
        int i = 2;
        int temp;
        if (n == 0) return;
        System.out.println(a);
        if (n == 1) return;
        System.out.println(b);
        if (n == 2) return;

        while (i < n) {
            temp = b;
            b = a + b;
            a = temp;
            System.out.println(b);
            i++;
        }
    }

    static int countVowels(String text) {
        int count = 0;
        char[] letters = text.toLowerCase().toCharArray();
        for(char l: letters){
            if(l == 'a' || l == 'e' || l == 'i' || l == 'o' || l == 'u' || l == 'y'){
                count ++;
            }
        }
        return count;
    }

    static int findMaximum(int a, int b, int c){
        int max = a;
        if (b > max) max = b;
        if (c > max) max = c;

        return max;
    }

    static int sumOfDigits(int number){
        int sum = 0;
        while(number > 0){
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    static boolean isPerfect(int number){
        int sumOfDivisors = 1;
        for (int i = 2; i*i<=number; i++){
            if (number % i == 0){
                if(number/i != i) sumOfDivisors += i + number/i;
                else sumOfDivisors += i;

            }
        }
        return number == sumOfDivisors;
    }


    public static void main(String[] args) {
            System.out.println("sumOfNumbers(4) = " + sumOfNumbers(4));
            System.out.println("factorial(5) = " + factorial(5));
            System.out.println("evenOrOdd(7) = " + evenOrOdd(7));
            System.out.println("countDigits(12345) = " + countDigits(12345));
            System.out.println("reverseNumber(1234) = " + reverseNumber(1234));
            System.out.println("isPrime(13) = " + isPrime(13));
            System.out.println("sumOfEvenNumbers(10) = " + sumOfEvenNumbers(10));
            System.out.println("powerCalculator(2, 5) = " + powerCalculator(2, 5));
            System.out.println("isPalindrome(121) = " + isPalindrome(121));
            System.out.println("countVowels(\"hello\") = " + countVowels("hello"));
            System.out.println("findMaximum(3, 7, 5) = " + findMaximum(3, 7, 5));
            System.out.println("sumOfDigits(987) = " + sumOfDigits(987));
            System.out.println("isPerfect(28) = " + isPerfect(28));



    }
}