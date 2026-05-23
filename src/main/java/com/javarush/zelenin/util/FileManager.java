package com.javarush.zelenin.util;

import com.javarush.zelenin.constant.Const;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * A utility class for managing I/O operations.
 */
public final class FileManager {

    private FileManager() {
        throw new UnsupportedOperationException(Const.UNSUPPORTED_OPERATION);
    }

    /**
     * Reads all lines from a file as a {@link Stream}.
     * @param filePath the path to the file
     * @return the lines from the file as a {@link Stream}
     * @throws IOException if an I/O error occurs
     */
    public static Stream<String> readFile(String filePath) throws IOException {
        return Files.lines(resolvePath(filePath));
    }

    /**
     * Writes a {@link Stream} of lines of text to a file.
     * @param lines a {@link Stream} of lines of text
     * @param filePath the path to the file
     * @throws IOException if an I/O error occurs
     */
    public static void writeFile(Stream<String> lines, String filePath) throws IOException {
        Files.write(resolvePath(filePath), (Iterable<String>) lines::iterator);
    }

    /**
     * Converts a relative path to a local resource (file or directory)
     * into a {@link Path} object containing the full path.
     * @param filePath the path to the resource as a {@link String}
     * @return a {@link Path} object containing the full path
     */
    public static Path resolvePath(String filePath) {
        String expandedPath = filePath.startsWith("~")
                ? filePath.replaceFirst("^~", System.getProperty("user.home"))
                : filePath;
        return Path.of(expandedPath).toAbsolutePath().normalize();
    }
}
