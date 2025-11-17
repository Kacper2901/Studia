
import static java.lang.IO.println;
import static term.term.*;    //including package to be able to use simple print()

public static void draw_frame_c(int x, int y, int l, char c){
    framexyc(x, y, x + l - 1, y + l - 1, c);
}

//add your procedures here
public static final int MAX_SQUARES = 200;
final int SPEED3 = 1;
final int SPEED2 = 2;
final int SPEED1 = 5;


public static class TGame {
    public int x;
    public int y;
    public int hor_length = 120;
    public int vert_length = 30;
    public TSquares[] squares = new TSquares[MAX_SQUARES];
    public int squaresCount;
    public int loopCount;
    public TPlayer[] players = new TPlayer[4];
    public int playerCount;
    public int activeSquares;
    public int time;
}

public static void setGame(TGame game, int x, int y, TSquares... squares) {
    game.x = x;
    game.y = y;
    game.squaresCount = 0;
    game.loopCount = 0;
    game.time = 60;
    game.playerCount = 0;

    for(TSquares s: squares){
        addSquare(game,s);
    }
}

public static boolean boardCollision(TGame game, TSquares s) {
    if (checkUpY(game, s) || checkDownY(game, s) || checkLeftX(game, s) || checkRightX(game, s)) {
        return true;
    }
    return false;
}


public static class TPlayer{
    public int x;
    public int y;
    public int size;
    public int dx;
    public int dy;
    public int color;
    public int score;
    public int hz;
}

public static TPlayer createPlayer(int color, int x, int y, int hz){
    TPlayer player = new TPlayer();
    setPlayer(player, color, x, y, hz);
    return player;
}

public static void setPlayer(TPlayer player, int color, int x, int y, int hz){
    player.size = 3;
    player.color = color;
    player.dx = 0;
    player.dy = 0;
    player.score = 0;
    player.x = x;
    player.y = y;
    player.hz = hz;
}

public static void addPlayer(TGame game, TPlayer player){
    game.players[game.playerCount] = player;
    game.playerCount ++;
}

public static void updatePlayer(TPlayer player){
    setfgcolor(player.color);
    framexyc(player.x, player.y, player.x + player.size - 1, player.y + player.size - 1, ' ');
    player.x += player.dx;
    player.y += player.dy;
}

public static void printPlayer(TGame game){
    for(int i = 0; i < game.playerCount; i++){
        TPlayer player = game.players[i];
        setfgcolor(player.color);
        framexyc(player.x, player.y, player.x + player.size - 1, player.y + player.size - 1, '#');
    }
}

public static boolean playerSquareCollision(TPlayer player, TSquares square){
    return (((player.x + player.dx + player.size - 1 >= square.x + square.dx &&
            player.x + player.dx <= square.x + square.dx + square.size - 1) &&
            (player.y + player.dy + player.size - 1 >= square.y + square.dy &&
                    player.y + player.dy <= square.y + square.dy + square.size - 1)));
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
    boolean isActive;
}

public static void setSquare(TGame game, TSquares s, int speed){
    s.color = (int)(Math.random() * 11 + 1);
    s.size = (int)(Math.random()*2 + 1);
    s.x = (int)(Math.random()*117) + game.x + 1;
    s.y = (int)(Math.random()*27) + game.y + 1;
    s.dy = randomD(-1,1);
    s.speed = speed;
    s.isActive = true;
    if(s.dy == 0) {
        int[] temp = {-1,1};
        s.dx = temp[(int)(Math.random()*2)];
    }
    else{
        s.dx = randomD(-1,1);
    }
    s.loopCount = 0;
}

public static TSquares createSquare(TGame game, int speed){
    TSquares s = new TSquares();
    setSquare(game, s, speed);
    return s;
}

public static void addSquare(TGame game, TSquares s){
    game.squares[game.squaresCount] = s;
    game.squaresCount ++;
    game.activeSquares ++;
}

public static void updateSquares(TGame game){
    TSquares[] s = game.squares;

    for (int i = 0; i < game.squaresCount; i++) {
        if (s[i].isActive) {
            if (game.loopCount % s[i].speed == 0) {
                if (boardCollision(game, s[i]) || ((s[i].dx == 0 && s[i].dy == 0))) {
                    randomDirection(game, s[i]);
                }

                for (int j = 0; j < game.squaresCount; j++) {
                    if (squaresCollision(s[i], s[j]) && i != j && s[i].isActive && s[j].isActive) {
                        randomDirection(game, s[i], s[j]);
                        randomDirection(game, s[j], s[i]);
                    }
                }
                draw_frame_c(s[i].x, s[i].y, s[i].size, ' ');


                if(s[i].isActive){
                    s[i].x += s[i].dx;
                    s[i].y += s[i].dy;
                    setfgcolor(s[i].color);
                    draw_frame_c(s[i].x, s[i].y, s[i].size, '#');
                }
            }
        }
    }
}

