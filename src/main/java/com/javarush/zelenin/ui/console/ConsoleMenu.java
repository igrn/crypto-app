package com.javarush.zelenin.ui.console;

import com.javarush.zelenin.algorithm.Algorithm;
import com.javarush.zelenin.dto.Params;

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
        if (mode == 0) return null; //TODO убрать null

        System.out.printf(Message.SOURCE_PATH, Message.DEFAULTS[0][mode]);
        String sourcePath = readWithDefault("text/" + Message.DEFAULTS[0][mode]);
        System.out.printf(Message.DESTINATION_PATH, Message.DEFAULTS[1][mode]);
        String destinationPath = readWithDefault("text/" + Message.DEFAULTS[1][mode]);

        if (mode > 2) {
            return new Params(sourcePath, destinationPath, "", Algorithm.CAESAR);
        }
        System.out.print(Message.SECRET_KEY);
        String key = readWithDefault("1");
        return new Params(sourcePath, destinationPath, key, Algorithm.CAESAR);
    }

    private String readWithDefault(String defaultInput) {
        String input = userInput.nextLine();
        return input.isBlank() ? defaultInput : input;
    }
}
