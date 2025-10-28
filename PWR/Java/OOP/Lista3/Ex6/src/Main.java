//Kacper Gęśla 2901268
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)

int factorial_rec (int n, int res){
    if (n == 1 || n == 0){
        return res;
    }
    return factorial_rec(n - 1, res * n);
}

int factorial (int n){
    return factorial_rec(n, 1);
}
void main() {
    print(factorial(0) + "\n");
    print(factorial(1) + "\n");
    print(factorial(2) + "\n");
    print(factorial(3) + "\n");
    print(factorial(4) + "\n");
    print(factorial(5) + "\n");
}

