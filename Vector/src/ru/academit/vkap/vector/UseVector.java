package ru.academit.vkap.vector;

import static ru.academit.vkap.vector.Vector.*;

public class UseVector {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{1.1, 2.2});
        Vector vector2 = new Vector(new double[]{5.8, 3.8, 6.8});
        vector1.vectorAddition(vector2);
        vector2.vectorSubtraction(vector1);

        Vector vectorMultiplyOnScalar = new Vector(new double[]{1.2, 3.2, 3.4, 4.5});
        vectorMultiplyOnScalar.multiplyOnScalar(1.5);
        System.out.println("vectorMultiplyOnScalar: ");
        System.out.println(vectorMultiplyOnScalar.toString());
        System.out.println("Turn vector: ");
        Vector vectorTurn = new Vector(new double[]{1.3, 4.6, 6.5, 7.8});
        vectorTurn.turn();
        System.out.println(vectorTurn.toString());

        Vector vectorLength = new Vector(new double[]{1.4, 3.2, 2.2});
        System.out.println("Length of vector: ");
        System.out.println(vectorLength.getLength());

        System.out.println("Addition of vector");
        System.out.println(addition(vector1, vector2));

        System.out.println("Subtraction of vector");
        System.out.println(subtraction(vector1, vector2));

        System.out.println("Scalar product of vectors");
        System.out.println(multiplicationScalar(vector1, vector1));

    }
}
