package by.seleznov.task1.entity;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import by.seleznov.task1.exception.InvalidArrayException;
import org.junit.jupiter.api.Test;

class IntArrayBuilderTest {

    private static final int[] VALUES = {5, 1, 9};

    @Test
    void buildsArrayWithProvidedValues() throws InvalidArrayException {
        // given
        IntArrayBuilder builder = new IntArrayBuilder().withValues(VALUES);

        // when
        IntArrayEntity array = builder.build();

        // then
        assertArrayEquals(VALUES, array.getValues());
    }

    @Test
    void rejectsNullValues() {
        // given
        IntArrayBuilder builder = new IntArrayBuilder().withValues((int[]) null);

        // when
        InvalidArrayException exception = assertThrows(
                InvalidArrayException.class,
                builder::build);

        // then
        assertEquals("Array values must not be null.", exception.getMessage());
    }
}
