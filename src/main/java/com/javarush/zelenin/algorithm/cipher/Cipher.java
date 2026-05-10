package com.javarush.zelenin.algorithm.cipher;

import java.util.function.Supplier;

/**
 * Интерфейс для различных алгортимов шифрования. Умеет шифровать и расшифровывать текстовые данные.
 * */
public interface Cipher<T> {

    T parseKey(String key);

    String encrypt(String text, T key);

    String decrypt(String text, T key);

    enum Algorithm {
        CAESAR(CaesarCipher::new);

        private final Supplier<Cipher<?>> cipherFactory;

        Algorithm(Supplier<Cipher<?>> cipherFactory) {
            this.cipherFactory = cipherFactory;
        }

        public Cipher<?> createCipher() {
            return cipherFactory.get();
        }
    }
}
