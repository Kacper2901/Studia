abstract class Gift {
    private double weight;

    Gift(double weight){
        this.weight = weight;
    }

    void setWeight(double weight) {
        this.weight = weight;
    }

    abstract void unwrap();
}

class ToyCar extends Gift{
    final private Color color;
    final private String brand;
    final private boolean remoteControlled;
    ToyCar(double weight, Color color, String brand, boolean remoteControlled){
        super(weight);
        this.color = color;
        this.brand = brand;
        this.remoteControlled = remoteControlled;
    }

    @Override
    void unwrap() {
        System.out.println("ToyCar:\n" +
                "- color: " + color.toString().toLowerCase() + "\n" +
                "- brand: " + brand + "\n" +
                "- remote controlled:  " + remoteControlled + "\n");
    }
}

class Doll extends Gift{
    final private Gender gender;
    final private Color hairColor;

    Doll(double weight, Gender gender, Color hairColor){
        super(weight);
        this.gender = gender;
        this.hairColor = hairColor;
    }

    @Override
    void unwrap() {
        System.out.println("Doll:\n" +
                "- gender: " + gender.toString().toLowerCase() + "\n" +
                "- hair color: " + hairColor.toString().toLowerCase() + "\n");
    }
}

class Computer extends Gift{
    private String CPU;
    private String GPU;
    private int RAM;

    Computer(double weight, String CPU, String GPU, int RAM){
        super(weight);
        this.CPU = CPU;
        this.GPU = GPU;
        this.RAM = RAM;
    }

    @Override
    void unwrap() {
        System.out.println("Computer:\n" +
                "- CPU: " + CPU + "\n" +
                "- GPU: " + GPU + "\n" +
                "- RAM: " + RAM + " \n");
    }
}