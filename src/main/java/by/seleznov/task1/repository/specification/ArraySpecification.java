package by.seleznov.task1.repository.specification;

import by.seleznov.task1.entity.IntArrayEntity;

public interface ArraySpecification {

    boolean isSatisfiedBy(IntArrayEntity array);
}
