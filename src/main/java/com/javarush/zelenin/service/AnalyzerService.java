package com.javarush.zelenin.service;

import com.javarush.zelenin.algorithm.analyzer.Analyzer;
import com.javarush.zelenin.algorithm.analyzer.CaesarAnalyzer;
import com.javarush.zelenin.dto.Params;
import com.javarush.zelenin.util.FileManager;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnalyzerService {

    //TODO передалать на дженерики
    public Params handleBruteForce(Params params) {
        Analyzer<Integer> analyzer = new CaesarAnalyzer();
        Integer key;

        try (Stream<String> lines = FileManager.readFile(params.sourcePath())) {
            String sample = lines.limit(20).collect(Collectors.joining("\n"));
            key = analyzer.findKey(sample);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        return new Params(params.sourcePath(), params.destinationPath(),
                key.toString(), params.algorithm());
    }
}
