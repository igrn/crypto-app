package com.javarush.zelenin.constant;

import java.io.File;
import java.util.Map;
import java.util.Set;

import static java.util.Map.entry;

public interface Const {
    String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    Set<String> ROOT_DICTIONARY = Set.of(
            "вопрос", "времeн", "говори", "город", "государ", "жизн", "здесь",
            "когда", "котор", "можно", "очень", "после", "работ", "сегодня",
            "сейчас", "случа", "сказа", "стран", "только", "хорош", "человек", "через"
    );

    //These frequencies were calculated beforehand using the "dict.txt" file as a sample
    Map<Character, Double> REFERENCE_FREQUENCIES = Map.ofEntries(
            entry('а', 0.0816), entry('б', 0.0178), entry('в', 0.0453), entry('г', 0.0196),
            entry('д', 0.0307), entry('е', 0.0827), entry('ж', 0.0104), entry('з', 0.0169),
            entry('и', 0.0664), entry('й', 0.0116), entry('к', 0.0343), entry('л', 0.0509),
            entry('м', 0.0297), entry('н', 0.0654), entry('о', 0.1148), entry('п', 0.0260),
            entry('р', 0.0447), entry('с', 0.0529), entry('т', 0.0584), entry('у', 0.0278),
            entry('ф', 0.0020), entry('х', 0.0086), entry('ц', 0.0036), entry('ч', 0.0146),
            entry('ш', 0.0093), entry('щ', 0.0029), entry('ъ', 0.0005), entry('ы', 0.0194),
            entry('ь', 0.0197), entry('э', 0.0032), entry('ю', 0.0062), entry('я', 0.0220)
    );
    int SAMPLE_SIZE = 20; //Number of lines of text to be analyzed by an analyzer

    String DEFAULT_KEY = "1";
    String DEFAULT_PATH = "text" + File.separator + "%s.txt";
    String[] SOURCE_FILENAMES = { "", "text", "encrypted", "encrypted", "encrypted" };
    String[] DESTINATION_FILENAMES = { "", "encrypted", "decrypted", "bruteforce", "analyzed" };
    String INCORRECT_FILE = "Incorrect file: ";
    String UNSUPPORTED_ORERATION = "Unsupported operation";

}
