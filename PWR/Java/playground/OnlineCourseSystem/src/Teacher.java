public class Teacher extends User {
    private String emailAdress;
    private String supervisedClass;

    public Teacher(String name, int id, String emailAdress, String supervisedClass){
        setName(name);
        setId(id);
        setEmailAdress(emailAdress);
        setSupervisedClass(supervisedClass);
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getEmailAdress(){
        return this.emailAdress;
    }

    public void setSupervisedClass(String supervisedClass) {
        this.supervisedClass = supervisedClass;
    }

    public String getSupervisedClass(){
        return this.supervisedClass;
    }

    @Override
    public void showInfo(){
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getId());
        System.out.println("Email: " + getEmailAdress());
        System.out.println("Supervised class: " + getSupervisedClass());
        System.out.println();
    }
}
