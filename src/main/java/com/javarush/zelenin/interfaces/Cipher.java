package com.javarush.zelenin.interfaces;

public interface Cipher<T> {

    String encrypt(String text, T key);

    String decrypt(String text, T key);
}
