package com.javarush.zelenin.service;

import com.javarush.zelenin.algorithm.analyzer.Analyzer;
import com.javarush.zelenin.algorithm.cipher.Cipher;
import com.javarush.zelenin.dto.Params;
import com.javarush.zelenin.exception.AppException;
import com.javarush.zelenin.constant.Const;
import com.javarush.zelenin.util.FileManager;

import java.io.IOException;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnalyzerService {

    public Params handleBruteForce(Params params) {
        return process(params, Analyzer::findKeyViaBruteForce);
    }

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
            throw new AppException(e.getMessage());
        }
    }
}
