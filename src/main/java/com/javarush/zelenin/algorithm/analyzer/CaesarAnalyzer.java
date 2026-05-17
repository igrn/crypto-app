package com.javarush.zelenin.algorithm.analyzer;

import com.javarush.zelenin.algorithm.cipher.CaesarCipher;
import com.javarush.zelenin.util.Const;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CaesarAnalyzer implements Analyzer<Integer> {
    private final CaesarCipher cipher;

    public CaesarAnalyzer(CaesarCipher cipher) {
        this.cipher = cipher;
    }

    @Override
    public Integer findKeyViaBruteForce(String text) {
        Map<Integer, Long> matchCounts = new HashMap<>();

        for (int key = 1; key < Const.ALPHABET.length(); key++) {
            String decrypted = cipher.decrypt(text, key).toLowerCase();
            long matches = Const.ROOT_DICTIONARY.stream().filter(decrypted::contains).count();
            matchCounts.put(key, matches);
        }
        return matchCounts.entrySet().stream().max(Map.Entry.comparingByValue())
                .orElse(Map.entry(0,0L)).getKey();
    }

    @Override
    public Integer findKeyViaAnalysis(String text) {
        int bestKey = 0;
        double minScore = Double.MAX_VALUE;

        for (int key = 1; key < Const.ALPHABET.length(); key++) {
            String decrypted = cipher.decrypt(text, key);
            double score = compareWithReference(calculateFrequencies(decrypted));

            if (score < minScore) {
                minScore = score;
                bestKey = key;
            }
        }
        return bestKey;
    }

    private static Map<Character, Double> calculateFrequencies(String text) {
        Map<Character, Long> letterCounts = text.chars()
                .map(Character::toLowerCase)
                .filter(symbol -> Const.ALPHABET.indexOf(symbol) != -1)
                .mapToObj(letter -> (char) letter)
                .collect(Collectors.groupingBy(letter -> letter, Collectors.counting()));

        long totalLetters = letterCounts.values().stream().mapToLong(Long::longValue).sum();
        return Const.ALPHABET.chars()
                .mapToObj(letter -> (char) letter)
                .collect(Collectors.toMap(
                        letter -> letter,
                        letter -> {
                            long count = letterCounts.getOrDefault(letter, 0L);
                            return totalLetters > 0 ? (double) count / totalLetters : 0.0;
                        }
                ));
    }

    private static Double compareWithReference(Map<Character, Double> comparedFrequencies) {
        double score = 0.0;
        for (int i = 0; i < Const.ALPHABET.length(); i++) {
            char letter = Const.ALPHABET.charAt(i);
            double reference = Const.REFERENCE_FREQUENCIES.getOrDefault(letter, 0.0);
            double compared = comparedFrequencies.getOrDefault(letter, 0.0);
            score += Math.pow(reference - compared, 2);
        }
        return score;
    }
}
