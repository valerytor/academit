package ru.academit.outprogram;

import java.util.ArrayList;
import java.util.List;

import ru.academit.comparators.*;
import ru.academit.figuresgeometrics.*;
import ru.academit.Shape;

public class Shapes {
    public static void main(String[] args) {
        Square square1 = new Square(3.5);
        Square square2 = new Square(4.8);
        Triangle triangle1 = new Triangle(1, 2, 2, 2, 5, 2);
        Triangle triangle2 = new Triangle(2, 3, 3, 3, 8, 3);
        Rectangle rectangle1 = new Rectangle(3, 4);
        Rectangle rectangle2 = new Rectangle(6, 4);
        Circle circle1 = new Circle(3);
        Circle circle2 = new Circle(8);

        List<Shape> shapes = new ArrayList<>();
        shapes.add(square1);
        shapes.add(square2);
        shapes.add(triangle1);
        shapes.add(triangle2);
        shapes.add(rectangle1);
        shapes.add(rectangle2);
        shapes.add(circle1);
        shapes.add(circle2);
        shapes.sort(new AreaComparator());

        System.out.println("[Max of area]:");
        System.out.println(shapes.get(shapes.size() - 1));
        System.out.println();

        shapes.sort(new PerimeterComparator());
        System.out.println("[Second largest perimeter]:");
        System.out.println(shapes.get(shapes.size() - 2));
    }
}   