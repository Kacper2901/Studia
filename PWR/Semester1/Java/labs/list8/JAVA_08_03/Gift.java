abstract class Gift implements packable {
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

    @Override
    public void pack(){
        System.out.println("Packing the toy car with gift wrap...");
    }
}

class Doll extends Gift implements Fragile{
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
                "- hair color: " + hairColor.toString().toLowerCase() + "\n" +
                "- requires special handling: " + isFragile() + "\n");
    }

    @Override
    public void pack(){
        System.out.println("Packing the doll to the gift bag...");
    }

    @Override
    public boolean isFragile(){
        return false;
    }
}

class Computer extends Gift implements Fragile{
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
                "- RAM: " + RAM + " \n" +
                "- requires special handling: " + isFragile() + "\n");
    }

    @Override
    public boolean isFragile(){
        return true;
    }

    @Override
    public void pack(){
        System.out.println("Packing the computer to the box...");
    }
}