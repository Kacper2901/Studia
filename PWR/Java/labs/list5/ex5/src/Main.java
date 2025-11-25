
import static java.lang.IO.print;
import static java.lang.IO.println;
import static term.term.*;    //including package to be able to use simple print()

public static void draw_frame_c(int x, int y, int l, char c) {
    framexyc(x, y, x + l - 1, y + l - 1, c);
}

//add your procedures here
public static final int MAX_SQUARES = 200;
final int SPEED3 = 1;
final int SPEED2 = 2;
final int SPEED1 = 5;


public static class TGame {
    public TSquare[] squares = new TSquare[MAX_SQUARES];
    TBoard board;
    public int squaresCount;
    public int loopCount;
    public TPlayer[] players = new TPlayer[4];
    public int playerCount;
    public int activeSquares;
    public int time;
    public TPlayer tempPlayer = new TPlayer();
    boolean winnerGameFlag;
    boolean timeEndFlag;
    boolean interruptionFlag;
}

public static class TBoard{
    public int x;
    public int y;
    public int sizeX;
    public int sizeY;
}

public static class TPlayer {
    TSquare square;
    public int score;
    public int hz;
    int control;
}


public static class TSquare {
    public int color;
    public int x;
    public int size;
    public int y;
    public int direction;
    int dx;
    int dy;
    int speed;
    int loopCount;
    boolean isActive;
}

public void setGame(TGame game, TBoard board, TSquare... squares) {
    game.board = board;
    game.squaresCount = 0;
    game.loopCount = 0;
    game.time = 60;
    game.playerCount = 0;
    game.timeEndFlag = false;
    game.winnerGameFlag = false;
    game.interruptionFlag = false;

    for (TSquare s : squares) {
        addSquare(game, s);
    }
}

public static TPlayer createPlayer(int color, int x, int y, int hz) {
    TPlayer player = new TPlayer();
    setPlayer(player, color, x, y, hz);
    return player;
}

public static void setPlayer(TPlayer player, int color, int x, int y, int hz) {
    player.square = new TSquare();
    player.square.size = 3;
    player.square.color = color;
    player.square.dx = 0;
    player.square.dy = 0;
    player.score = 0;
    player.square.x = x;
    player.square.y = y;
    player.hz = hz;
    player.control = 0;
}

public void setSquare(TGame game, TSquare s, int speed) {
    s.color = (int) (Math.random() * 11 + 1);
    s.size = (int) (Math.random() * 2 + 1);
    s.x = (int) (Math.random() * game.board.sizeX - s.size - 1) + game.board.x + 1;
    s.y = (int) (Math.random() * game.board.sizeY -s.size - 1) + game.board.y + 1 ;
    s.dy = randFromInterval(-1, 1);
    s.speed = speed;
    s.isActive = true;
    if (s.dy == 0) {
        int[] temp = {-1, 1};
        s.dx = temp[(int) (Math.random() * 2)];
    } else {
        s.dx = randFromInterval(-1, 1);
    }
    s.loopCount = 0;
}

public static int randFromInterval(int l, int r) {
    return (int) (Math.random() * (r + 1 + Math.abs(l))) + l;
}

public void addSquare(TGame game, TSquare s) {
    game.squares[game.squaresCount] = s;
    game.squaresCount++;
    game.activeSquares++;
}

public TSquare createSquare(TGame game, int speed) {
    TSquare s = new TSquare();
    setSquare(game, s, speed);
    return s;
}

public TBoard createBoard(int x, int y, int sizeX, int sizeY){
    TBoard board = new TBoard();
    setBoard(board, x, y, sizeX, sizeY);
    return board;
}

public void setBoard(TBoard board, int x, int y, int sizeX, int sizeY){
    board.x = x;
    board.y = y;
    board.sizeX = sizeX;
    board.sizeY = sizeY;
}

void updatePlayerPos(TPlayer player){
    player.square.x += player.square.dx;
    player.square.y += player.square.dy;
}

