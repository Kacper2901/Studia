public class Main {
    final int DISK_SIZE = 500;
    final int HEAD_STARTING_POSITION = 25;

    void startSimulation(Simulation s){
        int fcfsTime = 0;
        int sstfTime = 0;
        int scanTime = 0;
        int cScanTime = 0;
        int missedEFD = 0;
        int efdTime = 0;

        for(int i = 0; i<50; i ++){

            s.setRequests(100);
            s.setDeadlineRequests(50);

            s.resetSimulation();
            efdTime += DiskScheduler.EDF(s.deadlineRequests, s).totalMovement;
            s.resetSimulation();
            missedEFD += DiskScheduler.EDF(s.deadlineRequests, s).missed;

            fcfsTime += DiskScheduler.FCFS(s.headBuffor, s.requests, s);

            s.resetSimulation();
            sstfTime += DiskScheduler.SSTF(s.headBuffor, s.requests, s);

            s.resetSimulation();
            scanTime += DiskScheduler.SCAN(s.diskSize, s.requests, s);

            s.resetSimulation();
            cScanTime += DiskScheduler.C_SCAN(s.diskSize, s.requests, s);

        }

        System.out.println("fcfs: " + fcfsTime/50);
        System.out.println("sstf: " + sstfTime/50);
        System.out.println("scan: " + scanTime/50);
        System.out.println("C-Scan: " + cScanTime/50);
        System.out.println();
        System.out.println("EDF missed: " + missedEFD/50);
        System.out.println("EDF time: " + efdTime/50);

    }

    void main(){
        Simulation simulation = new Simulation(DISK_SIZE, HEAD_STARTING_POSITION);
        startSimulation(simulation);
    }
}
