public class Developer extends Employee{
    private int overtimeHours;

    public Developer(double baseSalary, String name, int overtimeHours){
        super(baseSalary, name);
        setOvertimeHours(overtimeHours);
    }

    public void setOvertimeHours(int overtimeHours){
        if (overtimeHours < 0){
            System.out.println("The number of overtime hours can't be negative");
        }
        else{
            this.overtimeHours = overtimeHours;
        }
    }

    public double getOvertimeHours(){
        return this.overtimeHours;
    }

    @Override
    public double calculateSalary(){
        return super.calculateSalary() + this.overtimeHours * 50;
    }

    @Override
    public void getInfo(){
        super.getInfo();
        System.out.println("Overtime hours: " + this.overtimeHours);
    }
}
