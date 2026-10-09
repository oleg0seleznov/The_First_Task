package by.seleznov.task1.factory;

import by.seleznov.task1.entity.IntArrayEntity;
import by.seleznov.task1.exception.InvalidArrayException;

public interface ArrayFactory {

    IntArrayEntity create(int[] values) throws InvalidArrayException;
}
