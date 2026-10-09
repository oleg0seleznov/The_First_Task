package by.seleznov.task1.repository.comparator;

import by.seleznov.task1.entity.IntArrayEntity;
import java.util.Comparator;

public class ElementCountComparator implements Comparator<IntArrayEntity> {

    @Override
    public int compare(IntArrayEntity first, IntArrayEntity second) {
        return Integer.compare(first.size(), second.size());
    }
}
