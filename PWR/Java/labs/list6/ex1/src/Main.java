//Kacper Gęśla 290168

import static java.lang.IO.print;
import static java.lang.IO.println;
import static term.term.*;

final int MAX_POINTS = 2000;


public static class TCornerPath {
    TPoint[] cornerPoints; //stores only corners, beggining and end
    int cornerPointsCount;
    int circuit;

}


public static class TPoint {
    int x;
    int y;
}

public TCornerPath createPath(){
    TCornerPath path = new TCornerPath();
    setCornerPath(path);
    return path;
}

public void setCornerPath(TCornerPath path) {
    path.cornerPoints = new TPoint[MAX_POINTS];
    path.cornerPointsCount = 0;
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

public void printPathArray(TCornerPath cornerPath){
    print("[");
    for(int i = 0; i < cornerPath.cornerPointsCount; i++){
        TPoint currPoint = cornerPath.cornerPoints[i];
        print("(" + currPoint.x + "," + currPoint.y + "), ");
    }
    print("]");
}

public void addPoint(TCornerPath cornerPath, TPoint point){
    int lastIdx = cornerPath.cornerPointsCount - 1;
    cornerPath.cornerPoints[lastIdx + 1] = point;
    if(cornerPath.cornerPointsCount == 1) cornerPath.circuit += countSegmentLength(cornerPath.cornerPoints[lastIdx], point) + 1;
    if(cornerPath.cornerPointsCount > 1) cornerPath.circuit += countSegmentLength(cornerPath.cornerPoints[lastIdx], point);
    cornerPath.cornerPointsCount++;
}

public boolean arePointsEqual(TPoint point1, TPoint point2){
    return point1.x == point2.x && point1.y == point2.y;
}

public boolean isPointInPath(TCornerPath cornerPath, TPoint point){
    int lastIdx = cornerPath.cornerPointsCount - 1;
    for(int i = 0; i <= lastIdx; i++){
        TPoint currPoint = cornerPath.cornerPoints[i];
        if(arePointsEqual(currPoint, point)) return true;
    }
    return false;
}

public TPoint getLastPointFromPath(TCornerPath cornerPath){
    return cornerPath.cornerPoints[cornerPath.cornerPointsCount - 1];
}

public boolean canSegmentBeDrawn(TPoint point1, TPoint point2){
    int xDifference = Math.abs(point1.x - point2.x);
    int yDifference = Math.abs(point1.y - point2.y);
    return point1.x == point2.x || point1.y == point2.y || xDifference == yDifference;
}

public int getDirection(int a, int b){
    if(b - a > 0) return 1;
    else if(b - a == 0) return 0;
    else return -1;
}

public void drawPoint(TPoint point, char c){
    gotoxy(point.x, point.y);
    print(c);
}

public void tryToDrawSegment(TPoint point1, TPoint point2, char c){
    if(canSegmentBeDrawn(point1, point2)){
        int dx = getDirection(point1.x, point2.x);
        int dy = getDirection(point1.y, point2.y);
        TPoint currPoint = point(point1.x, point1.y);
        while(!arePointsEqual(currPoint, point2)){
            drawPoint(currPoint, c);
            currPoint.x += dx;
            currPoint.y += dy;
        }
        drawPoint(point2, c);
    }
}

public void drawCornerPath(TCornerPath cornerPath, char c){
    int lastIdx = cornerPath.cornerPointsCount - 1;
    for(int i = 0; i < lastIdx; i++){
        TPoint point1 = cornerPath.cornerPoints[i];
        TPoint point2 = cornerPath.cornerPoints[i + 1];
        tryToDrawSegment(point1, point2, c);
    }
}

public void addCornerToPathIfPossible(TCornerPath cornerPath, TPoint newPoint){
    TPoint lastPoint = cornerPath.cornerPoints[cornerPath.cornerPointsCount - 1];
    if(canSegmentBeDrawn(lastPoint, newPoint)) addPoint(cornerPath, newPoint);
}

public void joinPaths(TCornerPath cornerPath, TCornerPath newCornerPath){
    TPoint lastCorner = cornerPath.cornerPoints[cornerPath.cornerPointsCount - 1];
    TPoint firstCorner = newCornerPath.cornerPoints[0];
    int lastIdxNewPath = newCornerPath.cornerPointsCount - 1;
    if(!arePointsEqual(lastCorner,firstCorner)) addPoint(cornerPath, firstCorner);
    for(int i = 1; i <= lastIdxNewPath; i++){
        addPoint(cornerPath, newCornerPath.cornerPoints[i]);
    }
}

public int countSegmentLength(TPoint point1, TPoint point2){
    return Math.max(Math.abs(point1.x - point2.x), Math.abs(point1.y - point2.y));
}

public int addSegment(TPoint[] path, int lastIdx, TPoint point1, TPoint point2){
    int dx = getDirection(point1.x, point2.x);
    int dy = getDirection(point1.y, point2.y);
    TPoint tempPoint = point(point1.x, point1.y);
    while(!arePointsEqual(tempPoint, point2)){
        path[lastIdx] = point(tempPoint.x, tempPoint.y);
        lastIdx++;
        tempPoint.x += dx;
        tempPoint.y += dy;
    }
    path[lastIdx] = tempPoint;

    return lastIdx;
}


public TPoint[] createPath(TCornerPath cornerPath){
    TPoint[] path = new TPoint[cornerPath.circuit];
    int lastIdx = 0;
    for(int i = 0; i < cornerPath.cornerPointsCount - 1; i++){
        lastIdx = addSegment(path, lastIdx, cornerPath.cornerPoints[i], cornerPath.cornerPoints[i+1]);
    }
    return path;

}

public void writeForward(TCornerPath cornerPath, String text, int startIdx, int wrapped){
    TPoint[] path = createPath(cornerPath);
    int currTextIdx = 0;
    if(wrapped == 1) startIdx %= cornerPath.circuit;
    while(currTextIdx < text.length()){
        gotoxy(path[startIdx].x, path[startIdx].y);
        print(text.charAt(currTextIdx));
        startIdx++;
        if(wrapped == 1) startIdx = (startIdx) % cornerPath.circuit;
        currTextIdx++;
    }

}

public void writeBackward(TCornerPath cornerPath, String text, int startIdx, int wrapped){

}

public void writeStringOnPath(TCornerPath cornerPath, String text, int startIdx, int direction, int wrapped){
    if(direction == 1) writeForward(cornerPath, text, startIdx, wrapped);
    if(direction == 0) writeBackward(cornerPath, text, startIdx, wrapped);
}


void main() {
    TCornerPath cornerPath1 = createElements(TCornerPath.class);
    setCornerPath(cornerPath1);
    addPoint(cornerPath1, point(1,1));
    addPoint(cornerPath1, point(1,10));
    addPoint(cornerPath1, point(10,10));
    addPoint(cornerPath1, point(10,1));


    writeForward(cornerPath1, "hello yusuf", 18, 1);
//    TCornerPath cornerPath2 = createElements(TCornerPath.class);
//    setCornerPath(cornerPath2);
//    addPoint(cornerPath2, point(52,3));
//    addPoint(cornerPath2, point(55,2));
//    addPoint(cornerPath2, point(50,2));
//
//
//
//    joinPaths(cornerPath1, cornerPath2);
//    drawCornerPath(cornerPath1, '*');
    readkey();



}






