package ru.academit.vkap.vector;

import java.util.Arrays;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Vector {
    private double[] components;

    public Vector(int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Dimension of vector can`t less or equal 0" + System.lineSeparator() + "Dimension:" + dimension + " Please, enter new value!");
        }

        components = new double[dimension];
    }

    public Vector(Vector vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Incorrect value of argument! " + System.lineSeparator() + "vector = null");
        }
        if (vector.components.length == 0) {
            throw new IllegalArgumentException("Obtained vector has incorrect length!" + System.lineSeparator() + "components.length = 0");
        }

        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Incorrect value of argument: components.length = 0");
        }

        components = Arrays.copyOf(array, array.length);
    }

    public Vector(int dimension, double[] array) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Incorrect value of argument: dimension = " + dimension);
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

    public void add(Vector vector) {
        сheck(vector);
        double[] arrayTemporary = getArrayAlignment(vector.components);
        DoubleBinaryOperator expression = Double::sum;
        components = IntStream.range(0, components.length)
                .mapToDouble(index -> expression.applyAsDouble(components[index], arrayTemporary[index]))
                .toArray();
    }

    public void subtract(Vector vector) {
        сheck(vector);

        double[] arrayTemporary = getArrayAlignment(vector.components);
        DoubleBinaryOperator expression = (x, y) -> x - y;
        components = IntStream.range(0, components.length)
                .mapToDouble(index -> expression.applyAsDouble(components[index], arrayTemporary[index]))
                .toArray();
    }

    private void сheck(Vector vector) {
        if (vector.components.length == 0) {
            throw new IllegalArgumentException("Incorrect value length of components: length = 0");
        }
    }

    private double[] getArrayAlignment(double[] array) {
        if (components.length != array.length) {
            int dimension = Math.max(components.length, array.length);
            components = Arrays.copyOf(components, dimension);

            return Arrays.copyOf(array, dimension);
        }

        return array;
    }

    public void multiplyOnScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void turn() {
        multiplyOnScalar(-1);
    }

    public double getLength() {
        double length = Arrays.stream(components).map(x -> x * x).sum();

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
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
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

    public static Vector getAmount(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.add(vector2);

        return result;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.subtract(vector2);

        return result;
    }

    public static double getScalarMultiplication(Vector vector1, Vector vector2) {
        double result = 0;
        int length = Math.min(vector1.getSize(), vector2.getSize());
        for (int i = 0; i < length; i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}
