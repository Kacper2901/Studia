//Kacper Gęśla 290168

import static java.lang.IO.print;
import static java.lang.IO.println;
import static term.term.*;

final int MAX_POINTS = 2000;
final int WRAP = 1;
final int NOT_WRAP = 0;
final int TOWARDS_END = 0;
final int TOWARDS_BEGINNING = 1;

public static class TCornerPath {
    TPoint[] corners; //stores only corners, beggining and end
    int cornersCount;
    int realPointsCount;
    int wrapped;
    int stringDirection;
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
    path.corners = new TPoint[MAX_POINTS];
    path.cornersCount = 0;
    path.realPointsCount = 0;
    path.wrapped = NOT_WRAP;
    path.stringDirection = TOWARDS_END;
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
    for(int i = 0; i < cornerPath.cornersCount; i++){
        TPoint currPoint = cornerPath.corners[i];
        print("(" + currPoint.x + "," + currPoint.y + "), ");
    }
    print("]");
}

public void addCorner(TCornerPath cornersPath, TPoint newCorner){
    int prevLastIdx = cornersPath.cornersCount - 1;
    cornersPath.corners[prevLastIdx + 1] = newCorner;
    cornersPath.cornersCount++;
    TPoint prevLastCorner = cornersPath.corners[Math.max(prevLastIdx, 0)];

    if(cornersPath.cornersCount == 1) cornersPath.realPointsCount = 1;
    if(cornersPath.cornersCount >= 2) cornersPath.realPointsCount += countSegmentLength(prevLastCorner, newCorner);
}

public boolean isPointBetweenCorners(TPoint targetPoint, TPoint firstCorner, TPoint secondCorner){
    if(arePointsEqual(targetPoint, firstCorner) || arePointsEqual(targetPoint,secondCorner)) return true;
    if(!isSegmentValid(firstCorner,secondCorner)) return false;

    TPoint leftCorner = (firstCorner.x <= secondCorner.x)
            ? firstCorner
            : secondCorner;
    TPoint rightCorner = (firstCorner.x >= secondCorner.x)
            ? firstCorner
            : secondCorner;
    TPoint topCorner = (firstCorner.y <= secondCorner.y)
            ? firstCorner
            : secondCorner;
    TPoint bottomCorner = (firstCorner.y >= secondCorner.y)
            ? firstCorner
            : secondCorner;

    boolean sameColumn = targetPoint.x == firstCorner.x &&
                         targetPoint.x == secondCorner.x &&
                         targetPoint.y >= topCorner.y &&
                         targetPoint.y <= bottomCorner.y;

    boolean sameRow = targetPoint.y == firstCorner.y &&
                      targetPoint.y == secondCorner.y &&
                      targetPoint.x >= leftCorner.x &&
                      targetPoint.x <= rightCorner.x;


    boolean onDiagonal = Math.abs(targetPoint.x - leftCorner.x) == Math.abs(targetPoint.y - leftCorner.y) &&
                         Math.abs(targetPoint.x - rightCorner.x) == Math.abs(targetPoint.y - rightCorner.y) &&
                         targetPoint.x >= leftCorner.x &&
                         targetPoint.x <= rightCorner.x &&
                         targetPoint.y >= topCorner.y &&
                         targetPoint.y <= bottomCorner.y;


    return sameColumn || sameRow || onDiagonal;

}

public boolean arePointsEqual(TPoint point1, TPoint point2){
    return point1.x == point2.x && point1.y == point2.y;
}

public boolean isPointInPath(TCornerPath cornerPath, TPoint targetPoint){
    if(cornerPath.cornersCount == 0) return false;
    if(cornerPath.cornersCount == 1) return arePointsEqual(targetPoint, cornerPath.corners[0]);

    for(int i = 0; i < cornerPath.cornersCount - 1; i++){
        TPoint firstCorner = cornerPath.corners[i];
        TPoint secondCorner = cornerPath.corners[i+1];
        if(isPointBetweenCorners(targetPoint, firstCorner, secondCorner)) return true;
    }
    return false;
}

