
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static java.lang.IO.println;
import static term.term.*;    //including package to be able to use simple print()






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
    TSquares[] s = b.squares;
    for (int i = 0; i < b.squaresCount; i++){
        if (s[i].coordinateIndex == 0){
            draw_frame_c(s[i].x_coordinates[7], s[i].y_coordinates[7], ' ');
        }
        else {
            draw_frame_c(s[i].x_coordinates[s[i].coordinateIndex - 1],s[i].y_coordinates[s[i].coordinateIndex - 1], ' ');
        }

        setfgcolor(s[i].color);
        draw_frame_c(s[i].x_coordinates[s[i].coordinateIndex], s[i].y_coordinates[s[i].coordinateIndex], '#');
        s[i].coordinateIndex = (s[i].coordinateIndex + 1) % 8;
        i++;
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
    setBoard(board1, 1, 1, createSquare(yellow), createSquare(red), createSquare(ltgreen), createSquare(blue));


    startProgram(board1);

}