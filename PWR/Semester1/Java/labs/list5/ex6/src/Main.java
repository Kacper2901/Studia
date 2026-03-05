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


void main() {
    clrscr();
    TPath path1 = createElements(TPath.class);
    TPath path2 = createElements(TPath.class);

    setPath(path1);
    setPath(path2);
    path1.idxText1 = 0;
    path1.idxText1 = path1.pointsCount / 2;

    String welcomeClock = "*>>Welcome to our game!<<";
    String welcomeAntiClock = ">>Welcome to our game!<<*";
    String startAntiClock = "*>>Press s to start<<";
    String startClock = ">>Press s to start<<*";

    String currWelcome = welcomeAntiClock;
    String currStart = startClock;


    addSectionsToPath(path1, point(1, 1), point(120, 1), point(120, 30), point(1, 30), point(1, 1));
    addSectionsToPath(path2, point(50, 7), point(70, 7), point(70, 22), point(50, 22), point(50, 7));
    setfgcolor(yellow);
    drawPath(path1, '*');
    setfgcolor(green);
    drawPath(path2, '*');

    path1.wrapAround = 1;
    path1.printDirection = 0;
    path2.wrapAround = 1;
    int currBeeIdx = 0;

    int currWelcomeIdx = (120 - 24) / 2;
    int lastWelcomeIdx = currWelcomeIdx + 24;
    int currStartIdx = 270 - currWelcomeIdx + 1;
    int lastStartIdx = currStartIdx - 20;

    int welcomeDirection = -1;
    int startDirection = 1;
    path1.printDirection = 1;
    int flag = 0;


    while (true){
        if (flag == 0) {
            setfgcolor(green);
            updateSmallFrame(path2, currBeeIdx, currBeeIdx + path2.pointsCount / 2);
            setfgcolor(yellow);
            updateBigFrame(path1, currStartIdx, currWelcomeIdx, welcomeAntiClock, startClock);
        }
        else{
            setfgcolor(green);
            updateSmallFrame(path2, currBeeIdx, currBeeIdx + path2.pointsCount / 2);
            setfgcolor(yellow);
            updateBigFrame(path1, currStartIdx, currWelcomeIdx, welcomeClock, startAntiClock);
        }

        delay(50);
        currBeeIdx++;
        if(hitStrings(currStartIdx, lastStartIdx, currWelcomeIdx, lastWelcomeIdx)){

            if (flag == 0) {
                setfgcolor(green);
                updateSmallFrame(path2, currBeeIdx, currBeeIdx + path2.pointsCount / 2);
                setfgcolor(yellow);
                updateBigFrame(path1, currStartIdx, currWelcomeIdx, welcomeClock, startAntiClock);
            }
            else{
                setfgcolor(green);
                updateSmallFrame(path2, currBeeIdx, currBeeIdx + path2.pointsCount / 2);
                setfgcolor(yellow);
                updateBigFrame(path1, currStartIdx, currWelcomeIdx, welcomeAntiClock, startClock);
            }
            flag = (flag + 1)%2;
            startDirection*=-1;
            welcomeDirection*=-1;


        }
        delay(50);
        currStartIdx = (((currStartIdx + startDirection) % path1.pointsCount) +path1.pointsCount) % path1.pointsCount;
        currWelcomeIdx = (((currWelcomeIdx + welcomeDirection) % path1.pointsCount) +path1.pointsCount) % path1.pointsCount;
        lastWelcomeIdx = (((lastWelcomeIdx + welcomeDirection) % path1.pointsCount) +path1.pointsCount) % path1.pointsCount;
        lastStartIdx = (((lastStartIdx + startDirection) % path1.pointsCount) +path1.pointsCount) % path1.pointsCount;

    }
}






