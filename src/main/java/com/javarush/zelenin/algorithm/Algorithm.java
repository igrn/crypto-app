package com.javarush.zelenin.algorithm;

import com.javarush.zelenin.algorithm.analyzer.Analyzer;
import com.javarush.zelenin.algorithm.analyzer.CaesarAnalyzer;
import com.javarush.zelenin.algorithm.cipher.CaesarCipher;
import com.javarush.zelenin.algorithm.cipher.Cipher;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * An enum containing all available encryption algorithms.
 */
@SuppressWarnings("unchecked")
public enum Algorithm {
    CAESAR(1, CaesarCipher::new, cipher -> new CaesarAnalyzer((CaesarCipher) cipher));

    private final int id;
    private final Supplier<Cipher<?>> cipherFactory;
    private final Function<Cipher<?>, Analyzer<?>> analyzerFactory;

    Algorithm(Integer id, Supplier<Cipher<?>> cipherFactory,
              Function<Cipher<?>, Analyzer<?>> analyzerFactory) {
        this.id = id;
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

    /**
     * Finds an algorithm by its id.
     * @param id id of the algorithm to return
     * @return the algorithm with the specified id
     * @throws IllegalArgumentException if the provided id doesn't match any algorithm
     */
    public static Algorithm fromId(Integer id) {
        return Arrays.stream(values()).filter(algorithm -> algorithm.id == id)
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