boolean squareBoardCollision(TGame game, TSquare square){
    int leftX = square.x + square.dx;
    int rightX = leftX + square.size - 1;
    int upY = square.y + square.dy;
    int downY = upY + square.size - 1;
    return ((leftX == game.board.x || rightX == game.board.x + game.board.sizeX - 1) ||
            (upY == game.board.y || downY == game.board.y + game.board.sizeY - 1));
}

boolean squaresCollision(TSquare s1, TSquare s2){
    int s1Left = s1.x + s1.dx;
    int s2Left = s2.x + s2.dx;
    int s1Right = s1Left + s1.size - 1;
    int s2Right = s2Left + s2.size - 1;
    int s1Up = s1.y + s1.dy;
    int s2Up = s2.y + s2.dy;
    int s1Down = s1Up + s1.size - 1;
    int s2Down = s2Up + s2.size - 1;

    return ((s1Right >= s2Left &&
            s1Left <= s2Right) &&
            (s1Down >= s2Up &&
            s1Up <= s2Down));
}


public  boolean hitAnyPlayer(TPlayer p, TGame game) {
    for (int i = 0; i < game.playerCount; i++) {
        if (squaresCollision(p.square, game.players[i].square) && p != game.players[i]) return true;
    }
    return false;
}


public void drawPlayer(TPlayer player) {
    int prevX = player.square.x - player.square.dx;
    int prevY = player.square.y - player.square.dy;
    setfgcolor(player.square.color);
    framexyc(prevX, prevY, prevX + player.square.size - 1, prevY + player.square.size - 1, ' ');
    framexyc(player.square.x, player.square.y, player.square.x + player.square.size - 1, player.square.y + player.square.size - 1, '#');
}

void drawPlayers(TGame game){
    for(int i = 0; i < game.playerCount; i++){
        drawPlayer(game.players[i]);
    }
}

void drawSquare(TSquare square){
    setfgcolor(square.color);
    framexyc(square.x, square.y, square.x + square.size - 1, square.y + square.size - 1, '#');
}

public void drawBoard(TGame game) {
    framexyc(game.board.x, game.board.y, game.board.x + game.board.sizeX - 1, game.board.y + game.board.sizeY - 1, '#');
    for (int i = 0; i < game.squaresCount; i++) {
        drawSquare(game.squares[i]);
    }
    for (int j = 0; j < game.playerCount; j++){
        drawPlayer(game.players[j]);
    }
}

public void randomDirection(TGame game, TSquare s) {
    while ((s.dy == 0 & s.dx == 0) || squareBoardCollision(game, s)) {
        s.dx = randFromInterval(-1, 1);
        s.dy = randFromInterval(-1, 1);
    }
}

public void randomDirection(TGame game, TSquare s1, TSquare s2) {
    while ((s1.dy == 0 & s1.dx == 0) || squareBoardCollision(game, s1) || squareBoardCollision(game, s2) || squaresCollision(s1, s2)) {
        s1.dx = randFromInterval(-1, 1);
        s1.dy = randFromInterval(-1, 1);
        s2.dx = randFromInterval(-1, 1);
        s2.dy = randFromInterval(-1, 1);
    }
}

public void updateSquaresXY(TGame game) {
    TSquare[] s = game.squares;

    for (int i = 0; i < game.squaresCount; i++) {
        if (s[i].isActive) {
            if (game.loopCount % s[i].speed == 0) {
                if (squareBoardCollision(game, s[i]) || ((s[i].dx == 0 && s[i].dy == 0))) {
                    randomDirection(game, s[i]);
                }

                for (int j = 0; j < game.squaresCount; j++) {
                    if (squaresCollision(s[i], s[j]) && i != j && s[i].isActive && s[j].isActive) {
                        randomDirection(game, s[i], s[j]);
                        randomDirection(game, s[j], s[i]);
                    }
                }
                draw_frame_c(s[i].x, s[i].y, s[i].size, ' ');


                if (s[i].isActive) {
                    s[i].x += s[i].dx;
                    s[i].y += s[i].dy;
                    setfgcolor(s[i].color);
                    draw_frame_c(s[i].x, s[i].y, s[i].size, '#');
                }
            }
        }
    }
}

