package com.javarush.zelenin.algorithm.cipher;

public interface Cipher<T> {

    T parseKey(String key);

    String encrypt(String text, T key);

    String decrypt(String text, T key);
}
