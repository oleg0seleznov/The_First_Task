package by.seleznov.task1.repository.comparator;

import by.seleznov.task1.entity.IntArrayEntity;
import java.util.Comparator;

public class ArrayIdComparator implements Comparator<IntArrayEntity> {

    @Override
    public int compare(IntArrayEntity first, IntArrayEntity second) {
        String firstId = first.getId();
        String secondId = second.getId();
        return firstId.compareTo(secondId);
    }
}
