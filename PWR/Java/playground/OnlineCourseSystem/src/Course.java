public class Course implements Rateable{
    private String courseName;
    private Teacher teacher;
    private int rateQuantity;
    private double rate;

    public Course(String courseName, Teacher teacher){
        setCourseName(courseName);
        setTeacher(teacher);
        setRateQuantity(0);
        setRate(0);
    }

    public void setRateQuantity(int rateQuantity) {
        this.rateQuantity = rateQuantity;
    }

    public int getRateQuantity(){
        return this.rateQuantity;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getRate(){
        return this.rate;
    }

    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

    public String getCourseName(){
        return this.courseName;
    }

    public void setTeacher(Teacher teacher){
        this.teacher = teacher;
    }

    public Teacher getTeacher(){
        return this.teacher;
    }

    @Override
    public void rate(int rating) {
        int tempQuantity = getRateQuantity();
        double tempRate = getRate();
        double newRate = tempRate * tempQuantity + rating;
        ++tempQuantity;
        setRateQuantity(tempQuantity);
        setRate(newRate / tempQuantity);
    }

    public void getInfo(){
        System.out.println("Course name: " + getCourseName());
        System.out.println("Teacher: " + getTeacher().getName());
        System.out.println("Rate: " + getRate());
        System.out.println("Quantity: " + getRateQuantity());
        System.out.println("mult: " + getRateQuantity()*getRate());
        System.out.println();
    }
}
