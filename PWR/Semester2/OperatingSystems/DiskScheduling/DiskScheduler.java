import java.util.*;


class EDFResult {
    int totalMovement;
    int missed;

    EDFResult(int missed, int totalMovement) {
        this.totalMovement = totalMovement;
        this.missed = missed;
    }
}

public class DiskScheduler {

    public static EDFResult EDF(ArrayList<Request> requests, Simulation sim) {
        ArrayList<Request> requestsCopy = new ArrayList<>(requests);
        int head = sim.head;
        int time = 0;
        int totalMovement = 0;
        int missedCount = 0;


        Collections.sort(requestsCopy, Comparator.comparingInt(Request -> Request.deadline));

        for(Request req: requestsCopy) {
            int step = Math.abs(head - req.block);

            if(time + step > req.deadline){
                missedCount++;
            }
            else{
                time += step;
                totalMovement += step;
                head = req.block;
            }
        }

        return new EDFResult(missedCount, totalMovement);
    }

    public static int FCFS(int head, List<Request> requests, Simulation sim){
        ArrayList<Request> requestsCopy = new ArrayList<>(requests);
        int totalMovement = 0;
        sim.time = 0;

        for(Request req: requestsCopy){
            int step = Math.abs(head - req.block);
            head = req.block;
            totalMovement+=step;
        }

        return totalMovement;
    }


    public static int SSTF(int head, List<Request> requests, Simulation sim){
        ArrayList<Request> requestsCopy = new ArrayList<>(requests);
        Request currClosest;
        int totalMovement = 0;

        while(!requestsCopy.isEmpty()){
            currClosest = findClosestRequest(head, requestsCopy,sim);
            totalMovement += Math.abs(currClosest.block - head);
            head = currClosest.block;
            requestsCopy.remove(currClosest);
        }

        return totalMovement;
    }

    public static int SCAN(int diskSize, ArrayList<Request> requests, Simulation sim) {
        int head = sim.head;
        int totalMovement = 0;

        ArrayList<Request> left = new ArrayList<>();
        ArrayList<Request> right = new ArrayList<>();

        for (Request req : requests) {
            if (req.block < head) left.add(req);
            else right.add(req);
        }

        Collections.sort(left, Comparator.comparingInt(Request -> Request.block));
        Collections.sort(right, Comparator.comparingInt(Request -> Request.block));

        for (Request req : right) {
            totalMovement += Math.abs(head - req.block);
            head = req.block;
        }

        totalMovement += diskSize - 1 - head;

        for (int i = left.size() - 1; i >= 0; i--) {
            Request currRequest = left.get(i);
            totalMovement += Math.abs(head - currRequest.block);
            head = currRequest.block;
        }

        return totalMovement;

    }

    public static int C_SCAN(int diskSize, ArrayList<Request> requests, Simulation sim) {
        int head = sim.head;
        int totalMovement = 0;

        ArrayList<Request> left = new ArrayList<>();
        ArrayList<Request> right = new ArrayList<>();

        for (Request req : requests) {
            if (req.block < head) left.add(req);
            else right.add(req);
        }

        Collections.sort(left, Comparator.comparingInt(Request -> Request.block));
        Collections.sort(right, Comparator.comparingInt(Request -> Request.block));

        for (Request req : right) {
            totalMovement += Math.abs(head - req.block);
            head = req.block;
        }

        totalMovement += diskSize - 1 - head;
        totalMovement += diskSize - 1;
        head = 0;

        for (Request req : left) {
            totalMovement += Math.abs(head - req.block);
            head = req.block;
        }


        return totalMovement;
    }

    private static Request findClosestRequest(int head, List<Request> requests, Simulation sim){
        Request currClosest = requests.get(0);
        if(!currClosest.isArrived(sim.time)) sim.time = currClosest.arrivalTime;
        for(Request req: requests){
            if(Math.abs(head - currClosest.block) > Math.abs(head - req.block)){
                currClosest = req;
            }
        }
        return currClosest;
    }

}
