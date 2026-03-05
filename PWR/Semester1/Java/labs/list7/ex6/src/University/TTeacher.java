package University;

import static java.lang.IO.println;

public class TTeacher {
    TPerson personal_data;
    int salary;

    public TTeacher(TPerson personal_data, int salary) {
        this.personal_data = personal_data;
        this.salary = salary;
    }

    TPerson getPersonal_data() {
        return personal_data;
    }

    void setPersonal_data(TPerson personal_data) {
        this.personal_data = personal_data;
    }

    int getSalary() {
        return salary;
    }

    void setSalary(int salary) {
        this.salary = salary;
    }

    public void printTeacher() {
        personal_data.printPerson();
        println("Salary: " + salary);
    }
}