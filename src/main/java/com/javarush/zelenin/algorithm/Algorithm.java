package com.javarush.zelenin.algorithm;

import com.javarush.zelenin.algorithm.analyzer.Analyzer;
import com.javarush.zelenin.algorithm.analyzer.CaesarAnalyzer;
import com.javarush.zelenin.algorithm.cipher.CaesarCipher;
import com.javarush.zelenin.algorithm.cipher.Cipher;

import java.util.function.Supplier;

public enum Algorithm {
    CAESAR(CaesarCipher::new, CaesarAnalyzer::new);

    private final Supplier<Cipher<?>> cipherFactory;
    private final Supplier<Analyzer<?>> analyzerFactory;

    Algorithm(Supplier<Cipher<?>> cipherFactory,
              Supplier<Analyzer<?>> analyzerFactory) {
        this.cipherFactory = cipherFactory;
        this.analyzerFactory = analyzerFactory;
    }

    public Cipher<?> createCipher() {
        return cipherFactory.get();
    }

    public Analyzer<?> createAnalyzer() {
        return analyzerFactory.get();
    }
}
