package ru.academit.figuresgeometrics;

import ru.academit.Shape;

public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double length1, double length2) {
        this.width = length1;
        this.height = length2;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
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
