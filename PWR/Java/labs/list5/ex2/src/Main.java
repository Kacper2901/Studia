
import static java.lang.IO.println;
import static term.term.*;    //including package to be able to use simple print()

final public static int BOARD_WIDTH = 121;
final public static int BOARD_HEIGHT = 31;

public static class TBoards{
    public int x;
    public int y;
    public int width;
    public int height;
    public int color;
    public TPaddel leftPaddel;
    public TPaddel rightPaddel;
    public TBall ball;
    public TPlayer player1;
    public TPlayer player2;
    public TGame game;
}


public static class TPaddel{
    public int startX;
    public int startY;
    public int width;
    public int height;
    public int x;
    public int y;
    public int color;
    public int dy;
    public boolean isMovingUp;
    public boolean isMovingDown;
}

public static class TBall{
    public int startX;
    public int startY;
    public int x;
    public int y;
    public int color;
    public int dx;
    public int dy;
}

public static class TPlayer{
    public int score;
    public TPaddel paddel;
}

public static class TGame{
    public int loopCount;
    public int loopsWithoutMovement;
}



public static void setBoard(TBoards board, int x, int y, int width, int height, int color, TGame game){
    board.x = x;
    board.y = y;
    board.width = width;
    board.height = height;
    board.color = color;
    board.game = game;
}

public static TPaddel createPaddel(int width, int height, int color){
    TPaddel paddel = new TPaddel();
    setPaddel(paddel, width, height, color);
    return paddel;
}

public static void setPaddel(TPaddel paddel, int width, int height, int color){
    paddel.width = width;
    paddel.height = height;
    paddel.color = color;
}

public static TPlayer createPlayer(){
    TPlayer player = new TPlayer();
    setPlayer(player);
    return player;
}

public static void setPlayer(TPlayer player){
    player.score = 0;
}

public static void addPlayer(TBoards board, TPlayer leftPlayer, TPlayer rightPlayer){
    leftPlayer.paddel = board.leftPaddel;
    rightPlayer.paddel = board.rightPaddel;
    board.player1 = leftPlayer;
    board.player2 = rightPlayer;
}

public static TBall createBall(int color){
    TBall ball = new TBall();
    setBall(ball, color);
    return ball;
}

public static void setBall(TBall ball, int color){
    ball.dy = 0;
    ball.dx = randomDirection();
    ball.color = color;
}

public static void addBall(TBoards board, TBall ball){
    ball.startX = board.x + (int)(Math.round((double)((board.width) / 2)));
    ball.startY = board.y + (int)(Math.round((double)((board.height) / 2)));
    ball.x = ball.startX;
    ball.y = ball.startY;
    board.ball = ball;
}

public static void resetPaddles(TPaddel paddel1, TPaddel paddel2){
    paddel1.x = paddel1.startX;;
    paddel1.y = paddel1.startY;;
    paddel2.x = paddel2.startX;
    paddel2.y = paddel2.startY;
    printPaddle(paddel1, '#');
    printPaddle(paddel2, '#');
}

public static void addPaddles(TBoards board, TPaddel paddel1, TPaddel paddel2){
    int offsetX =(int)(board.width * 0.2);
    int offsetY =(int)((board.height - paddel1.height)/2);

    paddel1.startX = board.x + offsetX;
    paddel2.startX = board.x + board.width - offsetX - paddel2.width;
    paddel1.startY = board.y + offsetY;
    paddel2.startY = board.y + board.height - offsetY - paddel2.height;
    resetPaddles(paddel1, paddel2);

    board.leftPaddel = paddel1;
    board.rightPaddel = paddel2;
}

public static TGame createGame(){
    TGame game = new TGame();
    setGame(game);
    return game;
}

public static void setGame(TGame game){
    game.loopCount = 0;
    game.loopsWithoutMovement = 2;
}


public static void printBoard(TBoards board){
    setfgcolor(white);
    framexyc(board.x,board.y,board.width, board.height, '#');
    setfgcolor(board.leftPaddel.color);
    printPaddle(board.leftPaddel,'#');
    setfgcolor(board.rightPaddel.color);
    printPaddle(board.rightPaddel,'#');
}

public static void resetBall(TBoards board){
    board.ball.y = board.ball.startY;
    board.ball.x = board.ball.startX;
    printBall(board.ball, '*');
}

public static void resetBoard(TBoards board){
    resetPaddles(board.leftPaddel, board.rightPaddel);
    resetBall(board);
}

public static void printRectangle(int x, int y, int length, int width, char c){
    framexyc(x, y , x + width - 1, y + length - 1, c);
}

public static void printPaddle(TPaddel paddel, char c){
    setfgcolor(paddel.color);
    printRectangle(paddel.x, paddel.y, paddel.height, paddel.width, c);
}

public static void printBall(TBall ball, char c){
    setfgcolor(ball.color);
    gotoxy(ball.x, ball.y);
    write(c);
}

public static int randomDirection(){
    int[] directions = {-1, 1};
    return directions[(int)(Math.random()*2)];
}

public static void updateBall(TBall ball){
    printBall(ball, ' ');
    ball.x += ball.dx;
    ball.y += ball.dy;
    printBall(ball, '*');
}

public static void updatePaddle(TPaddel paddel){
    printPaddle(paddel, ' ');
    paddel.y += paddel.dy;
    printPaddle(paddel, '#');
}

public static boolean paddleBoardCollision(TPaddel paddel, TBoards board){
    if (paddel.y - 1 <= 1 || paddel.y +1 >= board.height){
        return true;
    }
    return false;
}

public static boolean ballBounceCollision(TBoards board){
    if(board.ball.y + board.ball.dy == board.y || board.ball.y + board.ball.dy == board.height + board.y - 1){
        return true;
    }
    return false;
}

