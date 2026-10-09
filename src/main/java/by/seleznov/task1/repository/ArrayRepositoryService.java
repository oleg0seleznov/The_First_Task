package by.seleznov.task1.repository;

import by.seleznov.task1.entity.IntArrayEntity;
import by.seleznov.task1.repository.specification.ArraySpecification;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface ArrayRepositoryService {

    void add(IntArrayEntity array);

    void addAll(Iterable<IntArrayEntity> arrays);

    boolean removeById(String id);

    void updateElement(String id, int index, int value);

    Optional<IntArrayEntity> findById(String id);

    List<IntArrayEntity> findByElement(int value);

    List<IntArrayEntity> findBy(ArraySpecification specification);

    List<IntArrayEntity> findAllSorted(Comparator<IntArrayEntity> comparator);

    List<IntArrayEntity> findAll();
}
