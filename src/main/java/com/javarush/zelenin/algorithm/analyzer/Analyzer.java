package com.javarush.zelenin.algorithm.analyzer;

public interface Analyzer<T> {

    T findKeyViaBruteForce(String text);

    T findKeyViaAnalysis(String text);
}
