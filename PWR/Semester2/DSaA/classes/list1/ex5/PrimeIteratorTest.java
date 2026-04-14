import static org.junit.Assert.*;

public class PrimeIteratorTest {

    @org.junit.Test
    public void next() {
        PrimeIterator it = new PrimeIterator(100);
        assertEquals(2, it.next());
        assertEquals(3, it.next());
        assertEquals(5, it.next());
        assertEquals(7, it.next());
        assertEquals(11, it.next());
        assertEquals(13, it.next());
        assertEquals(17, it.next());
        assertEquals(19, it.next());
        assertEquals(23, it.next());
        assertEquals(29, it.next());
        assertEquals(31, it.next());
        assertEquals(37, it.next());
        assertEquals(41, it.next());
        assertEquals(43, it.next());
        assertEquals(47, it.next());
    }
}