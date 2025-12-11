//Kacper Gęśla 290168

import static java.lang.IO.print;
import static java.lang.IO.println;
import static term.term.*;

class Main {
    final static int MAX_POINTS = 2000;
    final static int WRAP = 1;
    final static int NOT_WRAP = 0;
    final static int TOWARDS_END = 0;
    final static int TOWARDS_BEGINNING = 1;
    final static String BIG_RECTANGLE_TEXT1 = ">>Welcome to our world!<<";
    final static String BIG_RECTANGLE_TEXT2 = ">>Press space to start<<";

    public static class TSectionPath {
        TSection[] sections; //stores only corners, beggining and end
        int realPointsCount;
        int wrapped;
        int stringDirection;
        int sectionsCount;
        int color;
        int past1Idx;
        int past2Idx;
        double string1Idx;
        double string2Idx;
        int roundedString1Idx;
        int roundedString2Idx;
        int pathLength;
    }

    public static class PointsCountAndSectionIdx {
        int pointsCount;
        int sectionIdx;
    }

    public static class TSection {
        TPoint begPoint;
        TPoint endPoint;
    }

    public static class TBoard{
        TSectionPath bigRectangle;
        TSectionPath smallRectangle;
        TSectionPath weirdPath;
        TSectionPath spiral;
        boolean breakFlag;
        int stringSpeed;
        boolean bounceFlag;
        int loopCount;
    }






