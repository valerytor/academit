package ru.academit.vkap.test_vector;

import ru.academit.vkap.vector.Vector;

import static ru.academit.vkap.vector.Vector.*;

public class VectorTest {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{1.1, 2.2});
        Vector vector2 = new Vector(new double[]{5.8, 3.8, 6.8});
        vector1.add(vector2);
        vector2.subtract(vector1);

        Vector vectorForMultiplyOnScalar = new Vector(new double[]{1.2, 3.2, 3.4, 4.5});
        vectorForMultiplyOnScalar.multiplyOnScalar(2);
        System.out.println("vectorMultiplyOnScalar: ");
        System.out.println(vectorForMultiplyOnScalar);
        System.out.println("Turn vector: ");
        Vector vectorForTurn = new Vector(new double[]{1.3, 4.6, 6.5, 7.8});
        vectorForTurn.turn();
        System.out.println(vectorForTurn.toString());

        Vector vectorLength = new Vector(new double[]{2, 2, 2, 2});
        System.out.println("Length of vector: ");
        System.out.println(vectorLength.getLength());

        System.out.println("Addition of vector");
        System.out.println(getSum(vector1, vector2));

        System.out.println("Subtraction of vector");
        System.out.println(getDifference(vector1, vector2));

        System.out.println("Scalar product of vectors");
        System.out.println(getScalarMultiplication(vector1, vector1));
    }
}
