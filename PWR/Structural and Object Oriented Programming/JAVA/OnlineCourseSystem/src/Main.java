class Main{
    public static void main(String[] args) {
        User[] user = {new Teacher("Kowalski", 1111, "kowalski@gmail.com", "IVB"),
                new Student("Kacper", 2111, 111222333, "VIIIA")};


        Course Maths = new Course("Maths", (Teacher) user[0]);

        Maths.getInfo();
        Maths.rate(9);
        Maths.getInfo();
        Maths.rate(9);
        Maths.getInfo();
        Maths.rate(6);
        Maths.getInfo();
        Maths.rate(3);
        Maths.getInfo();
        Maths.rate(2);
        Maths.getInfo();


        for (User u: user){
            u.showInfo();
        }

    }
}