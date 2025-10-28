//Kacper Gęśla 2901268
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)



int factorial(int n){
    int res = 2;

    if (n == 0 || n == 1){return 1;};

    for (int i = 3; i <= n; i++){
        res *= i;
    }

    return res;
}

void main() {
    print(factorial(0) + "\n");
    print(factorial(1) + "\n");
    print(factorial(2) + "\n");
    print(factorial(3) + "\n");
    print(factorial(4) + "\n");
    print(factorial(5) + "\n");
}