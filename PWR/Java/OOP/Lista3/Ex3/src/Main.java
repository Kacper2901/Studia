//For best results run this code in at least 120x30 terminal window
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)

//void digits_backwards(int x){
//    if (x == 0){
//        print(0 + "\n");
//    }
//    else{
//        while (x >= 10){
//            print(x % 10 + ", ");
//            x /= 10;
//        }
//        print(x + "\n");
//    }
//}

int digitCount(int x){
    int res = 0;
    if (x == 0){return 1;}

    while (x > 0){
        res ++;
        x /= 10;
    }
    return res;
}

int digitAtPostition(int x, int pos, int length){
    int i = length - 1; //indexing starts with 0
    while (i != pos){
        x /= 10;
        i --;
    }
    return x % 10;
}

void digits_backwards(int x){
    int length = digitCount(x);
    for (int i = length - 1; i > 0; i--){
        print(digitAtPostition(x, i, length) + ", ");
    }
    print(digitAtPostition(x, 0, length) + "\n");
}


void main() {
    digits_backwards(345);
    digits_backwards(0);
    digits_backwards(5);
    digits_backwards(10);
    digits_backwards(20000);


}