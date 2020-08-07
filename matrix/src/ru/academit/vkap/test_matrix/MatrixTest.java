package ru.academit.vkap.test_matrix;

import ru.academit.vkap.matrix.Matrix;
import ru.academit.vkap.vector.Vector;

import static ru.academit.vkap.matrix.Matrix.additionMatrix;
import static ru.academit.vkap.matrix.Matrix.subtractionMatrix;

public class MatrixTest {
    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{1, -2, 4});
        Vector vector2 = new Vector(new double[]{44, 5});
        Vector vector3 = new Vector(new double[]{4, 5, 8});
        Vector[] vectors = new Vector[]{vector1, vector2};
        Matrix matrix = new Matrix(vectors);
        matrix.showMatrixSize();
        matrix.makeTranspose();
        matrix.showMatrixSize();
        matrix.multiplyOnScalar(2.2);

        Vector[] vectors2 = new Vector[]{vector2, vector3};
        Matrix secondMatrix = new Matrix(vectors2);
        System.out.println("Calculate of determinant: " + secondMatrix.getDeterminant());
        secondMatrix.multiplyOnVector(vector2);
        System.out.println("Multiple on vector: " + secondMatrix);
        matrix.additionMatrix(secondMatrix);
        System.out.println("Addition of Matrix: " + matrix);
        matrix.subtractionMatrix(secondMatrix);
        System.out.println("Subtraction of Matrix" + matrix);

        additionMatrix(matrix,secondMatrix);
        subtractionMatrix(matrix,secondMatrix);
    }
}