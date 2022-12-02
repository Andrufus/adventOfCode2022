package utils;

import java.nio.file.FileSystems;
import java.nio.file.Path;


public class FileUtils {

    private static final String INPUTS_DIRECTORY = "inputs";

    public static Path getInputAsPath(int dayNumber) {
        return FileSystems.getDefault().getPath(INPUTS_DIRECTORY + "/day" + dayNumber + ".txt").toAbsolutePath();
    }
}
