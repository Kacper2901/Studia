public class Student extends User{
    private int parentPhoneNumber;
    private String classNumber;

    public Student(String name, int id, int parentPhoneNumber, String classNumber){
        setName(name);
        setId(id);
        setParentPhoneNumber(parentPhoneNumber);
        setClassNumber(classNumber);
    }

    public void setParentPhoneNumber(int parentPhoneNumber) {
        this.parentPhoneNumber = parentPhoneNumber;
    }

    public int getParentPhoneNumber(){
        return this.parentPhoneNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getClassNumber(){
        return this.classNumber;
    }

    @Override
    public void showInfo(){
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getId());
        System.out.println("Parent's Phone Number: " + getParentPhoneNumber());
        System.out.println("Class: " + getClassNumber());
        System.out.println();
    }
}
