package by.seleznov.task1.repository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import by.seleznov.task1.entity.IntArrayEntity;
import by.seleznov.task1.repository.comparator.ArrayIdComparator;
import by.seleznov.task1.repository.comparator.ElementCountComparator;
import by.seleznov.task1.repository.comparator.FirstElementComparator;
import by.seleznov.task1.repository.specification.AggregateSpecification;
import by.seleznov.task1.repository.specification.ElementSpecification;
import by.seleznov.task1.repository.specification.IdSpecification;
import by.seleznov.task1.warehouse.ArrayStatistics;
import by.seleznov.task1.warehouse.Warehouse;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ArrayRepositoryTest {

    private static final String FIRST_ID = "repository-test-first";
    private static final String SECOND_ID = "repository-test-second";
    private static final IntArrayEntity FIRST_ARRAY =
            new IntArrayEntity(FIRST_ID, new int[] {5, 1, 9});
    private static final IntArrayEntity SECOND_ARRAY =
            new IntArrayEntity(SECOND_ID, new int[] {2, 6});

    private final ArrayRepositoryService repository = ArrayRepository.getInstance();
    private final Warehouse warehouse = Warehouse.getInstance();

    @AfterEach
    void removeTestArrays() {
        repository.removeById(FIRST_ID);
        repository.removeById(SECOND_ID);
    }

    @Test
    void findsArraysByIdAndElement() {
        // given
        repository.add(FIRST_ARRAY);
        repository.add(SECOND_ARRAY);

        // when
        List<IntArrayEntity> foundArrays = repository.findByElement(6);
        Optional<IntArrayEntity> foundById = repository.findById(FIRST_ID);

        // then
        assertEquals(1, foundArrays.size());
        assertTrue(foundById.isPresent());
        IntArrayEntity arrayByElement = foundArrays.get(0);
        IntArrayEntity arrayById = foundById.get();
        assertEquals(SECOND_ID, arrayByElement.getId());
        assertEquals(FIRST_ID, arrayById.getId());
        assertTrue(repository.findBy(new IdSpecification(FIRST_ID)).contains(FIRST_ARRAY));
        assertTrue(repository.findBy(new ElementSpecification(9)).contains(FIRST_ARRAY));
    }

    @Test
    void findsArraysByAggregateAndElementCount() {
        // given
        repository.add(FIRST_ARRAY);
        repository.add(SECOND_ARRAY);
        AggregateSpecification sumGreaterThanTen = new AggregateSpecification(
                AggregateSpecification.Metric.SUM,
                AggregateSpecification.Comparison.GREATER_THAN,
                10);
        AggregateSpecification countEqualToTwo = new AggregateSpecification(
                AggregateSpecification.Metric.ELEMENT_COUNT,
                AggregateSpecification.Comparison.EQUAL_TO,
                2);
        AggregateSpecification averageLessThanFive = new AggregateSpecification(
                AggregateSpecification.Metric.AVERAGE,
                AggregateSpecification.Comparison.LESS_THAN,
                5);
        AggregateSpecification maximumEqualToNine = new AggregateSpecification(
                AggregateSpecification.Metric.MAXIMUM,
                AggregateSpecification.Comparison.EQUAL_TO,
                9);
        AggregateSpecification minimumGreaterThanZero = new AggregateSpecification(
                AggregateSpecification.Metric.MINIMUM,
                AggregateSpecification.Comparison.GREATER_THAN,
                0);

        // when
        List<IntArrayEntity> sumMatches = repository.findBy(sumGreaterThanTen);

        // then
        assertEquals(List.of(FIRST_ARRAY), sumMatches);
        assertEquals(List.of(SECOND_ARRAY), repository.findBy(countEqualToTwo));
        assertEquals(List.of(SECOND_ARRAY), repository.findBy(averageLessThanFive));
        assertEquals(List.of(FIRST_ARRAY), repository.findBy(maximumEqualToNine));
        assertEquals(List.of(FIRST_ARRAY, SECOND_ARRAY), repository.findBy(minimumGreaterThanZero));
    }

    @Test
    void sortsArraysWithComparators() {
        // given
        repository.add(FIRST_ARRAY);
        repository.add(SECOND_ARRAY);

        // when
        List<IntArrayEntity> sortedByFirstValue =
                repository.findAllSorted(new FirstElementComparator());

        // then
        assertEquals(List.of(SECOND_ARRAY, FIRST_ARRAY), sortedByFirstValue);
        assertEquals(
                List.of(FIRST_ARRAY, SECOND_ARRAY),
                repository.findAllSorted(new ArrayIdComparator()));
        assertEquals(
                List.of(SECOND_ARRAY, FIRST_ARRAY),
                repository.findAllSorted(new ElementCountComparator()));
    }

    @Test
    void updatesWarehouseStatisticsAndRemovesArray() {
        // given
        repository.add(FIRST_ARRAY);

        // when
        repository.updateElement(FIRST_ID, 0, -4);

        // then
        Optional<ArrayStatistics> statistics = warehouse.findStatistics(FIRST_ID);
        assertTrue(statistics.isPresent());
        ArrayStatistics values = statistics.get();
        assertEquals(6L, values.getSum());
        assertEquals(OptionalDouble.of(2.0), values.getAverage());
        OptionalInt minimum = values.getMinimum();
        OptionalInt maximum = values.getMaximum();
        assertEquals(-4, minimum.getAsInt());
        assertEquals(9, maximum.getAsInt());
        Optional<IntArrayEntity> updatedArrayResult = repository.findById(FIRST_ID);
        assertTrue(updatedArrayResult.isPresent());
        IntArrayEntity updatedArray = updatedArrayResult.get();
        assertArrayEquals(new int[] {-4, 1, 9}, updatedArray.getValues());
    }

    @Test
    void removesArrayFromRepositoryAndWarehouse() {
        // given
        repository.add(FIRST_ARRAY);

        // when
        boolean removed = repository.removeById(FIRST_ID);

        // then
        assertTrue(removed);
        assertFalse(warehouse.findStatistics(FIRST_ID).isPresent());
    }
}
