
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

public static void setGame(TGame game, TBoard board, TSquare... squares) {
    game.board = board;
    game.squaresCount = 0;
    game.loopCount = 0;
    game.time = 60;
    game.playerCount = 0;

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
    player.square.size = 3;
    player.square.color = color;
    player.square.dx = 0;
    player.square.dy = 0;
    player.score = 0;
    player.square.x = x;
    player.square.y = y;
    player.hz = hz;
}

public static void setSquare(TGame game, TSquare s, int speed) {
    s.color = (int) (Math.random() * 11 + 1);
    s.size = (int) (Math.random() * 2 + 1);
    s.x = (int) (Math.random() * 117) + game.board.x + 1;
    s.y = (int) (Math.random() * 27) + game.board.y + 1;
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

public static void addSquare(TGame game, TSquare s) {
    game.squares[game.squaresCount] = s;
    game.squaresCount++;
    game.activeSquares++;
}

public static TSquare createSquare(TGame game, int speed) {
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

boolean squareBoardCollision(TGame game, TSquare square){
    int leftX = square.x + square.dx;
    int rightX = leftX + square.size - 1;
    int upY = square.y + square.dy;
    int downY = upY + square.size - 1;
    return (leftX <= game.board.x || rightX >= game.board.x + game.board.sizeX ||
            upY <= game.board.y || downY >= game.board.y + game.board.sizeY);
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
            s2Right <= s1Left) ||
            (s1Down >= s2Up &&
            s2Down <= s1Up));
}

public static void printBoard(TGame game) {
    framexyc(game.board.x, game.board.y, game.board.x + game.board.sizeX - 1, game.board.y + game.board.sizeY - 1, '#');
    for (int i = 0; i < game.playerCount; i++) {
        updatePlayer(game.players[i]);
    }
}
