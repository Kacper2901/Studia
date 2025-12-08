//Kacper Gęśla 290168

import static java.lang.IO.print;
import static term.term.*;

public class Main {

    final static int MAX_POINTS = 2000;
    final static int WRAP = 1;
    final static int NOT_WRAP = 0;
    static final int TOWARDS_END = 0;
    static final int TOWARDS_BEGINNING = 1;


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

    public TCornerPath createPath() {
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

    public void printPathArray(TCornerPath cornerPath) {
        print("[");
        for (int i = 0; i < cornerPath.cornersCount; i++) {
            TPoint currPoint = cornerPath.corners[i];
            print("(" + currPoint.x + "," + currPoint.y + "), ");
        }
        print("]");
    }

    public int safeModulo(int n, int divisor) {
        return (n % divisor + divisor) % divisor;
    }


    public void addCorner(TCornerPath cornersPath, TPoint newCorner) {
        int prevLastIdx = cornersPath.cornersCount - 1;
        cornersPath.corners[prevLastIdx + 1] = newCorner;
        cornersPath.cornersCount++;
        TPoint prevLastCorner = cornersPath.corners[Math.max(prevLastIdx, 0)];

        if (cornersPath.cornersCount == 1) cornersPath.realPointsCount = 1;
        if (cornersPath.cornersCount >= 2) cornersPath.realPointsCount += countSegmentLength(prevLastCorner, newCorner);
    }

    public boolean isPointBetweenCorners(TPoint targetPoint, TPoint firstCorner, TPoint secondCorner) {
        if (arePointsEqual(targetPoint, firstCorner) || arePointsEqual(targetPoint, secondCorner)) return true;
        if (!isSegmentValid(firstCorner, secondCorner)) return false;

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

    public boolean arePointsEqual(TPoint point1, TPoint point2) {
        return point1.x == point2.x && point1.y == point2.y;
    }

    public boolean isPointInPath(TCornerPath cornerPath, TPoint targetPoint) {
        if (cornerPath.cornersCount == 0) return false;
        if (cornerPath.cornersCount == 1) return arePointsEqual(targetPoint, cornerPath.corners[0]);

        for (int i = 0; i < cornerPath.cornersCount - 1; i++) {
            TPoint firstCorner = cornerPath.corners[i];
            TPoint secondCorner = cornerPath.corners[i + 1];
            if (isPointBetweenCorners(targetPoint, firstCorner, secondCorner)) return true;
        }
        return false;
    }

    public TPoint getLastPointFromPath(TCornerPath cornerPath) {
        return cornerPath.corners[cornerPath.cornersCount - 1];
    }

    public boolean isSegmentValid(TPoint point1, TPoint point2) {
        int xDifference = Math.abs(point1.x - point2.x);
        int yDifference = Math.abs(point1.y - point2.y);
        return point1.x == point2.x || point1.y == point2.y || xDifference == yDifference;
    }

    public int getDirection(int a, int b) {
        if (b - a > 0) return 1;
        else if (b - a == 0) return 0;
        else return -1;
    }

    public void drawPoint(TPoint point, char c) {
        gotoxy(point.x, point.y);
        print(c);
    }

    public void drawPointOffset(TPoint point, char c, TPoint offset) {
        gotoxy(point.x + offset.x, point.y + offset.y);
        print(c);
    }

    public void tryToDrawSegment(TPoint firstCorner, TPoint secondCorner, char c) {
        if (!isSegmentValid(firstCorner, secondCorner)) {
            drawPoint(firstCorner, c);
            drawPoint(secondCorner, c);
            return;
        }
        int dx = getDirection(firstCorner.x, secondCorner.x);
        int dy = getDirection(firstCorner.y, secondCorner.y);
        TPoint tempPoint = point(firstCorner.x, firstCorner.y);

        while (!arePointsEqual(tempPoint, secondCorner)) {
            drawPoint(tempPoint, c);
            tempPoint.x += dx;
            tempPoint.y += dy;
        }
        drawPoint(tempPoint, c);
    }

    public void drawCornerPath(TCornerPath cornerPath, char c) {
        for (int i = 0; i < cornerPath.cornersCount - 1; i++) {
            tryToDrawSegment(cornerPath.corners[i], cornerPath.corners[i + 1], c);
        }
    }


    public void addPath(TCornerPath basePath, TCornerPath addPath) {
        int addCornersCount = addPath.cornersCount;
        TPoint lastBaseCorner = getLastPointFromPath(basePath);
        TPoint firstAddCorner = addPath.corners[0];

        if (addCornersCount == 0) return;
        if (!arePointsEqual(firstAddCorner, lastBaseCorner)) addCorner(basePath, firstAddCorner);
        for (int i = 1; i < addPath.cornersCount; i++) {
            addCorner(basePath, addPath.corners[i]);
        }
    }

    public int countSegmentLength(TPoint point1, TPoint point2) {
        return Math.max(Math.abs(point1.x - point2.x), Math.abs(point1.y - point2.y));
    }

    public void addSegment(TCornerPath cornerPath, TPoint point1, TPoint point2) {
        if (!isSegmentValid(point1, point2)) return;
        TPoint lastPoint = getLastPointFromPath(cornerPath);
        if (!arePointsEqual(point1, lastPoint)) addCorner(cornerPath, point1);
        addCorner(cornerPath, point2);
    }

    public void addMultipleSegments(TCornerPath cornerPath, TPoint... corners) {
        for (TPoint corner : corners) {
            int lastCornerIdx = cornerPath.cornersCount - 1;
            if (lastCornerIdx < 0 || !arePointsEqual(corner, cornerPath.corners[lastCornerIdx]))
                addCorner(cornerPath, corner);
        }
    }

    String sliceString(String text, int leftIdx, int rigthIdx) {
        String newText = "";
        for (int i = 0; i < text.length(); i++) {
            if (i >= leftIdx && i <= rigthIdx) newText += text.charAt(i);
        }
        return newText;
    }


    public void writeTowardsEnd(TCornerPath cornerPath, String text, int startIdx, TPoint offset) {
        if (text.length() > cornerPath.realPointsCount)
            text = sliceString(text, 0, cornerPath.realPointsCount - 1);
        if (cornerPath.cornersCount <= 1) return;
        if (text.length() == 0) return;
        int currRealIdx = -1;
        int printedChars = 0;
        int totalPoints = cornerPath.realPointsCount;

        if (cornerPath.wrapped == WRAP) {
            startIdx = safeModulo(startIdx, totalPoints);
        } else {
            if (startIdx >= totalPoints) return;
            if (startIdx < 0) {
                text = sliceString(text, Math.abs(startIdx), text.length() - 1);
                startIdx = 0;
            }
        }
        int currCornerIdx = 0;

        TPoint tempPoint = point(cornerPath.corners[currCornerIdx].x, cornerPath.corners[currCornerIdx].y);
        TPoint nextCorner = cornerPath.corners[currCornerIdx + 1];
        int dx = getDirection(tempPoint.x, nextCorner.x);
        int dy = getDirection(tempPoint.y, nextCorner.y);

        while (printedChars < text.length()) {

            if (currCornerIdx >= cornerPath.cornersCount - 1) {
                if (cornerPath.wrapped == NOT_WRAP) return;
                currCornerIdx = 0;
            }

            while (true) {
                delay(250);

                currRealIdx++;

                if (currRealIdx >= startIdx) {
                    drawPointOffset(tempPoint, text.charAt(printedChars), offset);
                    printedChars++;
                    if (printedChars >= text.length()) return;
                }
                if (arePointsEqual(tempPoint, nextCorner)) break;
                tempPoint.x += dx;
                tempPoint.y += dy;

            }

            currCornerIdx++;
            if (currCornerIdx >= cornerPath.cornersCount - 1) {
                if (cornerPath.wrapped == NOT_WRAP) return;
                currCornerIdx = 0;
                tempPoint = point(cornerPath.corners[0].x, cornerPath.corners[0].y);
                nextCorner = cornerPath.corners[currCornerIdx + 1];
                dx = getDirection(tempPoint.x, nextCorner.x);
                dy = getDirection(tempPoint.y, nextCorner.y);
                continue;
            }
            nextCorner = cornerPath.corners[currCornerIdx + 1];
            dx = getDirection(tempPoint.x, nextCorner.x);
            dy = getDirection(tempPoint.y, nextCorner.y);
            tempPoint.x += dx;
            tempPoint.y += dy;

        }
    }


    public void writeTowardsBeginning(TCornerPath cornerPath, String text, int startIdx, TPoint offset) {
        startIdx = cornerPath.realPointsCount - 1 - startIdx;
        if (text.length() > cornerPath.realPointsCount)
            text = sliceString(text, 0, cornerPath.realPointsCount - 1);
        if (cornerPath.cornersCount <= 1) return;
        if (text.length() == 0) return;

        int currRealIdx = -1;
        int printedChars = 0;
        int totalPoints = cornerPath.realPointsCount;

        if (cornerPath.wrapped == WRAP) {
            startIdx = safeModulo(startIdx, totalPoints);
        } else {
            if (startIdx >= totalPoints) return;
            if (startIdx < 0) {
                text = sliceString(text, Math.abs(startIdx), text.length() - 1);
                startIdx = 0;
            }
        }

        int currCornerIdx = cornerPath.cornersCount - 1;

        TPoint tempPoint = point(cornerPath.corners[currCornerIdx].x,
                cornerPath.corners[currCornerIdx].y);
        TPoint nextCorner = cornerPath.corners[currCornerIdx - 1];
        int dx = getDirection(tempPoint.x, nextCorner.x);
        int dy = getDirection(tempPoint.y, nextCorner.y);

        while (printedChars < text.length()) {

            if (currCornerIdx <= 0) {
                if (cornerPath.wrapped == NOT_WRAP) return;
                currCornerIdx = cornerPath.cornersCount - 1;
            }

            while (true) {
                delay(250);

                currRealIdx++;

                if (currRealIdx >= startIdx) {
                    drawPointOffset(tempPoint, text.charAt(printedChars), offset);
                    printedChars++;
                    if (printedChars >= text.length()) return;
                }

                if (arePointsEqual(tempPoint, nextCorner)) break;

                tempPoint.x += dx;
                tempPoint.y += dy;
            }

            currCornerIdx--;

            if (currCornerIdx <= 0) {
                if (cornerPath.wrapped == NOT_WRAP) return;
                currCornerIdx = cornerPath.cornersCount - 1;
                tempPoint = point(cornerPath.corners[currCornerIdx].x,
                        cornerPath.corners[currCornerIdx].y);
                nextCorner = cornerPath.corners[currCornerIdx - 1];
                dx = getDirection(tempPoint.x, nextCorner.x);
                dy = getDirection(tempPoint.y, nextCorner.y);
                continue;
            }

            nextCorner = cornerPath.corners[currCornerIdx - 1];
            dx = getDirection(tempPoint.x, nextCorner.x);
            dy = getDirection(tempPoint.y, nextCorner.y);
            tempPoint.x += dx;
            tempPoint.y += dy;
        }
    }


    public void writeStringOnPathOffset(TCornerPath cornerPath, String text, int startIdx, TPoint offset) {
        int writingDirection = cornerPath.stringDirection;
        if (writingDirection == TOWARDS_END) writeTowardsEnd(cornerPath, text, startIdx, offset);
        if (writingDirection == TOWARDS_BEGINNING) writeTowardsBeginning(cornerPath, text, startIdx, offset);
    }


    public void writeStringOnPath(TCornerPath cornerPath, String text, int startIdx) {
        int writingDirection = cornerPath.stringDirection;
        if (writingDirection == TOWARDS_END) writeTowardsEnd(cornerPath, text, startIdx, point(0, 0));
        if (writingDirection == TOWARDS_BEGINNING) writeTowardsBeginning(cornerPath, text, startIdx, point(0, 0));
    }


    static void main(String[] args) {
        clrscr();
        Main main = new Main();
        TCornerPath cornerPath1 = createElements(TCornerPath.class);
        main.setCornerPath(cornerPath1);

        main.addMultipleSegments(cornerPath1, main.point(3, 1), main.point(3, 10), main.point(12, 10), main.point(12, 1), main.point(4, 1));


        cornerPath1.wrapped = WRAP;
        main.writeStringOnPathOffset(cornerPath1, "abcdefghijklmnoprstuwxyzabcdefghijklmnoprstuwxyz", 25, main.point(2, 2));
//    writeBackward(cornerPath1, "hello yusuf", 2, 0);
        TCornerPath cornerPath2 = createElements(TCornerPath.class);
        main.setCornerPath(cornerPath2);


//    drawCornerPath(cornerPath1, '*');
        readkey();


    }
}