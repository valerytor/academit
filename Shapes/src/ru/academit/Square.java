package ru.academit;

public class Square implements Shape {
    private double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return Math.pow(sideLength, 2);
    }

    @Override
    public double getPerimeter() {
        return (double) (4 * sideLength);
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
