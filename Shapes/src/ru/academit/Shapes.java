package ru.academit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shapes {
    public static void main(String[] args) {
        Square sqare1 = new Square(3.5);
        Square sqare2 = new Square(4.8);
        Triangle triangle1 = new Triangle(1, 2, 2, 2, 5, 2);
        Triangle triangle2 = new Triangle(2, 3, 3, 3, 8, 3);
        Rectangle rectangle1 = new Rectangle(3, 4);
        Rectangle rectangle2 = new Rectangle(6, 4);
        Circle circle1 = new Circle(3);
        Circle circle2 = new Circle(8);

        List<Shape> shape = new ArrayList<>();
        shape.add(sqare1);
        shape.add(sqare2);
        shape.add(triangle1);
        shape.add(triangle2);
        shape.add(rectangle1);
        shape.add(rectangle2);
        shape.add(circle1);
        shape.add(circle2);
        Collections.sort(shape, new AreaComparator());

        System.out.println("[Max of area]:");
        System.out.println(shape.get(shape.size() - 1).toString());
        System.out.println();

        Collections.sort(shape, new PerimeterComparator());
        System.out.println("[Second largest perimeter]:");
        System.out.println(shape.get(shape.size() - 2).toString());
    }
}
