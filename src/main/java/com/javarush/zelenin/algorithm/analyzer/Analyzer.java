package com.javarush.zelenin.algorithm.analyzer;

/**
 * An object that analyzes encrypted text in order to find the best possible key to decrypt it.
 * @param <T> key type ({@link Integer}, {@link String}, etc.)
 */
public interface Analyzer<T> {

    /**
     * Finds the key for encrypted text via brute-force.
     * @param text a {@link String} containing the encrypted data
     * @return the encryption key of the appropriate type
     */
    T findKeyViaBruteForce(String text);

    /**
     * Finds the key for encrypted text via frequency analysis.
     * @param text a {@link String} containing the encrypted data
     * @return the encryption key of the appropriate type
     */
    T findKeyViaAnalysis(String text);
}
