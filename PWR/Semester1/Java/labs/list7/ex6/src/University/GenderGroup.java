package University;

import static java.lang.IO.println;

public class GenderGroup{
    TStudent[] students;
    int studentsCount;

    public GenderGroup(TUniversity uni, TSex gender) {
        setGenderGroup(uni, gender);
    }

    TStudent[] getStudents() {
        return students;
    }

    public void setStudents(TStudent[] students) {
        this.students = students;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setGenderGroup(TUniversity uni, TSex gender){
        TStudent[] genderStudents = new TStudent[100];
        TStudent[] students = uni.getStudents();
        int currIdx = 0;

        for (int i = 0; i < uni.getStudentCount(); i++){
            if(gender.toString().equals(students[i].getPersonalData().getSex().toString())){
                genderStudents[currIdx] = TStudent.copyStudent(students[i]);
                currIdx++;
            }
        }

        this.students = genderStudents;
        this.studentsCount = currIdx;
    }

    public static void sortStudents(TProperties key, GenderGroup group){
        switch (key){
            case WEIGHT:
                for (int i = 0; i < group.getStudentsCount(); i++){
                    for(int j = 1; j < group.getStudentsCount() - i; j++){
                        if (group.students[j - 1].personalData.getWeight() > group.students[j].personalData.getWeight() ){
                            TStudent temp = group.students[j-1];
                            group.students[j-1] = group.students[j];
                            group.students[j] = temp;
                        }
                    }
                }
                break;
            case SURNAME:
                for (int i = 0; i < group.getStudentsCount(); i++){
                    for(int j = 1; j < group.getStudentsCount()-i; j++){
                        if (group.students[j - 1].personalData.getSurname().compareTo(group.students[j].personalData.getSurname()) > 0){
                            TStudent temp = group.students[j-1];
                            group.students[j-1] = group.students[j];
                            group.students[j] = temp;
                        }
                    }
                }
                break;
            case CITY:
                for (int i = 0; i < group.getStudentsCount(); i++){
                    for(int j = 1; j < group.getStudentsCount()-i; j++){
                        if (group.students[j - 1].personalData.getHomeAddress().getCity().compareTo(group.students[j].personalData.getHomeAddress().getCity()) > 0){
                            TStudent temp = group.students[j-1];
                            group.students[j-1] = group.students[j];
                            group.students[j] = temp;
                        }
                    }
                }
                break;
        }
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    public void printGenderGroup(){
        for(int i = 0; i < studentsCount; i++){
            students[i].printStudent();
            println();
        }
    }
}