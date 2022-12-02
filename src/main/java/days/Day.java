package days;

import utils.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class Day<T> {

    Supplier<Stream<T>> inputSupplier;

    public Day() {
        inputSupplier = () -> {
            try {
                return Files.lines(FileUtils.getInputAsPath(getDayNumber()))
                        .map(getMappingFunction());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    abstract Function<String, T> getMappingFunction();

    abstract Integer getDayNumber();

    public abstract String getResultPartOne();

    public abstract String getResultPartTwo();
}