TSquare findClosestSquare(TGame game, TPlayer player){
    TSquare closestSquare = game.squares[0];
    int smallestDist = game.board.sizeX + game.board.sizeY + 10000;
    int distX, distY;
    int totalDist;
    for(int i = 0; i < game.squaresCount; i++){
        distX = player.square.x - game.squares[i].x;
        distY = player.square.y - game.squares[i].y;
        totalDist = Math.abs(distX) + Math.abs(distY);
        if(totalDist < smallestDist && game.squares[i].isActive){
            smallestDist = totalDist;
            closestSquare = game.squares[i];
        }
    }
    return closestSquare;
}

void chooseChaseDirection(TPlayer player, TSquare s){
    int distX = s.x - player.square.x;
    int distY = s.y - player.square.y;
    player.square.dx = 1;
    player.square.dy = 1;

    if(Math.abs(distX) > Math.abs(distY)) player.square.dy = 0;
    else player.square.dx = 0;

    if(distX < 0) player.square.dx *= -1;
    if(distY < 0 ) player.square.dy *= -1;

}

void manhattanBOT(TGame game, TPlayer player){
    TSquare targetSquare = findClosestSquare(game,player);
    chooseChaseDirection(player,targetSquare);
    if(!hitAnyPlayer(player, game) && !squareBoardCollision(game, player.square))updatePlayerPos(player);
}

public void updatePlayerPoints(TGame game) {
    TSquare[] s = game.squares;
    for (int i = 0; i < game.squaresCount; i++) {
        if (s[i].isActive) {
            for (int j = 0; j < game.playerCount; j++) {
                if (squaresCollision(game.players[j].square, s[i])) {
                    s[i].isActive = false;
                    game.activeSquares--;
                    draw_frame_c(s[i].x, s[i].y, s[i].size, ' ');
                    sound(game.players[j].hz, 20);
                    if (s[i].size == 1) game.players[j].score += 4;
                    if (s[i].size == 2) game.players[j].score += 1;
                }
            }
        }
    }
}

public static void setTimer(TGame game) {
    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            game.time--;
        }
    };

    timer.schedule(timerTask, 1000, 1000);
}

public static void addPlayer(TGame game, TPlayer player) {
    game.players[game.playerCount] = player;
    game.playerCount++;
}

TPlayer interpretKey(TGame game, String pressedKey){
    TPlayer movedPlayer;
    TPlayer player1 = game.players[0];
    TPlayer player2 = game.players[1];
    movedPlayer = game.tempPlayer;
    player1.square.dy = 0;
    player1.square.dx = 0;
    player2.square.dx = 0;
    player2.square.dy = 0;

    switch (pressedKey){
        case "w":
            player1.square.dx = 0;
            player1.square.dy = -1;
            movedPlayer = player1;
            break;
        case "s":
            player1.square.dx = 0;
            player1.square.dy = 1;
            movedPlayer = player1;
            break;
        case "a":
            player1.square.dx = -1;
            player1.square.dy = 0;
            movedPlayer = player1;
            break;
        case "d":
            player1.square.dx = 1;
            player1.square.dy = 0;
            movedPlayer = player1;
            break;
        case "i":
            player2.square.dx = 0;
            player2.square.dy = -1;
            movedPlayer = player2;
            break;
        case "k":
            player2.square.dx = 0;
            player2.square.dy = 1;
            movedPlayer = player2;
            break;
        case "j":
            player2.square.dx = -1;
            player2.square.dy = 0;
            movedPlayer = player2;
            break;
        case "l":
            player2.square.dx = 1;
            player2.square.dy = 0;
            movedPlayer = player2;
            break;
        case "q":
            game.interruptionFlag = true;
            movedPlayer = player2;
            player2.square.dx = 0;
            player2.square.dy = 0;
            break;
        case "":
            movedPlayer.square.dx = 0;
            movedPlayer.square.dy = 0;
            break;
    }
    return movedPlayer;
}

