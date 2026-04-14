import java.util.*;

public class Simulation{
    private double currTime;
    private double deltaTime;
    private Process currentProcess;


    private double avgWaitingTime;
    private double totalWaitingTimeSJF_NP;
    private double totalWaitingTimeSJF_P;
    private double totalWaitingTimeRR;

    private double avarageWaitingTimeFCFS;
    private double avarageWaitingTimeSJF_NP;
    private double avarageWaitingTimeSJF_P;
    private double avarageWaitingTimeRR;
    private double quantum;
    private double quantumRemain;
    private int currProcessIdx;

    private int lastActivatedProcessIdx;

    private double totalWaitingTime;


    ArrayList<Process> processes = new ArrayList<Process>();
    ArrayList<Process> executedProcesses = new ArrayList<Process>();
    ArrayList<Process> processesQueue = new ArrayList<Process>();

    Simulation(double deltaTime, double quantum){
        this.deltaTime = deltaTime;
        this.totalWaitingTimeRR = 0;
        this.avgWaitingTime = 0;
        this.totalWaitingTimeSJF_NP = 0;
        this.totalWaitingTimeSJF_P = 0;
        this.avarageWaitingTimeFCFS = 0;
        this.avarageWaitingTimeRR = 0;
        this.avarageWaitingTimeSJF_NP = 0;
        this.avarageWaitingTimeSJF_P = 0;
        this.quantum = quantum;
        this.quantumRemain = quantum;
    }

    void RR_simulation(){
        lastActivatedProcessIdx = -1;
        currProcessIdx = 0;

        while(!areAllProcessesDone()){
            RR_step();
        }
        calculateTotalWaitingTime();
        avgWaitingTime = totalWaitingTime /processes.size();
    }

    void RR_step(){
        addStartedProcessesToQueue();
        if(!processesQueue.isEmpty()){
            quantumPhase();
            if(isCurrProcessDone()) {
                endCurrProcess();
                if(!processesQueue.isEmpty()) currProcessIdx = (currProcessIdx + 1) % processesQueue.size();
                quantumRemain = quantum;
            }
            if(quantumRemain <= 0){
                quantumRemain = quantum;
                currProcessIdx = (currProcessIdx + 1) % processesQueue.size();
            }
        }
        currTime += deltaTime;

    }

    void SJF_NP_simulation(){
        lastActivatedProcessIdx = -1;
        while(!areAllProcessesDone()){
            SJF_NP_step();
        }
        calculateTotalWaitingTime();
        avgWaitingTime = totalWaitingTime /processes.size();
    }

    void SJF_NP_step(){
        addStartedProcessesToQueue();
        if(currentProcess == null && !processesQueue.isEmpty()){
            sortByLength();
            currentProcess = processesQueue.get(0);
        }
        if (!processesQueue.isEmpty()) {
            doProcessorPhaseSJF();
            if(isHeadProcessDone()){
                endHeadProcess();
                currentProcess = null;
            }
        }
        currTime += deltaTime;

    }

    void SJF_P_simulation(){
        lastActivatedProcessIdx = -1;
        while(!areAllProcessesDone()){
            SJF_P_step();
        }
        calculateTotalWaitingTime();
        avgWaitingTime = totalWaitingTime /processes.size();
    }

    void SJF_P_step(){
        addStartedProcessesToQueue();
        sortByLength();
        if (!processesQueue.isEmpty()) {
            doProcessorPhaseFCFS();
            if(isHeadProcessDone()){
                endHeadProcess();
            }
        }
        currTime += deltaTime;

    }

    void FCFS_simulation(){
        lastActivatedProcessIdx = -1;
        while(!areAllProcessesDone()){
            FCFS_step();
        }
        calculateTotalWaitingTime();
        avgWaitingTime += totalWaitingTime /processes.size();
    }

    void FCFS_step(){
        addStartedProcessesToQueue();
        if(!processesQueue.isEmpty()){
            doProcessorPhaseFCFS();
            if(isHeadProcessDone()) endHeadProcess();
        }
        currTime += deltaTime;
    }

    void addStartedProcessesToQueue() {
        while (lastActivatedProcessIdx + 1 < processes.size()) {
            Process nextProcess = processes.get(lastActivatedProcessIdx + 1);
            if (!isProcessStarted(nextProcess)){
                break;
            }
            processesQueue.add(nextProcess);
            lastActivatedProcessIdx ++;
        }
    }

    void endCurrProcess(){
        Process currProcess = processesQueue.get(currProcessIdx);
        currProcess.setWaitingTime(currTime - currProcess.getArrivTime() - currProcess.getBurstTime());
        executedProcesses.add(currProcess);
        processesQueue.remove(currProcessIdx);
    }

    void quantumPhase(){
        Process currProcess = processesQueue.get(currProcessIdx);
        currProcess.setRemainingTime(Math.max(currProcess.getRemainingTime() - deltaTime,0));
        quantumRemain -= deltaTime;
    }

