
import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static java.lang.IO.println;
import static term.term.*;    //including package to be able to use simple print()

public static void draw_frame_c(int x, int y, int l, char c){
    framexyc(x, y, x + l - 1, y + l - 1, c);
}

//add your procedures here
final int MAX_SQUARES = 200;
final int SPEED3 = 1;
final int SPEED2 = 2;
final int SPEED1 = 5;


public static class TBoards{
    public int x;
    public int y;
    public int hor_length = 120;
    public int vert_length = 30;
    public TSquares[] squares = new TSquares[200];
    public int squaresCount;
    public int loopCount;
}

public static void setBoard(TBoards b, int x, int y, TSquares... squares) {
    b.x = x;
    b.y = y;
    b.squaresCount = 0;
    b.loopCount = 0;
    for(TSquares s: squares){
        addSquare(b,s);
    }
}

public static void updateSquares(TBoards b){
    TSquares[] s = b.squares;
    for (int i = 0; i < b.squaresCount; i++){
        if (b.loopCount % s[i].speed == 0){
            draw_frame_c(s[i].x, s[i].y, s[i].size, ' ');

            if(boardCollision(s[i]) || (s[i].dx == 0 && s[i].dy == 0)) {
                randomDirection(s[i]);
            }


            s[i].x += s[i].dx;
            s[i].y += s[i].dy;
            setfgcolor(s[i].color);
            draw_frame_c(s[i].x, s[i].y, s[i].size, '#');
        }
    }
    b.loopCount = (b.loopCount + 1) % 10;

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

public static void setSquare(TSquares s, int speed){
    s.color = (int)(Math.random() * 11 + 1);
    s.size = (int)(Math.random()*2 + 1);
    s.x = (int)(Math.random()*(120 -2- s.size) + 2 );
    s.y = (int)(Math.random()*(30 - 2 - s.size) + 2);
    s.dy = randomD(-1,1);
    s.speed = speed;
    if(s.dy == 0) {
        int[] temp = {-1,1};
        s.dx = temp[(int)(Math.random()*2)];
    }
    else{
        s.dx = randomD(-1,1);
    }

    s.loopCount = 0;

}

public static TSquares createSquare(int speed){
    TSquares s = new TSquares();
    setSquare(s, speed);
    return s;
}

public static void addSquare(TBoards b, TSquares s){
    b.squares[b.squaresCount] = s;
    b.squaresCount ++;

}

public static int randomD(int l, int r){
    return (int)(Math.random()*(r + 1 + Math.abs(l))) + l;
}

public static int randomValues(int length, int[] arr){
    return arr[(int)(Math.random()*length)];
}

public static void randomDirection(TSquares s){
    while ((s.dy == 0 & s.dx == 0) || boardCollision(s)) {
        s.dx = randomD(-1, 1);
        s.dy = randomD(-1, 1);
    }
}

public static boolean checkRightX(TSquares s){
    return (s.x + s.dx + s.size - 1 >= 120);
}

public static boolean checkLeftX(TSquares s){
    return (s.x + s.dx <= 1);
}

public static boolean checkUpY(TSquares s){
    return (s.y + s.dy <= 1);
}

public static boolean checkDownY(TSquares s){
    return s.y + s.dy + s.size - 1 >= 30;
}

//public static boolean squaresCollision(TSquares s1, TSquares s2){
//
//}

public static boolean boardCollision(TSquares s) {
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
    setBoard(board1, 1, 1);
//
    addSquare(board1, createSquare(SPEED3));
    addSquare(board1, createSquare(SPEED2));
    addSquare(board1, createSquare(SPEED1));



//    print(board1.squaresCount);
    startProgram(board1);



//    for(int i = 0; i<20; i++){
//        println(randomValues(2, new int[] {0,1}));
//    }
}


