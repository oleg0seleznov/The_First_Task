package by.seleznov.task1.service;

import by.seleznov.task1.entity.IntArrayEntity;

public interface SortService {

    IntArrayEntity sortByBubble(IntArrayEntity array);

    IntArrayEntity sortByInsertion(IntArrayEntity array);
}
