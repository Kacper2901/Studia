
import static java.lang.IO.*;  //including package to be able to use simple print()

//move cursor position to column x, row y
public static void gotoxy(int x, int y) {
    String GOTO_XY = "\u001b[%d;%dH";
    print(String.format(GOTO_XY, y, x));
}

//clear the terminal window
void clrscr() {
    String CLEAR_SCREEN = "\u001b[2J";
    print(String.format(CLEAR_SCREEN));
}

void cursor_hide() {
    String HIDE_CURSOR = "\u001b[?25l";
    print(String.format(HIDE_CURSOR));
}

void cursor_show() {
    String SHOW_CURSOR = "\u001b[?25h";
    print(String.format(SHOW_CURSOR));
}

void delay(int msec) {
    try {
        Thread.sleep(msec);
    } catch (InterruptedException e) {}
}

//constants with identifiers of basic colors
//see: https://en.wikipedia.org/wiki/ANSI_escape_code#8-bit
final int black = 0;
final int brown = 1;
final int green = 2;
final int yellow = 3;
final int blue = 4;
final int magenta = 5;
final int cyan = 6;
final int ltgrey = 7;
final int grey = 8;
final int red = 9;
final int ltgreen = 10;
final int ltyellow = 11;
final int ltblue = 12;
final int white = 15;

//set text foreground color
void setfgcolor(int n) {
    String SET_FG_COLOR = "\u001b[38;5;%dm";
    print(String.format(SET_FG_COLOR,n));
}

//set text background color
void setbgcolor(int n) {
    String SET_BG_COLOR = "\u001b[48;5;%dm";
    print(String.format(SET_BG_COLOR,n));
}

//add your procedures here
int MAX_SQUARES = 200;


public static class TBoards{
    int x;
    int y;
    int hor_length = 120;
    int vert_length = 30;
    TSquares[] squares = new TSquares[200];
    int squaresCount;
}

public static class TSquares{
    int color;
    int[] x_coordinates = new int[8];
    int[] y_coordinates = new int[8];
}

public static void setBoard(TBoards b, int x, int y, TSquares... squares) {
    b.x = x;
    b.y = y;
    b.squaresCount = 0;

    for(TSquares s: squares){
        addSquare(b,s);
        b.squaresCount ++;
    }
}

public static void printArr(int[] a, int size){
    for(int i = 0; i < size; i++){
        print(a[i] + " ");
    }
    println(" ");
}

public static TBoards createBoard(int x, int y, TSquares... squares){
    TBoards b = new TBoards();
    setBoard(b, x, y, squares);
    return b;
}

public static void addSquare(TBoards b, TSquares s){
    b.squares[b.squaresCount] = s;
    b.squaresCount ++;
}

public static void setSquare(TSquares s, int color){
    s.color = color;
    for(int i = 0; i < 8; i++){
        s.x_coordinates[i] = (int)(Math.random()*118 + 1);
        s.y_coordinates[i] = (int)(Math.random()*28 + 1);
    }
}

public static TSquares createSquare(int color){
    TSquares s = new TSquares();
    setSquare(s, color);
    return s;
}

public static void draw_horiz_line_c(int x1, int x2, int y, char c){
    gotoxy(x1,y);

    while (x1 <= x2){
        print(c);
        ++x1;
        gotoxy(x1,y);
    }
}

public static void draw_vert_line_c(int x, int y1, int y2, char c){
    gotoxy(x,y1);

    while (y1 <= y2){
        print(c);
        ++y1;
        gotoxy(x,y1);
    }
}

public static void draw_frame_c(int x, int y, char c){
    draw_vert_line_c(x, y, x + 2, c);
    draw_vert_line_c(x2, y1, y2, c);
    draw_horiz_line_c(x1, x2, y1, c);
    draw_horiz_line_c(x1, x2, y2, c);
}

public static void printBoard(TBoards b){
    gotoxy(b.x, b.y);
    dra
}



void main() {
    TSquares square1 = createSquare(yellow);
    TSquares square2 = createSquare(red);
    TSquares square3 = createSquare(ltgreen);
    TSquares square4 = createSquare(ltblue);
    TBoards board1 = createBoard(1,1, square1, square2);

}