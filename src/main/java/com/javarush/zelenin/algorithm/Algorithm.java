package com.javarush.zelenin.algorithm;

import com.javarush.zelenin.algorithm.analyzer.Analyzer;
import com.javarush.zelenin.algorithm.analyzer.CaesarAnalyzer;
import com.javarush.zelenin.algorithm.cipher.CaesarCipher;
import com.javarush.zelenin.algorithm.cipher.Cipher;

import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public enum Algorithm {
    CAESAR(CaesarCipher::new, cipher -> new CaesarAnalyzer((CaesarCipher) cipher));

    private final Supplier<Cipher<?>> cipherFactory;
    private final Function<Cipher<?>, Analyzer<?>> analyzerFactory;

    Algorithm(Supplier<Cipher<?>> cipherFactory,
              Function<Cipher<?>, Analyzer<?>> analyzerFactory) {
        this.cipherFactory = cipherFactory;
        this.analyzerFactory = analyzerFactory;
    }

    public <T> Cipher<T> createCipher() {
        return (Cipher<T>) cipherFactory.get();
    }

    public <T> Analyzer<T> createAnalyzer(Cipher<T> cipher) {
        return (Analyzer<T>) analyzerFactory.apply(cipher);
    }
}
