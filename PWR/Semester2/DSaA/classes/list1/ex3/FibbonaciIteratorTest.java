import static org.junit.jupiter.api.Assertions.*;

class FibbonaciIteratorTest {

    @org.junit.jupiter.api.Test
    void next() {
        FibbonaciIterator it = new FibbonaciIterator();
        assertEquals(1,it.next());
        assertEquals(1,it.next());
        assertEquals(2,it.next());
        assertEquals(3,it.next());
        assertEquals(5,it.next());
        assertEquals(8,it.next());
        assertEquals(13,it.next());
        assertEquals(21,it.next());
    }
}