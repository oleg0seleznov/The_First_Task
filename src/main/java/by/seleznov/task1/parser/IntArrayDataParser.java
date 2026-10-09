package by.seleznov.task1.parser;

import by.seleznov.task1.entity.IntArrayEntity;
import by.seleznov.task1.exception.ArrayParserException;
import by.seleznov.task1.exception.InvalidArrayException;
import by.seleznov.task1.factory.ArrayFactory;
import by.seleznov.task1.repository.ArrayRepository;
import by.seleznov.task1.repository.ArrayRepositoryService;
import by.seleznov.task1.validation.ArrayDataValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IntArrayDataParser implements ArrayDataParser {

    private static final Logger LOGGER = LogManager.getLogger(IntArrayDataParser.class);
    private static final Pattern SEPARATOR_PATTERN = Pattern.compile("[,;\\s\\u2013-]+");

    private final ArrayDataValidator validator;
    private final ArrayFactory factory;
    private final ArrayRepositoryService repository;

    public IntArrayDataParser(ArrayDataValidator validator, ArrayFactory factory) {
        this(validator, factory, ArrayRepository.getInstance());
    }

    public IntArrayDataParser(
            ArrayDataValidator validator,
            ArrayFactory factory,
            ArrayRepositoryService repository) {
        if (validator == null || factory == null || repository == null) {
            throw new ArrayParserException("Parser dependencies must not be null.");
        }
        this.validator = validator;
        this.factory = factory;
        this.repository = repository;
    }

    @Override
    public List<IntArrayEntity> parse(List<String> lines) throws InvalidArrayException {
        if (lines == null) {
            throw new InvalidArrayException("Array data lines must not be null.");
        }
        List<IntArrayEntity> arrays = new ArrayList<>();
        for (int lineIndex = 0; lineIndex < lines.size(); lineIndex++) {
            String line = lines.get(lineIndex);
            if (line == null || line.trim().isEmpty()) {
                continue;
            }
            if (validator.isValid(line)) {
                int[] values = parseValues(line);
                IntArrayEntity array = factory.create(values);
                repository.add(array);
                arrays.add(array);
            } else {
                LOGGER.warn("Skipping invalid array data at line {}.", lineIndex + 1);
            }
        }
        return arrays;
    }

    private int[] parseValues(String line) {
        String[] tokens = SEPARATOR_PATTERN.split(line.trim());
        int[] values = new int[tokens.length];
        for (int index = 0; index < tokens.length; index++) {
            values[index] = Integer.parseInt(tokens[index]);
        }
        return values;
    }
}
