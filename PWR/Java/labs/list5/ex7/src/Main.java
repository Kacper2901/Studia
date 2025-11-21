//Kacper Gęśla 290168

import static java.lang.IO.println;
import static java.lang.IO.print;
import static term.term.*;    //including package to be able to use simple print()

final int MAX_POINTS = 2000;


public static class TPath {
    TPoint[] points;
    int pointsCount;
    int printDirection;
    int wrapAround;
    int idxText1;
    int idxText2;
}


public static class TPoint {
    int x;
    int y;
}

public TPath createPath(){
    TPath path = new TPath();
    setPath(path);
    return path;
}

public void setPath(TPath path) {
    path.points = new TPoint[MAX_POINTS];
    path.pointsCount = 0;
}

public TPoint point(int x, int y) {
    TPoint point = new TPoint();
    setPoint(point, x, y);
    return point;
}

public void setPoint(TPoint point, int x, int y) {
    point.x = x;
    point.y = y;
}

public void printPath(TPath path){
    print("path = [");
    for(int i = 0; i < path.pointsCount; i++){
        print("(" + path.points[i].x + "," + path.points[i].y + "), ");
    }
    print("]");
}

public void appendPoint(TPoint point, TPath path){
    path.points[path.pointsCount] = point;
    path.pointsCount++;
}

public void drawPoint(TPoint point, char c) {
    gotoxy(point.x, point.y);
    print(c);
}

public void drawPath(TPath path, char c) {
    for (int i = 0; i < path.pointsCount; i++) {
        drawPoint(path.points[i], c);
    }
}

public boolean pointsEqual(TPoint a, TPoint b){
    return a.x == b.x && a.y == b.y;
}

public boolean isPointInPath(TPoint point, TPath path){
    for (int i = 0; i < path.pointsCount; i++) {
        if(pointsEqual(path.points[i], point)) return true;
    }
    return false;
}

public boolean segmentValid(TPoint a, TPoint b){
    return (a.x == b.x ||
            a.y == b.y ||
            Math.abs(a.x - b.x) == Math.abs(a.y - b.y));
}

public boolean sameRow(TPoint a, TPoint b){
    return a.y == b.y;
}

public boolean sameColumn(TPoint a, TPoint b){
    return a.x == b.x;
}

public boolean areDiagonal(TPoint a, TPoint b){
    return Math.abs(a.x - b.x) == Math.abs(a.y - b.y);
}

public boolean lastPointEqualSegmentStart(TPoint start, TPath path){
    TPoint lastElement = path.points[path.pointsCount - 1];
    return (pointsEqual(lastElement, start));
}

public boolean firstPointEqualSegmentEnd(TPoint end, TPath path){
    TPoint firstElement = path.points[0];
    return (pointsEqual(firstElement, end));
}

public int getDx(TPoint a, TPoint b){
    if(b.x - a.x > 0) return 1;
    if(b.x - a.x < 0) return -1;
    else return 0;
}

public int getDy(TPoint a, TPoint b){
    if(b.y - a.y > 0) return 1;
    if(b.y - a.y < 0) return -1;
    else return 0;
}

String sliceString(String text, int leftIdx, int rigthIdx){
    String newText = "";
    for (int i = 0; i < text.length(); i ++){
        if(i >= leftIdx && i <= rigthIdx) newText += text.charAt(i);
    }
    return newText;
}

public void appendSegmentToPath(TPoint a, TPoint b, TPath path){
    if (segmentValid(a,b)){
        int dx = getDx(a, b);
        int dy = getDy(a, b);
        int tempX = a.x + dx;
        int tempY = a.y + dy;

        if(path.pointsCount == 0 || !lastPointEqualSegmentStart(a, path)) appendPoint(a, path);
        while (tempX != b.x || tempY != b.y){
            appendPoint(point(tempX, tempY), path);
            if (tempX != b.x) tempX += dx;
            if (tempY != b.y) tempY += dy;
        }
        if(!firstPointEqualSegmentEnd(b, path)) appendPoint(b, path);
    }
}

public void addSectionsToPath(TPath path, TPoint... points){
    for(int i = 0; i < points.length - 1; i++){
        if (segmentValid(points[i], points[i + 1])) appendSegmentToPath(points[i], points[i + 1], path);
        else appendPoint(points[i], path);
    }
    TPoint lastGivenPoint = points[points.length - 1];
    TPoint firstPathPoint = path.points[0];
    if(!pointsEqual(firstPathPoint, lastGivenPoint)) appendPoint(lastGivenPoint, path);
}

