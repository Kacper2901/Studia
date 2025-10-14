public class Main {
    public static void main(String[] args){
        Employee[] employees = {new Manager(1000,"Kacper", 500),
                                new Manager(1000, "Wiktoria", 500),
                                new Developer(500, "Jacek", 5),
                                new Developer(400, "Kazik", 4)};

        for (Employee emp: employees){
            emp.getInfo();
            System.out.println("Total: " + emp.calculateSalary());
            System.out.println();
        }
    }
}