public static void updatePlayerPoints(TGame game){
    TSquares[] s = game.squares;

    for (int i = 0; i < game.squaresCount; i++) {
        if (s[i].isActive) {
            for (int j = 0; j < game.playerCount; j++){
                if(playerSquareCollision(game.players[j], s[i])){
                    s[i].isActive = false;
                    draw_frame_c(s[i].x, s[i].y, s[i].size, ' ');
                    game.activeSquares --;
                    sound(game.players[j].hz, 20);
                    if(s[i].size == 1) game.players[j].score += 4;
                    if(s[i].size == 2) game.players[j].score += 1;
                }
            }
        }
    }
}

public static boolean squaresCollision(TSquares s1, TSquares s2){
    return !(s1.x + s1.size + s1.dx - 1 < s2.x + s2.dx ||
            s2.x + s2.size - 1 + s2.dx < s1.x + s1.dx ||
            s1.y + s1.size - 1 + s1.dy < s2.y + s2.dy ||
            s2.y + s2.size - 1 + s2.dy < s1.y + s1.dy);
}


public static int randomD(int l, int r){
    return (int)(Math.random()*(r + 1 + Math.abs(l))) + l;
}

public static int randomValues(int length, int[] arr){
    return arr[(int)(Math.random()*length)];
}

public static void randomDirection(TGame game, TSquares s){
    while ((s.dy == 0 & s.dx == 0) || boardCollision(game, s)) {
        s.dx = randomD(-1, 1);
        s.dy = randomD(-1, 1);
    }
}

public static void randomDirection(TGame game, TSquares s1, TSquares s2 ){
    while ((s1.dy == 0 & s1.dx == 0) || boardCollision(game, s1)|| boardCollision(game, s2) || squaresCollision(s1, s2)) {
        s1.dx = randomD(-1, 1);
        s1.dy = randomD(-1, 1);
        s2.dx = randomD(-1,1);
        s2.dy = randomD(-1,1);
    }
}

public static boolean hitPlayers(TPlayer p1, TPlayer p2){
    return (p1.x + p1.size + p1. dx - 1 >= p2.x && p1.x + p1.dx <= p2.x + p2.size - 1 && p1.y + p1.dy + p1.size - 1 >= p2.y && p1.y + p1.dy <= p2.y + p2.size - 1); //from right
}

public static boolean hitAnyPlayer(TPlayer p, TGame game){
    for (int i = 0; i < game.playerCount; i++){
        if(hitPlayers(p, game.players[i]) && p != game.players[i]) return true;
    }
    return false;
}

public static boolean checkRightX(TGame game,TSquares s){
    return (s.x + s.dx + s.size - 1 >= game.x - 1 + 120);
}

public static boolean checkLeftX(TGame game, TSquares s){
    return (s.x + s.dx <= game.x);
}

public static boolean checkUpY(TGame game, TSquares s){
    return (s.y + s.dy <= game.y);
}

public static boolean checkDownY(TGame game, TSquares s){
    return s.y + s.dy + s.size - 1 >= 30 + game.y - 1;
}

public static boolean pHitRight(TGame game, TPlayer p){
    return (p.x + p.dx + p.size - 1  >= 120 + game.x - 1);
}

public static boolean pHitLeft(TGame game, TPlayer p){
    return (p.x + p.dx <= game.x);
}

public static boolean pHitUp(TGame game, TPlayer p){
    return (p.y + p.dy <= game.y);
}

public static boolean pHitDown(TGame game, TPlayer p){
    return p.y + p.dy + p.size - 1 >= 30 + game.y - 1;
}

public static boolean MODEL(TGame game, int x_coor, int y_coor,TPlayer player, boolean flag){
    if(!flag) return false;
    player.dy = y_coor;
    player.dx = x_coor;

    if (!hitAnyPlayer(player, game)) {
        if (!pHitRight(game, player) && !pHitLeft(game, player) && !pHitUp(game, player) && !pHitDown(game, player)) {
            updatePlayer(player);
        }
    }
    else {
        sound(400, 20);
    }

    player.dx = 0;
    player.dy = 0;

    updatePlayerPoints(game);
    updateSquares(game);
    game.loopCount = (game.loopCount + 1) % 10;
    VIEW(game);
    return true;
}

public static void VIEW(TGame game){
    TSquares[] s = game.squares;

    for (int i = 0; i < game.squaresCount; i++) {
        if (s[i].isActive) {
            setfgcolor(s[i].color);
            draw_frame_c(s[i].x, s[i].y, s[i].size, '#');
        }
    }
    printPlayer(game);
    printScore(game);
    printTime(game);
}

