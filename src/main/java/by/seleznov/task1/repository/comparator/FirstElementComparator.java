package by.seleznov.task1.repository.comparator;

import by.seleznov.task1.entity.IntArrayEntity;
import java.util.Comparator;

public class FirstElementComparator implements Comparator<IntArrayEntity> {

    @Override
    public int compare(IntArrayEntity first, IntArrayEntity second) {
        int[] firstValues = first.getValues();
        int[] secondValues = second.getValues();
        if (firstValues.length == 0 && secondValues.length == 0) {
            return 0;
        }
        if (firstValues.length == 0) {
            return -1;
        }
        if (secondValues.length == 0) {
            return 1;
        }
        return Integer.compare(firstValues[0], secondValues[0]);
    }
}
