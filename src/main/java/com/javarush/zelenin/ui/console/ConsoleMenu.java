package com.javarush.zelenin.ui.console;

import com.javarush.zelenin.algorithm.Algorithm;
import com.javarush.zelenin.dto.Params;
import com.javarush.zelenin.constant.Const;
import com.javarush.zelenin.dto.Result;

import java.util.Scanner;

public class ConsoleMenu {
    private final Scanner userInput;

    public ConsoleMenu(Scanner userInput) {
        this.userInput = userInput;
    }

    public Mode selectMode() {
        System.out.print(Message.MODE_SELECTION_MENU);
        int mode;
        while (true) {
            try {
                mode = Integer.parseInt(userInput.nextLine());
                if (mode < 0 || mode > 4) throw new IllegalArgumentException();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.INVALID_MODE);
                System.out.print(Message.SELECT_MODE);
            }
        }
        return Mode.values()[mode];
    }

    public Params createParams(Mode mode) {
        if (mode == Mode.EXIT) {
            return new Params("", "", Const.DEFAULT_KEY, Algorithm.CAESAR);
        }
        System.out.println(Message.SELECTED_MODE[mode.ordinal()]);
        String defaultFileName = Const.SOURCE_FILENAMES[mode.ordinal()];
        System.out.printf(Message.SOURCE_PATH, defaultFileName);
        String sourcePath = readWithDefault(Const.DEFAULT_PATH.formatted(defaultFileName));
        defaultFileName = Const.DESTINATION_FILENAMES[mode.ordinal()];
        System.out.printf(Message.DESTINATION_PATH, defaultFileName);
        String destinationPath = readWithDefault(Const.DEFAULT_PATH.formatted(defaultFileName));

        if (mode.ordinal() > 2) {
            return new Params(sourcePath, destinationPath, Const.DEFAULT_KEY, Algorithm.CAESAR);
        }
        System.out.printf(Message.ENTER_KEY, Const.DEFAULT_KEY);
        String key = readWithDefault(Const.DEFAULT_KEY);
        return new Params(sourcePath, destinationPath, key, Algorithm.CAESAR);
    }

    public void printResult(Result result) {
        switch (result.resultCode()) {
            case OK -> System.out.printf(Message.RESULT_OK, result.message());
            case ERROR -> System.out.printf(Message.RESULT_ERROR, result.message());
            case EXIT -> System.out.print(result.message());
        }
    }

    private String readWithDefault(String defaultInput) {
        String input = userInput.nextLine();
        return input.isBlank() ? defaultInput : input;
    }

    public enum Mode {
        EXIT, ENCRYPT, DECRYPT, BRUTEFORCE, ANALYZE
    }
}
