package by.seleznov.task1.parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import by.seleznov.task1.entity.IntArrayEntity;
import by.seleznov.task1.exception.InvalidArrayException;
import by.seleznov.task1.factory.ArrayFactory;
import by.seleznov.task1.factory.IntArrayFactory;
import by.seleznov.task1.validation.ArrayDataValidator;
import by.seleznov.task1.validation.IntArrayDataValidator;
import java.util.List;
import org.junit.jupiter.api.Test;

class IntArrayDataParserTest {

    private final ArrayDataValidator validator = new IntArrayDataValidator();
    private final ArrayFactory factory = new IntArrayFactory();
    private final ArrayDataParser parser = new IntArrayDataParser(validator, factory);

    @Test
    void parsesValidLinesAndSkipsInvalidLines() throws InvalidArrayException {
        // given
        List<String> lines = List.of("1; 2; 3", "1, 2, x3, 6..5, 77", "", "4 5 6");

        // when
        List<IntArrayEntity> arrays = parser.parse(lines);

        // then
        assertEquals(2, arrays.size());
        assertArrayEquals(new int[] {1, 2, 3}, arrays.get(0).getValues());
        assertArrayEquals(new int[] {4, 5, 6}, arrays.get(1).getValues());
    }
}
