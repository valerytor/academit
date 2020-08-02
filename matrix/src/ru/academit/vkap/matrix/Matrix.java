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
        System.out.println("ROWS.LENGTH: " + rows.length);
        if (rows.length == 1) {
            return rows[0].getComponent(0);

        }
        if (rows.length == 2) {
            return rows[0].getComponent(0) * rows[1].getComponent(1) -
                    rows[0].getComponent(1) * rows[1].getComponent(0);

        }

        return getMatrixDeterminant(getDoubleArray());
    }

    private double[][] getDoubleArray() {
        System.out.println("rows.length: "+rows.length+" rows[0].getSize(): "+rows[0].getSize()+" rows[1].getSize(): "+rows[1].getSize());
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
        for (int i=0; i<rows.length; i++){
            result=result.concat("{");
            for (int j=0; j<rows[i].getSize();j++){
                result=result.concat(String.valueOf(rows[i].getComponent(j))+", ");
            }
            result=result.replaceAll(", $"," ").concat("},");
        }
        return result.concat("}");
 }

}
