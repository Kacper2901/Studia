//this file presents two basic examples of the creation and usage of complex data types
//until not told otherwise remove the code below before creating your solutions

import static java.lang.IO.*;  //including package IO to be able to use simple print()
import static java.lang.IO.println;
import static term.term.*;     //includes package term (createElements() functions were moved there)


//***************************************************
//        Example A. Complex data types
//***************************************************

public static class TPoint {   //types need to be static to work as records while being internal classes (inside other class)
        int x;                 //as we are using simplified syntax, then it is the only way (we can not move them outside, as the class Main is hidden)
        int y;                 //to make them non-static, they would have to be in separate files (each in separate file - not handy now)
        }                      //types need to be public to allow createElements() to access them from the other package

public static class TPointsAroundCenter {
    TPoint[] points = new TPoint[40];  //all arrays must have their size set to allow automatic memory allocation with createElements()
    TPoint central_point;              //this will also be allocated by createElements()
};

public static class TAlienRobot {      //just another "random" record type for presentation how to use createElements()
    TPoint[] visited_places = new TPoint[100];  //Remark: it would be good to create constants 
    TPoint[] targets = new TPoint[10];          //    representing sizes of arrays - "magic values" are not recommended
    TPointsAroundCenter[] danger_zones = new TPointsAroundCenter[4];
    TPointsAroundCenter bot_structure; // = new TPointsAroundCenter();  //no need to use automatic constructor here, createElements() will handle this
    int[] plasma_containers =  new int[7];
    int max_speed;
    TAlienRobot sub_bot;    //Alien robot can include a robot which can include a robot... This is an alien tech. (recursive structure, do not use such things now)
    String name;
    String[] access_passwords = new String[10];
};

//helper function to create point from x and y - such functions can be very useful to create elements from data
static TPoint point(int x, int y){
    TPoint p = new TPoint();
    p.x=x;
    p.y=y;
    return p;
}

//***************************************************
//     Example A. Creation of example variables
//***************************************************

int [] solar_wind_levels = new int[10];   //all elements will be allocated automatically by Java (no need to use createElements() for arrays of primitive types)
TPoint[] lightning_locations = new TPoint[20];   //reference to array is created but elements of complex type are not created -> createElements(lightning_locations) needed
TPointsAroundCenter proposed_targets;  //not initialised at all => proposed_targets is null; proposed_targets=createElements(TPointsAroundCenter.class) can handle this
TAlienRobot abot;

//Example A. Simple test of memory allocation and read/write access to created structures
//example how to easily create and access nested structures defined above
void StructInitTest() {
    println("Testing allocation of example structures");
    println();
    //createElements(solar_wind_levels);  //this can be run but there is no need, as solar_wind_levels array is already initialised with its elements (primitive types are initialised automatically)
    //solar_wind_levels = createElements(int[].class);  //such approach will not work as size of an array will not be known
    solar_wind_levels[0]=0;
    solar_wind_levels[1]=2;
    solar_wind_levels[2]=7;
    println(">>> Local arrays of primitive types: OK");

    createElements(lightning_locations); //b is not null so we can analyse its type and allocate memory if needed
    lightning_locations[1].x=0;
    lightning_locations[1].y=1;
    println(">>> Local arrays of complex types: OK");

    //createElements(proposed_targets);  //this will not work because proposed_targets==null (and type can not be revealed from null)
    proposed_targets = createElements(TPointsAroundCenter.class);
    proposed_targets.points[2].x=1;
    proposed_targets.points[2]=point(3,2);
    proposed_targets.central_point=point(2,2);
    println(">>> Nested complex structures with arrays of complex types: OK");

    TAlienRobot abot2 = new TAlienRobot();
    createElements(abot2);            //here abot2 is not null so we can analyse its type and allocate memory if needed
    abot2.visited_places[1].x=0;
    abot2.bot_structure.points[1].y=2;
    if (abot2.name.length()>0) println("Alien bot2 name is not empty!");   //abot2.name intentionally not set - test that length method is working
    else println("Alien bot2 name is empty");
    println(abot2.access_passwords[2]);
    abot2.access_passwords[2]="Xurpta";
    println(abot2.access_passwords[2]);

    //createElements(abot);  //this will not work because abot==null (and type can not be read from null)
    abot = createElements(TAlienRobot.class);    //so the class should be explicitely given to createElements()
    abot.danger_zones[1].points[3].y=3;
    abot.targets[2]=point(4,4);
    abot.name="Destroyer";
    if (abot.name.length()>0) println("Alien bot name is not empty!");
    else println("Alien bot name is empty!");
    println(">>> Complex structures with strings: OK");

    String[] known_alien_names = new String[10];
    createElements(known_alien_names);
    if (known_alien_names[0].length()>0) println(known_alien_names[0]);
    else println("Alien name is empty");
    known_alien_names[0] = "Blurp";
    known_alien_names[1] = "Slurp";
    known_alien_names[2] = "Plurp";
    println(known_alien_names[0]);
    println(">>> Local array of strings: OK");

    abot.sub_bot.name="Bot of second level";
    abot.sub_bot.sub_bot.name="Bot of third level";
    abot.sub_bot.sub_bot.sub_bot.name="Bot of fourth level";
    println(abot.sub_bot.name);
    println(abot.sub_bot.sub_bot.name);
    println(abot.sub_bot.sub_bot.sub_bot.name);
    println(">>> Recurrent substructures:  OK");
}



