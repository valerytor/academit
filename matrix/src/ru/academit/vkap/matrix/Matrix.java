package ru.academit.vkap.matrix;

import ru.academit.vkap.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int countRows, int countColumns) {
        if (countRows <= 0) {
            throw new IllegalArgumentException("Error: the row value can`t less than 0 Current value is: " + countRows);
        }
        if (countColumns <= 0) {
            throw new IllegalArgumentException("Error: the column value can`t less than 0 Current value is: " + countColumns);
        }

        this.rows = new Vector[countRows];
        for (int i = 0; i < countRows; i++) {
            this.rows[i] = new Vector(countColumns);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Incorrect value of argument! Variable matrix is null");
        }

        int maxLength = matrix.getRowsCount();
        rows = new Vector[maxLength];
        for (int i = 0; i < maxLength; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] doubleArray) {
        if (doubleArray == null) {
            throw new IllegalArgumentException("Incorrect value of argument! Array mast have values double[][]");
        }
        if (doubleArray.length == 0) {
            throw new IllegalArgumentException("Incorrect value of argument!  vectorArray is empty!");
        }

        int maxLength = getMaxLength(doubleArray);
        rows = new Vector[maxLength];
        for (int i = 0; i < maxLength; i++) {
            rows[i] = new Vector(maxLength, doubleArray[i]);
        }
    }

    public Matrix(Vector[] vectorArray) {
        if (vectorArray == null) {
            throw new IllegalArgumentException("Incorrect value of argument! Variable vectorArray is null");
        }
        if (vectorArray.length == 0) {
            throw new IllegalArgumentException("Incorrect value of argument! Variable vectorArray is empty!");
        }

        int maxLength = getMaxLength(vectorArray);
        rows = new Vector[vectorArray.length];
        for (int i = 0; i < vectorArray.length; i++) {
            rows[i] = new Vector(maxLength);
            rows[i].add(vectorArray[i]);
        }
    }

    private int getMaxLength(Vector[] vectors) {
        return Arrays.stream(vectors).map(Vector::getSize).max(Integer::compare).orElse(-1);
    }

    private int getMaxLength(double[][] array) {
        return Arrays.stream(array).mapToInt(x -> x.length).max().orElse(-1);
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        checkRange(index, rows.length);

        return new Vector(rows[index]);
    }

    public Vector setRow(int index, Vector vector) {
        checkRange(index, rows.length);
        int rowLength = vector.getSize();
        double[] tempArray = new double[rowLength];
        for (int i = 0; i < rowLength; i++) {
            tempArray[i] = vector.getComponent(i);
        }

        return new Vector(tempArray);
    }

    private Vector getColumn(int index) {
        checkRange(index, rows[0].getSize());
        Vector vector = new Vector(rows.length);
        for (int i = 0; i < rows.length; i++) {
            vector.setComponent(i, rows[i].getComponent(index));
        }

        return vector;
    }

    public void transpose() {
        Vector[] temp = new Vector[getColumnsCount()];
        for (int i = 0; i < getColumnsCount(); i++) {
            temp[i] = getColumn(i);
        }

        rows = temp;
    }

    public void multiplyOnScalar(double scalar) {
        for (Vector vector : rows) {
            vector.multiplyOnScalar(scalar);
        }
    }

    private void checkRange(int index, int arrayBound) {
        if (index < 0) {
            throw new IllegalArgumentException("Range error! Size of index must be more than 0. Entered value of index: " + index);
        }
        if (index >= arrayBound) {
            throw new IndexOutOfBoundsException("Range error! Index value is out of bounds of array. Entered value of index: " + index + "The value bounds of array: " + arrayBound);
        }
    }

    public double getDeterminant() {
        return getMatrixDeterminant(getDoubleArray());
    }

    private double[][] getDoubleArray() {
        double[][] temporaryArray = new double[rows.length][rows[0].getSize()];
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].getSize(); j++) {
                temporaryArray[i][j] = rows[i].getComponent(j);
            }
        }

        return temporaryArray;
    }

    private static double getMatrixDeterminant(double[][] matrix) {
        if (matrix.length == 1) {
            return matrix[0][0];
        }

        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double result = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            double[][] temporaryArray = new double[matrix.length - 1][matrix[0].length - 1];

            for (int j = 1; j < matrix.length; j++) {
                for (int k = 0; k < matrix[0].length; k++) {
                    if (k < i) {
                        temporaryArray[j - 1][k] = matrix[j][k];
                    } else if (k > i) {
                        temporaryArray[j - 1][k - 1] = matrix[j][k];
                    }
                }
            }

            result += matrix[0][i] * Math.pow(-1, i) * getMatrixDeterminant(temporaryArray);
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer("{");
        for (int i = 0; i < rows.length; i++) {
            result.append(rows[i]).append(",");
        }
        int charDelete = result.lastIndexOf(",");

        return result.deleteCharAt(charDelete).append("}").toString();
    }

    public Vector multiplyOnVector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Incorrect value of argument!  vector = null");
        }
        if (rows[0].getSize() < vector.getSize()) {
            throw new IllegalArgumentException("Size of vector is too big! Size: " + vector.getSize());
        }

        Vector result = new Vector(rows.length);
        for (int i = 0; i < rows.length; i++) {
            result.setComponent(i, Vector.getScalarMultiplication(rows[i], vector));
        }

        return result;
    }

    public void additionMatrix(Matrix matrix) {
        checkSizeMatrix(this, matrix);
        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public static Matrix additionMatrix(Matrix matrix1, Matrix matrix2) {
        checkSizeMatrix(matrix1, matrix2);
        Matrix matrix = new Matrix(matrix1);
        matrix.additionMatrix(matrix2);

        return matrix;
    }

    public void subtractionMatrix(Matrix matrix) {
        checkSizeMatrix(this, matrix);
        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix subtractionMatrix(Matrix matrix1, Matrix matrix2) {
        checkSizeMatrix(matrix1, matrix2);
        Matrix matrix = new Matrix(matrix1);
        matrix.subtractionMatrix(matrix2);

        return matrix;
    }

    public static Matrix multiplicationMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("Incorrect value of argument!  matrix1 = null");
        }
        if (matrix2 == null) {
            throw new IllegalArgumentException("Incorrect value of argument!  matrix2 = null");
        }
        int valueRows = matrix1.getRowsCount();
        int valueColumn = matrix2.getColumnsCount();
        Matrix tempoMatrix = new Matrix(valueRows, valueColumn);
        Matrix multiplyingMatrix = new Matrix(matrix2);
        multiplyingMatrix.transpose();

        for (int i = 0; i < valueRows; i++) {
            Vector vector1 = matrix1.rows[i];
            Vector vector2 = new Vector(valueRows);
            for (int j = 0; j < multiplyingMatrix.getRowsCount(); j++) {
                //Vector vector2 =matrix2.transpose();  rows[j];   vector2.turn();
                vector2.setComponent();
            }
            tempoMatrix.setRow(i)
        }

    }

    private static void checkSizeMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("Incorrect value of argument!  matrix1 = null");
        }
        if (matrix2 == null) {
            throw new IllegalArgumentException("Incorrect value of argument!  matrix2 = null");
        }
        if (matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Size of column are not equals! Size of current matrix: " + matrix1.getColumnsCount() + " Size of column entered matrix: " + matrix2.getColumnsCount());
        }
        if (matrix1.getRowsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Size of row are not equals! Size of row current matrix: " + matrix1.getRowsCount() + " Size of row entered matrix: " + matrix2.getRowsCount());
        }
    }
}
