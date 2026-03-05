import org.junit.Test;

import static org.junit.Assert.*;

public class RandomGeneratorFromSetsTest {

    @Test
    public void nextInt() {
        RandomGeneratorFromSets rand = new RandomGeneratorFromSets();
        for(int i = 0; i < 10000000; i++){
            int randNum = rand.nextInt();
            assertTrue(randNum >= 10 && randNum <= 20 || randNum >= 50 && randNum <= 70);
        }
    }
}