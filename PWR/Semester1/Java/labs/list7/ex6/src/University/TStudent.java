package University;

public class TStudent {
    final int MAX_GRADES = 100;
    TPerson personalData;
    double[] grades = new double[MAX_GRADES];
    int gradeCount;

    public TStudent(TPerson personalData, double... grades) {
        this.personalData = personalData;
        for(double g: grades){
            addGrade(g);
        }
    }

    void addGrade(double grade) {
        if (gradeCount < MAX_GRADES) {
            grades[gradeCount] = grade;
            gradeCount++;
        }
    }

    double calculateStudGradesAverage() {
        if (gradeCount == 0) return 0;
        double sum = 0;
        for (int i = 0; i < gradeCount; i++) sum += grades[i];
        return  sum / gradeCount;
    }

    public static TStudent copyStudent(TStudent src){
        TStudent student = new TStudent(new TPerson("", src.getPersonalData().getName(), src.getPersonalData().getSurname(), src.getPersonalData().getAge(),
                src.getPersonalData().getWeight(),src.getPersonalData().getSex(),
                new TAddress(src.getPersonalData().getHomeAddress().getCountry(),src.getPersonalData().getHomeAddress().getCity(),src.getPersonalData().getHomeAddress().getStreet(),
                        src.getPersonalData().getHomeAddress().getHouseNumber(),src.getPersonalData().getHomeAddress().getFlatNumber())));
        student.setGrades(src.grades);
        student.setGradeCount(src.getGradeCount());
        return student;
    }

    public static boolean compareStudents(TStudent s1, TStudent s2){
        TPerson data1 = s1.getPersonalData();
        TPerson data2 = s2.getPersonalData();

        if(!data1.getName().equals(data2.getName()) ||
                !data1.getSurname().equals(data2.getSurname()) ||
                data1.getAge() != data2.getAge() ||
                !(data1.getSex()).equals(data2.getSex()) ||
                data1.getWeight() != data2.getWeight() ||
                !data1.getHomeAddress().getCountry().equals(data2.getHomeAddress().getCountry()) ||
                !data1.getHomeAddress().getCity().equals(data2.getHomeAddress().getCity()) ||
                !data1.getHomeAddress().getHouseNumber().equals(data2.getHomeAddress().getHouseNumber()) ||
                !data1.getHomeAddress().getFlatNumber().equals(data2.getHomeAddress().getFlatNumber()) ||
                !data1.getHomeAddress().getStreet().equals(data2.getHomeAddress().getStreet()))
        {
            return false;
        }

        return true;
    }

    public void printStudent() {
        personalData.printPerson();
        System.out.printf("Average grade: %.2f%n", calculateStudGradesAverage());
    }

    int getMAX_GRADES() {
        return MAX_GRADES;
    }

    TPerson getPersonalData() {
        return personalData;
    }

    void setPersonalData(TPerson personalData) {
        this.personalData = personalData;
    }

    double[] getGrades() {
        return grades;
    }

    void setGrades(double[] grades) {
        this.grades = grades;
    }

    int getGradeCount() {
        return gradeCount;
    }

    void setGradeCount(int gradeCount) {
        this.gradeCount = gradeCount;
    }
}