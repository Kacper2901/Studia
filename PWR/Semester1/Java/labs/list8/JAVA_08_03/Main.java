public class Main {
    public static void main(String[] args) {
        SantaClaus santa = new SantaClaus();
        santa.addToBag(new ToyCar(0.32, Color.RED, "Ferrari", true),
                new ToyCar(0.1, Color.BLUE, "Honda", false),
                new Doll(0.25, Gender.MALE, Color.BLACK),
                new Doll(0.25, Gender.FEMALE, Color.GOLD),
                new Computer(7.32, "Intel Core i5-9400F", "GTX1660", 16),
                new Computer(8.22, "AMD Ryzen 5 5600", "AMD Radeon 6700 XT", 32));

        System.out.println("\n");
        santa.giveGifts();
    }
}
