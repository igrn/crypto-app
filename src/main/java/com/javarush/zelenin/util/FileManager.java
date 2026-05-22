package com.javarush.zelenin.util;

import com.javarush.zelenin.constant.Const;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public final class FileManager {

    private FileManager() {
        throw new UnsupportedOperationException(Const.UNSUPPORTED_OPERATION);
    }

    public static Stream<String> readFile(String filePath) throws IOException {
        return Files.lines(resolvePath(filePath));
    }

    public static void writeFile(Stream<String> lines, String filePath) throws IOException {
        Files.write(resolvePath(filePath), (Iterable<String>) lines::iterator);
    }

    public static Path resolvePath(String filePath) {
        String expandedPath = filePath.startsWith("~")
                ? filePath.replaceFirst("^~", System.getProperty("user.home"))
                : filePath;
        return Path.of(expandedPath).toAbsolutePath().normalize();
    }
}
