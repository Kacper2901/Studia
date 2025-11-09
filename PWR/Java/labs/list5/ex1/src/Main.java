
import static java.lang.IO.println;
import static term.term.*;    //including package to be able to use simple print()

final public static int BOARD_WIDTH = 121;
final public static int BOARD_HEIGHT = 31;

public static void printBoard(){
    setfgcolor(white);
    framexyc(1,1,BOARD_WIDTH, BOARD_HEIGHT, '#');
}

public static void resetBoard(){
    printPaddle1((int)((BOARD_HEIGHT - 2 - 7) / 2) + 2, '#');
    printPaddle2((int)((BOARD_HEIGHT - 2 - 7) / 2) + 2, '#');
    printBall((int)((BOARD_WIDTH - 2)/2) + 1, (int)((BOARD_HEIGHT - 2)/2) + 2, '*');
}

public static void printRectangle(int x, int y, int length, int width, char c){
    framexyc(x, y , x + width - 1, y + length - 1, c);
}

public static void printPaddle1(int y, char c){
    printRectangle((int)((BOARD_WIDTH - 2) * 0.1) + 1, y, 7, 3, c);
}

public static void printPaddle2(int y, char c){
    printRectangle((int)((BOARD_WIDTH - 2) * 0.9) + 1, y, 7, 3, c);
}

public static void printBall(int x, int y, char c){
    gotoxy(x,y);
    setfgcolor(white);
    write(c);
}

public static int randomDirection(){
    int[] directions = {-1, 1};
    return directions[(int)(Math.random()*2)];
}

public static void updateBall(int x, int y, int dx, int dy){
    printBall(x, y, ' ');
    printBall(x + dx, y + dy, '*');

}

public static void updatePaddle1(int y, int dy){
    printPaddle1(y, ' ');
    printPaddle1(y + dy, '#');
}

public static void updatePaddle2(int y, int dy){
    printPaddle2(y, ' ');
    printPaddle2(y + dy, '#');
}

public static boolean paddleBoardCollision(int y){
    if (y == 1 || y == BOARD_HEIGHT){
        return true;
    }
    return false;
}

public static boolean ballBounceCollision(int y){
    if(y == 1 || y == BOARD_HEIGHT){
        return true;
    }
    return false;
}






public static void game(){
    int paddel_1y = (int)((BOARD_HEIGHT - 2 - 7) / 2) + 2;
    int paddel_2y = (int)((BOARD_HEIGHT - 2 - 7) / 2) + 2;
    int dx;
    int dy = 0;
    int score1 = 0;
    int score2 = 0;
    int x = (int)((BOARD_WIDTH - 2)/2) + 1;
    int y = (int)((BOARD_HEIGHT - 2)/2) + 2;
    printBoard();
    resetBoard();
    dx = randomDirection();

    while(true){

        if(keypressed()){
            String keystr = readkeystr();
            if(keystr.equals("w") && paddel_1y - 1 > 1){
                updatePaddle1(paddel_1y, -1);
                paddel_1y -= 1;
            }
            if(keystr.equals("s") && paddel_1y + 1 < BOARD_HEIGHT - 6){
                updatePaddle1(paddel_1y, +1);
                paddel_1y += 1;
            }
            if(keystr.equals("i") && paddel_2y - 1 > 1){
                updatePaddle2(paddel_2y, -1);
                paddel_2y -= 1;
            }
            if(keystr.equals("k") && paddel_2y + 1 < BOARD_HEIGHT - 6){
                updatePaddle2(paddel_2y, +1);
                paddel_2y += 1;
            }
        }
        updateBall(x, y, dx, dy);
        delay(50);
        x += dx;
        y += dy;


    }




}

void main(){

    game();

}