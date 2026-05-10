package com.javarush.zelenin.service;

import com.javarush.zelenin.algorithm.cipher.Cipher;
import com.javarush.zelenin.dto.ParamsDto;
import com.javarush.zelenin.util.io.FileManager;

import java.io.IOException;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public class CipherService {

    public <T> void handleEncryption(ParamsDto paramsDto) {
        Cipher<T> cipher = (Cipher<T>) paramsDto.algorithm().createCipher();
        T key = cipher.parseKey(paramsDto.key());

        //TODO добавить обработку дефолтного файла (text/text.txt)
        try (Stream<String> lines = FileManager.readFile(paramsDto.sourcePath())) {
            Stream<String> encryptedLines = lines.map(line -> cipher.encrypt(line, key));
            String encryptedFilePath = FileManager.constructOutputPath(
                    paramsDto.sourcePath(), paramsDto.destinationPath()).toString();

            FileManager.writeFile(encryptedLines, encryptedFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage()); //TODO прописать исключения
        }
    }

    public <T> void handleDecryption(ParamsDto paramsDto) { }
}
