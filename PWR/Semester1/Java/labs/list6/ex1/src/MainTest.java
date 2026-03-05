import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void arePointsEqualTest() {

        assertFalse(Main.arePointsEqual(Main.point(5,7), Main.point(5,6)));
        assertFalse(Main.arePointsEqual(Main.point(10,3), Main.point(1,1)));
        assertFalse(Main.arePointsEqual(Main.point(5,4), Main.point(4,5)));
        assertTrue(Main.arePointsEqual(Main.point(1,1), Main.point(1,1)));
        assertTrue(Main.arePointsEqual(Main.point(1,4), Main.point(1,4)));
        assertTrue(Main.arePointsEqual(Main.point(76,56), Main.point(76,56)));
    }

    @Test
    void addSegmentTest() {
        Main.TCornerPath path = Main.createPath();
        Main.addCorner(path, Main.point(1,1));
        Main.addSegment(path, Main.point(1, 2), Main.point(1, 8));
        assertEquals(1, path.corners[0].x);
        assertEquals(1, path.corners[0].y);
        assertEquals(3, path.cornersCount);
        assertEquals(1, path.corners[1].x);
        assertEquals(2, path.corners[1].y);
        assertEquals(1, path.corners[2].x);
        assertEquals(8, path.corners[2].y);

        Main.addSegment(path, Main.point(9, 2), Main.point(12, 5));
        assertEquals(9, path.corners[3].x);
        assertEquals(2, path.corners[3].y);
        assertEquals(12, path.corners[4].x);
        assertEquals(5, path.corners[4].y);

    }


    @Test
    void isPointInPathTest() {
        Main.TCornerPath path = Main.createPath();

        Main.addCorner(path, Main.point(1, 1));
        Main.addCorner(path, Main.point(1, 2));
        Main.addMultipleSegments(path, Main.point(5,6), Main.point(5, 12));

        assertTrue(Main.isPointInPath(path, Main.point(1, 1)));
        assertTrue(Main.isPointInPath(path, Main.point(1, 2)));
        assertTrue(Main.isPointInPath(path, Main.point(5, 12)));
        assertTrue(Main.isPointInPath(path, Main.point(5, 6)));
        assertTrue(Main.isPointInPath(path, Main.point(3, 4)));
        assertTrue(Main.isPointInPath(path, Main.point(5, 8)));
        assertFalse(Main.isPointInPath(path, Main.point(6, 8)));
        assertFalse(Main.isPointInPath(path, Main.point(5, 13)));
        assertFalse(Main.isPointInPath(path, Main.point(4, 6)));

    }

    @Test
    void getLastPointFromPathTest() {
        Main.TCornerPath path = Main.createPath();

        Main.addCorner(path, Main.point(2, 2));
        Main.TPoint currLast = Main.getLastPointFromPath(path);
        assertTrue(Main.arePointsEqual(currLast, Main.point(2,2)));

        Main.addCorner(path, Main.point(3, 3));
        currLast = Main.getLastPointFromPath(path);
        assertTrue(Main.arePointsEqual(currLast, Main.point(3,3)));
        assertFalse(Main.arePointsEqual(currLast, Main.point(2,2)));

        Main.addMultipleSegments(path, Main.point(5,6), Main.point(5, 12));
        currLast = Main.getLastPointFromPath(path);
        assertTrue(Main.arePointsEqual(currLast, Main.point(5,12)));
        assertFalse(Main.arePointsEqual(currLast, Main.point(5,6)));
        assertFalse(Main.arePointsEqual(currLast, Main.point(2,2)));



    }

    @Test
    void isSegmentValidTest() {
        Main.TPoint originPoint = Main.point(5, 5);

        assertTrue(Main.isSegmentValid(originPoint, Main.point(5, 7)));
        assertTrue(Main.isSegmentValid(originPoint, Main.point(5, 2)));
        assertTrue(Main.isSegmentValid(originPoint, Main.point(1, 5)));
        assertTrue(Main.isSegmentValid(originPoint, Main.point(10, 5)));
        assertTrue(Main.isSegmentValid(originPoint, Main.point(7, 7)));
        assertTrue(Main.isSegmentValid(originPoint, Main.point(1, 1)));
        assertTrue(Main.isSegmentValid(originPoint, Main.point(3, 7)));
        assertTrue(Main.isSegmentValid(originPoint, Main.point(7, 3)));

        assertFalse(Main.isSegmentValid(originPoint, Main.point(6, 3)));
        assertFalse(Main.isSegmentValid(originPoint, Main.point(2, 7)));
        assertFalse(Main.isSegmentValid(originPoint, Main.point(3, 1)));
    }

}
