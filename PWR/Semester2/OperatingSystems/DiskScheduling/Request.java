class Request {
    int block;
    Integer deadline; // null = normal, int real-time
    int arrivalTime;
    boolean isServed;

    public Request(int block) {
        this.block = block;
        this.deadline = null;
        isServed = false;
    }

    public Request(int block, int deadline){
        this.block = block;
        this.deadline = deadline;
        isServed = false;
    }



    public Request(int block, int deadline, boolean hasDeadline) {
        this.block = block;
        this.deadline = deadline;
    }

    public boolean isRealTime() {
        return deadline != null;
    }

    public boolean isArrived(double currTime){
        return arrivalTime <= currTime;
    }

    public void resetRequest(){
        isServed = false;
    }
}