public static boolean CONTROLLER(TGame game) {
    int x_corr = 0;
    int y_corr = 0;
    TPlayer player = game.players[0];
    boolean flag = true;
    String key = "";
    if(!keypressed()) {
        y_corr = 0;
        x_corr = 0;
    }
    else{
        key = readkeystr();
        if(key.equals("q")) flag = false;
        else if(key.equals("w")){
            y_corr = -1;
            x_corr = 0;
            player = game.players[0];
        }
        if (key.equals("s")){
            y_corr = 1;
            x_corr = 0;
            player = game.players[0];
        }
        if(key.equals("a")){
            y_corr = 0;
            x_corr = -1;
            player = game.players[0];
        }
        if(key.equals("d")) {
            y_corr = 0;
            x_corr = 1;
            player = game.players[0];
        }
        if(key.equals("i")){
            y_corr = -1;
            x_corr = 0;
            player = game.players[1];
        }
        if (key.equals("k")){
            y_corr = 1;
            x_corr = 0;
            player = game.players[1];
        }
        if(key.equals("j")){
            y_corr = 0;
            x_corr = -1;
            player = game.players[1];
        }
        if(key.equals("l")) {
            y_corr = 0;
            x_corr = 1;
            player = game.players[1];
        }

    }
    return MODEL(game,x_corr, y_corr, player, flag);

}

public static void setTimer(TGame game){
    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            game.time --;
        }
    };

    timer.schedule(timerTask, 1000,1000);
}

public static void printBoard(TGame game){
    framexyc(game.x, game.y, game.x + game.hor_length - 1, game.y + game.vert_length - 1, '#');
    for(int i = 0; i < game.playerCount; i++){
        updatePlayer(game.players[i]);
    }
}

public static void printTime(TGame game){
    gotoxy((int)((game.x + game.hor_length)/2) - 5 - 8, game.y);
    setfgcolor(red);
    if(game.time >= 10){
        println("TIME: " + game.time);
    }
    else{
        println("TIME: 0" + game.time);
    }
}

public static void printScore(TGame game){
    for (int i = 0; i < game.playerCount; i++){
        gotoxy(game.x + 121, game.y + i);
        setfgcolor(game.players[i].color);
        if (game.players[i].score < 10) System.out.print("Player" + i + " SCORE: 00" + game.players[i].score);
        else if (game.players[i].score < 100) System.out.print(" SCORE: 0" + game.players[i].score);
        else System.out.print("Player" + i + 1 + " SCORE: " + game.players[i].score);
    }
}


public static void startProgram(TGame game){
    cursor_hide();
    clrscr();
    setfgcolor(white);
    printBoard(game);
    printScore(game);
    setTimer(game);

    while (true){

        if(game.time <= 0 && game.activeSquares > 0){
            clrscr();
            setfgcolor(white);
            framexyc(50, 20, 62, 22, '*');
            gotoxy(51,21);
            setfgcolor(red);
            System.out.print(" GAME OVER ");
            delay(4000);
            clrscr();
            break;
        }
        if(game.activeSquares == 0) {
            clrscr();
            int winnerPoints = 0;
            String winner = "";
            for(int i = 0; i < game.playerCount; i++){
                if(game.players[i].score > winnerPoints) winner = "Player" + i + 1;
                winnerPoints = game.players[i].score;
            }
            setfgcolor(white);
            framexyc(50, 20, 70, 22, '*');
            gotoxy(54,21);
            setfgcolor(yellow);
            System.out.print(winner + " WON!");

            delay(4000);
            clrscr();
            break;
        }

        if(!CONTROLLER(game)){
            clrscr();
            setfgcolor(white);
            framexyc(44, 20, 63, 22, '*');
            gotoxy(45,21);
            setfgcolor(red);
            System.out.print(" GAME INTERRUPTED ");
            delay(4000);
            clrscr();
            break;
        }

        delay(20);
    }
}

void main() {

    TGame game = createElements(TGame.class);
    setGame(game, 5,5 );
    addPlayer(game, createPlayer(blue, game.x + 1, game.y + 26, 600));
    addPlayer(game, createPlayer(red, game.x + 116, game.y + 26, 800));
    addSquare(game, createSquare(game,SPEED3));
    addSquare(game, createSquare(game,SPEED2));
    addSquare(game, createSquare(game,SPEED2));
    addSquare(game, createSquare(game,SPEED3));
    addSquare(game, createSquare(game,SPEED1));
    addSquare(game, createSquare(game,SPEED1));



    startProgram(game);
}
