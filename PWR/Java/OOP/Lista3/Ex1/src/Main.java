//Kacper Gęśla 2901268
//For best results run this code in at least 120x30 terminal window
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)

//add your procedures and functions here
void draw_tree(int N){
    setbgcolor(white);
    clrscr();
    final int max = 22;
    int starsCounter = 0;
    if (N > 0 && N < 23){
        setfgcolor(green);
        for (int i = 1; i <= N + 2; i++){
            for (int j = 1; j <= 22; j++) {
                if (i <= N) {
                    if (j > 22 - i) {
                        print("* ");
                    } else {
                        print(" ");
                    }
                }
                else{
                    setfgcolor(brown);
                    if (N < 4) {
                        if (j == 22) {
                            print("|");
                        } else {
                            print(" ");
                        }
                        i += 1;
                    }
                    else {
                        if (j == 21) {
                            print("|||");
                        }
                        else {
                            print(" ");
                        }
                    }
                }
            }
            print("\n");
        }
    }
}





void main() {
    draw_tree(3);
    print("\n");
    draw_tree(1);
    print("\n");
    draw_tree(22);
}