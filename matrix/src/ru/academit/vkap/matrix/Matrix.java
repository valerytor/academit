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
            rows[i] = new Vector(doubleArray[i]);
        }
    }

    public Matrix(Vector[] vectorArray) {
        if (vectorArray == null) {
            throw new IllegalArgumentException("Incorrect value of argument!  vectorArray = null");
        }
        rows = Arrays.copyOf(vectorArray, vectorArray.length);
    }

    private int getMaxLength(Vector[] vectors) {
        return Arrays.stream(vectors).map(Vector::getSize).max(Integer::compare).orElse(-1);
    }

    private int getMaxLength(double[][] array) {
        return Arrays.stream(array).mapToInt(x -> x.length).max().orElse(-1);
    }

    public void showMatrixSize(){
        System.out.println("Size of matrix is column:"+getColumnSize()+" row:"+getRowSize());
    }

    private int getRowSize(){
        return rows.length;
    }

    private int getColumnSize(){
        return rows[0].getSize();
    }
}