void addPaths(TPath p1, TPath p2){
    if(p2.pointsCount == 0) return;
    if(p1.pointsCount == 0) p1 = p2;
    TPoint firstP1 = p1.points[0];
    TPoint lastP1 = p1.points[p1.pointsCount - 1];
    TPoint firstP2 = p2.points[0];
    TPoint lastP2 = p2.points[p2.pointsCount - 1];

    if(firstP2 != lastP1) appendPoint(firstP2, p1);

    for(int i = 1; i < p2.pointsCount - 1; i++){
        appendPoint(p2.points[i], p1);
    }

    if(firstP1 != lastP2) appendPoint(lastP2, p1);
}

String reverseString(String text){
    String reversedText = "";
    for(int i = 0; i < text.length(); i ++){
        reversedText = text.charAt(i) + reversedText;
    }
    return reversedText;
}

void writeForwards(String text, TPath path, int i){
    int numPrintedChars = 0;
    if(path.wrapAround == 1) {
        if (i < 0) i = (i % path.pointsCount) + path.pointsCount;
        if (i > path.pointsCount) i %= path.pointsCount;
        while (numPrintedChars < text.length()){
            drawPoint(path.points[i % path.pointsCount], text.charAt(numPrintedChars));
            numPrintedChars++;
            i++;
        }
    }
    if(path.wrapAround == 0){
        if(i < 0){
            text = sliceString(text, Math.abs(i), text.length() - 1);
            i = 0;
        }
        if(i > path.pointsCount) text = "";
        while(numPrintedChars < text.length() && i < path.pointsCount){
            drawPoint(path.points[i], text.charAt(numPrintedChars));
            numPrintedChars++;
            i++;
        }
    }
}

void writeBackwards(String text, TPath path, int i){
    int numPrintedChars = 0;
    if(path.wrapAround == 1){
        if (i < 0) i = (i % path.pointsCount) + path.pointsCount;
        if (i > path.pointsCount) i %= path.pointsCount;
        while (numPrintedChars < text.length()){
            drawPoint(path.points[(i % path.pointsCount + path.pointsCount)%path.pointsCount], text.charAt(numPrintedChars));
            numPrintedChars++;
            i--;
        }
    }
    if(path.wrapAround == 0){
        if(i < 0) text = "";
        if(i > path.pointsCount) {
            text = sliceString(text, 0,Math.abs(i));
            i = path.pointsCount - 1;
        }
        while(numPrintedChars < text.length()){
            if(i > 0 && i < path.pointsCount)
                drawPoint(path.points[i], text.charAt(numPrintedChars));
            numPrintedChars++;
            i--;
        }
    }
}

void writeStringOnPath(String text, TPath path, int i){
    if(path.printDirection == 0){
        writeForwards(text,path,i);
    }
    if(path.printDirection == 1){
        writeBackwards(text,path,i);
    }
}


boolean hitStrings(int s1_first, int s1_last, int s2_first, int s2_last){
    return (Math.abs(s1_first - s2_first) < 2 || Math.abs(s1_last - s2_last) < 2) || Math.abs(s1_first - s1_last) < 2 || Math.abs(s1_first - s2_last) < 2;
}

void updateSmallFrame(TPath path, int bee1Idx, int bee2Idx){
    String text1 = "*Bee ready!";
    writeStringOnPath(text1, path, bee1Idx);
    writeStringOnPath(text1, path, bee2Idx);
}



void updateBigFrame(TPath path, int startIdx, int welcomeIdx, String welcome, String start){


    path.printDirection = (path.printDirection + 1) % 2;
    writeStringOnPath(welcome, path,welcomeIdx);
    path.printDirection = (path.printDirection + 1) % 2;
    writeStringOnPath(start, path,startIdx);


}
public static class TGame{
    TBoard board;
    TPlayer player1;
    TPlayer player2;
    TPlayer winner;
}

void setGame(TGame game, TBoard board, TPlayer player1, TPlayer player2){
    game.board = board;
    game.player1 = player1;
    game.player2 = player2;
}

public static class TPlayer{
    TPath body;
    String name;
    int speed;
    int color;
    int dx;
    int dy;
    boolean hitFlag;
}

TPlayer createPlayer(TPoint startPoint, String name, int color, int speed, int dx, int dy){
    TPlayer player = new TPlayer();
    setPlayer(player, startPoint, name, color, speed, dx, dy);
    return player;
}

void setPlayer(TPlayer player, TPoint startPoint, String name, int color, int speed, int dx, int dy){
    player.body = createPath();
    appendPoint(startPoint, player.body);
    player.name = name;
    player.color = color;
    player.speed = speed;
    player.dx = dx;
    player.dy = dy;
    player.hitFlag = false;
}

