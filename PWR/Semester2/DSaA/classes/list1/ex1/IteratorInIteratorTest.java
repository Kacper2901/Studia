import static org.junit.jupiter.api.Assertions.*;

class IteratorInIteratorTest {

    @org.junit.jupiter.api.Test
    void next() {
        Integer[] arr = {1,2,3,4,5,6,7,8,9,10,11,12};
        int k = 3;
        IteratorInIterator<Integer> it = new IteratorInIterator<>(new ArrayIterator<>(arr),k);
        assertEquals(k, it.next());
        assertEquals(2*k, it.next());
        assertEquals(3*k, it.next());
        assertEquals(true, it.hasNext());
        assertEquals(4*k, it.next());
        assertEquals(false, it.hasNext());
    }
}