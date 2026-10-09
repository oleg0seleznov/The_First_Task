package by.seleznov.task1.service.impl;

import by.seleznov.task1.entity.IntArrayEntity;
import by.seleznov.task1.service.CalculateService;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public class DefaultCalculateService implements CalculateService {

    @Override
    public OptionalInt findMinimum(IntArrayEntity array) {
        int[] values = array.getValues();
        if (values.length == 0) {
            return OptionalInt.empty();
        }

        int minimum = values[0];
        for (int value : values) {
            if (value < minimum) {
                minimum = value;
            }
        }
        return OptionalInt.of(minimum);
    }

    @Override
    public OptionalInt findMaximum(IntArrayEntity array) {
        int[] values = array.getValues();
        if (values.length == 0) {
            return OptionalInt.empty();
        }

        int maximum = values[0];
        for (int value : values) {
            if (value > maximum) {
                maximum = value;
            }
        }
        return OptionalInt.of(maximum);
    }

    @Override
    public OptionalLong calculateSum(IntArrayEntity array) {
        int[] values = array.getValues();
        if (values.length == 0) {
            return OptionalLong.empty();
        }

        long sum = 0L;
        for (int value : values) {
            sum += value;
        }
        return OptionalLong.of(sum);
    }

    @Override
    public OptionalDouble calculateAverage(IntArrayEntity array) {
        int[] values = array.getValues();
        if (values.length == 0) {
            return OptionalDouble.empty();
        }

        long sum = 0L;
        for (int value : values) {
            sum += value;
        }
        return OptionalDouble.of((double) sum / values.length);
    }
}
