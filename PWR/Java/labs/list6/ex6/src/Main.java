
import static java.lang.IO.print;
import static java.lang.IO.println;
import static term.term.*;    //including package to be able to use simple print()
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;


public static void draw_frame_c(int x, int y, int l, char c) {
    framexyc(x, y, x + l - 1, y + l - 1, c);
}

//add your procedures here
public static final int MAX_SQUARES = 200;
final double SLOWEST = 1;
final double SLOW = 1.2;
final double MEDIUM = 1.5;
final double FAST = 2.0;
final double FASTEST = 3.0;


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
    int targetSquareIdx;
    double pastX;
    double pastY;
}


public static class TSquare {
    public int color;
    public double x;
    public int size;
    public double y;
    public int direction;
    int dx;
    int dy;
    double speed;
    int loopCount;
    boolean isActive;
    double gravity;

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

public TPlayer createPlayer(int color, int x, int y, int hz) {
    TPlayer player = new TPlayer();
    setPlayer(player, color, x, y, hz);
    return player;
}

public void setPlayer(TPlayer player, int color, int x, int y, int hz) {
    player.square = new TSquare();
    player.square.size = 3;
    player.square.color = color;
    player.square.dx = 0;
    player.square.dy = 0;
    player.score = 0;
    player.square.x = x;
    player.square.y = y;
    player.square.speed = 1;
    player.hz = hz;
    player.control = 0;
    player.targetSquareIdx = 0;
    player.pastX = player.square.x;
    player.pastY = player.square.y;
}

public void setSquare(TGame game, TSquare s, double speed) {
    s.color = (int) (Math.random() * 11 + 1);
    s.size = (int) (Math.random() * 2 + 1);
    s.x = (int) (Math.random() * (game.board.sizeX - 3)) + game.board.x + 1;
    s.y = (int) (Math.random() * (game.board.sizeY - 3)) + game.board.y + 1;
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
    s.gravity = -0.5;
}

public static int randFromInterval(int l, int r) {
    return (int) (Math.random() * (r + 1 + Math.abs(l))) + l;
}

public void addSquare(TGame game, TSquare s) {
    game.squares[game.squaresCount] = s;
    game.squaresCount++;
    game.activeSquares++;
}

public TSquare createSquare(TGame game, double speed) {
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
    player.pastX = player.square.x;
    player.pastY = player.square.y;
    player.square.x += player.square.dx;
    player.square.y += player.square.dy;
}

boolean squareBoardCollision(TGame game, TSquare square){
    int leftX = (int)(square.x + square.speed * square.dx);
    int rightX = leftX + square.size - 1;
    int upY = (int)(square.y + square.speed * square.dy);
    int downY = upY + square.size - 1;
    return ((leftX <= game.board.x || rightX >= game.board.x + game.board.sizeX - 1) ||
            (upY <= game.board.y || downY >= game.board.y + game.board.sizeY - 1));
}

boolean squaresCollision(TSquare s1, TSquare s2){
    int s1Left = (int)(s1.x + s1.speed*s1.dx);
    int s2Left = (int)(s2.x + s2.speed*s2.dx);
    int s1Right = s1Left + s1.size - 1;
    int s2Right = s2Left + s2.size - 1;
    int s1Up = (int)(s1.y + s1.dy*s1.speed);
    int s2Up = (int)(s2.y + s2.dy*s2.speed);
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
    int prevX = (int)player.pastX;
    int prevY = (int)player.pastY;
    setfgcolor(player.square.color);
    framexyc(prevX, prevY, prevX + player.square.size - 1, prevY + player.square.size - 1, ' ');
    framexyc((int)player.square.x, (int)player.square.y, (int)player.square.x + player.square.size - 1, (int)player.square.y + player.square.size - 1, '#');
}

void drawPlayers(TGame game){
    for(int i = 0; i < game.playerCount; i++){
        drawPlayer(game.players[i]);
    }
}

void drawSquare(TSquare square){
    setfgcolor(square.color);
    framexyc((int)square.x, (int)square.y, (int)square.x + square.size - 1, (int)square.y + square.size - 1, '#');
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
    while ((s.dy == 0 && s.dx == 0) || squareBoardCollision(game, s)) {
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
                    if (squareBoardCollision(game, s[i]) || ((s[i].dx == 0 && s[i].dy == 0))) {
                        randomDirection(game, s[i]);
                    }

                    for (int j = 0; j < game.squaresCount; j++) {
                        if (squaresCollision(s[i], s[j]) && i != j && s[i].isActive && s[j].isActive) {
                            randomDirection(game, s[i], s[j]);
                            randomDirection(game, s[j], s[i]);
                        }
                    }
                    draw_frame_c((int)s[i].x, (int)s[i].y, s[i].size, ' ');


                    if (s[i].isActive) {
                        s[i].x += s[i].speed*s[i].dx;
                        s[i].y += s[i].speed*s[i].dy;
                        setfgcolor(s[i].color);
                        draw_frame_c((int)s[i].x, (int)s[i].y, s[i].size, '#');
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
        distX = (int)player.square.x - (int)game.squares[i].x;
        distY = (int)player.square.y - (int)game.squares[i].y;
        totalDist = Math.abs(distX) + Math.abs(distY);
        if(totalDist < smallestDist && game.squares[i].isActive){
            smallestDist = totalDist;
            closestSquare = game.squares[i];
        }
    }
    return closestSquare;
}

void chooseChaseDirection(TPlayer player, TSquare s){
    int distX = (int)s.x - (int)player.square.x;
    int distY = (int)s.y - (int)player.square.y;
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

int chooseActiveSquare(TGame game){
    int randIdx = randFromInterval(0,game.squaresCount - 1);
    TSquare square = game.squares[randIdx];
    while (!square.isActive){
        randIdx = randFromInterval(0,game.squaresCount - 1);
        gotoxy(130,30);
        print(randIdx);
        square = game.squares[randIdx];
    }

    return randIdx;
}

void targetBOT(TGame game, TPlayer player){
    while(!game.squares[player.targetSquareIdx].isActive) player.targetSquareIdx = chooseActiveSquare(game);
    chooseChaseDirection(player, game.squares[player.targetSquareIdx]);
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
                    draw_frame_c((int)s[i].x, (int)s[i].y, s[i].size, ' ');
                    sound(game.players[j].hz, 5);
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
            draw_frame_c((int)game.squares[i].x, (int)game.squares[i].y, game.squares[i].size, '#');
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
    else if(game.winnerGameFlag){
        String winner;
        if(game.players[0].score > game.players[1].score)  winner = "Player1";
        else winner = "Player2";
        framexyc(50,14,63, 16, '*');
        gotoxy(51,15);
        setfgcolor(grey);
        print(winner + " WON ");
    }
}

public void useConfigFile(TPlayer player1, TPlayer player2){
    boolean player1Initialized = false;
    boolean player2Initialized = false;
    String namePlayer1 = "Player1";
    String namePlayer2 = "Player2";
    File conf = new File("conf.cfg");
    try(Scanner confRead = new Scanner(conf)){
        while(confRead.hasNextLine()){
            String line = confRead.nextLine();
            line = line.trim();
            if(line.contains(namePlayer1)){
                char last = line.charAt(line.length() - 1);
                if(last == '1') player1.control = 1;
                else if(last == '2') player1.control = 2;
                else player1.control = 0;
                player1Initialized = true;
            }
            if(line.contains(namePlayer2)){
                char last = line.charAt(line.length() - 1);
                if(last == '1') player2.control = 1;
                else if(last == '2') player2.control = 2;
                else player2.control = 0;
                player2Initialized = true;
            }
        }
        if(!player1Initialized) player1.control = 0;
        if(!player2Initialized) player2.control = 0;
    }
    catch (FileNotFoundException e){
        try {
            player1.control = 0;
            player2.control = 0;
            conf.createNewFile();
            FileWriter confWriter = new FileWriter(conf);
            confWriter.write("Player1: 0\n");
            confWriter.write("Player2: 0\n");
            confWriter.close();
        }
        catch (IOException exeption){
            println("error while createing file");
        }
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
        else if (game.players[i].control == 2) targetBOT(game, game.players[i]);
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
    useConfigFile(game.players[0], game.players[1]);



    while (!game.timeEndFlag && !game.winnerGameFlag && !game.interruptionFlag) CONTROLLER(game);
    showResults(game);

    readkey();
    gotoxy(200,200);
}

void main(){
    TGame game = createElements(TGame.class);
    setGame(game, createBoard(1,1,120,30));
    for(int i = 0; i < 5; i++){
        addSquare(game, createSquare(game, SLOW));
        addSquare(game, createSquare(game, FAST));
        addSquare(game, createSquare(game, SLOWEST));
    }

    addPlayer(game,createPlayer(blue, game.board.x + 1, game.board.y + game.board.sizeY - 4, 400));
    addPlayer(game,createPlayer(red, game.board.x + game.board.sizeX - 4, game.board.y + game.board.sizeY - 4, 400));
//    game.players[1].control = 2;

    startGame(game);

}