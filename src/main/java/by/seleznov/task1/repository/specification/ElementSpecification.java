package by.seleznov.task1.repository.specification;

import by.seleznov.task1.entity.IntArrayEntity;

public class ElementSpecification implements ArraySpecification {

    private final int expectedValue;

    public ElementSpecification(int expectedValue) {
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean isSatisfiedBy(IntArrayEntity array) {
        for (int value : array.getValues()) {
            if (value == expectedValue) {
                return true;
            }
        }
        return false;
    }
}
