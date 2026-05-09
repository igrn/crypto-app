package com.javarush.zelenin.util.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileManager {

    public Stream<String> readFile(String filePath) throws IOException {
        return Files.lines(getFullPath(filePath));
    }

    public void writeFile(Stream<String> lines, String filePath) throws IOException {
        Files.write(getFullPath(filePath), (Iterable<String>) lines::iterator);
    }

    //TODO добавить возможность записывать файл, если указал с раширением (иначе исключение)
    public static Path constructOutputPath(String filePath, String newFileName) {
        String originalFile = getFullPath(filePath).getFileName().toString();

        if (originalFile.equals(newFileName + ".txt")) {
            throw new IllegalArgumentException("Имя нового файла совпадает с именем исходного файла!");
        }
        return Path.of(getFullPath(filePath).getParent().toString(), newFileName + ".txt");
    }

    public static Path getFullPath(String filePath) {
        String expandedPath = filePath.startsWith("~")
                ? filePath.replaceFirst("^~", System.getProperty("user.home"))
                : filePath;

        return Path.of(expandedPath).toAbsolutePath().normalize();
    }

    //TODO Добавить валидатор

}
