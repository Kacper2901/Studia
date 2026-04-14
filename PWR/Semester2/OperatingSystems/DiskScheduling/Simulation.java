import java.util.*;
public class Simulation {
    int time;
    ArrayList<Request> requests;
    ArrayList<Request> deadlineRequests;
    int diskSize;
    int headStartingPosition;
    int head;
    int headBuffor;
    Random rand = new Random();


    Simulation(int diskSize, int headStartingPosition){
        this.headStartingPosition = headStartingPosition;
        this.diskSize = diskSize;
        this.time = 0;
        this.head = headStartingPosition;
        this.headBuffor = headStartingPosition;
    }

    public void setRequests(int N){
        requests = new ArrayList<>();
        for (int i = 0; i < N; i++){
            int block = (int)(rand.nextDouble()*diskSize);
            requests.add(new Request(block));
        }
    }

    public void setDeadlineRequests(int N){
        deadlineRequests = new ArrayList<>();

        for(int i = 0; i<N; i++){
            int block = (int)(rand.nextDouble()*diskSize);
            int deadline = (int)(rand.nextDouble()*diskSize*20);
            deadlineRequests.add(new Request(block, deadline));
        }
    }

    public void resetSimulation(){
        time = 0;
        for(Request req: requests){
            req.resetRequest();
        }
        head = headBuffor;
    }
}
