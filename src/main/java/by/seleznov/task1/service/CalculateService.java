package by.seleznov.task1.service;

import by.seleznov.task1.entity.IntArrayEntity;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public interface CalculateService {

    OptionalInt findMinimum(IntArrayEntity array);

    OptionalInt findMaximum(IntArrayEntity array);

    OptionalLong calculateSum(IntArrayEntity array);

    OptionalDouble calculateAverage(IntArrayEntity array);
}