public TPoint getLastPointFromPath(TCornerPath cornerPath){
    return cornerPath.corners[cornerPath.cornersCount - 1];
}

public boolean isSegmentValid(TPoint point1, TPoint point2){
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

public void tryToDrawSegment(TPoint firstCorner, TPoint secondCorner, char c){
    if(!isSegmentValid(firstCorner, secondCorner)){
        drawPoint(firstCorner, c);
        drawPoint(secondCorner, c);
        return;
    }
    int dx = getDirection(firstCorner.x, secondCorner.x);
    int dy = getDirection(firstCorner.y, secondCorner.y);
    TPoint tempPoint = point(firstCorner.x, firstCorner.y);

    while(!arePointsEqual(tempPoint, secondCorner)){
        drawPoint(tempPoint, c);
        tempPoint.x += dx;
        tempPoint.y += dy;
    }
    drawPoint(tempPoint, c);
}

public void drawCornerPath(TCornerPath cornerPath, char c){
    for(int i = 0; i < cornerPath.cornersCount -1; i++){
        tryToDrawSegment(cornerPath.corners[i], cornerPath.corners[i+1], c);
    }
}



public void addPath(TCornerPath basePath, TCornerPath addPath){
    int addCornersCount = addPath.cornersCount;
    TPoint lastBaseCorner = getLastPointFromPath(basePath);
    TPoint firstAddCorner = addPath.corners[0];

    if(addCornersCount == 0) return;
    if(!arePointsEqual(firstAddCorner, lastBaseCorner)) addCorner(basePath, firstAddCorner);
    for(int i = 1; i < addPath.cornersCount; i++){
        addCorner(basePath, addPath.corners[i]);
    }
}

public int countSegmentLength(TPoint point1, TPoint point2){
    return Math.max(Math.abs(point1.x - point2.x), Math.abs(point1.y - point2.y));
}

public void addSegment(TCornerPath cornerPath ,TPoint point1, TPoint point2){
    if(!isSegmentValid(point1, point2)) return;
    TPoint lastPoint = getLastPointFromPath(cornerPath);
    if(!arePointsEqual(point1, lastPoint)) addCorner(cornerPath, point1);
    addCorner(cornerPath, point2);
}

public void addMultipleSegments(TCornerPath cornerPath ,TPoint ... corners) {
    for (TPoint corner : corners) {
        int lastCornerIdx = cornerPath.cornersCount - 1;
        if (lastCornerIdx < 0 || !arePointsEqual(corner, cornerPath.corners[lastCornerIdx])) addCorner(cornerPath,corner);
    }
}


public void writeForward(TCornerPath cornerPath, String text, int startIdx, int wrapped){

}

public void writeBackward(TCornerPath cornerPath, String text, int startIdx, int wrapped){

}

public void writeStringOnPath(TCornerPath cornerPath, String text, int startIdx, int direction, int wrapped){
    if(direction == 1) writeForward(cornerPath, text, startIdx, wrapped);
    if(direction == 0) writeBackward(cornerPath, text, startIdx, wrapped);
}


void main() {
    clrscr();
    TCornerPath cornerPath1 = createElements(TCornerPath.class);
    setCornerPath(cornerPath1);

    addMultipleSegments(cornerPath1, point(1,1), point(1,10), point(10,10), point(10,1), point(1,1), point(2,15), point(2,18), point(5,5), point(7,6));


    printPathArray(cornerPath1);
//    writeForward(cornerPath1, "hello yusuf", 21, 1);
//    writeBackward(cornerPath1, "hello yusuf", 2, 0);
    TCornerPath cornerPath2 = createElements(TCornerPath.class);
    setCornerPath(cornerPath2);


    drawCornerPath(cornerPath1, '*');
    readkey();



}






