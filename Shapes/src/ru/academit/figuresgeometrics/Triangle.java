package ru.academit.figuresgeometrics;

import ru.academit.Shape;

public class Triangle implements Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    private double max(double... arguments) {
        double value = arguments[0];

        for (double arg : arguments) {
            value = Math.max(value, arg);
        }

        return value;
    }

    private double min(double... arguments) {
        double value = arguments[0];

        for (double arg : arguments) {
            value = Math.min(value, arg);
        }

        return value;
    }

    private double getLength(double xa, double ya, double xb, double yb) {
        return Math.sqrt(Math.pow((xb - xa), 2) - Math.pow((yb - ya), 2));
    }

    @Override
    public double getWidth() {
        return this.max(x1, x2, x3) - this.min(x1, x2, x3);
    }

    @Override
    public double getHeight() {
        return this.max(y1, y2, y3) - this.min(y1, y2, y3);
    }

    @Override
    public double getArea() {
        double halfPerimeter = getPerimeter() / 2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - getLength(x1, y1, x2, y2)) * (halfPerimeter - getLength(x1, y1, x3, y3)) * (halfPerimeter - getLength(x2, y2, x3, y3)));
    }

    @Override
    public double getPerimeter() {
        return getLength(x1, y1, x2, y2) + getLength(x2, y2, x3, y3) + getLength(x3, y3, x1, y1);
    }

    @Override
    public String toString() {
        return
                "Shape:Square" + System.lineSeparator() +
                        "Width: " + getWidth() + System.lineSeparator() +
                        "Height: " + getHeight() + System.lineSeparator() +
                        "Area: " + getArea() + System.lineSeparator() +
                        "Perimeter: " + getPerimeter();
    }
}
