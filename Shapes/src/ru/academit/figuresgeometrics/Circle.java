package ru.academit.figuresgeometrics;

import ru.academit.Shape;

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
                "Shape:Square" + System.lineSeparator() +
                        "Width: " + getWidth() + System.lineSeparator() +
                        "Height: " + getHeight() + System.lineSeparator() +
                        "Area: " + getArea() + System.lineSeparator() +
                        "Perimeter: " + getPerimeter();
    }
}
