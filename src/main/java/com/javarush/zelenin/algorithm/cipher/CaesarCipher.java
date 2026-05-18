package com.javarush.zelenin.algorithm.cipher;

import com.javarush.zelenin.constant.Const;

public class CaesarCipher implements Cipher<Integer> {

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
                    int pos = Const.ALPHABET.indexOf(Character.toLowerCase(symbol));
                    if (pos == -1) return symbol;

                    int newPos = Math.floorMod(pos + key, Const.ALPHABET.length());
                    char newLetter = Const.ALPHABET.charAt(newPos);
                    return Character.isLowerCase(symbol) ? newLetter : Character.toUpperCase(newLetter);
                })
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
