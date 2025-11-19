//Kacper Gęśla 290168

import static java.lang.IO.println;
import static java.lang.IO.print;
import static term.term.*;    //including package to be able to use simple print()

final int MAX_POINTS = 2000;


public static class TPath {
    TPoint[] points;
    int pointsCount;
}


public static class TPoint {
    int x;
    int y;
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
    TPoint lastPathPoint = path.points[path.pointsCount - 1];
    if(!pointsEqual(lastPathPoint, lastGivenPoint)) appendPoint(lastGivenPoint, path);
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

void writeStringForwards(TPath path, String text, int startIndex, char c, int wrap){
    int pointsAfterStartId = (path.pointsCount - 1) - startIndex;
    int carryCharCount = text.length() - (pointsAfterStartId + 1);
    int currCharIndex = 0;
    for(int i = 0; i < path.pointsCount; i++){
        if(i >= startIndex && currCharIndex < text.length()){
            drawPoint(path.points[i], text.charAt(currCharIndex));
            currCharIndex++;
        }
        else drawPoint(path.points[i], c);
    }
    if (wrap == 1){
        int i = 0;
        while(carryCharCount > 0){
            if(i < startIndex){
                drawPoint(path.points[i], text.charAt(currCharIndex));
                currCharIndex++;
            }
            carryCharCount--;
            i++;
        }
    }
}

void writeStringBackwards(TPath path, String text, int startIndex, char c, int wrap){
    int pointsBeforeStartId = startIndex;
    int carryCharCount = text.length() - (pointsBeforeStartId + 1);
    int currCharIndex = 0;
    for(int i = path.pointsCount - 1; i >= 0; i--){
        if(i <= startIndex && currCharIndex < text.length()){
            drawPoint(path.points[i], text.charAt(currCharIndex));
            currCharIndex++;
        }
        else drawPoint(path.points[i], c);
    }
    if (wrap == 1){
        int i = path.pointsCount - 1;
        while(carryCharCount > 0){
            if(i > startIndex){
                drawPoint(path.points[i], text.charAt(currCharIndex));
                currCharIndex++;
            }
            carryCharCount --;
            i--;
        }
    }
}


void writeStringOnPath(TPath path, String text, int startIndex, char c, int reverse, int wrap) {
    if (reverse == 0) {
        writeStringForwards(path, text, startIndex, c, wrap);
    }
    if (reverse == 1){
        writeStringBackwards(path, text, startIndex, c, wrap);
    }
}

void main() {
    clrscr();
    TPath path1 = createElements(TPath.class);
    TPath path2 = createElements(TPath.class);

    setPath(path1);
    setPath(path2);
    appendPoint(point(5,5), path1);
    appendPoint(point(5,6), path1);
    appendPoint(point(5,7), path1);
    appendPoint(point(6,7), path1);
    appendPoint(point(7,7), path1);
    appendPoint(point(8,7), path1);
    appendPoint(point(9,7), path1);
    appendPoint(point(10,7), path1);
    appendPoint(point(10,8), path1);
    appendPoint(point(10,9), path1);
//    appendPoint(point(10,10), path1);
//    appendPoint(point(10,11), path1);

    println("");
//    setfgcolor(green);
//    addSectionsToPath(path1, point(1,1), point(10,1), point(10,10)); //creates a path in a form of rectangle
//    drawPath(path1, '#');
//    addSectionsToPath(path2, point(1,10), point(1,1));
//    clrscr();
//    drawPath(path2, '#');
//    addPaths(path1, path2);
//    clrscr();
//    delay(500);
//    drawPath(path1, '#');
//    gotoxy(1,20);
    writeStringOnPath(path1, "hej wici123", 5, '#', 0, 1);

    gotoxy(1,20);

}
