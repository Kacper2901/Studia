
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
    printRectangle((int)((BOARD_WIDTH - 2) * 0.2) + 1, y, 7, 3, c);
}

public static void printPaddle2(int y, char c){
    printRectangle((int)((BOARD_WIDTH - 2) * 0.8) + 1, y, 7, 3, c);
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

public static boolean ballPaddleCollision(int p1y, int p2y, int p1x, int p2x, int ball_x, int ball_y){
    return (ball_x == p1x + 2 && ball_y >= p1y && ball_y <= p1y + 6) || (ball_x == p2x && ball_y >= p2y && ball_y <= p2y + 6);
}

public static void printScore(int score1, int score2){
    gotoxy(59,1);
    setfgcolor(red);
    println(score1 + ":" + score2);
    setfgcolor(white);
}


public static void game(){
    clrscr();
    int startPadel1y = (int)((BOARD_HEIGHT - 2 - 7) / 2) + 2;
    int startPadel2y = (int)((BOARD_HEIGHT - 2 - 7) / 2) + 2;
    int startPadel1x = (int)((BOARD_WIDTH - 2) * 0.2) + 1;
    int startPadel2x = (int)((BOARD_WIDTH - 2) * 0.8) + 1;
    int paddel_1y = startPadel1y;
    int paddel_2y = startPadel2y;
    int paddel_1x = startPadel1x;
    int paddel_2x = startPadel2x;
    int dx;
    int dy = 0;
    int score1 = 0;
    int score2 = 0;
    int startX = (int)((BOARD_WIDTH - 2)/2) + 1;
    int startY = (int)((BOARD_HEIGHT - 2)/2) + 2;
    int ballX = startX;
    int ballY = startY;
    printBoard();
    resetBoard();
    printScore(score1,score2);
    dx = randomDirection();
    int loopCount = 0;
    int loopNoMovement = 2;
    boolean isPaddleMovingUp = false;
    boolean isPaddleMovingDown = false;

    while(true){
        if(keypressed()){
            String keystr = readkeystr();
            if (keystr.equals(" ")) break;
        }
    }

    while(true){
        if (loopNoMovement > 2) {
            isPaddleMovingUp = false;
            isPaddleMovingDown = false;
        }
        if(keypressed()){
            String keystr = readkeystr();
            if(keystr.equals("w") && paddel_1y - 1 > 1){
                updatePaddle1(paddel_1y, -1);
                paddel_1y -= 1;
                isPaddleMovingUp = true;
                isPaddleMovingDown = false;
                loopNoMovement = 0;
            }
            if(keystr.equals("s") && paddel_1y + 1 < BOARD_HEIGHT - 6){
                updatePaddle1(paddel_1y, +1);
                paddel_1y += 1;
                isPaddleMovingDown = true;
                isPaddleMovingUp = false;
                loopNoMovement = 0;
            }
            if(keystr.equals("i") && paddel_2y - 1 > 1){
                updatePaddle2(paddel_2y, -1);
                paddel_2y -= 1;
                isPaddleMovingUp = true;
                isPaddleMovingDown = false;
                loopNoMovement = 0;
            }
            if(keystr.equals("k") && paddel_2y + 1 < BOARD_HEIGHT - 6){
                updatePaddle2(paddel_2y, +1);
                paddel_2y += 1;
                isPaddleMovingDown = true;
                isPaddleMovingUp = false;
                loopNoMovement = 0;
            }
            if(keystr.equals("esc")) break;
        }
        else loopNoMovement += 1;



        if (loopCount % 1 == 0){
            if (ballBounceCollision(ballY +dy)){
                dy *= -1;
                sound(600,40);

            }

            if (ballPaddleCollision(paddel_1y, paddel_2y, paddel_1x, paddel_2x, ballX + dx, ballY + dy)){
                if(isPaddleMovingUp) dy = -1;
                else if(isPaddleMovingDown) dy = 1;
                else dy = 0;
                if(ballX > BOARD_WIDTH / 2) sound(1000,40);
                else sound(800,40);
                dx *= -1;
            }

            else if(ballX == paddel_2x || ballX == paddel_1x + 2){
                if(ballX == paddel_1x + 2) score2 ++;
                else score1 ++;
                if (score1 == 3) break;
                if (score2 == 3) break;
                printPaddle1(paddel_1y, ' ');
                printPaddle2(paddel_2y, ' ');
                printBall(ballX,ballY,' ');
                resetBoard();
                printScore(score1,score2);
                ballX = startX;
                ballY = startY;
                sound(400,40);
                paddel_1x = startPadel1x;
                paddel_2x = startPadel2x;
                paddel_1y = startPadel1y;
                paddel_2y = startPadel2y;
                dx = randomDirection();
                while(true){
                    if(keypressed()){
                        String keystr = readkeystr();
                        if (keystr.equals(" ")) break;
                    }
                }
                continue;
            }
            updateBall(ballX, ballY, dx, dy);
            ballX += dx;
            ballY += dy;
        }
        loopCount = (loopCount + 1) % 10;
    }

    clrscr();
    gotoxy(60, 15);
    setfgcolor(red);
    if (score2 != 3 && score1 != 3) {
        println("Game Interrupted");
        gotoxy(200,200);
        readkeystr();
        return;
    }
    String winner = "Left Player";
    if (score2>score1) winner = "Right Player";
    System.out.print(winner);
    setfgcolor(white);
    System.out.print(" won " + score1 + ":" + score2 + "!");
    gotoxy(200,200);
    readkeystr();
}

void main(){

    game();


}