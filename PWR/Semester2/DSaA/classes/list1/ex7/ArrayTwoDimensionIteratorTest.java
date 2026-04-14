import static org.junit.Assert.*;

public class ArrayTwoDimensionIteratorTest {

    @org.junit.Test
    public void nextForwards() {
        Integer[][] arr = {{1,2,3},{5,6,7},{}, {9,10,11},{}};
        ArrayTwoDimensionIterator it = new ArrayTwoDimensionIterator<>(arr);

        assertEquals(1,it.next());
        assertEquals(2,it.next());
        assertEquals(3,it.next());
        assertEquals(5,it.next());
        assertEquals(6,it.next());
        assertEquals(7,it.next());
        assertEquals(9,it.next());
        assertEquals(10,it.next());
        assertEquals(11,it.next());
        assertEquals(false, it.hasNext());
    }

    @org.junit.Test
    public void nextBackwards() {
        Integer[][] arr = {{1,2,3},{5,6,7}, {9,10,11}};
        ArrayTwoDimensionIterator it = new ArrayTwoDimensionIterator<>(arr);

        assertEquals(11,it.next());
        assertEquals(10,it.next());
        assertEquals(9,it.next());
        assertEquals(7,it.next());
        assertEquals(6,it.next());
        assertEquals(5,it.next());
        assertEquals(3,it.next());
        assertEquals(2,it.next());
        assertEquals(1,it.next());
    }
}