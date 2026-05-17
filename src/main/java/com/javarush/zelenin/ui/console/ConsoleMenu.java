package com.javarush.zelenin.ui.console;

import com.javarush.zelenin.algorithm.Algorithm;
import com.javarush.zelenin.dto.Params;
import com.javarush.zelenin.util.Const;

import java.util.Scanner;

public class ConsoleMenu {
    private final Scanner userInput;

    public ConsoleMenu(Scanner userInput) {
        this.userInput = userInput;
    }

    public Integer getMode() {
        System.out.print(Message.MODE_SELECTION_MENU);
        int mode;
        while (true) {
            try {
                mode = Integer.parseInt(userInput.nextLine());
                if (mode < 0 || mode > 4) throw new IllegalArgumentException();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(Message.INCORRECT_MODE_SELECTION);
                System.out.print(Message.SELECT_MODE);
            }
        }
        System.out.println(Message.SELECTED_MODE[mode]);
        return mode;
    }

    public Params getParams(Integer mode) {
        if (mode == 0) return new Params("", "", "", Algorithm.CAESAR);

        String defaultFileName = Const.SOURCE_FILENAMES[mode];
        System.out.printf(Message.SOURCE_PATH, defaultFileName);
        String sourcePath = readWithDefault(Const.DEFAULT_PATH.formatted(defaultFileName));
        defaultFileName = Const.DESTINATION_FILENAMES[mode];
        System.out.printf(Message.DESTINATION_PATH, defaultFileName);
        String destinationPath = readWithDefault(Const.DEFAULT_PATH.formatted(defaultFileName));

        if (mode > 2) {
            return new Params(sourcePath, destinationPath, "", Algorithm.CAESAR);
        }
        System.out.print(Message.ENTER_KEY);
        String key = readWithDefault(Const.DEFAULT_KEY);
        return new Params(sourcePath, destinationPath, key, Algorithm.CAESAR);
    }

    private String readWithDefault(String defaultInput) {
        String input = userInput.nextLine();
        return input.isBlank() ? defaultInput : input;
    }
}
