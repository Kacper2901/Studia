//Kacper Gęśla 2901268
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)

long fibonacci_it(long n){
    if (n == 0 ) return 0;
    if (n == 1 ) return 1;

    long res = 1;
    long prev = 0;
    long i = 1;
    long temp;

    while (i < n){
        temp = res;
        res += prev;
        prev = temp;
        i++;
    }

    return res;
}

long fibonacci_rec(long n){
    if (n == 0) return 0;
    if (n == 1) return 1;

    return fibonacci_rec(n - 1) + fibonacci_rec(n - 2);
}

long fibonacci_rectail(long n, long res, long prev){
    if (n == 0) return prev;
    if (n == 1) return res;

    return fibonacci_rectail(n - 1, prev + res, res);
}

String round_to_6frac(double n){
    return String.format("%.6f", n);
}

void main() {
//    //without printing 10 times
//
//    long it_start_time = System.nanoTime();
//    for (int i = 0; i < 10; i++) {
//        fibonacci_it(45);
//    }
//    long it_end_time = System.nanoTime();
//
//    long rec_start_time = System.nanoTime();
//    for (int i = 0; i < 10; i++) {
//        fibonacci_rec(45);
//    }
//    long rec_end_time = System.nanoTime();
//
//    long rectail_start_time = System.nanoTime();
//    for (int i = 0; i < 10; i++) {
//        fibonacci_rectail(45, 1, 0);
//    }
//    long rectail_end_time = System.nanoTime();
//
//
//    print("Avarage Time without printing (10 times, n = 45): \n");
//    print("Iteration: " + round_to_6frac((it_end_time - it_start_time) / 10.0 / 1000000.0) + "ms\n");
//    print("Non-tail recursion: " + round_to_6frac((rec_end_time - rec_start_time) / 10.0 / 1000000.0) + "ms\n");
//    print("Tail recursion: " + round_to_6frac((rectail_end_time - rectail_start_time) / 10.0 / 1000000.0) + "ms\n");

//    System.out.println();
//    System.out.println();


//    long it_start_time = System.nanoTime();
//    for (int i = 0; i < 1000; i++){
//        fibonacci_it(45);
//    }
//    long it_end_time = System.nanoTime();
//
//
//    long rectail_start_time = System.nanoTime();
//    for (int i = 0; i < 1000; i++){
//        fibonacci_rectail(45, 1, 0);
//    }
//    long rectail_end_time = System.nanoTime();
//
//    print("Avarage Time without printing (1000 times, n = 45): \n");
//    print("Iteration: " + round_to_6frac((it_end_time - it_start_time) / 1000.0 / 1000000.0) + "ms\n");
//    print("Tail recursion: " + round_to_6frac((rectail_end_time - rectail_start_time) /1000.0 /1000000.0) + "ms\n");


    //with printing 10 times

//    long  it_start_time = System.nanoTime();
//    for (int i = 0; i < 10; i++){
//        print(fibonacci_it(45) + "\n");
//    }
//    long it_end_time = System.nanoTime();
//
//    long  rec_start_time = System.nanoTime();
//    for (int i = 0; i < 10; i++){
//        print(fibonacci_rec(45) + "\n");
//    }
//    long rec_end_time = System.nanoTime();
//
//    long  rectail_start_time = System.nanoTime();
//    for (int i = 0; i < 10; i++){
//        print(fibonacci_rectail(45, 1, 0) + "\n");
//    }
//    long rectail_end_time = System.nanoTime();
//
//    print("\n");
//    print("\n");
//    print("\n");
//
//    print("Avarage Time with printing (10 times, n = 45): \n");
//    print("Iteration: " + round_to_6frac((it_end_time - it_start_time) / 10.0 / 1000000.0) + "ms\n");
//    print("Non-tail recursion: " + round_to_6frac((rec_end_time - rec_start_time) / 10.0 / 1000000.0) + "ms\n");
//    print("Tail recursion: " +round_to_6frac ((rectail_end_time - rectail_start_time) /10.0 /1000000.0) + "ms\n");
//
//    System.out.println();
//    System.out.println();
//



long it_start_time = System.nanoTime();
for (int i = 0; i < 1000; i++){
    print(fibonacci_it(45));
}
long it_end_time = System.nanoTime();


long rectail_start_time = System.nanoTime();
for (int i = 0; i < 1000; i++){
    print(fibonacci_rectail(45, 1, 0));
}
long rectail_end_time = System.nanoTime();

print("Avarage Time with printing (1000 times, n = 45): \n");
print("Iteration: " + round_to_6frac((it_end_time - it_start_time) / 1000.0 / 1000000.0) + "ms\n");
print("Tail recursion: " + round_to_6frac((rectail_end_time - rectail_start_time) /1000.0 /1000000.0) + "ms\n");


}