public void drawSquares(TGame game){
    for (int i = 0; i < game.squaresCount; i++) {
        if (game.squares[i].isActive) {
            setfgcolor(game.squares[i].color);
            draw_frame_c(game.squares[i].x, game.squares[i].y, game.squares[i].size, '#');
        }
    }
}

public static void printScore(TGame game) {
    for (int i = 0; i < game.playerCount; i++) {
        gotoxy(game.board.x + game.board.sizeX + 1, game.board.y + i);
        setfgcolor(game.players[i].square.color);
        if (game.players[i].score < 10) System.out.print("Player" + i + " SCORE: 00" + game.players[i].score);
        else if (game.players[i].score < 100) System.out.print(" SCORE: 0" + game.players[i].score);
        else System.out.print("Player" + i + 1 + " SCORE: " + game.players[i].score);
    }
}

public static void printTime(TGame game) {
    gotoxy((int) ((game.board.x + game.board.sizeX) / 2) - 5 - 8, game.board.y);
    setfgcolor(red);
    if (game.time >= 10) {
        println("TIME: " + game.time);
    } else {
        println("TIME: 0" + game.time);
    }
}

void showResults(TGame game){
    clrscr();
    setfgcolor(white);
    if(game.interruptionFlag){
        framexyc(50,14,69, 16, '*');
        gotoxy(51,15);
        setfgcolor(red);
        print(" GAME INTERRUPTED ");
    }
    else if(game.timeEndFlag){
        framexyc(50,14,63, 18, '*');
        gotoxy(51,15);
        setfgcolor(grey);
        print(" TIME ENDED ");
    }
}

public void updatePlayerXY(TGame game, TPlayer movedPlayer){
    if (!hitAnyPlayer(movedPlayer, game)) {
        if (!squareBoardCollision(game, movedPlayer.square)) {
            updatePlayerPos(movedPlayer);
        }
    } else {
        sound(400, 20);
    }
}

void checkPlayersControl(TGame game){
    for(int i = 0; i < game.playerCount; i++){
        if (game.players[i].control == 1) manhattanBOT(game, game.players[i]);
    }
}

void CONTROLLER(TGame game){
    String key = "";
    if (keypressed()) key += readkeystr();
    MODEL(game, key);
}

void MODEL(TGame game, String pressedKey){
    TPlayer movedPlayer = interpretKey(game, pressedKey);
    if(movedPlayer.control == 0) updatePlayerXY(game, movedPlayer);
    checkPlayersControl(game);
    updatePlayerPoints(game);
    updateSquaresXY(game);
    if(game.activeSquares == 0) game.winnerGameFlag = true;
    if(game.time == 0) game.timeEndFlag = true;
    game.loopCount = (game.loopCount + 1) % 10;
    VIEW(game, movedPlayer);
}

public void VIEW(TGame game, TPlayer movedPlayer) {

    drawPlayers(game);
    drawSquares(game);
    printScore(game);
    printTime(game);
}


void startGame(TGame game){
    cursor_hide();
    setTimer(game);
    drawBoard(game);


    while (!game.timeEndFlag && !game.winnerGameFlag && !game.interruptionFlag) CONTROLLER(game);

    showResults(game);

    readkey();
    gotoxy(200,200);
}

void main(){
    TGame game = createElements(TGame.class);
    setGame(game, createBoard(1,1,120,30));
    addSquare(game, createSquare(game, 2));
    addSquare(game, createSquare(game, 2));
    addSquare(game, createSquare(game, 2));
    addSquare(game, createSquare(game, 2));
    addSquare(game, createSquare(game, 2));
    addSquare(game, createSquare(game, 2));
    addPlayer(game,createPlayer(blue, game.board.x + 1, game.board.y + game.board.sizeY - 4, 400));
    addPlayer(game,createPlayer(red, game.board.x + game.board.sizeX - 4, game.board.y + game.board.sizeY - 4, 400));
    game.players[1].control = 1;

    startGame(game);

}