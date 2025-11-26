//Kacper Gęśla 290168

import static java.lang.IO.println;
import static java.lang.IO.print;
import static term.term.*;    //including package to be able to use simple print()

final int MAX_INT = Integer.MAX_VALUE;
final int MAX_POINTS = 2000;

public static class TMatrix{
    int sizeX;
    int sizeY;
    int maxVal;
    int minVal;
    int[][] matrix;
}

TMatrix createMatrix(int sizeX, int sizeY){
    TMatrix matrix = new TMatrix();
    setMatrix(matrix, sizeX, sizeY);
    return matrix;
}

void setMatrix(TMatrix matrix, int sizeX, int sizeY){
    matrix.matrix = new int[sizeY][sizeX];
    matrix.sizeX = sizeX;
    matrix.sizeY = sizeY;
    matrix.minVal = MAX_INT;
    matrix.maxVal = 0;
}

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

int scaleColor(int hist_min, int hist_max, int val){
    return (int)(255.0 * ((val - hist_min) / (double) (hist_max - hist_min)));
}

void writeStringOnPath(String text, TPath path, int i){
    if(path.printDirection == 0){
        writeForwards(text,path,i);
    }
    if(path.printDirection == 1){
        writeBackwards(text,path,i);
    }
}

int[][] randMTX(int sizeX, int sizeY, int loops){
    int[][] matrix = new int[sizeY][sizeX];
    int x, y;
    int seed = 123456;

    SecureRandom secRand = new SecureRandom();
    Random rand = new Random(seed);
    for(int i = 0; i < sizeY; i++){
        for(int j = 0; j < sizeX; j++){
            matrix[i][j] = 0;
        }
    }

    for(int i = 0; i < loops; i++){
//        rand.setSeed(seed++);
//        x = rand.nextInt(sizeX);
//        y = rand.nextInt(sizeY);
        x = secRand.nextInt(sizeX);
        y = secRand.nextInt(sizeY);
        matrix[y][x]++;
    }

    return matrix;
}

void printMatrix(int[][] matrix, int sizeX, int sizeY){
    int hist_max = 0;
    int hist_min = MAX_INT;
    int green;

    for(int i = 0; i < sizeY; i++){
        for(int j = 0; j < sizeX; j++){
            if(matrix[i][j] < hist_min) hist_min = matrix[i][j];
            if(matrix[i][j] > hist_max) hist_max = matrix[i][j];
        }
    }

    for(int i = 0; i < sizeY; i++){
        for(int j = 0; j < sizeX; j++){
            gotoxy(j,i);
            green = scaleColor(hist_min,hist_max, matrix[i][j]);
            setfgcolor_rgb(10, green, 10);
            gotoxy(j + 1, i + 1);
            write('█');
        }
    }
}


void main() {
    int[][] randomMatrix = randMTX(200,50,5000000);
    printMatrix(randomMatrix,200,50);

}