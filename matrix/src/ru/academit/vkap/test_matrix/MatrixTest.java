package ru.academit.vkap.test_matrix;

import ru.academit.vkap.matrix.Matrix;
import ru.academit.vkap.vector.Vector;

public class MatrixTest {

    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{1,-2,4,9,-8,6});
        Vector vector2 = new Vector(new double[]{4,5,-7.7,5,6,7,8,9});
        Vector vector3 = new Vector(new double[]{4,5,8,9});
        //Matrix matrix = new Matrix(vector1, vector2);
        Vector[] vectors = new Vector[]{vector1,vector2};
        Matrix matrix =new Matrix(vectors);
        matrix.showMatrixSize();
        matrix.makeTranspose();
        matrix.showMatrixSize();
        matrix.multiplyOnScalar(2.2);

        Vector[] vectors2 = new Vector[]{vector1,vector2,vector3};
        Matrix secondMatrix =new Matrix(vectors2);
        System.out.println("Calculate of determinant: "+secondMatrix.getDeterminant());
        System.out.println("toString: "+secondMatrix);
    }


}
