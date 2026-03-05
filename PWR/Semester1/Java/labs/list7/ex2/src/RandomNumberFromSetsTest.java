import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class RandomNumberFromSetsTest {
    RandomNumberFromSets rand = new RandomNumberFromSets();

    @Test
    void nextLong() {
        for(int i = 0; i < 10000; i++) {
            long randNum = rand.nextLong();
            assertTrue(randNum >= 10 && randNum <= 20 || randNum >= 50 && randNum <= 70);
        }
    }

    @Test
    void nextFloat() {
        for(int i = 0; i < 10000; i++) {
            long randNum = rand.nextLong();
            assertTrue(randNum >= 10 && randNum <= 20 || randNum >= 50 && randNum <= 70);
        }
    }

    @Test
    void nextDouble() {
        for(int i = 0; i < 10000; i++) {
            long randNum = rand.nextLong();
            assertTrue(randNum >= 10 && randNum <= 20 || randNum >= 50 && randNum <= 70);
        }
    }
}