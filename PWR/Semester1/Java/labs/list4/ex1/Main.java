//Kacper Gęśla 290168

import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static java.lang.IO.println;
import static term.term.*;     //includes package term (createElements() functions were moved there)

public static class Rand{
    static long seed = 1234;
    static long mod = 32;
    static long c = 143;
    static long a = 17875;

    public static long random(){
        long res = (Rand.seed * Rand.a + Rand.c) % mod;
        seed = res;
        return res;
    }
}

int randomTwoSets(){
    int[] set = new int[32];
    int j = 0;
    for (int i = 10; i <= 20; i++){
        set[j] = i;
        j++;
    }
    for (int i = 50; i <= 70; i++){
        set[j] = i;
        j++;
    }


    int random_num = (int)Rand.random();
    return set[random_num];

}


void main() {


    for (int i = 0; i<30; i++){
        print(randomTwoSets() + ", ");
    }
    println("");
    println("");
    println("");
}