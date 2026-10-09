package by.seleznov.task1.entity;

import by.seleznov.task1.exception.InvalidArrayException;
import java.util.Arrays;

public class IntArrayBuilder {

    private int[] values;

    public IntArrayBuilder withValues(int... values) {
        if (values == null) {
            this.values = null;
        } else {
            this.values = Arrays.copyOf(values, values.length);
        }
        return this;
    }

    public IntArrayEntity build() throws InvalidArrayException {
        if (values == null) {
            throw new InvalidArrayException("Array values must not be null.");
        }
        return new IntArrayEntity(values);
    }
}
