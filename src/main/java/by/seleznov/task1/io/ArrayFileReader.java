package by.seleznov.task1.io;

import by.seleznov.task1.exception.ArrayFileException;
import java.util.List;

public interface ArrayFileReader {

    List<String> readLines(String relativePath) throws ArrayFileException;
}
