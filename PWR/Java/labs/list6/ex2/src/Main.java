//Kacper Gęśla 290168

import static java.lang.IO.print;
import static java.lang.IO.println;
import static term.term.*;

final int MAX_POINTS = 2000;
final int WRAP = 1;
final int NOT_WRAP = 0;
final int TOWARDS_END = 0;
final int TOWARDS_BEGINNING = 1;

public static class TSectionPath {
    TSection[] sections; //stores only corners, beggining and end
    int realPointsCount;
    int wrapped;
    int stringDirection;
    int sectionsCount;
}

public static class TSection{
    TPoint begPoint;
    TPoint endPoint;
}


public static class TPoint {
    int x;
    int y;
}

public TSection Section(TPoint begPoint, TPoint endPoint){
    TSection section = new TSection();
    setSection(section, begPoint, endPoint);
    return section;
}

public void setSection(TSection section, TPoint begPoint, TPoint endPoint){
    section.begPoint = begPoint;
    section.endPoint = endPoint;
}

public TSectionPath createPath(){
    TSectionPath path = new TSectionPath();
    setSectionPath(path);
    return path;
}

public void setSectionPath(TSectionPath path) {
    path.sections = new TSection[MAX_POINTS];
    path.sectionsCount = 0;
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

public void printSection(TSection section){
    print("(" + section.begPoint.x + "," + section.begPoint.y + ")");
}

public void printPathArray(TSectionPath cornerPath){
    print("{");
    for(int i = 0; i < cornerPath.sectionsCount; i++){
        TSection currSection = cornerPath.sections[i];
        print("[(" + currSection.begPoint.x + "," + currSection.begPoint.y + "),("+ currSection.endPoint.x + ","+ currSection.endPoint.y+")], ");
    }
    print("}");
}

public int safeModulo(int n, int divisor){
    return (n%divisor + divisor) % divisor;
}

public int getSectionLength(TSection section){
    return Math.max(Math.abs(section.endPoint.x - section.begPoint.x) + 1,
                    Math.abs(section.endPoint.y - section.begPoint.y) + 1);
}

public TPoint getSectionNthPoint(TSection section, int n){
    n = n-1;
    int sectionLength = getSectionLength(section);
    if(n >= sectionLength - 1) return section.endPoint;
    if(n <= 0) return section.begPoint;

    TPoint tempPoint = point(section.begPoint.x, section.begPoint.y);
    int dx = getDirection(section.begPoint.x, section.endPoint.x);
    int dy = getDirection(section.begPoint.y, section.endPoint.y);
    for(int i = 0; i < n; i++){
        tempPoint.x += dx;
        tempPoint.y += dy;
    }
    return tempPoint;
}



public boolean isPointInSegment(TPoint targetPoint, TPoint firstCorner, TPoint secondCorner){
    if(arePointsEqual(targetPoint, firstCorner) || arePointsEqual(targetPoint,secondCorner)) return true;
    if(!isSectionValid(firstCorner,secondCorner)) return false;

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

public void printPoint(TPoint point){
    print("(" + point.x + "," + point.y + ")");
}


public boolean arePointsEqual(TPoint point1, TPoint point2){
    return point1.x == point2.x && point1.y == point2.y;
}

public boolean isPointInPath(TSectionPath sectionPath, TPoint targetPoint){
    if(sectionPath.sectionsCount == 0) return false;

    for(int i = 0; i < sectionPath.sectionsCount; i++){
        TPoint firstCorner = sectionPath.sections[i].begPoint;
        TPoint secondCorner = sectionPath.sections[i].endPoint;
        if(isPointInSegment(targetPoint, firstCorner, secondCorner)) return true;
    }
    return false;
}

public TSection getLastSegmentFromPath(TSectionPath sectionPath){
    return sectionPath.sections[sectionPath.sectionsCount - 1];
}

public boolean isSectionValid(TPoint point1, TPoint point2){
    int xDifference = Math.abs(point1.x - point2.x);
    int yDifference = Math.abs(point1.y - point2.y);
    return point1.x == point2.x || point1.y == point2.y || xDifference == yDifference;
}

public int getDirection(int a, int b){
    if(b - a > 0) return 1;
    else if(b - a == 0) return 0;
    else return -1;
}

public boolean sectionsOverlap(TSection section1, TSection section2){
    int section1Length = getSectionLength(section1);
    for(int n = 1; n <= section1Length; n++){
        TPoint NthPoint = getSectionNthPoint(section1, n);
        if(isPointInSegment(NthPoint, section2.begPoint, section2.endPoint)) return true;
    }
    return false;
}

public int getPathLenght(TSectionPath path){
    if(path.sectionsCount == 0) return 0;
    TPoint firstPoint = path.sections[0].begPoint;
    TPoint lastPoint = path.sections[path.sectionsCount - 1].endPoint;
    int pointCount = getSectionLength(path.sections[0]);
    TPoint prevEndPoint = path.sections[0].endPoint;

    for(int i = 1; i < path.sectionsCount; i++){
        TSection currSection = path.sections[i];
        TPoint currBegPoint = currSection.begPoint;
        pointCount+=getSectionLength(currSection);
        if(arePointsEqual(currBegPoint, prevEndPoint)) pointCount --;
        prevEndPoint = currSection.endPoint;
    }
    if(arePointsEqual(firstPoint, lastPoint)) pointCount--;

    return pointCount;
}

public TPoint iterateBackwardsToFindNth(TSection section, int currLength, int n){
    int currIdx = currLength - 1;
    int dx = getDirection(section.begPoint.x, section.endPoint.x);
    int dy = getDirection(section.begPoint.y, section.endPoint.y);
    TPoint tempPoint = point(section.endPoint.x, section.endPoint.y);
    while(n != currIdx){
        currIdx--;
        tempPoint.x -= dx;
        tempPoint.y -= dy;
    }
    return tempPoint;
}

public TPoint getPathNthPoint(TSectionPath sectionPath, int n){
    n--;
    if(n < 0) return sectionPath.sections[0].begPoint;
    if(n > getPathLenght(sectionPath) - 1) return sectionPath.sections[sectionPath.sectionsCount - 1].endPoint;

    int currPointCount = getSectionLength(sectionPath.sections[0]);
    int currSecIdx = 0;

    while(currPointCount -1 < n){
        currSecIdx++;
        TPoint prevEnd = sectionPath.sections[currSecIdx - 1].endPoint;
        TPoint currBeg = sectionPath.sections[currSecIdx].begPoint;
        currPointCount += getSectionLength(sectionPath.sections[currSecIdx]);
        if(arePointsEqual(prevEnd,currBeg)) currPointCount--;
    }
    return iterateBackwardsToFindNth(sectionPath.sections[currSecIdx], currPointCount, n);
}

public void drawPoint(TPoint point, char c){
    gotoxy(point.x, point.y);
    print(c);
}

public void drawSection(TSection section,  char c){
    if(!isSectionValid(section.begPoint, section.endPoint)){
        drawPoint(section.begPoint, c);
        drawPoint(section.endPoint, c);
        return;
    }
    int dx = getDirection(section.begPoint.x, section.endPoint.x);
    int dy = getDirection(section.begPoint.y, section.endPoint.y);
    TPoint tempPoint = point(section.begPoint.x, section.begPoint.y);

    while(!arePointsEqual(tempPoint, section.endPoint)){
        drawPoint(tempPoint, c);
        tempPoint.x += dx;
        tempPoint.y += dy;
    }
    drawPoint(tempPoint, c);
}

public void drawSectionPath(TSectionPath sectionPath, char c){
    for(int i = 0; i < sectionPath.sectionsCount; i++){
        drawSection(sectionPath.sections[i],c);
    }
}


public void addPath(TSectionPath basePath, TSectionPath addPath){
    int addSectionsCount = addPath.sectionsCount;
    if(addSectionsCount == 0) return;
    for(int i = 0; i < addPath.sectionsCount; i++){
        addSection(basePath, addPath.sections[i]);
    }
}




//
public void addSection(TSectionPath sectionPath , TSection section){
    if(!isSectionValid(section.begPoint, section.endPoint)) return;
    int lastSectionIdx = sectionPath.sectionsCount - 1;
    sectionPath.sections[lastSectionIdx + 1] = section;
    sectionPath.sectionsCount++;
}

public void addMultipleSegments(TSectionPath sectionPath , TSection ... sections) {
    for (TSection section : sections) {
        addSection(sectionPath,section);
    }
}

String sliceString(String text, int leftIdx, int rigthIdx){
    String newText = "";
    for (int i = 0; i < text.length(); i ++){
        if(i >= leftIdx && i <= rigthIdx) newText += text.charAt(i);
    }
    return newText;
}


public void writeTowardsEnd(TSectionPath cornerPath, String text, int startIdx){

}


public void writeTowardsBeginning(TSectionPath cornerPath, String text, int startIdx) {
}


public void writeStringOnPath(TSectionPath cornerPath, String text, int startIdx){
    int writingDirection = cornerPath.stringDirection;
    if(writingDirection == TOWARDS_END) writeTowardsEnd(cornerPath, text, startIdx);
    if(writingDirection == TOWARDS_BEGINNING) writeTowardsBeginning(cornerPath, text, startIdx);
}


void main() {
    clrscr();
    TSectionPath sectionPath = createElements(TSectionPath.class);
    setSectionPath(sectionPath);

    addMultipleSegments(sectionPath, Section(point(1,1), point(1,10)),Section(point(1,10), point(10,10)), Section(point(10,10), point(10,1)), Section(point(20,1), point(2,1)));
    printPathArray(sectionPath);
    printPoint(getPathNthPoint(sectionPath, 29));

//    writeTowardsBeginning(cornerPath1, "abcdefghijklmnoprstuwxyzabcdefghijklmnoprstuwxyz", 25);
//    writeBackward(cornerPath1, "hello yusuf", 2, 0);
    TSectionPath cornerPath2 = createElements(TSectionPath.class);
    setSectionPath(cornerPath2);


//    drawCornerPath(cornerPath1, '*');
    readkey();



}






