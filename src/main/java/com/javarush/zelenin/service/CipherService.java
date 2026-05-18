package com.javarush.zelenin.service;

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

public class CipherService {

    public Result handleEncryption(Params params) {
        return process(params, Cipher::encrypt);
    }

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
