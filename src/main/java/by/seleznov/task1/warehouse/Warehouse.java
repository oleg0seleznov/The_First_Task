package by.seleznov.task1.warehouse;

import by.seleznov.task1.entity.IntArrayEntity;
import by.seleznov.task1.service.CalculateService;
import by.seleznov.task1.service.impl.DefaultCalculateService;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public class Warehouse {

    private static final Warehouse INSTANCE = new Warehouse();

    private final Map<String, ArrayStatistics> statisticsById = new HashMap<>();
    private final CalculateService calculateService = new DefaultCalculateService();

    private Warehouse() {
    }

    public static Warehouse getInstance() {
        return INSTANCE;
    }

    void updateStatistics(IntArrayEntity array) {
        recalculate(array);
    }

    void removeStatistics(String id) {
        statisticsById.remove(id);
    }

    public Optional<ArrayStatistics> findStatistics(String id) {
        return Optional.ofNullable(statisticsById.get(id));
    }

    public Map<String, ArrayStatistics> getAllStatistics() {
        return Collections.unmodifiableMap(new HashMap<>(statisticsById));
    }

    private void recalculate(IntArrayEntity array) {
        OptionalLong sum = calculateService.calculateSum(array);
        OptionalDouble average = calculateService.calculateAverage(array);
        OptionalInt maximum = calculateService.findMaximum(array);
        OptionalInt minimum = calculateService.findMinimum(array);
        ArrayStatistics statistics = new ArrayStatistics(
                sum.orElse(0L),
                average,
                maximum,
                minimum,
                array.size());
        statisticsById.put(array.getId(), statistics);
    }
}
