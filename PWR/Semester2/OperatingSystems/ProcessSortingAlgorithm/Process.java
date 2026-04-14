import java.util.Random;

public class Process{
    private int number;
    private double burstTime;
    private double remainingTime;
    private double arrivTime;
    private double waitingTime;
    private static Random random = new Random();

    public Process(int number, double burstTime, double arrivTime){
        this.number = number;
        this.burstTime = burstTime;
        this.arrivTime = arrivTime;
        this.waitingTime = 0;
        this.remainingTime = burstTime;
    }





    public static double getLongBurstTime(){
        return random.nextDouble()*50+50;
    }

    public static double getShortBurstTime(){
        return random.nextDouble()*4;
    }

    public static double getArrivalTime(){
        return random.nextDouble()*20;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(double burstTime) {
        this.burstTime = burstTime;
    }

    public double getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(double remainingTime) {
        this.remainingTime = remainingTime;
    }

    public double getArrivTime() {
        return arrivTime;
    }

    public void setArrivTime(double arrivTime) {
        this.arrivTime = arrivTime;
    }

    public double getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(double waitingTime) {
        this.waitingTime = waitingTime;
    }
}