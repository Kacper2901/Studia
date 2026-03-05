
import University.*;


import static java.lang.IO.*;
import static java.lang.IO.println;

public static void addExampleUniversityData(TUniversity univ) {
    univ.addStudent(new TStudent(new TPerson("id0001","Jan","Kowalski",20,75,TSex.MALE,new TAddress("Poland","Wrocław","Piłsudskiego","10","2")),5,4.0,4,5.0));
    univ.addStudent( new TStudent(new TPerson("id0002","Anna","Nowak",19,58,TSex.FEMALE,new TAddress("Poland","Kraków","Długa","15","7")),1,3.5,4.0));
    univ.addStudent( new TStudent(new TPerson("id0003","Michał","Wiśniewski",22,82,TSex.MALE,new TAddress("Poland","Poznań","Główna","3","1")),3,5.0,4.5,4.0,3.5));
    univ.addStudent( new TStudent(new TPerson("id0004","Katarzyna","Zielińska",21,62,TSex.FEMALE,new TAddress("Poland","Gdańsk","Morska","22","4")),2,4.0,4.0,4.5));
    univ.addStudent( new TStudent(new TPerson("id0005","Piotr","Lewandowski",23,88,TSex.MALE,new TAddress("Poland","Warszawa","Marszałkowska","100","12")),4,3.0,3.5,4.0));
    univ.addStudent( new TStudent(new TPerson("id0006","Natalia","Kamińska",18,55,TSex.FEMALE,new TAddress("Poland","Łódź","Piotrkowska","200","9")),1,5.0));
    univ.addStudent( new TStudent(new TPerson("id0007","Tomasz","Dąbrowski",24,90,TSex.MALE,new TAddress("Poland","Szczecin","Portowa","8","3")),3,2.5,3.0,3.5,4.0,4.5));
    univ.addStudent( new TStudent(new TPerson("id0008","Agnieszka","Kaczmarek",20,60,TSex.FEMALE,new TAddress("Poland","Bydgoszcz","Leśna","14","6")),2,4.5,4.5));
    univ.addStudent( new TStudent(new TPerson("id0009","Paweł","Mazur",21,78,TSex.MALE,new TAddress("Poland","Lublin","Narutowicza","5","10")),3,3.0,3.0,3.5));
    univ.addStudent( new TStudent(new TPerson("id0010","Monika","Wójcik",22,59,TSex.FEMALE,new TAddress("Poland","Rzeszów","Rejtana","30","2")),1,4.0,4.5,5.0,5.0));
}

public static void addExampleUniversityData2(TUniversity univ) {
    univ.addStudent(new TStudent(new TPerson("id0001", "Luke", "Skywalker", 23, 73, TSex.MALE, new TAddress("Tatooine", "Mos Eisley", "Anchorhead", "1", "0")), 3, 5.0, 5.0, 4.5));
    univ.addStudent( new TStudent(new TPerson("id0002", "Leia", "Organa", 22, 55, TSex.FEMALE, new TAddress("Alderaan", "Aldera City", "Royal Way", "10", "1")), 2, 5.0, 4.5, 5.0, 4.5));
    univ.addStudent( new TStudent(new TPerson("id0003", "Han", "Solo", 29, 80, TSex.MALE, new TAddress("Corellia", "Coronet", "Docking Bay", "7", "3")), 1, 3.0, 3.5));
    univ.addStudent( new TStudent(new TPerson("id0004", "Anakin", "Skywalker", 25, 85, TSex.MALE, new TAddress("Tatooine", "Mos Espa", "Podracer St", "99", "4")), 4, 2.0, 3.0, 3.5, 4.0));
    univ.addStudent( new TStudent(new TPerson("id0005", "Padmé", "Amidala", 26, 54, TSex.FEMALE, new TAddress("Naboo", "Theed", "Plaza Way", "5", "2")), 2, 5.0, 5.0, 4.5));


}











void main() {
    TUniversity univ1 = new TUniversity("PWR", new TAddress("Poland", "Wroclaw", "idk", "21", "none"), new TStudent[200] , new TTeacher[200], 0, 0, 1000000);
    TUniversity univ2 = new TUniversity("UWR", new TAddress("Poland", "Wroclaw", "idk22", "123", "none"), new TStudent[200] , new TTeacher[200], 0, 0, 234234234);


    addExampleUniversityData(univ1);

    addExampleUniversityData2(univ2);

    println("==== Print uni1 info ====");
    univ1.printUniversity();
    println();
    println();
    println();

    println("=== Find best student in uni1 (Jan Kowalski expected) ===");
    univ1.findBestStudent().printStudent();
    println("===============");
    println();
    println();
    println();

    GenderGroup maleGroup = new GenderGroup(univ1, TSex.MALE);
    maleGroup.printGenderGroup();
    println("==============");
    println();
    println();

    println("===== Print males from univ1 (different kinds of sorting) =====");
    println();
    println();

    println("=== Sorted by weight: ===");
    GenderGroup.sortStudents(TProperties.WEIGHT, maleGroup);
    maleGroup.printGenderGroup();
    println();
    println();

    println("=== Sorted by surname: ===");
    GenderGroup.sortStudents(TProperties.SURNAME, maleGroup);
    maleGroup.printGenderGroup();
    println();
    println();

    println("=== Sorted by city: ===");
    GenderGroup.sortStudents(TProperties.CITY,maleGroup);
    maleGroup.printGenderGroup();
    println();
    println();

    println("==============");
    println();
    println();
    println();
    println();

    println("=====Extend university by array of students====");
    println();
    println("***before (uni2: star wars characters):***");
    println();
    univ2.printStudents();
    univ2.extendUniversity(maleGroup);
    println();
    println();
    println();
    println("***after(expects star wars characters + males from uni1):***");
    println();
    univ2.printStudents();
    println();
    println();


    println("Extend university by array of students (students already in the university, so nothing should change)");
    println();
    println("before:");
    println();
    univ1.printStudents();
    univ1.extendUniversity(maleGroup);
    println();
    println();
    println();
    println("after:");
    println();
    univ1.printStudents();
    println();

}