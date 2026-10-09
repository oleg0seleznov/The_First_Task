package by.seleznov.task1.factory;

import by.seleznov.task1.entity.IntArrayBuilder;
import by.seleznov.task1.entity.IntArrayEntity;
import by.seleznov.task1.exception.InvalidArrayException;

public class IntArrayFactory implements ArrayFactory {

    @Override
    public IntArrayEntity create(int[] values) throws InvalidArrayException {
        return new IntArrayBuilder()
                .withValues(values)
                .build();
    }
}
