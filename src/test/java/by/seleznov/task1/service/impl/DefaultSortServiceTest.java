package by.seleznov.task1.service.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import by.seleznov.task1.entity.IntArrayEntity;
import org.junit.jupiter.api.Test;

class DefaultSortServiceTest {

    private static final int[] VALUES = {8, -3, 11, 2};
    private static final int[] SORTED_VALUES = {-3, 2, 8, 11};
    private static final IntArrayEntity ARRAY = new IntArrayEntity(VALUES);

    private final DefaultSortService service = new DefaultSortService();

    @Test
    void sortsByBubble() {
        // given
        IntArrayEntity array = ARRAY;

        // when
        IntArrayEntity result = service.sortByBubble(array);

        // then
        assertArrayEquals(SORTED_VALUES, result.getValues());
    }

    @Test
    void sortsByInsertion() {
        // given
        IntArrayEntity array = ARRAY;

        // when
        IntArrayEntity result = service.sortByInsertion(array);

        // then
        assertArrayEquals(SORTED_VALUES, result.getValues());
    }

    @Test
    void doesNotChangeInputArray() {
        // given
        IntArrayEntity array = ARRAY;

        // when
        service.sortByInsertion(array);

        // then
        assertArrayEquals(VALUES, array.getValues());
    }
}
