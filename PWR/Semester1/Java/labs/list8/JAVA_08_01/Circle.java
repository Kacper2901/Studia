class Circle extends Shape{
    private double radius;
    Circle(double radius){
        this.radius = radius;
    }

    @Override
    double getArea() {
        return 3.14 * this.radius * this.radius;
    }

    void setRadius(double radius) {
        this.radius = radius;
    }

    double getRadius() {
        return radius;
    }
}