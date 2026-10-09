package by.seleznov.task1.warehouse;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public class ArrayStatistics {

    private final long sum;
    private final OptionalDouble average;
    private final OptionalInt maximum;
    private final OptionalInt minimum;
    private final int elementCount;

    public ArrayStatistics(
            long sum,
            OptionalDouble average,
            OptionalInt maximum,
            OptionalInt minimum,
            int elementCount) {
        this.sum = sum;
        this.average = average;
        this.maximum = maximum;
        this.minimum = minimum;
        this.elementCount = elementCount;
    }

    public long getSum() {
        return sum;
    }

    public OptionalDouble getAverage() {
        return average;
    }

    public OptionalInt getMaximum() {
        return maximum;
    }

    public OptionalInt getMinimum() {
        return minimum;
    }

    public int getElementCount() {
        return elementCount;
    }
}
