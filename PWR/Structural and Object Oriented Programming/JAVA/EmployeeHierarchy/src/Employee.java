public class Employee{
    protected double baseSalary;
    protected String name;

    public Employee(double baseSalary, String name){
            setName(name);
            setBaseSalary(baseSalary);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setBaseSalary(double baseSalary){
        if (baseSalary >= 0) {
            this.baseSalary = baseSalary;
        }
        else{
            System.out.println("Salary can't be the negative number");
        }
    }

    public double getBaseSalary(){
        return this.baseSalary;
    }

    public void getInfo(){
        System.out.println("Name: " + getName());
        System.out.println("Base salary: " + getBaseSalary());
    }

    public double calculateSalary(){
        return baseSalary;
    }
}