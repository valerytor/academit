package ru.academit;

public class Rectangle implements Shape,Comparable<Shape> {
    double length1, length2;

    public Rectangle(double length1, double length2) {
        this.length1 = length1;
        this.length2 = length2;
    }

    @Override
    public double getWidth() {
        return Math.min(length1, length2);
    }

    @Override
    public double getHeight() {
        return Math.max(length1, length2);
    }

    @Override
    public double getArea() {
        return length1 * length2;
    }

    @Override
    public double getPerimeter() {
        return 2 * (length1 * length2);
    }

    @Override
    public int compareTo(Shape o) {
        return (int) (this.getArea()-o.getArea());
    }
}
