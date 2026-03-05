class Car{
    private String brand;
    private int horsePower;
    private int Vmax;

    public Car(String brand, int horsePower, int Vmax){
        this.setBrand(brand);
        this.setHorsePower(horsePower);
        this.setVmax(Vmax);
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public String getBrand(){
        return "[Brand]: " + this.brand;
    }

    public void setHorsePower(int horsePower) {
        if (horsePower < 100) {
            System.out.println("Is it even the car?");
        } else {
            this.horsePower = horsePower;
        }
    }

    public String getHorsePower(){
        return "[HP]: " + this.horsePower;
    }

    public void setVmax(int Vmax){
        if (Vmax < 200){
            System.out.println("Nice bike!");
        }
        else{
            this.Vmax = Vmax;
        }
    }

    public String getVmax(){
        return "[Vmax]: " + this.Vmax;
    }

    public void info(){
        System.out.println(this.brand);
        System.out.println(this.horsePower);
        System.out.println(this.Vmax);
        System.out.println();
    }
}


public class Cars{
    public static void main(String[] args){
        Car puegeot = new Car("Peugeot", 163, 220);
        Car opel = new Car("Opel", 140, 190);
        Car romet = new Car("Romet", 99,99);

        puegeot.info();
        opel.info();
        romet.info();
    }
}