    void endHeadProcess(){
        Process currProcess = processesQueue.getFirst();
        currProcess.setWaitingTime(currTime - currProcess.getArrivTime() - currProcess.getBurstTime());
        executedProcesses.add(processesQueue.getFirst());
        processesQueue.removeFirst();
    }

    void doProcessorPhaseFCFS(){
        Process currProcess = processesQueue.getFirst();
        currProcess.setRemainingTime(Math.max(currProcess.getRemainingTime() - deltaTime, 0));
    }

    void doProcessorPhaseSJF(){
        currentProcess.setRemainingTime(Math.max(currentProcess.getRemainingTime() - deltaTime, 0));
    }

    void calculateTotalWaitingTime(){
        totalWaitingTime = 0;
        for(int i = 0; i<processes.size(); i++){
            totalWaitingTime += processes.get(i).getWaitingTime();
        }

    }

    boolean isHeadProcessDone(){
        return processesQueue.getFirst().getRemainingTime() <= 0;
    }

    boolean isCurrProcessDone(){
        Process currProcess = processesQueue.get(currProcessIdx);
        return currProcess.getRemainingTime() <= 0;
    }



    void prepareSimulation(){
        currTime = 0;
        lastActivatedProcessIdx = -1;
        resetRemainingTime();
        executedProcesses.clear();
        totalWaitingTime = 0;
        processesQueue.clear();
    }

    void resetRemainingTime(){
        for(int i = 0; i < processes.size(); i++){
            processes.get(i).setRemainingTime(processes.get(i).getBurstTime());
            processes.get(i).setWaitingTime(0);
        }
    }

    void resetSimulation(){
        currTime = 0;
        executedProcesses.clear();
        processesQueue.clear();
        setProcesses();
    }

    void setProcesses(){
        processes.clear();
        for(int i = 0; i < 5; i++){
            processes.add(new Process(processes.size(), Process.getLongBurstTime(), Process.getArrivalTime()));
            for(int j = 0; j < 9; j++){
                processes.add(new Process(processes.size(), Process.getShortBurstTime(), Process.getArrivalTime()));
            }
        }
        processes.sort(Comparator.comparingDouble(Process::getArrivTime));
    }

    public double getDeltaTime() {
        return deltaTime;
    }

    public void setDeltaTime(double deltaTime) {
        this.deltaTime = deltaTime;
    }

    public Process getCurrentProcess() {
        return currentProcess;
    }

    public void setCurrentProcess(Process currentProcess) {
        this.currentProcess = currentProcess;
    }

    public double getAvgWaitingTime() {
        return avgWaitingTime;
    }

    public void setAvgWaitingTime(double avgWaitingTime) {
        this.avgWaitingTime = avgWaitingTime;
    }

    public double getTotalWaitingTimeSJF_P() {
        return totalWaitingTimeSJF_P;
    }

    public void setTotalWaitingTimeSJF_P(double totalWaitingTimeSJF_P) {
        this.totalWaitingTimeSJF_P = totalWaitingTimeSJF_P;
    }

    public double getTotalWaitingTimeSJF_NP() {
        return totalWaitingTimeSJF_NP;
    }

    public void setTotalWaitingTimeSJF_NP(double totalWaitingTimeSJF_NP) {
        this.totalWaitingTimeSJF_NP = totalWaitingTimeSJF_NP;
    }

    public double getTotalWaitingTimeRR() {
        return totalWaitingTimeRR;
    }

    public void setTotalWaitingTimeRR(double totalWaitingTimeRR) {
        this.totalWaitingTimeRR = totalWaitingTimeRR;
    }

    public double getAvarageWaitingTimeFCFS() {
        return totalWaitingTime / processes.size();
    }

    public void setAvarageWaitingTimeFCFS(double avarageWaitingTimeFCFS) {
        this.avarageWaitingTimeFCFS = avarageWaitingTimeFCFS;
    }

    public double getAvarageWaitingTimeSJF_NP() {
        return avarageWaitingTimeSJF_NP;
    }

    public void setAvarageWaitingTimeSJF_NP(double avarageWaitingTimeSJF_NP) {
        this.avarageWaitingTimeSJF_NP = avarageWaitingTimeSJF_NP;
    }

    public double getAvarageWaitingTimeRR() {
        return avarageWaitingTimeRR;
    }

    public void setAvarageWaitingTimeRR(double avarageWaitingTimeRR) {
        this.avarageWaitingTimeRR = avarageWaitingTimeRR;
    }

    public double getAvarageWaitingTimeSJF_P() {
        return avarageWaitingTimeSJF_P;
    }

    public void setAvarageWaitingTimeSJF_P(double avarageWaitingTimeSJF_P) {
        this.avarageWaitingTimeSJF_P = avarageWaitingTimeSJF_P;
    }

    boolean isProcessStarted(Process process){
        return process.getArrivTime() <= currTime;
    }

    boolean areAllProcessesDone(){
        return executedProcesses.size() == processes.size();
    }

    void sortByLength(){
        processesQueue.sort(Comparator.comparingDouble(Process::getBurstTime));
    }
}