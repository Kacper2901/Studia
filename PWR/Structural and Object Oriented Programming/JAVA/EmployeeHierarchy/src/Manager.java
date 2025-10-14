public class Manager extends Employee{
    private double bonus;

    public Manager(double baseSalary, String name, double bonus){
        super(baseSalary,name);
        setBonus(bonus);
    }

    public void setBonus(double bonus){
        if (bonus < 0){
            System.out.println("Bonus can't be a negative number");
        }
        else{
            this.bonus = bonus;
        }
    }

    public double getBonus(){
        return this.bonus;
    }

    @Override
    public double calculateSalary() {
        return super.calculateSalary() + bonus;
    }

    @Override
    public void getInfo(){
        super.getInfo();
        System.out.println("Bonus: " + this.bonus);
    }


}
