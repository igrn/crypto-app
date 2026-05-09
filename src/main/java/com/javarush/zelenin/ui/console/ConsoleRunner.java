package com.javarush.zelenin.ui.console;

import com.javarush.zelenin.controller.ControllerRegistry;

import java.util.Scanner;

//TODO вынести ввод/вывод в консоль на уровень интерфейса
public class ConsoleRunner {
    private final Scanner userInput;
    private final ControllerRegistry controllers;

    public ConsoleRunner(Scanner userInput, ControllerRegistry controllers) {
        this.userInput = userInput;
        this.controllers = controllers;
    }

    public void run() {
        switch (getMode()) {
            case 1 -> controllers.cipherController().handleEncryption(userInput); //TODO убрать userInput
            case 2 -> controllers.cipherController().handleDecryption();
            case 3 -> controllers.analyzerController().handleBruteForce();
            case 4 -> controllers.analyzerController().handleStatAnalysis();
            case 0 -> System.out.println("Выход из приложения"); //TODO обработчик
        }
    }

    private int getMode() {
        System.out.print(Message.MODE_SELECTION_MENU);
        int mode;
        while (true) {
            try {
                mode = Integer.parseInt(userInput.nextLine());
                if (mode < 0 || mode > 4) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println(Message.NUMBER_FORMAT_EXCEPTION);
                System.out.print(Message.SELECT_MODE);
            }
        }
        return mode;
    }
}
