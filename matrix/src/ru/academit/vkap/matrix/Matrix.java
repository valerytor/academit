package ru.academit.vkap.matrix;

import ru.academit.vkap.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rows, int columns) {
        if (rows < 0) {
            throw new IllegalArgumentException("Error: the row value can`t less than 0 Current value is: " + rows);
        }
        if (columns < 0) {
            throw new IllegalArgumentException("Error: the column value can`t less than 0 Current value is: " + columns);
        }

        this.rows = new Vector[rows];
        for (int i = 0; i < rows; i++) {
            this.rows[i] = new Vector(columns);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Incorrect value of argument!  matrix = null");
        }

        int maxLength = getMaxLength(matrix.rows);
        rows = new Vector[maxLength];
        for (int i = 0; i < maxLength; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] doubleArray) {
        if (doubleArray == null) {
            throw new IllegalArgumentException("Incorrect value of argument! Array mast have values double[][]");
        }

        for (int i = 0; i < doubleArray.length; i++) {
            for (int j = 0; j < doubleArray[i].length; j++) {
                if (doubleArray[i][j] < 0) {
                    throw new IllegalArgumentException("Incorrect value of array for [" + i + "][" + j + "] is " + doubleArray[i][j] + "Value must be more than 0");
                }
            }
        }

        int maxLength = getMaxLength(doubleArray);
        rows = new Vector[maxLength];
        for (int i = 0; i < maxLength; i++) {
            rows[i] = new Vector(maxLength, doubleArray[i]);
        }
    }

    public Matrix(Vector[] vectorArray) {
        if (vectorArray == null) {
            throw new IllegalArgumentException("Incorrect value of argument!  vectorArray = null");
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

    public void showMatrixSize() {
        System.out.println("Size of matrix is:  column-" + getColumnSize() + " row-" + getRowSize());
    }

    private int getRowSize() {
        return rows.length;
    }

    private int getColumnSize() {
        return rows[0].getSize();
    }

    private Vector getVectorString(int index) {
        checkRange(index);

        return rows[index];
    }

    private void setVectorString(int index, Vector vector) {
        checkRange(index);

        rows[index] = new Vector(vector);
    }

    private Vector getVectorColumn(int index) {
        checkRange(index);
        Vector vector = new Vector(rows.length);
        for (int i = 0; i < rows.length; i++) {
            vector.setComponent(i, rows[i].getComponent(index));
        }

        return vector;
    }

    public void makeTranspose() {
        Vector[] temp = new Vector[getColumnSize()];

        for (int i = 0; i < getRowSize(); i++) {
            for (int j = 0; j < getColumnSize(); j++) {
                if (temp[j] == null) {
                    temp[j] = new Vector(getRowSize());
                }
                temp[j].setComponent(i, rows[i].getComponent(j));
            }
        }
        rows = temp;
    }

    public void multiplyOnScalar(double scalar) {
        for (Vector vector : rows) {
            vector.multiplyOnScalar(scalar);
        }
    }

    private void checkRange(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Range error! Size of index must be more than 0. Entered value of index: " + index);
        }
        if (index >= rows.length) {
            throw new IllegalArgumentException("Range error! Size of index out of size array. Entered value of index: " + index);
        }
    }

    public double getDeterminant() {
        //System.out.println("ROWS.LENGTH: " + rows.length);
        return getMatrixDeterminant(getDoubleArray());
    }

    private double[][] getDoubleArray() {
        //System.out.println("rows.length: " + rows.length + " rows[0].getSize(): " + rows[0].getSize() + " rows[1].getSize(): " + rows[1].getSize());
        double[][] temporaryArray = new double[rows.length][rows[0].getSize()];
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < rows[i].getSize(); j++) {
                temporaryArray[i][j] = rows[i].getComponent(j);
            }
        }

        return temporaryArray;
    }

    private double getMatrixDeterminant(double[][] matrix) {
        double[][] temporaryArray;
        double result = 0;
        if (matrix.length == 1) {

            return matrix[0][0];
        }
        if (matrix.length == 2) {

            return ((matrix[0][0] * matrix[1][1]) - (matrix[0][1] * matrix[1][0]));
        }
        for (int i = 0; i < matrix[0].length; i++) {
            temporaryArray = new double[matrix.length - 1][matrix[0].length - 1];
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
        String result = new String("{");
        for (int i = 0; i < rows.length; i++) {
            result = result.concat("{");
            for (int j = 0; j < rows[i].getSize(); j++) {
                result = result.concat(String.valueOf(rows[i].getComponent(j)) + ",");
            }
            result = result.replaceAll(",$", "").concat("},");
        }

        return result.replaceAll(",$", "").concat("}");
    }

    public void multiplyOnVector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Incorrect value of argument!  vector = null");
        }
        if (rows[0].getSize() < vector.getSize()) {
            throw new IllegalArgumentException("Size of vector is too big! Size: " + vector.getSize());
        }

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < vector.getSize(); j++) {
                rows[i].setComponent(j, rows[i].getComponent(j) * vector.getComponent(j));
            }
        }
    }

    public void additionMatrix(Matrix matrix) {
        checkSizeMatrix(this, matrix);
        addMatrix(this, matrix);
    }

    public static void additionMatrix(Matrix matrix1, Matrix matrix2) {
        checkSizeMatrix(matrix1, matrix2);
        addMatrix(matrix1, matrix2);
    }

    public void subtractionMatrix(Matrix matrix) {
        checkSizeMatrix(this, matrix);
        subtMatrix(this, matrix);
    }

    public static void subtractionMatrix(Matrix matrix1, Matrix matrix2) {
        checkSizeMatrix(matrix1, matrix2);
        subtMatrix(matrix1, matrix2);
    }

    private static void addMatrix(Matrix matrix1, Matrix matrix2) {
        checkSizeMatrix(matrix1, matrix2);
        for (int i = 0; i < matrix1.rows.length; i++) {
            for (int j = 0; j < matrix1.rows[j].getSize(); j++) {
                matrix1.rows[i].setComponent(j, matrix1.rows[i].getComponent(j) + matrix2.rows[i].getComponent(j));
            }
        }
    }

    private static void subtMatrix(Matrix matrix1, Matrix matrix2) {
        checkSizeMatrix(matrix1, matrix2);
        for (int i = 0; i < matrix1.rows.length; i++) {
            for (int j = 0; j < matrix1.rows[j].getSize(); j++) {
                matrix1.rows[i].setComponent(j, matrix1.rows[i].getComponent(j) - matrix2.rows[i].getComponent(j));
            }
        }
    }

    private static void checkSizeMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new IllegalArgumentException("Incorrect value of argument!  matrix1 = null");
        }
        if (matrix2 == null) {
            throw new IllegalArgumentException("Incorrect value of argument!  matrix2 = null");
        }
        if (matrix1.getColumnSize() != matrix2.getColumnSize()) {
            throw new IllegalArgumentException("Size of column are not equals! Size of current matrix: " + matrix1.getColumnSize() + "Size of column entered matrix: " + matrix2.getColumnSize());
        }
        if (matrix1.getRowSize() != matrix2.getRowSize()) {
            throw new IllegalArgumentException("Size of row are not equals! Size of row current matrix: " + matrix1.getRowSize() + "Size of row entered matrix: " + matrix2.getRowSize());
        }
    }
}
