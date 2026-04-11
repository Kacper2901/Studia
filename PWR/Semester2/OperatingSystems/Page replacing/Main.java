public class Main {
    public static  int RAM_SIZE = 50;
    public static  int DISK_SIZE = 500;
    public static  int PAGE_REQUESTS_CAPACITY = 1000;
    public static  int LOCALITY_DELTA = 10;

    public static void runTest(int ramSize, int delta) {
        Main.RAM_SIZE = ramSize;
        Main.LOCALITY_DELTA = delta;
        Simulation sim = new Simulation();
        sim.setPageRequests();

        System.out.println(String.format("--- TEST: RAM=%d, Delta=%d ---", ramSize, delta));
        System.out.println("FIFO: " + sim.FIFO());
        sim.ram.resetFrames();
        System.out.println("ALRU: " + sim.ALRU());
        sim.ram.resetFrames();
        System.out.println("LRU:  " + sim.LRU());
        sim.ram.resetFrames();
        System.out.println("OPT:  " + sim.OPT());
        sim.ram.resetFrames();
        System.out.println("RAND: " + sim.RAND());
        System.out.println();
    }

    public static void main(String[] args) {
        runTest(100, 5);
        runTest(30, 15);
        runTest(5, 50);
    }
}
