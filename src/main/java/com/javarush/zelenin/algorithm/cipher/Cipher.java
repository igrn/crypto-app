package com.javarush.zelenin.algorithm.cipher;

/**
 * An object that encrypts or decrypts text data.
 * @param <T> key type ({@link Integer}, {@link String}, etc.)
 */
public interface Cipher<T> {

    /**
     * Converts a {@link String} key into a specific {@link Cipher}'s type.
     * @param key an encryption key represented as a {@link String}
     * @return a converted key that has the same type as its {@link Cipher}
     */
    T parseKey(String key);

    /**
     * Encrypts text data using a key.
     * @param text a {@link String} containing the text to be encrypted
     * @param key an encryption key of the appropriate type
     * @return a {@link String} containing the encrypted text
     */
    String encrypt(String text, T key);

    /**
     * Decrypts text data using a key.
     * @param text a {@link String} containing the text to be decrypted
     * @param key an encryption key of the appropriate type
     * @return a {@link String} containing the decrypted text
     */
    String decrypt(String text, T key);
}
