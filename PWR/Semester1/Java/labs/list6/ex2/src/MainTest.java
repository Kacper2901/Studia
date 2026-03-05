import static org.junit.jupiter.api.Assertions.*;


class MainTest {

    static void assertPointEquals(Main.TPoint expected, Main.TPoint actual) {
        assertNotNull(actual, "Point is null");
        assertEquals(expected.x, actual.x, "X coordinate mismatch");
        assertEquals(expected.y, actual.y, "Y coordinate mismatch");
    }

    @org.junit.jupiter.api.Test
    void getSectionLengthTest() {
        Main.TSection section1 = Main.Section(Main.point(1,1), Main.point(1,5));
        Main.TSection section2 = Main.Section(Main.point(1,10), Main.point(1,1));
        Main.TSection section3 = Main.Section(Main.point(1,1), Main.point(5,5));
        Main.TSection section4 = Main.Section(Main.point(1,1), Main.point(13,5));
        Main.TSection section5 = Main.Section(Main.point(1,1), Main.point(1,1));

        assertEquals(5, Main.getSectionLength(section1));
        assertEquals(10, Main.getSectionLength(section2));
        assertEquals(5, Main.getSectionLength(section3));
        assertEquals(0, Main.getSectionLength(section4));
        assertEquals(1, Main.getSectionLength(section5));
    }

    @org.junit.jupiter.api.Test
    void getSectionNthPointTest() {
        Main.TPoint section1Nth = Main.getSectionNthPoint(Main.Section(Main.point(1,1), Main.point(1,5)), 1);
        Main.TPoint section2Nth = Main.getSectionNthPoint(Main.Section(Main.point(1,10), Main.point(1,1)), 3);
        Main.TPoint section3Nth = Main.getSectionNthPoint(Main.Section(Main.point(1,1), Main.point(1,5)), 10);
        Main.TPoint section4Nth = Main.getSectionNthPoint(Main.Section(Main.point(1,1), Main.point(1,5)), -1);
        Main.TPoint section5Nth = Main.getSectionNthPoint(Main.Section(Main.point(1,1), Main.point(5,5)), 2);

        assertPointEquals(Main.point(1,1),section1Nth);
        assertPointEquals(Main.point(1,8),section2Nth);
        assertPointEquals(Main.point(1,5),section3Nth);
        assertPointEquals(Main.point(1,1),section4Nth);
        assertPointEquals(Main.point(2,2),section5Nth);


    }

    @org.junit.jupiter.api.Test
    void sectionsOverlapTest() {
        Main.TSection section1 = Main.Section(Main.point(3,1), Main.point(13,11));
        Main.TSection section2 = Main.Section(Main.point(3,1), Main.point(3,10));
        Main.TSection section3 = Main.Section(Main.point(13,11), Main.point(18,16));
        Main.TSection section4 = Main.Section(Main.point(1,7), Main.point(7,1));
        Main.TSection section5 = Main.Section(Main.point(20,20), Main.point(20,20));

        assertTrue(Main.sectionsOverlap(section1,section2));
        assertTrue(Main.sectionsOverlap(section3,section1));
        assertTrue(Main.sectionsOverlap(section1,section4));
        assertFalse(Main.sectionsOverlap(section1,section5));

    }

    @org.junit.jupiter.api.Test
    void getPathLengthTest() {
        Main.TSectionPath sectionPath = Main.createPath();
        Main.TSection section1 = Main.Section(Main.point(3,1), Main.point(13,11));
        Main.TSection section2 = Main.Section(Main.point(3,1), Main.point(3,10));
        Main.TSection section3 = Main.Section(Main.point(13,11), Main.point(18,16));
        Main.TSection section4 = Main.Section(Main.point(1,7), Main.point(7,1));
        Main.TSection section5 = Main.Section(Main.point(20,20), Main.point(20,20));

        Main.addMultipleSegments(sectionPath, section2, section1, section3, section4, section5);
        //10 + 11 + 5   + 7   + 1
        assertEquals(34, Main.getPathLength(sectionPath));
    }

    @org.junit.jupiter.api.Test
    void getPathNthPointTest(){
        Main.TSectionPath sectionPath = Main.createPath();
        Main.TSection section1 = Main.Section(Main.point(3,1), Main.point(13,11));
        Main.TSection section2 = Main.Section(Main.point(3,1), Main.point(3,10));
        Main.TSection section3 = Main.Section(Main.point(13,11), Main.point(18,16));
        Main.TSection section4 = Main.Section(Main.point(1,7), Main.point(7,1));
        Main.TSection section5 = Main.Section(Main.point(20,20), Main.point(20,20));

        Main.addMultipleSegments(sectionPath, section2, section1, section3, section4, section5);
        assertPointEquals(Main.point(3,1), Main.getPathNthPoint(sectionPath, 1));
        assertPointEquals(Main.point(3,1), Main.getPathNthPoint(sectionPath, -5));
        assertPointEquals(Main.point(20,20), Main.getPathNthPoint(sectionPath, 34));
        assertPointEquals(Main.point(20,20), Main.getPathNthPoint(sectionPath, 35));
        assertPointEquals(Main.point(7,5), Main.getPathNthPoint(sectionPath, 15));
        assertPointEquals(Main.point(5,3), Main.getPathNthPoint(sectionPath, 31));
    }
}