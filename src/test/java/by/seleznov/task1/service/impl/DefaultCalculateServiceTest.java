package by.seleznov.task1.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import by.seleznov.task1.entity.IntArrayEntity;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import org.junit.jupiter.api.Test;

class DefaultCalculateServiceTest {

    private static final int[] VALUES = {8, -3, 11, 2};
    private static final IntArrayEntity ARRAY = new IntArrayEntity(VALUES);
    private static final IntArrayEntity EMPTY_ARRAY = new IntArrayEntity(new int[0]);

    private final DefaultCalculateService service = new DefaultCalculateService();

    @Test
    void findsMinimum() {
        // given
        IntArrayEntity array = ARRAY;

        // when
        OptionalInt result = service.findMinimum(array);

        // then
        assertEquals(OptionalInt.of(-3), result);
    }

    @Test
    void findsMaximum() {
        // given
        IntArrayEntity array = ARRAY;

        // when
        OptionalInt result = service.findMaximum(array);

        // then
        assertEquals(OptionalInt.of(11), result);
    }

    @Test
    void calculatesSum() {
        // given
        IntArrayEntity array = ARRAY;

        // when
        OptionalLong result = service.calculateSum(array);

        // then
        assertEquals(OptionalLong.of(18L), result);
    }

    @Test
    void calculatesAverage() {
        // given
        IntArrayEntity array = ARRAY;

        // when
        OptionalDouble result = service.calculateAverage(array);

        // then
        assertEquals(OptionalDouble.of(4.5), result);
    }

    @Test
    void returnsEmptyForEmptyArray() {
        // given
        IntArrayEntity array = EMPTY_ARRAY;

        // when
        OptionalInt result = service.findMinimum(array);

        // then
        assertTrue(result.isEmpty());
    }
}
