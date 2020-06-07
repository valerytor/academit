package ru.academit.vkap.test_matrix;

import ru.academit.vkap.matrix.Matrix;
import ru.academit.vkap.vector.Vector;

public class MatrixTest {

    public static void main(String[] args) {
        Vector vector1 = new Vector(new double[]{1.1, 2.2,3});
        Vector vector2 = new Vector(new double[]{1, 2,3,4,5});
        Matrix matrix = new Matrix(1, 1);
        Vector[] vectors = new Vector[]{vector1,vector2};
        //System.out.println(matrix.getMaxLength(vectors));
    }


}
