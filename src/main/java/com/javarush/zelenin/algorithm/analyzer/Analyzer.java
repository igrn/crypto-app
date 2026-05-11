package com.javarush.zelenin.algorithm.analyzer;

public interface Analyzer<T> {

    T findKey(String text);
}
