package ru.academit.vkap.test_matrix;

import ru.academit.vkap.matrix.Matrix;
import ru.academit.vkap.vector.Vector;

import static ru.academit.vkap.matrix.Matrix.additionMatrix;
import static ru.academit.vkap.matrix.Matrix.subtractionMatrix;

public class MatrixTest {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{1, -2, 4});
        Vector vector2 = new Vector(new double[]{44, 5});
        Vector vector3 = new Vector(new double[]{4, 7, 8});
        Vector[] vectors = new Vector[]{vector1, vector2};
        Matrix matrix = new Matrix(vectors);
        matrix.transpose();
        matrix.multiplyOnScalar(2.2);

        Vector[] vectors2 = new Vector[]{vector2, vector3};
        Matrix secondMatrix = new Matrix(vectors2);

        Vector[] vectors3 = new Vector[]{vector1, vector2};
        Matrix thridMatrix = new Matrix(vectors3);

        System.out.println("Calculate of determinant: " + secondMatrix.getDeterminant());
        Vector multiplyVectors= secondMatrix.multiplyOnVector(vector2);
        System.out.println("Multiple on vector: " + multiplyVectors);

        thridMatrix.additionMatrix(secondMatrix);
        System.out.println("Addition of Matrix: " + thridMatrix);

        thridMatrix.subtractionMatrix(secondMatrix);
        System.out.println("Subtraction of Matrix" + thridMatrix);

        additionMatrix(thridMatrix, secondMatrix);
        subtractionMatrix(thridMatrix, secondMatrix);
    }
}