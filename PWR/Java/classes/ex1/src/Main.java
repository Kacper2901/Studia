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

void main() {
   System.out.println("Sum of 10 first numbers: ");
   System.out.println(sumOfNumbers(10));
   System.out.println();
   System.out.println();

}
