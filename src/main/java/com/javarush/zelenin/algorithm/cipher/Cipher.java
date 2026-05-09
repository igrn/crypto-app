package com.javarush.zelenin.algorithm.cipher;

/**
 * Интерфейс для различных алгортимов шифрования. Умеет шифровать и расшифровывать текстовые данные.
 * */
public interface Cipher<T> {

    String encrypt(String text, T key);

    String decrypt(String text, T key);
}
