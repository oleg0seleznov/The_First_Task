package by.seleznov.task1.repository.specification;

import by.seleznov.task1.entity.IntArrayEntity;

public class IdSpecification implements ArraySpecification {

    private final String id;

    public IdSpecification(String id) {
        this.id = id;
    }

    @Override
    public boolean isSatisfiedBy(IntArrayEntity array) {
        return id != null && id.equals(array.getId());
    }
}
