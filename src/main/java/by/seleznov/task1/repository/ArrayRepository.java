package by.seleznov.task1.repository;

import by.seleznov.task1.entity.IntArrayEntity;
import by.seleznov.task1.exception.ArrayRepositoryException;
import by.seleznov.task1.repository.specification.ArraySpecification;
import by.seleznov.task1.warehouse.ArrayObserver;
import by.seleznov.task1.warehouse.ArraySubject;
import by.seleznov.task1.warehouse.Warehouse;
import by.seleznov.task1.warehouse.WarehouseObserver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ArrayRepository implements ArrayRepositoryService, ArraySubject {

    private static final ArrayRepository INSTANCE = new ArrayRepository();

    private final Map<String, IntArrayEntity> arraysById = new LinkedHashMap<>();
    private final List<ArrayObserver> observers = new ArrayList<>();
    private final ArrayObserver warehouseObserver = new WarehouseObserver(Warehouse.getInstance());

    private ArrayRepository() {
        addObserver(warehouseObserver);
    }

    public static ArrayRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(IntArrayEntity array) {
        if (array == null) {
            throw new ArrayRepositoryException("Array must not be null.");
        }
        if (arraysById.containsKey(array.getId())) {
            throw new ArrayRepositoryException("Array id already exists: " + array.getId());
        }
        arraysById.put(array.getId(), array);
        for (ArrayObserver observer : observers) {
            observer.onArrayAdded(array);
        }
    }

    @Override
    public void addAll(Iterable<IntArrayEntity> arrays) {
        if (arrays == null) {
            throw new ArrayRepositoryException("Arrays must not be null.");
        }
        for (IntArrayEntity array : arrays) {
            add(array);
        }
    }

    @Override
    public boolean removeById(String id) {
        IntArrayEntity removedArray = arraysById.remove(id);
        if (removedArray == null) {
            return false;
        }
        for (ArrayObserver observer : observers) {
            observer.onArrayRemoved(id);
        }
        return true;
    }

    @Override
    public void updateElement(String id, int index, int value) {
        IntArrayEntity array = arraysById.get(id);
        if (array == null) {
            throw new ArrayRepositoryException("Array was not found: " + id);
        }
        int[] values = array.getValues();
        if (index < 0 || index >= values.length) {
            throw new ArrayRepositoryException("Element index is out of range: " + index);
        }
        values[index] = value;
        IntArrayEntity updatedArray = array.withValues(values);
        arraysById.put(id, updatedArray);
        for (ArrayObserver observer : observers) {
            observer.onArrayChanged(updatedArray);
        }
    }

    @Override
    public Optional<IntArrayEntity> findById(String id) {
        return Optional.ofNullable(arraysById.get(id));
    }

    @Override
    public List<IntArrayEntity> findByElement(int value) {
        List<IntArrayEntity> matches = new ArrayList<>();
        for (IntArrayEntity array : arraysById.values()) {
            for (int element : array.getValues()) {
                if (element == value) {
                    matches.add(array);
                    break;
                }
            }
        }
        return Collections.unmodifiableList(matches);
    }

    @Override
    public List<IntArrayEntity> findBy(ArraySpecification specification) {
        if (specification == null) {
            throw new ArrayRepositoryException("Search specification must not be null.");
        }
        List<IntArrayEntity> matches = new ArrayList<>();
        for (IntArrayEntity array : arraysById.values()) {
            if (specification.isSatisfiedBy(array)) {
                matches.add(array);
            }
        }
        return Collections.unmodifiableList(matches);
    }

    @Override
    public List<IntArrayEntity> findAllSorted(Comparator<IntArrayEntity> comparator) {
        if (comparator == null) {
            throw new ArrayRepositoryException("Sort comparator must not be null.");
        }
        List<IntArrayEntity> sortedArrays = new ArrayList<>(arraysById.values());
        sortedArrays.sort(comparator);
        return Collections.unmodifiableList(sortedArrays);
    }

    @Override
    public List<IntArrayEntity> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(arraysById.values()));
    }

    @Override
    public void addObserver(ArrayObserver observer) {
        if (observer == null) {
            throw new ArrayRepositoryException("Observer must not be null.");
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(ArrayObserver observer) {
        if (observer == warehouseObserver) {
            throw new ArrayRepositoryException("The warehouse observer cannot be removed.");
        }
        observers.remove(observer);
    }
}
