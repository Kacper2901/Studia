




void startSimulation(Simulation simulation, int n){
    double avgFCFS = 0;
    double avgSJF_NP = 0;
    double avgSJF_P = 0;
    double avgRR = 0;

    for(int i = 0; i < n; i++){
        simulation.resetSimulation();
        simulation.prepareSimulation();
        simulation.FCFS_simulation();
        avgFCFS += simulation.getAvarageWaitingTimeFCFS();

        simulation.prepareSimulation();
        simulation.SJF_NP_simulation();
        avgSJF_NP += simulation.getAvgWaitingTime();

        simulation.prepareSimulation();
        simulation.SJF_P_simulation();
        avgSJF_P += simulation.getAvgWaitingTime();

        simulation.prepareSimulation();
        simulation.RR_simulation();
        avgRR += simulation.getAvgWaitingTime();
    }

    System.out.println();
    System.out.println("FCFS end: " + avgFCFS / n);
    System.out.println("SJF_NP end: " + avgSJF_NP / n);
    System.out.println("SJF_P end: " + avgSJF_P / n);
    System.out.println("RR end: " + avgRR / n);

}

void main(){
    double quantum = 20;
    double deltaTime = 0.1;
    Simulation simulation = new Simulation(deltaTime, quantum);
    simulation.resetSimulation();
    startSimulation(simulation, 50);
}