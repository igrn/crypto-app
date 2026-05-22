package com.javarush.zelenin.ui.console;

import com.javarush.zelenin.constant.Const;

import java.io.File;
import java.util.Arrays;

enum Mode {
    ENCRYPT(1, "encryption", "text", "encrypted"),
    DECRYPT(2, "decryption", "encrypted", "decrypted"),
    BRUTEFORCE(3, "brute-force", "encrypted", "bruteforce"),
    ANALYZE(4, "analysis", "encrypted", "analyzed"),
    EXIT(0, "", "", "");

    private final int id;
    private final String name;
    private final String source;
    private final String destination;

    Mode(Integer id, String name, String source, String destination) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.destination = destination;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getSource() {
        return Const.DEFAULT_PATH.formatted(File.separator, source);
    }

    String getDestination() {
        return Const.DEFAULT_PATH.formatted(File.separator, destination);
    }

    static Mode fromId(Integer id) {
        return Arrays.stream(values()).filter(mode -> mode.id == id)
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
