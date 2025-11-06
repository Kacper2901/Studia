int sumOfNumbers(int n){
    int res = 0;
    for(int i = 0; i < n; i++){
        res += i;
    }
    return res;
}

int factorial(int n){
    int res = 1;

    for (int i = 2; i <= n; i++){
        res *= i;
    }

    return res;
}

boolean evenOrOdd(int number){
    if (number % 2 == 0){
        return  true;
    }
    else{
        return false;
    }
}

int countDigits(int n){
    if (n == 0){
        return 1;
    }

    int res = 0;

    while(n != 0){
        res += 1;
        n /= 10;
    }

    return res;
}

int reverseNumber(int number){
    int newNumber = 0;

    if (number == 0) return 0;

    while (number > 0){
        newNumber +=  number % 10;
        number /= 10;
        newNumber *= 10;
    }

    return newNumber;
}


boolean isPrime(int number){
    for (int i = 2; i * i <= number; i++){
        if(number % i == 0) return false;
    }
    return true;
}


int sumOfEvenNumbers(int n){
    int res = 0;

    for (int i = 2; i <= n; i += 2){
        res += i;
    }

    return res;
}

int powerCalculator(int base, int exponent){
    int res = 1;

    while (exponent > 0){
        res *= base;
        exponent--;
    }

    return res;
}

void multiplicationTable(int n){
    for (int i = 0; i <= 10; i++){
        System.out.println(n + " x " + i + " = " + n*i);
    }
}


boolean isPalindrome(int n){
    if (n == reverseNumber(n)) return true;
    else return false;
}

void printFibonacci(int n){
    int a = 0;
    int b = 1;
    int i = 2;
    int temp;
    if (n == 0) return;
    System.out.println(a);
    if (n == 1) return;
    System.out.println(b);
    if (n == 2) return;

    while (i < n){
        temp = b;
        temp = a + b;
        a = temp;
        System.out.println(b);
        i++;
    }
}






void main() {
   System.out.println("Sum of 10 first numbers: ");
   System.out.println(sumOfNumbers(10));
   System.out.println();
   System.out.println();

}
