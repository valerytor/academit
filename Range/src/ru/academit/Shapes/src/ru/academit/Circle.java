package ru.academit;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getWidth() {
        return 2 * radius;
    }

    @Override
    public double getHeight() {
        return 2 * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return
                "[INFO]Shape:Square" + System.lineSeparator() +
                        "[INFO]Width: " + getWidth() + System.lineSeparator() +
                        "[INFO]Height: " + getHeight() + System.lineSeparator() +
                        "[INFO]Area: " + getArea() + System.lineSeparator() +
                        "[INFO]Perimeter: " + getPerimeter();
    }
}
