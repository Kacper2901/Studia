//Kacper Gęśla 290168

import java.awt.*;

import static java.lang.IO.print;
import static java.lang.IO.println;
import static term.term.*;

class Main {
    final static int MAX_POINTS = 2000;
    final static int WRAP = 1;
    final static int NOT_WRAP = 0;
    final static int TOWARDS_END = 0;
    final static int TOWARDS_BEGINNING = 1;

    public static class TSectionPath {
        TSection[] sections; //stores only corners, beggining and end
        int realPointsCount;
        int wrapped;
        int stringDirection;
        int sectionsCount;
        int string1Idx;
        int string2Idx;
    }

    public static class PointsCountAndSectionIdx {
        int pointsCount;
        int sectionIdx;
    }

    public static class TSection {
        TPoint begPoint;
        TPoint endPoint;
    }


    public static class TPoint {
        int x;
        int y;
    }

    public static PointsCountAndSectionIdx createPointsCountAndSectionIdx(int pointsCount, int sectionIdx) {
        PointsCountAndSectionIdx data = new PointsCountAndSectionIdx();
        setPointsCountAndSectionIdx(data, pointsCount, sectionIdx);
        return data;
    }

    public static void setPointsCountAndSectionIdx(PointsCountAndSectionIdx data, int pointsCount, int sectionIdx) {
        data.pointsCount = pointsCount;
        data.sectionIdx = sectionIdx;
    }

    public static TPoint getFirstPathPoint(TSectionPath path){
        return path.sections[0].begPoint;
    }

    public static TSection Section(TPoint begPoint, TPoint endPoint) {
        TSection section = new TSection();
        setSection(section, begPoint, endPoint);
        return section;
    }

    public static void setSection(TSection section, TPoint begPoint, TPoint endPoint) {
        section.begPoint = begPoint;
        section.endPoint = endPoint;
    }

    public static TSectionPath createPath() {
        TSectionPath path = new TSectionPath();
        setSectionPath(path);
        return path;
    }

    public static void setSectionPath(TSectionPath path) {
        path.sections = new TSection[MAX_POINTS];
        path.sectionsCount = 0;
        path.realPointsCount = 0;
        path.wrapped = NOT_WRAP;
        path.stringDirection = TOWARDS_END;
    }

    public static TPoint point(int x, int y) {
        TPoint point = new TPoint();
        setPoint(point, x, y);
        return point;
    }

    public static void setPoint(TPoint point, int x, int y) {
        point.x = x;
        point.y = y;
    }

    public static void printSection(TSection section) {
        print("(" + section.begPoint.x + "," + section.begPoint.y + ")");
    }

    public static void printPathArray(TSectionPath cornerPath) {
        print("{");
        for (int i = 0; i < cornerPath.sectionsCount; i++) {
            TSection currSection = cornerPath.sections[i];
            print("[(" + currSection.begPoint.x + "," + currSection.begPoint.y + "),(" + currSection.endPoint.x + "," + currSection.endPoint.y + ")], ");
        }
        print("}");
    }

    public static int safeModulo(int n, int divisor) {
        return (n % divisor + divisor) % divisor;
    }

    public static int getSectionLength(TSection section) {
        if (!isSectionValid(section.begPoint, section.endPoint)) return 0;
        if (arePointsEqual(section.begPoint, section.endPoint)) return 1;
        return Math.max(Math.abs(section.endPoint.x - section.begPoint.x) + 1,
                Math.abs(section.endPoint.y - section.begPoint.y) + 1);
    }

    public static TPoint getSectionNthPoint(TSection section, int n) {
        n = n - 1;
        int sectionLength = getSectionLength(section);
        if (n >= sectionLength - 1) return section.endPoint;
        if (n <= 0) return section.begPoint;

        TPoint tempPoint = point(section.begPoint.x, section.begPoint.y);
        int dx = getDirection(section.begPoint.x, section.endPoint.x);
        int dy = getDirection(section.begPoint.y, section.endPoint.y);
        for (int i = 0; i < n; i++) {
            tempPoint.x += dx;
            tempPoint.y += dy;
        }
        return tempPoint;
    }


