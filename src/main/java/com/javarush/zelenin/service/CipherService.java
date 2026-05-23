package com.javarush.zelenin.service;

import com.javarush.zelenin.algorithm.Algorithm;
import com.javarush.zelenin.algorithm.cipher.Cipher;
import com.javarush.zelenin.constant.Const;
import com.javarush.zelenin.dto.Params;
import com.javarush.zelenin.dto.Result;
import com.javarush.zelenin.dto.Result.Code;
import com.javarush.zelenin.exception.AppException;
import com.javarush.zelenin.util.FileManager;
import com.javarush.zelenin.util.TriFunction;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * A service for encrypting or decrypting text files.
 */
public class CipherService {

    /**
     * Encrypts a text file with the provided encryption {@link Algorithm}.
     * @param params the application's {@link Params} with an encryption key
     * @return {@link Result} of the encryption if it was successful
     * @throws AppException if the operation has failed
     */
    public Result handleEncryption(Params params) {
        return process(params, Cipher::encrypt);
    }

    /**
     * Decrypts a text file with the provided encryption {@link Algorithm}.
     * @param params the application's {@link Params} with an encryption key
     * @return {@link Result} of the decryption if it was successful
     * @throws AppException if the operation has failed
     */
    public Result handleDecryption(Params params) {
        return process(params, Cipher::decrypt);
    }

    private static <T> Result process(Params params, TriFunction<Cipher<T>, String, T, String> operation) {
        try (Stream<String> lines = FileManager.readFile(params.sourcePath())) {
            Cipher<T> cipher = params.algorithm().createCipher();
            T key = cipher.parseKey(params.key());
            Stream<String> processedLines = lines.map(line -> operation.apply(cipher, line, key));
            FileManager.writeFile(processedLines, params.destinationPath());
            return new Result(Code.OK, FileManager.resolvePath(params.destinationPath()).toString());
        } catch (IOException e) {
            throw new AppException(Const.INCORRECT_FILE + e.getMessage());
        } catch (NumberFormatException e) {
            throw new AppException(e.getMessage());
        }
    }
}
