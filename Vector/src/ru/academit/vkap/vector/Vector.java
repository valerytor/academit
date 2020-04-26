package ru.academit.vkap.vector;

import java.util.Arrays;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Vector {
    private double[] components;

    public Vector(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Dimension of vector can`t less or equal 0" + System.lineSeparator() + "Please, enter new value!");
        }
        components = new double[dimension];
    }

    public Vector(Vector vector) {
        if (vector == null || this == vector) {
            throw new IllegalArgumentException("Incorrect value of argument!");
        }
        if (vector.components.length == 0) {
            throw new IllegalArgumentException("Obtained vector has incorrect length!");
        }
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Incorrect value of argument!");
        }
        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int dimension, double[] array) {
        if (array.length == 0 || dimension == 0 || array.length > dimension) {
            throw new IllegalArgumentException("Incorrect value of argument!");
        }
        if (array.length < dimension) {
            components = new double[dimension];
            System.arraycopy(array, 0, components, 0, array.length);
        }
        components = Arrays.copyOf(array, dimension);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        return Arrays.stream(components).mapToObj(Double::toString).collect(Collectors.joining(", ", "{", "}"));
    }

    public void vectorAddition(Vector vector) {
        RuntimeException check = vectorCheck(vector);
        if (check != null) {
            throw check;
        }
        double[] array2 = arrayAlignment(vector.components);
        components = getArrayCalculation('+', array2);
    }

    public void vectorSubtraction(Vector vector) {
        RuntimeException check = vectorCheck(vector);
        if (check != null) {
            throw check;
        }
        double[] array2 = arrayAlignment(vector.components);
        components = getArrayCalculation('-', array2);
    }

    private RuntimeException vectorCheck(Vector vector) {
        if (vector.components.length == 0) {
            return new IllegalArgumentException("Incorrect value length of components!");
        }

        return null;
    }

    private double[] getArrayCalculation(char exp, double[] array) {
        char[] charArray = new char[]{'-', '+'};
        if (IntStream.range(0, charArray.length).mapToObj(i -> charArray[i]).noneMatch(c -> c == exp)) {
            throw new IllegalArgumentException("Char of expression is not correct");
        }

        DoubleBinaryOperator expression;
        if (exp == '+') {
            expression = Double::sum;
        } else {
            expression = (x, y) -> x - y;
        }

        return IntStream.range(0, components.length).mapToDouble(index -> expression.applyAsDouble(components[index], array[index])).toArray();
    }

    private double[] arrayAlignment(double[] array) {
        if (components.length != array.length) {
            int dimension = Math.max(components.length, array.length);
            components = Arrays.copyOf(components, dimension);

            return Arrays.copyOf(array, dimension);
        }

        return array;
    }

    public void multiplyOnScalar(double scalar) {
        components = Arrays.stream(components).map(x -> x * scalar).toArray();
    }

    public void turn() {
        multiplyOnScalar(-1);
    }

    public double getLength() {
        double length = Arrays.stream(components).sum();

        return Math.sqrt(length);
    }

    public double getComponent(int index) {
        return components[index];
    }

    public void setComponent(int index, double component) {
        components[index] = component;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Vector p = (Vector) o;

        return Arrays.equals(components, p.components);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(components);

        return hash;
    }

    public static Vector addition(Vector vector1, Vector vector2) {
        Vector itog = new Vector(vector1);
        itog.vectorAddition(vector2);

        return itog;
    }

    public static Vector subtraction(Vector vector1, Vector vector2){
        Vector itog = new Vector(vector1);
        itog.vectorSubtraction(vector2);

        return itog;
    }

    public static double multiplicationScalar(Vector vector1, Vector vector2) {
        double result = 0;
        int length = Math.min(vector1.getSize(), vector2.getSize());
        for (int i = 0; i < length; i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}
