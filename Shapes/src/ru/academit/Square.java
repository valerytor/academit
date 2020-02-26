package ru.academit;

public class Square implements Shape,Comparable<Shape> {
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
        return Math.pow(sideLength,2);
    }

    @Override
    public double getPerimeter() {
        return 4*sideLength;
    }

    @Override
    public int compareTo(Shape o) {
        return (int) (this.getArea()-o.getArea());
    }
}
