package by.seleznov.task1.validation;

import java.util.regex.Pattern;

public class IntArrayDataValidator implements ArrayDataValidator {

    private static final Pattern ARRAY_PATTERN =
            Pattern.compile("\\d+(?:[,;\\s\\u2013-]+\\d+)*");
    private static final Pattern SEPARATOR_PATTERN = Pattern.compile("[,;\\s\\u2013-]+");
    private static final Pattern INTEGER_PATTERN = Pattern.compile("\\d+");

    @Override
    public boolean isValid(String line) {
        if (line == null || line.trim().isEmpty()) {
            return false;
        }

        String trimmedLine = line.trim();
        if (!ARRAY_PATTERN.matcher(trimmedLine).matches()) {
            return false;
        }
        String[] tokens = SEPARATOR_PATTERN.split(trimmedLine);
        if (tokens.length == 0) {
            return false;
        }

        for (String token : tokens) {
            if (!INTEGER_PATTERN.matcher(token).matches() || !fitsIntegerRange(token)) {
                return false;
            }
        }
        return true;
    }

    private boolean fitsIntegerRange(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
