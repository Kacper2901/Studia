import java.util.Random;

class RandomNumberFromSets extends Random {


    @Override
    public int nextInt() {
        int idx = nextInt(32);
        int value = (idx <= 10) ? 10 + idx
                : 50 + (idx - 11);
        return value;
    }

    @Override
    public long nextLong() {
        return (long) nextInt();
    }

    @Override
    public float nextFloat() {
        int idx = nextInt(30);
        int integer = (idx <= 9) ? 10 + idx
                : 50 + (idx - 10);

        return (float) integer + super.nextFloat();
    }

    @Override
    public double nextDouble() {
        int idx = nextInt(30);
        int integer = (idx <= 9) ? 10 + idx
                : 50 + (idx - 10);

        return (double) integer + super.nextDouble();    }
}
