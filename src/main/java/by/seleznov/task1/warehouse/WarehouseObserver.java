package by.seleznov.task1.warehouse;

import by.seleznov.task1.entity.IntArrayEntity;
import by.seleznov.task1.exception.ArrayRepositoryException;

public class WarehouseObserver implements ArrayObserver {

    private final Warehouse warehouse;

    public WarehouseObserver(Warehouse warehouse) {
        if (warehouse == null) {
            throw new ArrayRepositoryException("Warehouse must not be null.");
        }
        this.warehouse = warehouse;
    }

    @Override
    public void onArrayAdded(IntArrayEntity array) {
        warehouse.updateStatistics(array);
    }

    @Override
    public void onArrayChanged(IntArrayEntity array) {
        warehouse.updateStatistics(array);
    }

    @Override
    public void onArrayRemoved(String id) {
        warehouse.removeStatistics(id);
    }
}
