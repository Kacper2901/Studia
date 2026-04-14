import org.junit.Test;

import static org.junit.Assert.*;

public class DoubleIteratorTest {

    @Test
    public void next() {
        Integer[] arr1 = {1,3,5,7,9,11,13,15};

        Integer[] arr2 = {2,4,6,8,10,12,14};
        DoubleIterator doubleIt = new DoubleIterator(new ArrayIterator(arr1), new ArrayIterator(arr2));

        assertEquals(true, doubleIt.hasNext());
        assertEquals(1, doubleIt.next());
        assertEquals(2, doubleIt.next());
        assertEquals(3, doubleIt.next());
        assertEquals(4, doubleIt.next());
        assertEquals(5, doubleIt.next());
        assertEquals(6, doubleIt.next());
        assertEquals(7, doubleIt.next());
        assertEquals(8, doubleIt.next());
        assertEquals(9, doubleIt.next());
    }
}