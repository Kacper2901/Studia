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
    public TPlayer player;
    public int activeSquares;
}

public static class TGame{
    public int time;
    public TBoards board;
}

public static class TPlayer{
    public int x;
    public int y;
    public int size;
    public int dx;
    public int dy;
    public int color;
    public int score;
}

public static TPlayer createPlayer(){
    TPlayer player = new TPlayer();
    setPlayer(player);
    return player;
}

public static void setPlayer(TPlayer player){
    player.size = 3;
    player.color = green;
    player.dx = 0;
    player.dy = 0;
    player.score = 0;
}

public static void addPlayer(TBoards board, TPlayer player){
    player.x = (int)((board.x + board.hor_length)/2);
    player.y = (int)((board.y + board.vert_length)/2);
    board.player = player;
}

public static TGame createGame(TBoards board){
    TGame game = new TGame();
    setGame(game, board);
    return game;
}

public static void setGame(TGame game, TBoards board){
    game.board = board;
    game.time = 60;
}

public static void setBoard(TBoards b, int x, int y, TSquares... squares) {
    b.x = x;
    b.y = y;
    b.squaresCount = 0;
    b.loopCount = 0;
    for(TSquares s: squares){
        addSquare(b,s);
    }
    b.activeSquares = b.loopCount;
}

public static void updateSquares(TBoards b){
    TSquares[] s = b.squares;

    for (int i = 0; i < b.squaresCount; i++) {
        if (s[i].isActive) {
            if(playerSquareCollision(b.player, s[i])){
                s[i].isActive = false;
                draw_frame_c(s[i].x, s[i].y, s[i].size, ' ');
                setfgcolor(b.player.color);
                draw_frame_c(b.player.x, b.player.y, b.player.size,'#');
                b.activeSquares --;
                if(s[i].size == 1) b.player.score += 4;
                if(s[i].size == 2) b.player.score += 1;
            }
            if (b.loopCount % s[i].speed == 0) {
                draw_frame_c(s[i].x, s[i].y, s[i].size, ' ');
                if (boardCollision(s[i]) || (s[i].dx == 0 && s[i].dy == 0)) {
                    randomDirection(s[i]);
                }

                for (int j = 0; j < b.squaresCount; j++) {
                    if (squaresCollision(s[i], s[j]) && i != j) {
                        randomDirection(s[i], s[j]);
                        randomDirection(s[j], s[i]);
                    }
                }

                if(s[i].isActive){
                    s[i].x += s[i].dx;
                    s[i].y += s[i].dy;
                    setfgcolor(s[i].color);
                    draw_frame_c(s[i].x, s[i].y, s[i].size, '#');
                }
            }
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
    boolean isActive;
}

public static void setSquare(TSquares s, int speed){
    s.color = (int)(Math.random() * 11 + 1);
    s.size = (int)(Math.random()*2 + 1);
    s.x = (int)(Math.random()*(120 -2- s.size) + 2 );
    s.y = (int)(Math.random()*(30 - 2 - s.size) + 2);
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

public static void randomDirection(TSquares s1, TSquares s2 ){
    while ((s1.dy == 0 & s1.dx == 0) || boardCollision(s1)|| boardCollision(s2) || squaresCollision(s1, s2)) {
        s1.dx = randomD(-1, 1);
        s1.dy = randomD(-1, 1);
        s2.dx = randomD(-1,1);
        s2.dy = randomD(-1,1);
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

public static boolean checkRightX(TPlayer p){
    return (p.x + p.dx + p.size - 1 < 120);
}

public static boolean checkLeftX(TPlayer p){
    return (p.x + p.dx > 1);
}

public static boolean checkUpY(TPlayer p){
    return (p.y + p.dy > 1);
}

public static boolean checkDownY(TPlayer p){
    return p.y + p.dy + p.size - 1 < 30;
}

public static void updatePlayer(TPlayer player){
    setfgcolor(player.color);
    framexyc(player.x, player.y, player.x + player.size - 1, player.y + player.size - 1, ' ');
    player.x += player.dx;
    player.y += player.dy;
    framexyc(player.x, player.y, player.x + player.size - 1, player.y + player.size - 1, '#');
}

public static boolean squaresCollision(TSquares s1, TSquares s2){
    return !(s1.x + s1.size + s1.dx - 1 < s2.x + s2.dx ||
            s2.x + s2.size - 1 + s2.dx < s1.x + s1.dx ||
            s1.y + s1.size - 1 + s1.dy < s2.y + s2.dy ||
            s2.y + s2.size - 1 + s2.dy < s1.y + s1.dy);
}

public static boolean boardCollision(TPlayer p) {
    if (checkUpY(p) || checkDownY(p) || checkLeftX(p) || checkRightX(p)) {
        return true;
    }
    return false;
}

public static boolean playerSquareCollision(TPlayer player, TSquares square){
    return (((player.x + player.dx + player.size - 1 >= square.x + square.dx &&
            player.x + player.dx <= square.x + square.dx + square.size - 1) &&
            (player.y + player.dy + player.size - 1 >= square.y + square.dy &&
            player.y + player.dy <= square.y + square.dy + square.size - 1)));
}

public static boolean boardCollision(TSquares s) {
    if (checkUpY(s) || checkDownY(s) || checkLeftX(s) || checkRightX(s)) {
        return true;
    }
    return false;
}

public static void printBoard(TBoards b){
    framexyc(b.x, b.y, b.x + b.hor_length - 1, b.y + b.vert_length - 1, '#');
    updatePlayer(b.player);
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
            if(keystr.equals("w")){
                b.player.dy = -1;
                b.player.dx = 0;
                if(checkUpY(b.player)) updatePlayer(b.player);
            }
            if (keystr.equals("s")){
                b.player.dy = 1;
                b.player.dx = 0;
                if(checkDownY(b.player)) updatePlayer(b.player);
            }
            if(keystr.equals("a")){
                b.player.dy = 0;
                b.player.dx = -1;
                if(checkLeftX(b.player)) updatePlayer(b.player);
            }
            if(keystr.equals("d")){
                b.player.dy = 0;
                b.player.dx = 1;
                if(checkRightX(b.player)) updatePlayer(b.player);
            }
        }



        updateSquares(b);
        delay(20);
    }
}

void main() {

    TBoards board1 = createElements(TBoards.class);
    setBoard(board1, 1, 1);
    addPlayer(board1, createPlayer());
    addSquare(board1, createSquare(SPEED3));
    addSquare(board1, createSquare(SPEED1));
    addSquare(board1, createSquare(SPEED2));
    addSquare(board1, createSquare(SPEED3));


    startProgram(board1);
}