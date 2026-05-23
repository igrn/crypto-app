package com.javarush.zelenin.algorithm;

import com.javarush.zelenin.algorithm.analyzer.Analyzer;
import com.javarush.zelenin.algorithm.analyzer.CaesarAnalyzer;
import com.javarush.zelenin.algorithm.cipher.CaesarCipher;
import com.javarush.zelenin.algorithm.cipher.Cipher;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * An enum containing all available encryption algorithms.
 */
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

    /**
     * Creates a suitable {@link Cipher} from the calling {@link Algorithm} enum.
     * @param <T> type of the key used by the algorithm's {@link Cipher}
     * @return a new {@link Cipher} object
     */
    public <T> Cipher<T> createCipher() {
        return (Cipher<T>) cipherFactory.get();
    }

    /**
     * Creates a suitable {@link Analyzer} from the calling {@link Algorithm} enum.
     * @param <T> type of the key used by the algorithm's {@link Analyzer}
     * @return a new {@link Analyzer} object
     */
    public <T> Analyzer<T> createAnalyzer(Cipher<T> cipher) {
        return (Analyzer<T>) analyzerFactory.apply(cipher);
    }
}