    static void setTBoard(TBoard board, TSectionPath bigRectangle, TSectionPath smallRectange, TSectionPath weirdPath, TSectionPath spiral){
        board.bigRectangle = bigRectangle;
        board.spiral = spiral;
        board.smallRectangle = smallRectange;
        board.weirdPath = weirdPath;
        board.breakFlag =false;
        board.bounceFlag = false;
        board.stringSpeed = 20;
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

    public static TSection section(TPoint begPoint, TPoint endPoint) {
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
        path.wrapped = WRAP;
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

    static void changeDirectionOfString(TSectionPath sectionPath){
        switch (sectionPath.stringDirection){
            case 1:
                sectionPath.stringDirection = 0;
                break;
            case 0:
                sectionPath.stringDirection = 1;
                break;
        }
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

        return text.substring(leftIdx, rigthIdx + 1);
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

    static TSectionPath createSquarePath(TSectionPath path, TPoint A, TPoint B, TPoint C, TPoint D){
        addMultipleSegments(path, section(A,B), section(B,C), section(C,D), section(D,A));
        return path;
    }

    static void interpretInputKey(TBoard board, String key){
        switch (key){
            case "q":
                board.breakFlag = true;
                break;
            case "p":
                changeDirectionOfString(board.weirdPath);
                break;
            default:
                break;
        }
    }

    static TSectionPath createWeirdPath(TSectionPath path){
        addMultipleSegments(path, section(point(17,3), point(55, 3)),
                                  section(point(55,3), point(55,6)),
                                  section(point(55,23),point(55, 27)),
                                  section(point(55,27), point(61,27)),
                                  section(point(61,27), point(65,23)),
                                  section(point(71,18), point(76,13)),
                                  section(point(76,13), point(76,9)),
                                  section(point(76,9), point(71,9)),
                                  section(point(49, 9), point(35, 9)),
                                  section(point(35,9), point(35,14)),
                                  section(point(35,19), point(35,27)),
                                  section(point(35,27), point(51,27)),
                                  section(point(65,27), point(90,27)),
                                  section(point(90,27),point(90,31)),
                                  section(point(90,31), point(100, 31)),
                                  section(point(100,31), point(100, 11)), //21 -22
                                  section(point(100, 7), point(100, 3)),
                                  section(point(100,3), point(35, 3)),
                                  section(point(35,3), point(35, 5)),
                                  section(point(35,5), point(58,5)),
                                  section(point(58,5), point(58,3)),
                                  section(point(58,3), point(65,3)),
                                  section(point(65,3), point(65,5)),
                                  section(point(65,5), point(76, 5)),
                                  section(point(76,5), point(76,7)),
                                  section(point(70, 31), point(17, 31)),
                                  section(point(17,31), point(17,25)),
                                  section(point(17,19), point(17, 14)),
                                  section(point(17, 10), point(17,3))  );

        return path;
    }

    static TSectionPath createSpirtalPath(TSectionPath path, TPoint centerPoint, int cycles){
        int i = 0;

        int len = 1;
        TPoint begPoint = point(centerPoint.x, centerPoint.y);
        TPoint endPoint = point(begPoint.x, begPoint.y);

        while(i < cycles){
            endPoint = point(begPoint.x, begPoint.y + len);
            addSection(path, section(begPoint, endPoint));
            begPoint = point(endPoint.x, endPoint.y);
            len += 3;
            endPoint = point(begPoint.x -len, begPoint.y);
            addSection(path,section(begPoint,endPoint));
            begPoint = point(endPoint.x, endPoint.y);
            endPoint = point(begPoint.x, begPoint.y - len);
            addSection(path,section(begPoint,endPoint));
            len+=3;
            begPoint = point(endPoint.x, endPoint.y);
            endPoint = point(begPoint.x + len, begPoint.y);
            addSection(path,section(begPoint,endPoint));
            begPoint = point(endPoint.x, endPoint.y);


            i++;
        }
        return path;
    }

    static void updateSmallRectangle(TBoard board, TSectionPath path, double time){
        int direction = (path.stringDirection == 0) ? 1 : -1;
        path.past1Idx = (int)path.string1Idx;
        path.string1Idx  = safeModulo(path.string1Idx + direction * board.stringSpeed * time, path.pathLength);
        path.string2Idx  = safeModulo(path.string1Idx + 35 + direction, path.pathLength);
    }

    static void updateBigRectangle(TBoard board, TSectionPath path){
        int direction = (path.stringDirection == 0) ? 1 : -1;
        path.past1Idx = path.string1Idx;
        path.past2Idx = path.string2Idx;
        path.string1Idx = safeModulo(path.string1Idx + direction, path.pathLength);
        path.string2Idx = safeModulo(path.string2Idx - direction, path.pathLength);
        if(Math.abs(path.string1Idx - path.string2Idx) <= 1 || Math.abs(path.string1Idx + BIG_RECTANGLE_TEXT1.length() - (path.string2Idx - BIG_RECTANGLE_TEXT2.length()) - 1) <= 1){
            path.stringDirection = (path.stringDirection + 1) % 2;
            board.bounceFlag = true;
        }



    }

    static void drawBoard(TBoard board){
        clrscr();
        cursor_hide();
        setfgcolor(board.smallRectangle.color);
        drawSectionPath(board.bigRectangle, '*');
        drawSectionPath(board.smallRectangle, '*');
        setfgcolor(board.weirdPath.color);
        drawSectionPath(board.weirdPath, '+');
        setfgcolor(board.spiral.color);
        drawSectionPath(board.spiral, '+');
    }

    static void controller(TBoard board){
        String keystr = "";
        if(board.loopCount % board.stringSpeed == 0 && keypressed()) keystr = readkeystr();
        model(board, keystr);
    }

    static void model(TBoard board, String key){
        interpretInputKey(board, key);
        updateSmallRectangle(board, board.smallRectangle);
        updateBigRectangle(board, board.bigRectangle);
        if(board.stringSpeed >= 6 && board.loopCount % board.stringSpeed ==0 ) board.stringSpeed -= 1;

        view(board);
    }

    static void view(TBoard board){
        setfgcolor(board.smallRectangle.color);
        writeStringOnPath(board.smallRectangle, "*", board.smallRectangle.past1Idx);
        writeStringOnPath(board.smallRectangle, "*", safeModulo(board.smallRectangle.past1Idx + 36, board.smallRectangle.pathLength));
        setfgcolor(board.bigRectangle.color);
        if(board.bigRectangle.stringDirection == 1) {
            writeStringOnPath(board.bigRectangle, "*", board.bigRectangle.past1Idx + BIG_RECTANGLE_TEXT1.length() - 1);
            writeStringOnPath(board.bigRectangle, "*", board.bigRectangle.past2Idx  - BIG_RECTANGLE_TEXT2.length() + 1 );
        }
        else{
            writeStringOnPath(board.bigRectangle, "*", board.bigRectangle.past1Idx);
            writeStringOnPath(board.bigRectangle, "*", board.bigRectangle.past2Idx);
        }
        writeStringOnPath(board.smallRectangle, "Be ready!", board.smallRectangle.string1Idx);
        writeStringOnPath(board.smallRectangle, "Be ready!", board.smallRectangle.string2Idx);
        setfgcolor(ltgreen);
        writeTowardsEnd(board.bigRectangle, BIG_RECTANGLE_TEXT1, board.bigRectangle.string1Idx);
        setfgcolor(blue);
        writeTowardsBeginning(board.bigRectangle, BIG_RECTANGLE_TEXT2, board.bigRectangle.string2Idx);

    }



    static void prepareBoard(TBoard board){
        setTBoard(board,createSquarePath(createPath(),point(1,1), point(120,1), point(120, 36), point(1,36)),
                createSquarePath(createPath(),point(50,7), point(70,7), point(70, 22), point(50,22)),
                createWeirdPath(createPath()),
                createSpirtalPath(createPath(), point(61, 15), 2));
        board.smallRectangle.color = white;
        board.bigRectangle.color = white;
        board.weirdPath.color = green;
        board.spiral.color = green;
        board.bigRectangle.pathLength = getPathLength(board.bigRectangle);
        board.smallRectangle.pathLength = getPathLength(board.smallRectangle);
        board.weirdPath.pathLength = getPathLength(board.weirdPath);
        board.spiral.pathLength = getPathLength(board.spiral);
        board.bigRectangle.stringDirection = 1;
        board.smallRectangle.string1Idx = 0;
        board.bigRectangle.string1Idx = 47;
        board.bigRectangle.string2Idx = 225;
        drawBoard(board);

    }

    static void startAnimation(TBoard board){
        cursor_hide();
        while(!board.breakFlag){
            if(board.bounceFlag){
                board.stringSpeed = 20;
                board.bounceFlag = false;
            }
            board.loopCount = (board.loopCount + 1) % 1000;
            controller(board);
        }
        readkey();
    }

    static void main(String[] args) {
        TBoard board = createElements(TBoard.class);
        prepareBoard(board);
        startAnimation(board);
    }
}



