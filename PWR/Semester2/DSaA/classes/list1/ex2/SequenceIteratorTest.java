import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SequenceIteratorTest {
    @Test
    public void nextTest(){
        int startNum = 5;
        SequenceIterator it = new SequenceIterator(startNum);
        assertEquals(5, it.next());
        assertEquals(6, it.next());
        assertEquals(7, it.next());
        assertEquals(8, it.next());
        assertEquals(9, it.next());
    }

}