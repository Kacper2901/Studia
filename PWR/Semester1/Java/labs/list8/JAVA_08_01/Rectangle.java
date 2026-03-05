class Rectangle extends Shape{
    private double width, height;

    Rectangle(double a, double b){
        this.width = a;
        this.height = b;
    }

    @Override
    double getArea() {
        return this.width * this.height;
    }

    void setWidthAndHeight(double a, double b){
        this.width = a;
        this.height = b;
    }

    double getWidth() {
        return width;
    }

    double getHeight() {
        return height;
    }
}
