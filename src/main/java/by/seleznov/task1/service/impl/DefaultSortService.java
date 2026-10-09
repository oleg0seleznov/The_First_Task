package by.seleznov.task1.service.impl;

import by.seleznov.task1.entity.IntArrayEntity;
import by.seleznov.task1.service.SortService;

public class DefaultSortService implements SortService {

    @Override
    public IntArrayEntity sortByBubble(IntArrayEntity array) {
        int[] values = array.getValues();
        for (int end = values.length - 1; end > 0; end--) {
            for (int index = 0; index < end; index++) {
                if (values[index] > values[index + 1]) {
                    int value = values[index];
                    values[index] = values[index + 1];
                    values[index + 1] = value;
                }
            }
        }
        return new IntArrayEntity(values);
    }

    @Override
    public IntArrayEntity sortByInsertion(IntArrayEntity array) {
        int[] values = array.getValues();
        for (int index = 1; index < values.length; index++) {
            int value = values[index];
            int position = index - 1;
            while (position >= 0 && values[position] > value) {
                values[position + 1] = values[position];
                position--;
            }
            values[position + 1] = value;
        }
        return new IntArrayEntity(values);
    }
}
