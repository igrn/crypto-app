package com.javarush.zelenin.dto;

import com.javarush.zelenin.algorithm.cipher.Cipher;

public record Params(String sourcePath, String destinationPath,
                     String key, Cipher.Algorithm algorithm) { }
