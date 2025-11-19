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

void writeStringForwards(TPath path, String text, int startIndex, char c){
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
    if (path.wrapAround == 1){
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

String sliceString(String text, int leftIdx, int rigthIdx){
    String newText = "";
    for (int i = 0; i < text.length(); i ++){
        if(i >= leftIdx && i <= rigthIdx) newText += text.charAt(i);
    }
    return newText;
}

void writeStringBackwards(TPath path, String text, int startIndex, char c){
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
    if (path.wrapAround == 1){
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

int correctStartIndex(TPath path, int startIndex){
    if(startIndex < 0 || startIndex >= path.pointsCount) {
        startIndex %= path.pointsCount;
        if(startIndex < 0) startIndex += path.pointsCount;
    }
    return startIndex;
}

String cutString(TPath path, String text, int startIndex){
    int howManyCut;
    int pathLastIdx = path.pointsCount - 1;
    if(startIndex < 0){
        if(path.printDirection == 1) return "";
        howManyCut = Math.abs(startIndex);
        text = sliceString(text, howManyCut, text.length() - 1);

    }
    else if(startIndex > pathLastIdx){
        if(path.printDirection == 0) return "";
        text = reverseString(text);
        howManyCut = startIndex - pathLastIdx;
        text = sliceString(text, 0, pathLastIdx - howManyCut);
        text = reverseString(text);
    }
    return text;
}

void writeStringOnPath(TPath path, String text, int startIndex, char c) {
    if(path.wrapAround == 1) startIndex = correctStartIndex(path, startIndex);
    if(path.wrapAround == 0){
        text = cutString(path, text, startIndex);
        if(startIndex < 0) startIndex = 0;
    }

    if (path.printDirection == 0) {
        writeStringForwards(path, text, startIndex, c);
    }
    if (path.printDirection == 1){
        writeStringBackwards(path, text, startIndex, c);
    }
}


void writeStringBackwardsOffset(TPath path, String text, int startIndex, char c, TPoint offset){
    int pointsBeforeStartId = startIndex;
    int carryCharCount = text.length() - (pointsBeforeStartId + 1);
    int currCharIndex = 0;
    TPoint offsetPoint;

    for(int i = path.pointsCount - 1; i >= 0; i--){
        offsetPoint = point(path.points[i].x + offset.x, path.points[i].y + offset.y);
        if(i <= startIndex && currCharIndex < text.length()){
            drawPoint(offsetPoint, text.charAt(currCharIndex));
            currCharIndex++;
        }
//        else drawPoint(offsetPoint, c);
    }
    if (path.wrapAround == 1){
        int i = path.pointsCount - 1;
        while(carryCharCount > 0){
            if(i > startIndex){
                offsetPoint = point(path.points[i].x + offset.x, path.points[i].y + offset.y);
                drawPoint(offsetPoint, text.charAt(currCharIndex));
                currCharIndex++;
            }
            carryCharCount --;
            i--;
        }
    }
}

void writeStringOnPathOffset(TPath path, String text, int startIndex, char c, TPoint offset) {
    if(path.wrapAround == 1) startIndex = correctStartIndex(path, startIndex);
    if(path.wrapAround == 0){
        text = cutString(path, text, startIndex);
        if(startIndex < 0) startIndex = 0;
    }

    if (path.printDirection == 0) {
        writeStringForwardsOffset(path, text, startIndex, c, offset);
    }
    if (path.printDirection == 1){
        writeStringBackwardsOffset(path, text, startIndex, c, offset);
    }
}


void writeStringForwardsOffset(TPath path, String text, int startIndex, char c, TPoint offset){
    int pointsAfterStartId = (path.pointsCount - 1) - startIndex;
    int carryCharCount = text.length() - (pointsAfterStartId + 1);
    int currCharIndex = 0;
    TPoint offsetPoint;
    for(int i = 0; i < path.pointsCount; i++){
        offsetPoint = point(path.points[i].x + offset.x, path.points[i].y + offset.y);
        if(i >= startIndex && currCharIndex < text.length()){
            drawPoint(offsetPoint, text.charAt(currCharIndex));
            currCharIndex++;
        }
//        else drawPoint(path.points[i], c);
    }
    if (path.wrapAround == 1){
        int i = 0;
        while(carryCharCount > 0){
            if(i < startIndex){
                offsetPoint = point(path.points[i].x + offset.x, path.points[i].y + offset.y);
                drawPoint(path.points[i], text.charAt(currCharIndex));
                currCharIndex++;
            }
            carryCharCount--;
            i++;
        }
    }
}

void updateBigFrame(TPath path, String text, char c){
    setfgcolor(yellow);

}


void main() {
    clrscr();
    TPath path1 = createElements(TPath.class);
    TPath path2 = createElements(TPath.class);

    setPath(path1);
    setPath(path2);
    path1.idxText1 = 0;
    path1.idxText1 = path1.pointsCount / 2;

    String text1 = "Bee ready!";


    addSectionsToPath(path1, point(3,3), point(122,3), point(122, 48), point(3,48), point(3,3));
    addSectionsToPath(path2, point(52,17), point(72,17), point(72, 32), point(52,32), point(52,17));
    setfgcolor(yellow);
    drawPath(path1, '#');
    setfgcolor(green);
    drawPath(path2, '#');





    println("");
//    setfgcolor(green);






    gotoxy(1,20);

}
