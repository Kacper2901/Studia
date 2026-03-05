package University;

import static java.lang.IO.println;

    public class TUniversity {
        final int MAX_PERSONS = 1000;
        String name;
        TAddress address;
        TStudent[] students;
        TTeacher[] teachers;
        int studentCount;
        int teacherCount;
        int yearlyIncome;


        public TUniversity(String name, TAddress address, TStudent[] students, TTeacher[] teachers, int studentCount, int teacherCount, int yearly_income) {
            this.name = name;
            this.address = address;
            this.students = students;
            this.teachers = teachers;
            this.studentCount = studentCount;
            this.teacherCount = teacherCount;
            this.yearlyIncome = yearly_income;

        }

        TStudent findStudentByName(TUniversity uni, String name) {
            int index_id = findStudentIndexByName(uni, name);
            if (index_id >=0) return uni.students[index_id];
            else return null; //no match
        }

        public void addTeacher(TUniversity uni, TTeacher t) {
            if (uni.teacherCount < MAX_PERSONS)
                uni.teachers[uni.teacherCount++] = t;
        }

        public void addStudent(TStudent s) {
            if (studentCount < MAX_PERSONS)
                students[studentCount++] = s;
        }

        public TStudent findBestStudent() {
            if (studentCount == 0) return null;
            TStudent best = students[0];
            double bestAvg = best.calculateStudGradesAverage();
            for (int i = 1; i < studentCount; i++) {
                double avg = students[i].calculateStudGradesAverage();
                if (avg > bestAvg) {
                    best = students[i];
                    bestAvg = avg;
                }
            }
            return best;
        }

        boolean isUnivIncomeHigherThanTeachersSalary() {
            int totalSalaries = 0;
            for (int i = 0; i < teacherCount; i++) {
                totalSalaries += teachers[i].salary;
            }
            return yearlyIncome > totalSalaries;
        }

        public int findStudentIndexByName(TUniversity uni, String name) {
            for (int i = 0; i < uni.studentCount; i++) {
                if (uni.students[i].personalData.getName().equalsIgnoreCase(name)) {
                    return i;
                }
            }
            return -1; //no match
        }

        public void extendUniversity(GenderGroup group){
            for (int i = 0; i < group.getStudentsCount(); i++){
                boolean exists = false;

                for (int j = 0; j < studentCount; j++){
                    if (TStudent.compareStudents(group.students[i], this.students[j])){
                        exists = true;
                        break;
                    }
                }
                if(!exists){
                    students[studentCount] = TStudent.copyStudent(group.students[i]);
                    studentCount ++;
                }

            }
        }



        int getMAX_PERSONS() {
            return MAX_PERSONS;
        }

        String getName() {
            return name;
        }

        void setName(String name) {
            this.name = name;
        }

        TAddress getAddress() {
            return address;
        }

        void setAddress(TAddress address) {
            this.address = address;
        }

        TStudent[] getStudents() {
            return students;
        }

        void setStudents(TStudent[] students) {
            this.students = students;
        }

        TTeacher[] getTeachers() {
            return teachers;
        }

        void setTeachers(TTeacher[] teachers) {
            this.teachers = teachers;
        }

        int getStudentCount() {
            return studentCount;
        }

        void setStudentCount(int studentCount) {
            this.studentCount = studentCount;
        }

        int getTeacherCount() {
            return teacherCount;
        }

        void setTeacherCount(int teacherCount) {
            this.teacherCount = teacherCount;
        }

        int getYearlyIncome() {
            return yearlyIncome;
        }

        void setYearlyIncome(int yearlyIncome) {
            this.yearlyIncome = yearlyIncome;
        }

        public void printStudents() {
            System.out.println("--- Students ---");
            for (int i = 0; i < studentCount; i++) {
                students[i].printStudent();
                println("--");
            }
        }

        public void printTeachers() {
            System.out.println("--- Teachers ---");
            for (int i = 0; i < teacherCount; i++) {
                teachers[i].printTeacher();
                println("--");
            }
        }

        public void printUniversity() {
            System.out.println("=== UNIVERSITY: " + name + " ===");
            System.out.print("Univ Address: ");
            address.printAddress();
            System.out.println("Yearly income: " + yearlyIncome + "\n");

            printStudents();
            printTeachers();
            println("================");
        }
    }