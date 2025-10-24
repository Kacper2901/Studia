//For best results run this code in at least 120x30 terminal window
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)

//add your procedures and functions here
void draw_tree(int N){
    final int mid = 24;
    int interval = 0;
    if (N > 0 && N <= 23){
        for (int i = 1; i <= N + 2; i++){
            for (int j = 1; j <= mid; j++) {
                if (i <= N) {
                    interval += 1;
                    if (j > mid - interval && j < mid + interval) {
                        print(" *");
                    } else {
                        print(" ");
                    }
                }
                else{
                    if (N < 4) {
                        if (j == 24) {
                            print("|");
                        } else {
                            print(" |");
                        }
                        i += 1;
                    }
                    else {
                        if (j == 24) {
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
}