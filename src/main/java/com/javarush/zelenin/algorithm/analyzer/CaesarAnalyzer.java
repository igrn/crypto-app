package com.javarush.zelenin.algorithm.analyzer;

import com.javarush.zelenin.algorithm.cipher.CaesarCipher;
import com.javarush.zelenin.algorithm.cipher.Cipher;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CaesarAnalyzer implements Analyzer<Integer> {
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"; //TODO объединить в одном классе констант
    private static final Set<String> ROOT_DICTIONARY = Set.of(
            "вопрос", "времeн", "говори", "город", "государ", "жизн", "здесь",
            "когда", "котор", "можно", "очень", "после", "работ", "сегодня",
            "сейчас", "случа", "сказа", "стран", "только", "хорош", "человек", "через"
    );

    @Override
    public Integer findKeyViaBruteForce(String text) {
        Cipher<Integer> cipher = new CaesarCipher();
        Map<Integer, Integer> matches = new HashMap<>();

        for (int key = 1; key <= ALPHABET.length(); key++) {
            String decrypted = cipher.decrypt(text, key).toLowerCase();
            int uniqueMatches = 0;
            for (String root : ROOT_DICTIONARY) {
                if (decrypted.contains(root)) uniqueMatches++;
            }
            matches.put(key, uniqueMatches);
        }
        return matches.entrySet().stream().max(Map.Entry.comparingByValue())
                .orElseThrow().getKey(); //TODO лучше обработать исключение
    }

    @Override
    public Integer findKeyViaAnalysis(String text) {
        return 0; //TODO анализ
    }
}
