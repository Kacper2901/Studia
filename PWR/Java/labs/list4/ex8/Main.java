
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static java.lang.IO.println;
import static term.term.*;    //including package to be able to use simple print()

//move cursor position to column x, row y
public static void gotoxy(int x, int y) {
    String GOTO_XY = "\u001b[%d;%dH";
    print(String.format(GOTO_XY, y, x));
}

//clear the terminal window
 public static void clrscr() {
    String CLEAR_SCREEN = "\u001b[2J";
    print(String.format(CLEAR_SCREEN));
}

public static void cursor_hide() {
    String HIDE_CURSOR = "\u001b[?25l";
    print(String.format(HIDE_CURSOR));
}

public static void cursor_show() {
    String SHOW_CURSOR = "\u001b[?25h";
    print(String.format(SHOW_CURSOR));
}

public static void delay(int msec) {
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
public static final int white = 15;

//set text foreground color
public static void setfgcolor(int n) {
    String SET_FG_COLOR = "\u001b[38;5;%dm";
    print(String.format(SET_FG_COLOR,n));
}

//set text background color
void setbgcolor(int n) {
    String SET_BG_COLOR = "\u001b[48;5;%dm";
    print(String.format(SET_BG_COLOR,n));
}




public static void draw_frame_c(int x, int y, char c){
    framexyc(x, y, x + 2, y + 2, c);
}

//add your procedures here
int MAX_SQUARES = 200;


public static class TBoards{
    public int x;
    public int y;
    public int hor_length = 120;
    public int vert_length = 30;
    public TSquares[] squares = new TSquares[200];
    public int squaresCount;
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

public static void updateSquares(TBoards b){
    for (TSquares s: b.squares){
        if (s.coordinateIndex == 0){
            draw_frame_c(s.x_coordinates[7], s.y_coordinates[7], ' ');
        }
        else {
            draw_frame_c(s.x_coordinates[s.coordinateIndex - 1],s.y_coordinates[s.coordinateIndex - 1], ' ');
        }

        setfgcolor(s.color);
        draw_frame_c(s.x_coordinates[s.coordinateIndex], s.y_coordinates[s.coordinateIndex], '#');
        s.coordinateIndex = (s.coordinateIndex + 1) % 8;
    }
}

public static class TSquares{
    public int color;
    public int[] x_coordinates = new int[8];
    public int[] y_coordinates = new int[8];
    public  int coordinateIndex;
}

public static void setSquare(TSquares s, int color){
    s.color = color;
    s.coordinateIndex = 1;
    for(int i = 0; i < 8; i++){
        s.x_coordinates[i] = (int)(Math.random()*116 + 2);
        s.y_coordinates[i] = (int)(Math.random()*26 + 2);
    }
}

public static TSquares createSquare(int color){
    TSquares s = new TSquares();
    setSquare(s, color);
    return s;
}

public static void addSquare(TBoards b, TSquares s){
    b.squares[b.squaresCount] = s;
    b.squaresCount ++;
}

public static void printBoard(TBoards b){
    framexyc(b.x, b.y, b.x + b.hor_length - 1, b.y + b.vert_length - 1, '#');
}



public static void startProgram(TBoards b){
//    cursor_hide();
    cursor_show();
    clrscr();
    setfgcolor(white);
    printBoard(b);

    while (true){
        if(keypressed()){
            String keystr = readkeystr();
            if(keystr.equals("q")) break;
        }
        updateSquares(b);
        delay(20);
    }

}



void main() {

    TBoards board1 = createElements(TBoards.class);
    setBoard(board1, 1, 1, createSquare(yellow), createSquare(red), createSquare(ltgreen), createSquare(ltblue));


    startProgram(board1);



}