package com.javarush.zelenin.ui.console;

import com.javarush.zelenin.algorithm.Algorithm;
import com.javarush.zelenin.constant.Const;
import com.javarush.zelenin.dto.Params;
import com.javarush.zelenin.dto.Result;

import java.util.Scanner;

/**
 * An object responsible for communicating with the user via the console.
 */
public class ConsoleMenu {
    private final Scanner userInput;

    public ConsoleMenu(Scanner userInput) {
        this.userInput = userInput;
    }

    Mode selectMode() {
        System.out.print(Message.MODE_SELECTION_MENU);
        while (true) {
            try {
                return Mode.fromId(Integer.parseInt(userInput.nextLine()));
            } catch (IllegalArgumentException e) {
                System.out.print(Message.INVALID_MODE);
            }
        }
    }

    Params buildParams(Mode mode) {
        if (mode == Mode.EXIT) return Params.EMPTY;

        System.out.printf(Message.SELECTED_MODE, mode.getName());
        String sourcePath = getParam(Message.SOURCE_PATH, mode.getSource());
        String destinationPath = getParam(Message.DESTINATION_PATH, mode.getDestination());

        if (mode == Mode.BRUTEFORCE || mode == Mode.ANALYZE) {
            return new Params(sourcePath, destinationPath, Const.DEFAULT_KEY, Algorithm.CAESAR);
        }
        String key = getParam(Message.ENTER_KEY, Const.DEFAULT_KEY);
        return new Params(sourcePath, destinationPath, key, Algorithm.CAESAR);
    }

    void printResult(Result result) {
        switch (result.resultCode()) {
            case OK -> System.out.printf(Message.RESULT_OK, result.message());
            case ERROR -> System.out.printf(Message.RESULT_ERROR, result.message());
            case EXIT -> System.out.print(result.message());
        }
    }

    private String getParam(String message, String defaultValue) {
        System.out.printf(message, defaultValue);
        String input = userInput.nextLine();
        return input.isBlank() ? defaultValue : input;
    }
}
