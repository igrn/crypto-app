package com.javarush.zelenin.algorithm.analyzer;

import com.javarush.zelenin.algorithm.cipher.CaesarCipher;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public class CaesarAnalyzer implements Analyzer<Integer> {
    private final CaesarCipher cipher;

    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"; //TODO объединить в одном классе констант
    private static final Set<String> ROOT_DICTIONARY = Set.of(
            "вопрос", "времeн", "говори", "город", "государ", "жизн", "здесь",
            "когда", "котор", "можно", "очень", "после", "работ", "сегодня",
            "сейчас", "случа", "сказа", "стран", "только", "хорош", "человек", "через"
    );

    //These frequencies were calculated beforehand using the "dict.txt" file as a sample
    private static final Map<Character, Double> SAMPLE_FREQUENCIES = Map.ofEntries(
            entry('а', 0.0816), entry('б', 0.0178), entry('в', 0.0453), entry('г', 0.0196),
            entry('д', 0.0307), entry('е', 0.0827), entry('ж', 0.0104), entry('з', 0.0169),
            entry('и', 0.0664), entry('й', 0.0116), entry('к', 0.0343), entry('л', 0.0509),
            entry('м', 0.0297), entry('н', 0.0654), entry('о', 0.1148), entry('п', 0.0260),
            entry('р', 0.0447), entry('с', 0.0529), entry('т', 0.0584), entry('у', 0.0278),
            entry('ф', 0.0020), entry('х', 0.0086), entry('ц', 0.0036), entry('ч', 0.0146),
            entry('ш', 0.0093), entry('щ', 0.0029), entry('ъ', 0.0005), entry('ы', 0.0194),
            entry('ь', 0.0197), entry('э', 0.0032), entry('ю', 0.0062), entry('я', 0.0220)
    );

    public CaesarAnalyzer(CaesarCipher cipher) {
        this.cipher = cipher;
    }

    @Override
    public Integer findKeyViaBruteForce(String text) {
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
        int bestKey = 0;
        double minScore = Double.MAX_VALUE;

        for (int key = 1; key <= ALPHABET.length(); key++) {
            String decryptedAttempt = cipher.decrypt(text, key);
            Map<Character, Double> attemptFreqs = calculateFrequencies(decryptedAttempt);
            double score = compareFrequencies(attemptFreqs);

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
                .filter(symbol -> ALPHABET.indexOf(symbol) != -1)
                .mapToObj(letter -> (char) letter)
                .collect(Collectors.groupingBy(letter -> letter, Collectors.counting()));

        long totalLetters = letterCounts.values().stream().mapToLong(Long::longValue).sum();
        return ALPHABET.chars()
                .mapToObj(letter -> (char) letter)
                .collect(Collectors.toMap(
                        letter -> letter,
                        letter -> {
                            long count = letterCounts.getOrDefault(letter, 0L);
                            return totalLetters > 0 ? (double) count / totalLetters : 0.0;
                        }
                ));
    }

    private static Double compareFrequencies(Map<Character, Double> encrypted) {
        double score = 0.0;
        for (int i = 0; i < ALPHABET.length(); i++) {
            char letter = ALPHABET.charAt(i);
            double sampleFreq = SAMPLE_FREQUENCIES.getOrDefault(letter, 0.0);
            double encryptedFreq = encrypted.getOrDefault(letter, 0.0);
            double diff = sampleFreq - encryptedFreq;
            score += diff * diff;
        }
        return score;
    }
}
