package by.seleznov.task1.io;

import by.seleznov.task1.exception.ArrayFileException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TextArrayFileReader implements ArrayFileReader {

    @Override
    public List<String> readLines(String relativePath) throws ArrayFileException {
        if (relativePath == null || relativePath.trim().isEmpty()) {
            throw new ArrayFileException("A relative file path must be provided.");
        }
        try {
            Path filePath = Path.of(relativePath);
            return Files.readAllLines(filePath);
        } catch (IOException exception) {
            throw new ArrayFileException("Unable to read array data from: " + relativePath, exception);
        } catch (InvalidPathException exception) {
            throw new ArrayFileException("Invalid relative file path: " + relativePath, exception);
        } catch (SecurityException exception) {
            throw new ArrayFileException("Access denied while reading: " + relativePath, exception);
        }
    }
}