//************************************************************************
//                             Example B.
//           A little more reasonable hierarchy of types
//                          (The University)
//************************************************************************

public static class TAddress {
    String country;
    String city;
    String street;
    String house_number;
    String flat_number;
}

public enum TSex {    //enum type, static by default
    male, female, unknown
}

public static class TPerson {
    String personID;   //unique identifier
    String name;
    String surname;
    int age;
    int weight;
    TSex sex;
    TAddress home_address;
}

final static int MAX_GRADES = 100;
final static int MAX_PERSONS = 1000;

public static class TStudent {      //very simplified student type
    TPerson personal_data;
    double[] grades = new double[MAX_GRADES];
    int gradeCount = 0;
}

public static class TTeacher {      //very simplified teacher type
    TPerson personal_data;
    int salary;
}

public static class TUniversity {   //very simplified university structure
    String name;
    TAddress address;
    TStudent[] students = new TStudent[MAX_PERSONS];
    TTeacher[] teachers = new TTeacher[MAX_PERSONS];
    int studentCount = 0;
    int teacherCount = 0;
    int yearly_income;
}

//************************************************************************
//                             Example B.
//       Procedures to create/use the University data to structures
//    
//************************************************************************

public static void setAddress(TAddress a, String country, String city, String street, String house, String flat) {
    a.country = country;
    a.city = city;
    a.street = street;
    a.house_number = house;
    a.flat_number = flat;
}

public static void setPerson(TPerson p, String id, String name, String surname, int age, int weight, TSex sex, TAddress addr) {
    p.personID = id;
    p.name = name;
    p.surname = surname;
    p.age = age;
    p.weight = weight;
    p.sex = sex;
    p.home_address = addr;
}

public static void setStudent(TStudent s, TPerson person, double... grades) {
    s.personal_data = person;
    s.gradeCount = 0;
    for (double g : grades) addGrade(s, g);  //grades is a set of double entries through vararg
}

public static void setTeacher(TTeacher t, TPerson person, int salary) {
    t.personal_data = person;
    t.salary = salary;
}

public static void setUniversity(TUniversity u, String name, TAddress addr, int yearly_income) {
    u.name = name;
    u.address = addr;
    u.yearly_income = yearly_income;
}

public static TAddress createAddress(String country, String city, String street, String house, String flat) {
    TAddress a = new TAddress();
    setAddress(a, country,city,street,house,flat);
    return a;
}

public static TAddress createAddress(String country, String city) {
    return createAddress(country,city,"","","");
}


public static TPerson createPerson(String id, String name, String surname, int age, int weight, TSex sex, TAddress addr) {
    TPerson p = new TPerson();
    setPerson(p, id, name,surname, age, weight, sex, addr);
    return p;
}

public static TPerson createPerson(String name, String surname) {
    return createPerson("", name, surname, 0, 0, TSex.unknown,
              createAddress("Unknown", "Unknown", "Unknown", "Unknown", "Unknown"));
}

public static TStudent createStudent(TPerson person, double... grades) {
    TStudent s = new TStudent();
    setStudent(s, person, grades);
    return s;
}

public static TTeacher createTeacher(TPerson person, int salary) {
    TTeacher t = new TTeacher();
    setTeacher(t, person, salary);
    return t;
}

public static void addStudent(TUniversity uni, TStudent s) {
    if (uni.studentCount < MAX_PERSONS)
      uni.students[uni.studentCount++] = s;
}

public static void addTeacher(TUniversity uni, TTeacher t) {
    if (uni.teacherCount < MAX_PERSONS)
      uni.teachers[uni.teacherCount++] = t;
}

public static void addGrade(TStudent s, double grade) {
    if (s.gradeCount < MAX_GRADES)
        s.grades[s.gradeCount++] = grade;
}

// Calculating average of student' grades
public static double calculateStudGradesAverage(TStudent s) {
    if (s.gradeCount == 0) return 0;
    int sum = 0;
    for (int i = 0; i < s.gradeCount; i++) sum += s.grades[i];
    return (double) sum / s.gradeCount;
}

public static TStudent findBestStudent(TUniversity uni) {
    if (uni.studentCount == 0) return null;
    TStudent best = uni.students[0];
    double bestAvg = calculateStudGradesAverage(best);
    for (int i = 1; i < uni.studentCount; i++) {
        double avg = calculateStudGradesAverage(uni.students[i]);
        if (avg > bestAvg) {
            best = uni.students[i];
            bestAvg = avg;
        }
    }
    return best;
}

