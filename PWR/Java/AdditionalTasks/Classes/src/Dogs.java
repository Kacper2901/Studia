class Dog{
    private String name;
    private int age;

    public Dog(String name, int age){
        this.setName(name);
        this.setAge(age);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setAge(int age){
        if (age < 0){
            System.out.println("age must not be less than zero!");
        }
        else{
            this.age = age;
        }
    }

    public int getAge(){
        return this.age;
    }

    public void bark(){
        System.out.println("bark! bark!");
    }
}


public class Dogs{
    public static void main(String[] args){
        Dog brysia = new Dog("Brysia", 1);

        System.out.println(brysia.getAge() + " " + brysia.getName());
        brysia.setAge(-1);
        System.out.println(brysia.getAge() + " " + brysia.getName());
        brysia.setAge(7);
        brysia.setName("Sonia");
        System.out.println(brysia.getAge() + " " + brysia.getName());
    }
}