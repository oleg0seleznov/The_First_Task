package by.seleznov.task1.parser;

import by.seleznov.task1.entity.IntArrayEntity;
import by.seleznov.task1.exception.InvalidArrayException;
import java.util.List;

public interface ArrayDataParser {

    List<IntArrayEntity> parse(List<String> lines) throws InvalidArrayException;
}
