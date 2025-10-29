//Kacper Gęśla 2901268
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)

//add your procedures and functions here
String round_to_6frac(double n){
    return String.format("%.6f", n);
}

long iter_fac(long n){
    long res = 2;
    if (n == 0 || n == 1){return 1;};

    for (long i = 3; i <= n; i++){
        res *= i;
    }
    return res;
}

long rec_fac (long n){
    if (n == 1 || n == 0){
        return 1;
    }
    return n * rec_fac(n - 1);
}

long rectail_fac (long n, long res){
    if (n == 1 || n == 0){
        return res;
    }
    return rectail_fac(n - 1, res * n);
}

void main() {
    //without printing 10 times
//
//    long it_start_time = System.nanoTime();
//    for (int i = 0; i < 10; i++) {
//        iter_fac(18);
//    }
//    long it_end_time = System.nanoTime();
//
//    long rec_start_time = System.nanoTime();
//    for (int i = 0; i < 10; i++) {
//        rec_fac(18);
//    }
//    long rec_end_time = System.nanoTime();
//
//    long rectail_start_time = System.nanoTime();
//    for (int i = 0; i < 10; i++) {
//        rectail_fac(18, 1);
//    }
//    long rectail_end_time = System.nanoTime();
//
//
//    print("Avarage Time without printing (10 times, n = 18): \n");
//    print("Iteration: " + round_to_6frac((it_end_time - it_start_time) / 10.0 / 1000000.0) + "ms\n");
//    print("Non-tail recursion: " + round_to_6frac((rec_end_time - rec_start_time) / 10.0 / 1000000.0) + "ms\n");
//    print("Tail recursion: " + round_to_6frac((rectail_end_time - rectail_start_time) / 10.0 / 1000000.0) + "ms\n");
//
//    System.out.println();
//    System.out.println();


//    long it_start_time = System.nanoTime();
//    for (int i = 0; i < 10000; i++) {
//        iter_fac(18);
//    }
//    long it_end_time = System.nanoTime();
//
//    long rec_start_time = System.nanoTime();
//    for (int i = 0; i < 10000; i++) {
//        rec_fac(18);
//    }
//    long rec_end_time = System.nanoTime();
//
//    long rectail_start_time = System.nanoTime();
//    for (int i = 0; i < 10000; i++) {
//        rectail_fac(18, 1);
//    }
//    long rectail_end_time = System.nanoTime();
//
//
//    print("Avarage Time without printing (10000 times, n = 18): \n");
//    print("Iteration: " + round_to_6frac((it_end_time - it_start_time) / 10000.0 / 1000000.0) + "ms\n");
//    print("Non-tail recursion: " + round_to_6frac((rec_end_time - rec_start_time) / 10000.0 / 1000000.0) + "ms\n");
//    print("Tail recursion: " + round_to_6frac((rectail_end_time - rectail_start_time) / 10000.0 / 1000000.0) + "ms\n");


//     long it_start_time = System.nanoTime();
//    for (int i = 0; i < 10; i++){
//        print(iter_fac(18));
//    }
//     long it_end_time = System.nanoTime();
//
//     long rec_start_time = System.nanoTime();
//    for (int i = 0; i < 10; i++){
//        print(rec_fac(18));
//    }
//     long rec_end_time = System.nanoTime();
//
//     long rectail_start_time = System.nanoTime();
//    for (int i = 0; i < 10; i++){
//        print(rectail_fac(18, 1));
//    }
//     long rectail_end_time = System.nanoTime();
//
//    print("Avarage Time with printing (10 times, n = 18): \n");
//    print("Iteration: " + round_to_6frac((it_end_time - it_start_time) / 10.0 / 1000000.0) + "ms\n");
//    print("Tail recursion: " + round_to_6frac((rec_end_time - rec_start_time) /10.0 /1000000.0) + "ms\n");
//    print("NON-Tail recursion: " + round_to_6frac((rectail_end_time - rectail_start_time) /10.0 /1000000.0) + "ms\n");


//
//
//
     long it_start_time = System.nanoTime();
    for (int i = 0; i < 10000; i++){
        print(iter_fac(18));
    }
     long it_end_time = System.nanoTime();

     long rec_start_time = System.nanoTime();
    for (int i = 0; i < 10000; i++){
        print(rec_fac(18));
    }
    long rec_end_time = System.nanoTime();

    long rectail_start_time = System.nanoTime();
    for (int i = 0; i < 10000; i++){
        print(rectail_fac(18, 1));
    }
    long rectail_end_time = System.nanoTime();

    print("Avarage Time with printing (10000 times, n = 18): \n");
    print("Iteration: " + round_to_6frac((it_end_time - it_start_time) / 10000.0 / 1000000.0) + "ms\n");
    print("NON-Tail recursion: " + round_to_6frac((rec_end_time - rec_start_time) /10000.0 /1000000.0) + "ms\n");
    print("Tail recursion: " + round_to_6frac((rectail_end_time - rectail_start_time) /10000.0 /1000000.0) + "ms\n");


}