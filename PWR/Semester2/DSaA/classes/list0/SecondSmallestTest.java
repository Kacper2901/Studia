import static org.junit.jupiter.api.Assertions.*;

class SecondSmallestTest{

    @org.junit.jupiter.api.Test
    void getSecondSmallestInt() throws NoAnswerException {
        SecondSmallest<Integer> sec = new SecondSmallest<>();
        Integer[] arr1 = {2,1,3,4,5};
        Integer[] arr2 = {12,142,54,12,11};
        Integer[] arr3 = {1};
        Integer[] arr4 = {1,1,1,1};
        Integer[] arr5 = {};

        assertEquals(2,sec.getSecondSmallest(arr1));
        assertEquals(12,sec.getSecondSmallest(arr2));
        assertThrows(NoAnswerException.class,() -> sec.getSecondSmallest(arr3));
        assertThrows(NoAnswerException.class,() -> sec.getSecondSmallest(arr4));
        assertThrows(NoAnswerException.class,() -> sec.getSecondSmallest(arr5));
    }

    @org.junit.jupiter.api.Test
    void getSecondSmallestStr() throws NoAnswerException {
        SecondSmallest<String> sec = new SecondSmallest<>();
        String[] arr1 = {"a","b","c","d"};
        String[] arr2 = {"basen", "ala", "dom"};
        String[] arr3 = {"l"};
        String[] arr4 = {"l", "l", "l"};
        String[] arr5 = {};

        assertEquals("b",sec.getSecondSmallest(arr1));
        assertEquals("basen",sec.getSecondSmallest(arr2));
        assertThrows(NoAnswerException.class,() -> sec.getSecondSmallest(arr3));
        assertThrows(NoAnswerException.class,() -> sec.getSecondSmallest(arr4));
        assertThrows(NoAnswerException.class,() -> sec.getSecondSmallest(arr5));
    }
}