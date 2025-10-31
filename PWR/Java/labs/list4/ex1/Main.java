//Kacper Gęśla 290168

import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static java.lang.IO.println;
import static term.term.*;     //includes package term (createElements() functions were moved there)

int prng(int seed){
    return (123 * seed + 321) % 10;
}


void main() {
    int seed = 1234;

    for (int i = 0; i < 20; i ++) {
        int a = prng(seed);
        seed = a;
        System.out.print(a + ", ");
    }



}