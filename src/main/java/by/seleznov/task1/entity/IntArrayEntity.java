package by.seleznov.task1.entity;

import java.util.Arrays;
import java.util.UUID;

public class IntArrayEntity {

    private final String id;
    private final int[] values;

    public IntArrayEntity(int[] values) {
        this(UUID.randomUUID().toString(), values);
    }

    public IntArrayEntity(String id, int[] values) {
        validateId(id);
        validateValues(values);
        this.id = id;
        this.values = Arrays.copyOf(values, values.length);
    }

    public String getId() {
        return id;
    }

    public int[] getValues() {
        return Arrays.copyOf(values, values.length);
    }

    public int size() {
        return values.length;
    }

    public IntArrayEntity withValues(int[] newValues) {
        return new IntArrayEntity(id, newValues);
    }

    private void validateId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Array id must not be blank.");
        }
    }

    private void validateValues(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException("Array values must not be null.");
        }
    }
}
