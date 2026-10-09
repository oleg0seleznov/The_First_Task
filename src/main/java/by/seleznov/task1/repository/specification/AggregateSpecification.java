package by.seleznov.task1.repository.specification;

import by.seleznov.task1.entity.IntArrayEntity;
import by.seleznov.task1.exception.ArrayRepositoryException;
import by.seleznov.task1.service.CalculateService;
import by.seleznov.task1.service.impl.DefaultCalculateService;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public class AggregateSpecification implements ArraySpecification {

    private final Metric metric;
    private final Comparison comparison;
    private final double expectedValue;
    private final CalculateService calculateService = new DefaultCalculateService();

    public AggregateSpecification(Metric metric, Comparison comparison, double expectedValue) {
        if (metric == null || comparison == null) {
            throw new ArrayRepositoryException("Aggregate metric and comparison must be provided.");
        }
        this.metric = metric;
        this.comparison = comparison;
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean isSatisfiedBy(IntArrayEntity array) {
        double actualValue = getValue(array);
        return !Double.isNaN(actualValue) && comparison.matches(actualValue, expectedValue);
    }

    private double getValue(IntArrayEntity array) {
        switch (metric) {
            case SUM: {
                OptionalLong sum = calculateService.calculateSum(array);
                return sum.isPresent() ? sum.getAsLong() : Double.NaN;
            }
            case AVERAGE: {
                OptionalDouble average = calculateService.calculateAverage(array);
                return average.orElse(Double.NaN);
            }
            case MAXIMUM: {
                OptionalInt maximum = calculateService.findMaximum(array);
                return maximum.isPresent() ? maximum.getAsInt() : Double.NaN;
            }
            case MINIMUM: {
                OptionalInt minimum = calculateService.findMinimum(array);
                return minimum.isPresent() ? minimum.getAsInt() : Double.NaN;
            }
            case ELEMENT_COUNT:
                return array.size();
            default:
                throw new IllegalStateException("Unsupported aggregate metric: " + metric);
        }
    }

    public enum Metric {
        SUM,
        AVERAGE,
        MAXIMUM,
        MINIMUM,
        ELEMENT_COUNT
    }

    public enum Comparison {
        LESS_THAN {
            @Override
            public boolean matches(double actualValue, double expectedValue) {
                return actualValue < expectedValue;
            }
        },
        EQUAL_TO {
            @Override
            public boolean matches(double actualValue, double expectedValue) {
                return Double.compare(actualValue, expectedValue) == 0;
            }
        },
        GREATER_THAN {
            @Override
            public boolean matches(double actualValue, double expectedValue) {
                return actualValue > expectedValue;
            }
        };

        public abstract boolean matches(double actualValue, double expectedValue);
    }
}
