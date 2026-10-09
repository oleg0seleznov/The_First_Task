package by.seleznov.task1.warehouse;

import by.seleznov.task1.entity.IntArrayEntity;

public interface ArrayObserver {

    void onArrayAdded(IntArrayEntity array);

    void onArrayChanged(IntArrayEntity array);

    void onArrayRemoved(String id);
}
