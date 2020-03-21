package ru.academit.figuresgeometrics;

import ru.academit.Shape;

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

        System.out.println("sideLength: " + sideLength);
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return 4 * sideLength;
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

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Square square = (Square) o;

        return sideLength == square.sideLength;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(sideLength);

        return hash;
    }
}
