package com.javarush.zelenin.service;

import com.javarush.zelenin.algorithm.analyzer.Analyzer;
import com.javarush.zelenin.dto.Params;
import com.javarush.zelenin.util.FileManager;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnalyzerService {
    private static final int SAMPLE_SIZE = 20;

    //TODO передалать на дженерики
    @SuppressWarnings("unchecked")
    public <T> Params handleBruteForce(Params params) {
        try (Stream<String> lines = FileManager.readFile(params.sourcePath())) {
            Analyzer<T> analyzer = (Analyzer<T>) params.algorithm().createAnalyzer();
            String sample = lines.limit(SAMPLE_SIZE).collect(Collectors.joining("\n"));
            T key = analyzer.findKeyViaBruteForce(sample);

            return new Params(params.sourcePath(), params.destinationPath(),
                    key.toString(), params.algorithm());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