public static int findStudentIndexByName(TUniversity uni, String name) {
    for (int i = 0; i < uni.studentCount; i++) {
        if (uni.students[i].personal_data.name.equalsIgnoreCase(name)) {
            return i;
        }
    }
    return -1; //no match
}

public static TStudent findStudentByName(TUniversity uni, String name) {
    int index_id = findStudentIndexByName(uni, name);
    if (index_id >=0) return uni.students[index_id];
    else return null; //no match
}

public static boolean isUnivIncomeHigherThanTeachersSalary(TUniversity uni) {
    int totalSalaries = 0;
    for (int i = 0; i < uni.teacherCount; i++) {
        totalSalaries += uni.teachers[i].salary;
    }
    return uni.yearly_income > totalSalaries;
}

public static void printAddress(TAddress a) {
    System.out.println("Address: "+a.street + " " + a.house_number + "/" + a.flat_number + ", " + a.city + ", " + a.country);
}

public static void printPerson(TPerson p) {
    println(p.name + " " + p.surname + " (" + p.sex + "), age: " + p.age + ", weight: " + p.weight);
    printAddress(p.home_address);
}

public static void printStudent(TStudent s) {
    printPerson(s.personal_data);
    System.out.printf("Average grade: %.2f%n", calculateStudGradesAverage(s));
}

public static void printTeacher(TTeacher t) {
    printPerson(t.personal_data);
    println("Salary: " + t.salary);
}

public static void printStudents(TUniversity uni) {
    System.out.println("--- Students ---");
    for (int i = 0; i < uni.studentCount; i++) {
        printStudent(uni.students[i]);
        println("--");
    }
}

public static void printTeachers(TUniversity uni) {
    System.out.println("--- Teachers ---");
    for (int i = 0; i < uni.teacherCount; i++) {
        printTeacher(uni.teachers[i]);
        println("--");
    }
}

public static void printUniversity(TUniversity uni) {
    System.out.println("=== UNIVERSITY: " + uni.name + " ===");
    System.out.print("Univ Address: ");
    printAddress(uni.address);
    System.out.println("Yearly income: " + uni.yearly_income + "\n");

    printStudents(uni);
    printTeachers(uni);
    println("================");
}

public static void addExampleUniversityData(TUniversity univ) {
    TStudent s = createStudent(createPerson("Sarah", "Connor"), 4,3,5,4.5);
    addStudent(univ, s);
    addStudent(univ, createStudent(createPerson("Kyle", "Reese"), 3,3.5,5.5,4,4.5));  //objects are created just to pass the data as variables
    addStudent(univ, createStudent(createPerson("Peter", "Silberman"), 4,4.5));
    addStudent(univ, createStudent(createPerson("T800", "Cyberdyne"), 7,7,7,7,7));
    addStudent(univ, createStudent(createPerson("Natan", "Marc"), 2,1,4,5));
    addStudent(univ, createStudent(createPerson("Olaf", "Snowman"), 7,4,3,7,7));
    addStudent(univ, createStudent(createPerson("Darth", "Vader"), 7,6,1,1,3,7));
    addStudent(univ, createStudent(createPerson("Harry", "Potter"), 7,7,3,3,7));
    addStudent(univ, createStudent(createPerson("Lionel", "Messi"), 1,2,3,4,5));
    addStudent(univ, createStudent(createPerson("Cristiano", "Ronaldo"), 2,3,4,5,2,4));
    addStudent(univ, createStudent(createPerson("Robert", "Lewandowski"), 7,6,5,4,3,2));
    addStudent(univ, createStudent(createPerson("Lamine", "Yamal"), 1,2,1));
    addStudent(univ, createStudent(createPerson("Jr", "Vinicious"), 7));

    setUniversity(univ, "Boston University", createAddress("USA", "Boston"), 1234567);
    univ.students[0].personal_data.sex=TSex.female;  //manual patching - not recommended but available
    univ.students[1].personal_data.sex=TSex.male;    //manual patching - not recommended but available
    univ.students[2].personal_data.sex=TSex.male;    //manual patching - not recommended but available
    univ.students[findStudentIndexByName(univ,"T800")].personal_data.weight=180;  //very risky: if student not found => error
    //findStudentByName(univ1,"T800").personal_data.weight=180;   //also risky solution - NullPointerException if student not found
}


void main() {
    clrscr();
//    //Example A: tests of data creation/access
//    StructInitTest();
//    print("Press any key to continue...");
//    wait_for_any_key();

    //Example B: the University
    clrscr();
    TUniversity univ1 = createElements(TUniversity.class);  //"magic" line to allocate memory of the whole structure

    addExampleUniversityData(univ1);

    printUniversity(univ1);
    println();

    println("=== Best Student ===");
    printStudent(findBestStudent(univ1));
    println("===============");

    print("Press any key to continue...");
    wait_for_any_key();
}