public static class TBoard{
    int x1;
    int y1;
    int x2;
    int y2;
    int color;
    TPath frame;
}

TBoard createBoard(int x1, int x2, int y1, int y2, int color){
    TBoard board = new TBoard();
    setBoard(board, x1, x2, y1, y2, color);
    return board;
}

void setBoard(TBoard board, int x1, int x2, int y1, int y2, int color){
    board.x1 = x1;
    board.x2 = x2;
    board.y1 = y1;
    board.y2 = y2;
    board.color = color;
    board.frame = createPath();
    addSectionsToPath(board.frame, point(x1,y1), point(x2, y1), point(x2,y2), point(x1,y2), point(x1,y1));
}

void initBoard(TGame game){
    clrscr();
    setGame(game, createBoard(1,120,1,30, yellow), createPlayer(point(3,15), "Player1", blue, 1, 1, 0), createPlayer(point(117,15), "Player2", red, 1, -1, 0));
    setfgcolor(game.board.color);
    int middleTop = (game.board.x1 + game.board.x2) / 2;
    int middleBottom = 3 * middleTop + (game.board.y2 - game.board.x1);
    String upperString = "TRON GAME";
    String bottomString =  "PRESS q TO EXIT";
    drawPath(game.board.frame, '*');
    writeStringOnPath(upperString, game.board.frame, middleTop - upperString.length() / 2);
    game.board.frame.printDirection = 1;
    writeStringOnPath(bottomString, game.board.frame, middleBottom + bottomString.length() / 2);
}

void initPlayers(TGame game){
    setfgcolor(game.player1.color);
    drawPath(game.player1.body, '#');
    setfgcolor(game.player2.color);
    drawPath(game.player2.body, '#');
}

void readKeyboard(TGame game) {
    while (true) {
        if (keypressed()) {
            String keystr1 = readkeystr();
            switch (keystr1) {
                case "w":
                    updatePlayerData(game, game.player1, 0, -1);
                    break;
                case "s":
                    updatePlayerData(game, game.player1, 0, 1);
                    break;
                case "a":
                    updatePlayerData(game, game.player1, -1, 0);
                    break;
                case "d":
                    updatePlayerData(game, game.player1, 1, 0);
                    break;
                case "i":
                    updatePlayerData(game, game.player2, 0, -1);
                    break;
                case "k":
                    updatePlayerData(game, game.player2, 0, 1);
                    break;
                case "j":
                    updatePlayerData(game, game.player2, -1, 0);
                    break;
                case "l":
                    updatePlayerData(game, game.player2, 1, 0);
                    break;
            }



        }
    }
}
boolean boardCollision(TGame game, TPlayer player, int dx, int dy){
    int lastIndex = player.body.pointsCount - 1;
    TPoint lastPoint = player.body.points[lastIndex];
    int newPointX= lastPoint.x + dx;
    int newPointY= lastPoint.y + dy;
    gotoxy(20,20);
    print(newPointY > game.board.y1);


    return !(newPointX > game.board.x1 &&
            newPointX < game.board.x2 &&
            newPointY > game.board.y1 &&
            newPointY < game.board.y2);
}

void updatePlayerData(TGame game,TPlayer player, int dx, int dy){

    if(!hitTail(game, player, dx, dy) && !boardCollision(game, player, dx, dy)){
        changePlayerDirection(player, dx, dy);
        drawNewStep(player);
    }
    else{
        player.hitFlag = true;
    }
}

boolean hitTail(TGame game, TPlayer player, int dx, int dy){
    int lastIndex = player.body.pointsCount - 1;
    TPoint lastPoint = player.body.points[lastIndex];
    int newPointX= lastPoint.x + dx;
    int newPointY= lastPoint.y + dy;

    return isPointInPath(point(newPointX,newPointY), game.player1.body) || isPointInPath(point(newPointX, newPointY), game.player2.body);
}



void changePlayerDirection(TPlayer player, int dx, int dy){
    player.dx = dx;
    player.dy = dy;
}

void drawNewStep(TPlayer player){
    int lastIndex = player.body.pointsCount - 1;
    TPoint lastPoint = player.body.points[lastIndex];
    int newPointX= lastPoint.x + player.dx;
    int newPointY= lastPoint.y + player.dy;
    appendPoint(point(newPointX, newPointY), player.body);
    gotoxy(20,20);
    setfgcolor(player.color);

    writeStringOnPath("*#", player.body, lastIndex);
}

void startGame(TGame game){
    cursor_hide();
    initBoard(game);
    initPlayers(game);
    readKeyboard(game);
}


void main() {
    TGame game = createElements(TGame.class);

    startGame(game);
    gotoxy(200,200);



}






