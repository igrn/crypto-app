package com.javarush.zelenin.dto;

import com.javarush.zelenin.algorithm.Algorithm;

public record Params(String sourcePath, String destinationPath,
                     String key, Algorithm algorithm) {
    public static final Params EMPTY = new Params("", "", "", Algorithm.CAESAR);

}
