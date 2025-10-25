import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static term.term.*;     //include package term (clrscr, gotoxt, setfgcolor, etc., were moved there for clarity)

int treeWidth(int width){
    return 2 * width  - 1;
}

int starsInLine(int line){
    return 2 * line - 1;
}

String treeLine(int stars){
    String line = "";
    for (int i = 0; i < stars; i++){
        line = line + "*";
    }
    line += "\n";
    return line;
}

void drawTreeBranches(int x, int y, int row, int width){
    int realWidth = treeWidth(width);
    int realStars = starsInLine(row);
    String line = treeLine(realStars);

    int startingPoint = x + (realWidth - realStars) / 2;

    gotoxy(startingPoint, y);
    print(line);
}

void draw_ctree(int x, int y, int n){
    for (int i = 1; i <= n; i++){
        drawTreeBranches(x, y + i, i, n);
    }
}

void main() {

draw_ctree(20, 10,  4);
draw_ctree(50, 1,  3);
draw_ctree(50, 20,  9);

}