public static boolean ballPaddle1Collision(TPaddel paddel1, TBall ball){
    return (ball.x + ball.dx == paddel1.x + paddel1.width - 1 && ball.y + ball.dy >= paddel1.dy + paddel1.y && ball.y + ball.dy <= paddel1.dy + paddel1.y + paddel1.height - 1);
}

public static boolean ballPaddle2Collision(TPaddel paddel2, TBall ball){
    return (ball.x + ball.dx == paddel2.x && ball.y + ball.dy >= paddel2.dy + paddel2.y && ball.y + ball.dy <= paddel2.y + paddel2.dy + paddel2.height - 1);
}

public static void printScore(TBoards board){
    gotoxy((int)((board.x + board.width - 2)/2), board.y);
    setfgcolor(red);
    println(board.player1.score + ":" + board.player2.score);
    setfgcolor(white);
}


public static void game(){
    cursor_hide();
    clrscr();
    TBoards board = createElements(TBoards.class);
    setBoard(board, 1,1,121,31,white, createGame());
    addBall(board, createBall(yellow));
    addPaddles(board, createPaddel(3,7, ltgreen), createPaddel(3,7,green));
    addPlayer(board, createPlayer(), createPlayer());
    
    printBoard(board);
    resetBoard(board);
    printScore(board);

    while(true){
        if(keypressed()){
            String keystr = readkeystr();
            if (keystr.equals(" ")) break;
        }
    }

    while(true){
        board.player1.paddel.dy = 0;
        board.player2.paddel.dy = 0;
        if (board.game.loopsWithoutMovement > 2) {
            board.player1.paddel.isMovingDown = false;
            board.player1.paddel.isMovingUp = false;
            board.player2.paddel.isMovingDown = false;
            board.player2.paddel.isMovingUp = false;
        }
        if(keypressed()){
            String keystr = readkeystr();
            if(keystr.equals("w") && board.player1.paddel.y - 1 >= board.y + 1){
                board.player1.paddel.dy = -1;
                board.player1.paddel.isMovingUp = true;
                board.player1.paddel.isMovingDown = false;
                board.game.loopsWithoutMovement = 0;
                updatePaddle(board.player1.paddel);

            }
            if(keystr.equals("s") && board.player1.paddel.y + board.player1.paddel.height <= board.y + board.height - 2){
                board.player1.paddel.dy = 1;
                board.player1.paddel.isMovingUp = false;
                board.player1.paddel.isMovingDown = true;
                board.game.loopsWithoutMovement = 0;
                updatePaddle(board.player1.paddel);

            }
            if(keystr.equals("i") && board.player2.paddel.y - 1 >= board.y + 1){
                board.player2.paddel.dy = -1;
                board.player2.paddel.isMovingUp = true;
                board.player2.paddel.isMovingDown = false;
                board.game.loopsWithoutMovement = 0;
                updatePaddle(board.player2.paddel);
            }
            if(keystr.equals("k") && board.player2.paddel.y + board.player2.paddel.height <= board.y + board.height - 2){
                board.player2.paddel.dy = 1;
                board.player2.paddel.isMovingUp = false;
                board.player2.paddel.isMovingDown = true;
                board.game.loopsWithoutMovement = 0;
                updatePaddle(board.player2.paddel);

            }
            if(keystr.equals("esc")) break;
        }
        else board.game.loopsWithoutMovement += 1;



        if (board.game.loopCount % 1 == 0){
            if (ballBounceCollision(board)){
                board.ball.dy *= -1;
                sound(600,40);

            }

            if (ballPaddle1Collision(board.leftPaddel, board.ball)){
                if(board.leftPaddel.isMovingUp) board.ball.dy = -1;
                else if(board.leftPaddel.isMovingDown) board.ball.dy = 1;
                else board.ball.dy = 0;
                sound(1000,40);
                board.ball.dx *= -1;
            }

            else if (ballPaddle2Collision(board.rightPaddel, board.ball)){
                if(board.rightPaddel.isMovingUp) board.ball.dy = -1;
                else if(board.rightPaddel.isMovingDown) board.ball.dy = 1;
                else board.ball.dy = 0;
                sound(800,40);
                board.ball.dx *= -1;
            }



            else if(board.ball.x == board.rightPaddel.x || board.ball.x == board.leftPaddel.x + board.leftPaddel.width - 1){
                if(board.ball.x == board.leftPaddel.x + board.leftPaddel.width - 1) board.player2.score ++;
                else board.player1.score ++;
                if (board.player2.score == 3 || board.player1.score == 3) break;
                printPaddle(board.leftPaddel, ' ');
                printPaddle(board.rightPaddel, ' ');
                printBall(board.ball,' ');
                resetBoard(board);
                printScore(board);
                sound(400,40);
                board.ball.dx = randomDirection();
                while(true){
                    if(keypressed()){
                        String keystr = readkeystr();
                        if (keystr.equals(" ")) break;
                    }
                }
                continue;
            }
            updateBall(board.ball);
        }
        board.game.loopCount = (board.game.loopCount + 1) % 10;
    }

    clrscr();
    gotoxy(60, 15);
    setfgcolor(red);
    if (board.player2.score != 3 && board.player1.score != 3) {
        println("Game Interrupted");
        gotoxy(200,200);
        readkeystr();
        return;
    }
    String winner = "Left Player";
    if (board.player2.score>board.player1.score) winner = "Right Player";
    System.out.print(winner);
    setfgcolor(white);
    System.out.print(" won " + board.player1.score + ":" + board.player2.score + "!");
    gotoxy(200,200);
    readkeystr();
    cursor_show();
}

void main(){

    game();


}