    public static boolean isPointInSegment(TPoint targetPoint, TPoint firstCorner, TPoint secondCorner) {
        if (arePointsEqual(targetPoint, firstCorner) || arePointsEqual(targetPoint, secondCorner)) return true;
        if (!isSectionValid(firstCorner, secondCorner)) return false;

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

    public static void printPoint(TPoint point) {
        print("(" + point.x + "," + point.y + ")");
    }


    public static boolean arePointsEqual(TPoint point1, TPoint point2) {
        return point1.x == point2.x && point1.y == point2.y;
    }

    public static boolean isPointInPath(TSectionPath sectionPath, TPoint targetPoint) {
        if (sectionPath.sectionsCount == 0) return false;

        for (int i = 0; i < sectionPath.sectionsCount; i++) {
            TPoint firstCorner = sectionPath.sections[i].begPoint;
            TPoint secondCorner = sectionPath.sections[i].endPoint;
            if (isPointInSegment(targetPoint, firstCorner, secondCorner)) return true;
        }
        return false;
    }

    public static TSection getLastSegmentFromPath(TSectionPath sectionPath) {
        return sectionPath.sections[sectionPath.sectionsCount - 1];
    }

    public static boolean isSectionValid(TPoint point1, TPoint point2) {
        int xDifference = Math.abs(point1.x - point2.x);
        int yDifference = Math.abs(point1.y - point2.y);
        return point1.x == point2.x || point1.y == point2.y || xDifference == yDifference;
    }

    public static int getDirection(int a, int b) {
        if (b - a > 0) return 1;
        else if (b - a == 0) return 0;
        else return -1;
    }

    public static boolean sectionsOverlap(TSection section1, TSection section2) {
        int section1Length = getSectionLength(section1);
        for (int n = 1; n <= section1Length; n++) {
            TPoint NthPoint = getSectionNthPoint(section1, n);
            if (isPointInSegment(NthPoint, section2.begPoint, section2.endPoint)) return true;
        }
        return false;
    }

    public static int getPathLength(TSectionPath path) {
        if (path.sectionsCount == 0) return 0;
        TPoint firstPoint = path.sections[0].begPoint;
        TPoint lastPoint = path.sections[path.sectionsCount - 1].endPoint;
        int pointCount = getSectionLength(path.sections[0]);
        TPoint prevEndPoint = path.sections[0].endPoint;

        for (int i = 1; i < path.sectionsCount; i++) {
            TSection currSection = path.sections[i];
            TPoint currBegPoint = currSection.begPoint;
            pointCount += getSectionLength(currSection);
            if (arePointsEqual(currBegPoint, prevEndPoint)) pointCount--;
            prevEndPoint = currSection.endPoint;
        }
        if (arePointsEqual(firstPoint, lastPoint)) pointCount--;

        return pointCount;
    }

    public static TPoint iterateBackwardsToFindNth(TSection section, int currLength, int n) {
        int currIdx = currLength - 1;
        int dx = getDirection(section.begPoint.x, section.endPoint.x);
        int dy = getDirection(section.begPoint.y, section.endPoint.y);
        TPoint tempPoint = point(section.endPoint.x, section.endPoint.y);
        while (n != currIdx) {
            currIdx--;
            tempPoint.x -= dx;
            tempPoint.y -= dy;
        }
        return tempPoint;
    }


    public static PointsCountAndSectionIdx findSectionWithNthIdx(TSectionPath sectionPath, int n) {
        if (n < 0) return createPointsCountAndSectionIdx(getSectionLength(sectionPath.sections[0]), 0);
        if (n > getPathLength(sectionPath) - 1)
            return createPointsCountAndSectionIdx(getPathLength(sectionPath), sectionPath.sectionsCount - 1);

        int currPointCount = getSectionLength(sectionPath.sections[0]);
        int currSecIdx = 0;

        while (currPointCount - 1 < n) {
            currSecIdx++;
            TPoint prevEnd = sectionPath.sections[currSecIdx - 1].endPoint;
            TPoint currBeg = sectionPath.sections[currSecIdx].begPoint;
            currPointCount += getSectionLength(sectionPath.sections[currSecIdx]);
            if (arePointsEqual(prevEnd, currBeg)) currPointCount--;
        }
        return createPointsCountAndSectionIdx(currPointCount, currSecIdx);
    }


    public static TPoint getPathNthPoint(TSectionPath sectionPath, int n) {
        n--;
        if (n < 0) return sectionPath.sections[0].begPoint;
        if (n > getPathLength(sectionPath) - 1) return sectionPath.sections[sectionPath.sectionsCount - 1].endPoint;
        PointsCountAndSectionIdx pointsCountAndSectionIdx = findSectionWithNthIdx(sectionPath, n);
        int currPointCount = pointsCountAndSectionIdx.pointsCount;
        int sectionWithNthIdx = pointsCountAndSectionIdx.sectionIdx;

        return iterateBackwardsToFindNth(sectionPath.sections[sectionWithNthIdx], currPointCount, n);
    }

    public static void drawPoint(TPoint point, char c) {
        gotoxy(point.x, point.y);
        print(c);
    }

    public static void drawSection(TSection section, char c) {
        if (!isSectionValid(section.begPoint, section.endPoint)) {
            drawPoint(section.begPoint, c);
            drawPoint(section.endPoint, c);
            return;
        }
        int dx = getDirection(section.begPoint.x, section.endPoint.x);
        int dy = getDirection(section.begPoint.y, section.endPoint.y);
        TPoint tempPoint = point(section.begPoint.x, section.begPoint.y);

        while (!arePointsEqual(tempPoint, section.endPoint)) {
            drawPoint(tempPoint, c);
            tempPoint.x += dx;
            tempPoint.y += dy;
        }
        drawPoint(tempPoint, c);
    }

    public static void drawSectionPath(TSectionPath sectionPath, char c) {
        for (int i = 0; i < sectionPath.sectionsCount; i++) {
            drawSection(sectionPath.sections[i], c);
        }
    }


    public static void addPath(TSectionPath basePath, TSectionPath addPath) {
        int addSectionsCount = addPath.sectionsCount;
        if (addSectionsCount == 0) return;
        for (int i = 1; i < addPath.sectionsCount; i++) {
            addSection(basePath, addPath.sections[i]);
        }
    }


    public static void addSection(TSectionPath sectionPath, TSection section) {
        if (!isSectionValid(section.begPoint, section.endPoint)) return;
        int lastSectionIdx = sectionPath.sectionsCount - 1;
        sectionPath.sections[lastSectionIdx + 1] = section;
        sectionPath.sectionsCount++;
    }

    public static void addMultipleSegments(TSectionPath sectionPath, TSection... sections) {
        for (TSection section : sections) {
            addSection(sectionPath, section);
        }
    }

    static String sliceString(String text, int leftIdx, int rigthIdx) {
        String newText = "";
        for (int i = 0; i < text.length(); i++) {
            if (i >= leftIdx && i <= rigthIdx) newText += text.charAt(i);
        }
        return newText;
    }


    static public void writeTowardsEnd(TSectionPath sectionPath, String text, int startIdx) {
        int pathLength = getPathLength(sectionPath);
        if (sectionPath.wrapped == WRAP) {
            startIdx = safeModulo(startIdx, pathLength);
        } else {
            if (startIdx >= pathLength) return;
            if (startIdx < 0) {
                text = sliceString(text, Math.abs(startIdx), text.length() - 1);
                startIdx = 0;
            }
        }
        if (text.length() > pathLength)
            text = sliceString(text, 0, pathLength - 1);
        if (sectionPath.sectionsCount < 1) return;
        if (text.length() == 0) return;


        PointsCountAndSectionIdx pointsCountAndSectionIdx = findSectionWithNthIdx(sectionPath, startIdx);
        int currSectionIdx = pointsCountAndSectionIdx.sectionIdx;
        TSection currSection = sectionPath.sections[currSectionIdx];
        int currPointsCount = pointsCountAndSectionIdx.pointsCount;
        int currTextIdx = 0;


        int currIdx = currPointsCount -1;
        int dx = getDirection(currSection.begPoint.x, currSection.endPoint.x);
        int dy = getDirection(currSection.begPoint.y, currSection.endPoint.y);
        TPoint tempPoint = point(currSection.endPoint.x, currSection.endPoint.y);
        //searches for start point
        while (startIdx != currIdx) {
            currIdx--;
            tempPoint.x -= dx;
            tempPoint.y -= dy;
        }
//        tempPoint.x -= dx;
//        tempPoint.y -= dy;

        //starts writing string
        while (currTextIdx < text.length()) {
            if (currSectionIdx >= sectionPath.sectionsCount && sectionPath.wrapped == WRAP) {
                currPointsCount = 0;
                currSectionIdx = 0;
                currSection = sectionPath.sections[currSectionIdx];
            } else if (currSectionIdx >= sectionPath.sectionsCount) return;
            if (arePointsEqual(tempPoint, currSection.endPoint)) {
                drawPoint(tempPoint, text.charAt(currTextIdx));
                currTextIdx++;
                if (currTextIdx >= text.length()) return;
                TPoint pastEndPoint = currSection.endPoint;
                currSectionIdx++;
                currPointsCount++;
                if (currSectionIdx >= sectionPath.sectionsCount && sectionPath.wrapped == WRAP) {
                    currPointsCount = 0;
                    currSectionIdx = 0;
                } else if (currSectionIdx >= sectionPath.sectionsCount) return;
                currSection = sectionPath.sections[currSectionIdx];
                dx = getDirection(currSection.begPoint.x, currSection.endPoint.x);
                dy = getDirection(currSection.begPoint.y, currSection.endPoint.y);
                tempPoint = point(currSection.begPoint.x, currSection.begPoint.y);
                if (arePointsEqual(tempPoint, pastEndPoint)) {
                    tempPoint.x += dx;
                    tempPoint.y += dy;
                }

            }
            drawPoint(tempPoint, text.charAt(currTextIdx));
            currTextIdx++;
            if (currTextIdx >= text.length()) return;
            currPointsCount++;
            tempPoint.x += dx;
            tempPoint.y += dy;

        }
    }


    static public void writeTowardsBeginning(TSectionPath sectionPath, String text, int startIdx) {
        int pathLength = getPathLength(sectionPath);
        if (sectionPath.wrapped == WRAP) {
            startIdx = safeModulo(startIdx, pathLength);
        } else {
            if (startIdx < 0) return;
            if (startIdx >= pathLength) {
                text = sliceString(text, startIdx - pathLength + 1, text.length() - 1);
                startIdx = pathLength - 1;
            }
        }
        if (text.length() > pathLength)
            text = sliceString(text, 0, pathLength - 1);
        if (sectionPath.sectionsCount < 1) return;
        if (text.length() == 0) return;


        PointsCountAndSectionIdx pointsCountAndSectionIdx = findSectionWithNthIdx(sectionPath, startIdx);
        int currSectionIdx = pointsCountAndSectionIdx.sectionIdx;
        TSection currSection = sectionPath.sections[currSectionIdx];
        int currPointsCount = pointsCountAndSectionIdx.pointsCount;
        int currTextIdx = 0;


        int currIdx = currPointsCount - 1;
        int dx = getDirection(currSection.begPoint.x, currSection.endPoint.x);
        int dy = getDirection(currSection.begPoint.y, currSection.endPoint.y);
        TPoint tempPoint = point(currSection.endPoint.x, currSection.endPoint.y);
        //searches for start point
        while (startIdx != currIdx) {
            currIdx--;
            tempPoint.x -= dx;
            tempPoint.y -= dy;
        }
//        tempPoint.x -= dx;
//        tempPoint.y -= dy;

        //starts writing string
        while (currTextIdx < text.length()) {
            if (currSectionIdx < 0 && sectionPath.wrapped == WRAP) {
                currPointsCount = 0;
                currSectionIdx = sectionPath.sectionsCount - 1;
                currSection = sectionPath.sections[currSectionIdx];
            } else if (currSectionIdx < 0) return;
            if (arePointsEqual(tempPoint, currSection.begPoint)) {
                drawPoint(tempPoint, text.charAt(currTextIdx));
                currTextIdx++;
                if (currTextIdx >= text.length()) return;
                TPoint pastBegPoint = currSection.begPoint;
                currSectionIdx--;
                currPointsCount++;
                if (currSectionIdx < 0 && sectionPath.wrapped == WRAP) {
                    currPointsCount = 0;
                    currSectionIdx = sectionPath.sectionsCount - 1;
                } else if (currSectionIdx < 0) return;
                currSection = sectionPath.sections[currSectionIdx];
                dx = getDirection(currSection.begPoint.x, currSection.endPoint.x);
                dy = getDirection(currSection.begPoint.y, currSection.endPoint.y);
                tempPoint = point(currSection.endPoint.x, currSection.endPoint.y);
                if (arePointsEqual(tempPoint, pastBegPoint)) {
                    tempPoint.x -= dx;
                    tempPoint.y -= dy;
                }
            }
            drawPoint(tempPoint, text.charAt(currTextIdx));
            currTextIdx++;
            if (currTextIdx >= text.length()) return;
            currPointsCount++;
            tempPoint.x -= dx;
            tempPoint.y -= dy;

        }
    }


    static public void writeStringOnPath(TSectionPath sectionPath, String text, int startIdx) {
        int writingDirection = sectionPath.stringDirection;
        if (writingDirection == TOWARDS_END) writeTowardsEnd(sectionPath, text, startIdx);
        if (writingDirection == TOWARDS_BEGINNING) writeTowardsBeginning(sectionPath, text, startIdx);
    }

    static boolean hitStrings(int s1_first, int s1_last, int s2_first, int s2_last) {
        return  Math.abs(s1_first - s2_first) < 2
                || Math.abs(s1_last  - s2_last)  < 2
                || Math.abs(s1_first - s2_last)  < 2
                || Math.abs(s1_last  - s2_first) < 2;
    }

    static void updateSmallFrame(TSectionPath path, int bee1Idx, int bee2Idx) {
        String text1 = "**Bee ready!";
        writeStringOnPath(path, text1, bee1Idx);
        writeStringOnPath(path, text1, bee2Idx);
    }

    public static void cancelChar(TSectionPath sectionPath, int idx, char c, String text){
        int textLength = text.length();
        if(sectionPath.stringDirection == 0) {
            drawPoint(getPathNthPoint(sectionPath,idx - 1), c);
            drawPoint(getPathNthPoint(sectionPath, idx + textLength + 2), c);
        }
        else{
            drawPoint(getPathNthPoint(sectionPath,idx - 1), c);
            drawPoint(getPathNthPoint(sectionPath, idx + textLength + 2), c);
        }
    }


    static void updateBigFrame(TSectionPath path, int startIdx, int welcomeIdx, String welcome, String start) {


        path.stringDirection = (path.stringDirection + 1) % 2;
        writeStringOnPath(path, welcome, welcomeIdx);
        path.stringDirection = (path.stringDirection + 1) % 2;
        writeStringOnPath(path, start, startIdx);



    }


    static void main(String[] args) {
        clrscr();
        clrscr();
        TSectionPath path1 = createElements(TSectionPath.class);
        TSectionPath path2 = createElements(TSectionPath.class);



        setSectionPath(path1);
        setSectionPath(path2);


        String welcomeClock = "**>>Welcome to our game!<<";
        String welcomeAntiClock = ">>Welcome to our game!<<**";
        String startAntiClock = "**>>Press s to start<<";
        String startClock = ">>Press s to start<<**";

        String currWelcome = welcomeAntiClock;
        String currStart = startClock;


        addMultipleSegments(path1, Section(point(1, 1), point(120, 1)), Section(point(120, 1), point(120, 30)), Section(point(120, 30), point(1, 30)), Section(point(1, 30), point(1, 1)));
        addMultipleSegments(path2, Section(point(50, 7), point(70, 7)), Section(point(70, 7), point(70, 22)), Section(point(70, 22), point(50, 22)), Section(point(50, 22), point(50, 7)));

        setfgcolor(yellow);
        drawSectionPath(path1, '*');
        setfgcolor(green);
        drawSectionPath(path2, '*');
        path1.realPointsCount = getPathLength(path1);
        path2.realPointsCount = getPathLength(path2);


        path1.wrapped = 1;
        path1.stringDirection = 0;
        path2.wrapped = 1;
        int currBeeIdx = 0;


        int currWelcomeIdx = (120 - 24) / 2;
        int lastWelcomeIdx = currWelcomeIdx + 24;
        int currStartIdx = 270 - currWelcomeIdx + 1;
        int lastStartIdx = currStartIdx - 20;

        int welcomeDirection = -1;
        int startDirection = 1;
        path1.stringDirection = 1;
        int flag = 0;
        int firstStartCollisionIdx = currStartIdx - 2;
        int lastStartCollisionIdx = lastStartIdx + 2;
        int firstWelcomeCollisionIdx = currWelcomeIdx + 2;
        int lastWelcomeCollisionIdx = lastWelcomeIdx - 2;



        while (true) {
            if (flag == 0) {
                setfgcolor(green);
                updateSmallFrame(path2, currBeeIdx, currBeeIdx + path2.realPointsCount / 2);
                setfgcolor(yellow);
                updateBigFrame(path1, currStartIdx, currWelcomeIdx, welcomeAntiClock, startClock);
            } else {
                setfgcolor(green);
                updateSmallFrame(path2, currBeeIdx, currBeeIdx + path2.realPointsCount / 2);
                setfgcolor(yellow);
                updateBigFrame(path1, currStartIdx, currWelcomeIdx, welcomeClock, startAntiClock);
            }

            delay(10);
            currBeeIdx++;
            if(welcomeDirection == 1){
                 firstStartCollisionIdx = currStartIdx;
                 lastStartCollisionIdx = lastStartIdx - 2;
                 firstWelcomeCollisionIdx = currWelcomeIdx;
                 lastWelcomeCollisionIdx = lastWelcomeIdx + 1;
            }
            else {
                 lastStartCollisionIdx = currStartIdx;
                 firstStartCollisionIdx = lastStartIdx;
                 lastWelcomeCollisionIdx = currWelcomeIdx;
                 firstWelcomeCollisionIdx = lastWelcomeIdx;
            }
            if (hitStrings(firstStartCollisionIdx, lastStartCollisionIdx, firstWelcomeCollisionIdx, lastWelcomeCollisionIdx)) {
                delay(2000);

                if (flag == 0) {
                    setfgcolor(green);
                    updateSmallFrame(path2, currBeeIdx, currBeeIdx + path2.realPointsCount / 2);
                    setfgcolor(yellow);
                    updateBigFrame(path1, currStartIdx, currWelcomeIdx, welcomeClock, startAntiClock);
                } else {
                    setfgcolor(green);
                    updateSmallFrame(path2, currBeeIdx, currBeeIdx + path2.realPointsCount / 2);
                    setfgcolor(yellow);
                    updateBigFrame(path1, currStartIdx, currWelcomeIdx, welcomeAntiClock, startClock);
                }
                flag = (flag + 1) % 2;
                startDirection *= -1;
                welcomeDirection *= -1;


            }
            delay(50);
            currStartIdx = (((currStartIdx + startDirection) % path1.realPointsCount) + path1.realPointsCount) % path1.realPointsCount;
            currWelcomeIdx = (((currWelcomeIdx + welcomeDirection) % path1.realPointsCount) + path1.realPointsCount) % path1.realPointsCount;
            lastWelcomeIdx = (((lastWelcomeIdx + welcomeDirection) % path1.realPointsCount) + path1.realPointsCount) % path1.realPointsCount;
            lastStartIdx = (((lastStartIdx + startDirection) % path1.realPointsCount) + path1.realPointsCount) % path1.realPointsCount;


        }
    }
}



