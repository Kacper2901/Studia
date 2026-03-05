import java.util.Random;

public class RandomGeneratorFromSets {
    private int[] sumOfSets;
    private Random random;

    public RandomGeneratorFromSets(){
        int currSumSetIdx = 0;
        sumOfSets = new int[32];
        random = new Random(12345);

        for(int i = 10; i<=20; i++){
            sumOfSets[currSumSetIdx] = i;
            currSumSetIdx ++;
        }
        for(int i = 50; i<=70; i++){
            sumOfSets[currSumSetIdx] = i;
            currSumSetIdx ++;
        }
    }

    void setSeed(int seed){
        random.setSeed(seed);
    }

    int nextInt(){
        int randomIdx = random.nextInt(32);
        return sumOfSets[randomIdx];
    }

}
