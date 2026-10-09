package by.seleznov.task1.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IntArrayDataValidatorTest {

    private final IntArrayDataValidator validator = new IntArrayDataValidator();

    @Test
    void acceptsSupportedSeparators() {
        // given
        String line = "1 – 2 - 3";

        // when
        boolean valid = validator.isValid(line);

        // then
        assertTrue(valid);
    }

    @Test
    void rejectsInvalidToken() {
        // given
        String line = "1, 2, x3, 6..5, 77";

        // when
        boolean valid = validator.isValid(line);

        // then
        assertFalse(valid);
    }

    @Test
    void rejectsTrailingSeparator() {
        // given
        String line = "11- 2 – 42-";

        // when
        boolean valid = validator.isValid(line);

        // then
        assertFalse(valid);
    }
}
