package com.javarush.zelenin.service;

import com.javarush.zelenin.algorithm.cipher.Cipher;
import com.javarush.zelenin.dto.Params;
import com.javarush.zelenin.util.FileManager;
import com.javarush.zelenin.util.TriFunction;

import java.io.IOException;
import java.util.stream.Stream;

public class CipherService {

    public void handleEncryption(Params params) {
        process(params, Cipher::encrypt);
    }

    public void handleDecryption(Params params) {
        process(params, Cipher::decrypt);
    }

    @SuppressWarnings("unchecked")
    private static <T> void process(Params params, TriFunction<Cipher<T>, String, T, String> operation) {
        try (Stream<String> lines = FileManager.readFile(params.sourcePath())) {
            Cipher<T> cipher = (Cipher<T>) params.algorithm().createCipher();
            T key = cipher.parseKey(params.key());
            Stream<String> processedLines = lines.map(line -> operation.apply(cipher, line, key));
            FileManager.writeFile(processedLines, params.destinationPath());
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
