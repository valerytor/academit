package ru.academit;

public class Triangle implements Shape {
    private double x1, y1, x2, y2, x3, y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    double max(double first, double... arguments) {
        double value = first;
        for (double arg : arguments) {
            value = Math.max(value, arg);
        }

        return value;
    }

    double min(double first, double... arguments) {
        double value = first;
        for (double arg : arguments) {
            value = Math.min(value, arg);
        }

        return value;
    }

    double getLength(double xa, double ya, double xb, double yb) {
        return Math.floor(Math.pow((xb - xa), 2) - Math.pow((yb - ya), 2));
    }

    @Override
    public double getWidth() {
        return (this.max(x1, x2, x3) - this.min(x1, x2, x3));
    }

    @Override
    public double getHeight() {
        return (this.max(y1, y2, y3) - this.min(y1, y2, y3));
    }

    @Override
    public double getArea() {
        return 0.5 * this.getWidth() * this.getHeight();
    }

    @Override
    public double getPerimeter() {
        return getLength(x1, y1, x2, y2) + getLength(x2, y2, x3, y3) + getLength(x3, y3, x1, y1);
    }
    @Override
    public String toString() {
        return
                "[INFO]Shape:Square"+ System.lineSeparator()+
                        "[INFO]Width: "+getWidth()+ System.lineSeparator()+
                        "[INFO]Height: "+getHeight()+ System.lineSeparator()+
                        "[INFO]Area: "+getArea()+ System.lineSeparator()+
                        "[INFO]Perimeter: "+getPerimeter();
    }
}
