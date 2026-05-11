package com.javarush.zelenin.service;

import com.javarush.zelenin.algorithm.cipher.Cipher;
import com.javarush.zelenin.dto.Params;
import com.javarush.zelenin.util.io.FileManager;

import java.io.IOException;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public class CipherService {

    public <T> void handleEncryption(Params params) {
        Cipher<T> cipher = (Cipher<T>) params.algorithm().createCipher();
        T key = cipher.parseKey(params.key());

        //TODO добавить обработку дефолтного файла (text/text.txt)
        try (Stream<String> lines = FileManager.readFile(params.sourcePath())) {
            Stream<String> encryptedLines = lines.map(line -> cipher.encrypt(line, key));
            String encryptedFilePath = FileManager.constructOutputPath(
                    params.sourcePath(), params.destinationPath()).toString();

            FileManager.writeFile(encryptedLines, encryptedFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage()); //TODO прописать исключения
        }
    }

    public <T> void handleDecryption(Params params) { }
}
