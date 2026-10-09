package by.seleznov.task1.entity;

import java.util.Arrays;

public class IntArrayEntity {

    private final int[] values;

    public IntArrayEntity(int[] values) {
        validateValues(values);
        this.values = Arrays.copyOf(values, values.length);
    }

    public int[] getValues() {
        return Arrays.copyOf(values, values.length);
    }

    public int size() {
        return values.length;
    }

    private void validateValues(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException("Array values must not be null.");
        }
    }
}
