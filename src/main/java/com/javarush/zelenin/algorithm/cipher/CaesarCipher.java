package com.javarush.zelenin.algorithm.cipher;

public class CaesarCipher implements Cipher<Integer> {
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    @Override
    public Integer parseKey(String key) {
        return Integer.parseInt(key);
    }

    @Override
    public String encrypt(String text, Integer key) {
        return transform(text, key);
    }

    @Override
    public String decrypt(String text, Integer key) {
        return transform(text, -key);
    }

    private static String transform(String text, Integer key) {
        return text.chars()
                .map(symbol -> {
                    int pos = ALPHABET.indexOf(Character.toLowerCase(symbol));
                    if (pos == -1) return symbol;

                    int newPos = Math.floorMod(pos + key, ALPHABET.length());
                    char newLetter = ALPHABET.charAt(newPos);
                    return Character.isLowerCase(symbol) ? newLetter : Character.toUpperCase(newLetter);
                })
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
