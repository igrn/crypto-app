package com.javarush.zelenin.dto;

import com.javarush.zelenin.algorithm.Algorithm;

/**
 * An object that holds the parameters of the application.
 * @param sourcePath a {@link String} with the path to the source file
 * @param destinationPath a {@link String} with the path to the destination file
 * @param key a {@link String} containing the encryption key
 * @param algorithm an encryption {@link Algorithm} enum (e.g. {@code CAESAR})
 */
public record Params(String sourcePath, String destinationPath,
                     String key, Algorithm algorithm) {
    public static final Params EMPTY = new Params("", "", "", Algorithm.CAESAR);

}
