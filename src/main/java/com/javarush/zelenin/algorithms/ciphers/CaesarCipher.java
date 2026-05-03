package com.javarush.zelenin.algorithms.ciphers;

import com.javarush.zelenin.interfaces.Cipher;

public class CaesarCipher implements Cipher<Integer> {
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    @Override
    public String encrypt(String text, Integer key) {
        return process(text, key);
    }

    @Override
    public String decrypt(String text, Integer key) {
        return process(text, -key);
    }

    //В случае с шифром Цезаря, шифрование и расшифровка идут по одному алгоритму
    private static String process(String text, Integer key) {
        StringBuilder result = new StringBuilder();

        for (char symbol : text.toCharArray()) {
            int pos = ALPHABET.indexOf(Character.toLowerCase(symbol));

            if (pos != -1) {
                int newPos = Math.floorMod(pos + key, ALPHABET.length());
                char newSymbol = Character.isLowerCase(symbol) ? ALPHABET.charAt(newPos)
                        : Character.toUpperCase(ALPHABET.charAt(newPos));
                result.append(newSymbol);
            } else {
                result.append(symbol); //я решил пропускать спецсимволы, пробелы и символы из других языков
            }
        }
        return result.toString();
    }
}
