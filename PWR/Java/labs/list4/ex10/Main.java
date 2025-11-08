
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static java.lang.IO.println;
import static term.term.*;    //including package to be able to use simple print()






public static void draw_frame_c(int x, int y, int l, char c){
    framexyc(x, y, x + l - 1, y + l - 1, c);
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
    }
}

public static void updateSquares(TBoards b){
    TSquares[] s = b.squares;
    for (int i = 0; i < b.squaresCount; i++){
        draw_frame_c(s[i].x, s[i].y, s[i].size, ' ');
        while(checkCollision(s[i])) {
            randomDirection(s[i]);
        }
        s[i].x += s[i].dx;
        s[i].y += s[i].dy;
        setfgcolor(s[i].color);
        draw_frame_c(s[i].x, s[i].y, s[i].size, '#');
    }
}

public static class TSquares{
    public int color;
    public int x;
    public int size;
    public int y;
    public int direction;
    int dx;
    int dy;
    int speed;
    int loopCount;
}

public static void setSquare(TSquares s){
    s.color = (int)(Math.random() * 11 + 1);
    s.size = (int)(Math.random()*2 + 1);
    s.x = (int)(Math.random()*(120 -2- s.size) + 2 );
    s.y = (int)(Math.random()*(30 - 2 - s.size) + 2);
    s.dy = randomD();
    s.dx = randomD();

    randomDirection(s);
    s.speed = (int)(Math.random() * 5);
    s.loopCount = 0;

}

public static TSquares createSquare(){
    TSquares s = new TSquares();
    setSquare(s);
    return s;
}

public static void addSquare(TBoards b, TSquares s){
    b.squares[b.squaresCount] = s;
    b.squaresCount ++;

}

public static int randomD(){
    return (int)(Math.random()*3 - 1);
}

public static void randomDirection(TSquares s){
        if (checkRightX(s) || checkLeftX(s)){
            int temp = s.dx;
            while(temp == s.dx){
                s.dx = randomD();
            }
        }

        if (checkUpY(s) || checkDownY(s)){
            int temp = s.dy;
            while(temp == s.dy){
                s.dy = randomD();
            }
        }

        while(s.dx == 0 && s.dy == 0){
            s.dx = randomD();
            s.dy = randomD();
        }
}

public static boolean checkRightX(TSquares s){
    return (s.x + s.dx + s.size - 1 == 120);
}

public static boolean checkLeftX(TSquares s){
    return (s.x + s.dx == 1);
}

public static boolean checkUpY(TSquares s){
    return (s.y + s.dy == 1);
}

public static boolean checkDownY(TSquares s){
    return s.y + s.dy + s.size - 1 == 30;
}


public static boolean checkCollision(TSquares s) {
    if (checkUpY(s) || checkDownY(s) || checkLeftX(s) || checkRightX(s)) {
        return true;
    }
    return false;
}

public static void printBoard(TBoards b){
    framexyc(b.x, b.y, b.x + b.hor_length - 1, b.y + b.vert_length - 1, '#');
}

public static void startProgram(TBoards b){
    cursor_hide();
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
    setBoard(board1, 1, 1, createSquare());
//
//    for (int i = 0; i < 2; i ++){
//        addSquare(board1, createSquare());
//    }

    print(board1.squaresCount);
    startProgram(board1);

}