//Kacper Gęśla 2901268
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)

int factorial_rec (int n){
    if (n == 1 || n == 0){
        return 1;
    }
    return n * factorial_rec(n - 1);
}

void main() {
    print(factorial_rec(0) + "\n");
    print(factorial_rec(1) + "\n");
    print(factorial_rec(2) + "\n");
    print(factorial_rec(3) + "\n");
    print(factorial_rec(4) + "\n");
    print(factorial_rec(5) + "\n");
}