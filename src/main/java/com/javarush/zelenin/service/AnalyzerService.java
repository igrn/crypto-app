package com.javarush.zelenin.service;

import com.javarush.zelenin.algorithm.analyzer.Analyzer;
import com.javarush.zelenin.algorithm.cipher.Cipher;
import com.javarush.zelenin.constant.Const;
import com.javarush.zelenin.dto.Params;
import com.javarush.zelenin.exception.AppException;
import com.javarush.zelenin.util.FileManager;

import java.io.IOException;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A service for analyzing encrypted text files.
 */
public class AnalyzerService {

    /**
     * Finds the key to decrypt a text file using brute-force.
     * @param params the application's {@link Params} with the correct key unknown
     * @return same {@link Params} with the correct key guessed by the {@link Analyzer}
     * @throws AppException if the operation has failed
     */
    public Params handleBruteForce(Params params) {
        return process(params, Analyzer::findKeyViaBruteForce);
    }

    /**
     * Finds the key to decrypt a text file using frequency analysis.
     * @param params the application's {@link Params} with the correct key unknown
     * @return same {@link Params} with the correct key guessed by the {@link Analyzer}
     * @throws AppException if the operation has failed
     */
    public Params handleAnalysis(Params params) {
        return process(params, Analyzer::findKeyViaAnalysis);
    }

    private static <T> Params process(Params params, BiFunction<Analyzer<T>, String, T> operation) {
        try (Stream<String> lines = FileManager.readFile(params.sourcePath())) {
            Cipher<T> cipher = params.algorithm().createCipher();
            Analyzer<T> analyzer = params.algorithm().createAnalyzer(cipher);
            String sample = lines.limit(Const.SAMPLE_SIZE).collect(Collectors.joining("\n"));
            String key = operation.apply(analyzer, sample).toString();
            return new Params(params.sourcePath(), params.destinationPath(), key, params.algorithm());
        } catch (IOException e) {
            throw new AppException(Const.INCORRECT_FILE + e.getMessage());
        }
    }
}
