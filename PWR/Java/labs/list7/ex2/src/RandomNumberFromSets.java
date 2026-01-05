import java.util.Random;

public class RandomNumberFromSets extends Random {
    public RandomNumberFromSets(long seed) {
        super(seed);
    }
    @Override
    public int nextInt() {
        int res = super.nextInt(32); // random number from 0 to 31

        if (res <= 10) {
            return 10 + res;
        } else {
            return 50 + (res - 11);
        }
    }

    @Override
    public long nextLong(){
        return (long)nextInt();
    }

    public float nextFloat(){
        float frac = super.nextFloat();
        float res = (float)nextInt() + frac;
        if (res > 20 && res < 50 || res > 70) return res - frac;
        else return res;
    }

    public double nextDouble(){
        double frac = super.nextDouble();
        double res = (double)nextInt() + frac;
        if (res > 20 && res < 50 || res > 70) return res - frac;
        else return res;
    }
}
