package com.javarush.zelenin.ui.console;

import com.javarush.zelenin.algorithm.cipher.Cipher;
import com.javarush.zelenin.controller.ControllerRegistry;
import com.javarush.zelenin.dto.ParamsDto;

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
        int mode = getMode(userInput);
        ParamsDto paramsDto = getParams(userInput);

        switch (mode) {
            case 1 -> controllers.cipherController().handleEncryption(paramsDto);
            case 2 -> controllers.cipherController().handleDecryption(paramsDto);
            case 3 -> controllers.analyzerController().handleBruteForce();
            case 4 -> controllers.analyzerController().handleStatAnalysis();
            case 0 -> System.out.println("Выход из приложения"); //TODO обработчик
        }
    }

    //TODO выделить в отдельный класс?
    private static Integer getMode(Scanner scanner) {
        System.out.print(Message.MODE_SELECTION_MENU);
        int mode;
        while (true) {
            try {
                mode = Integer.parseInt(scanner.nextLine());
                if (mode < 0 || mode > 4) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println(Message.NUMBER_FORMAT_EXCEPTION);
                System.out.print(Message.SELECT_MODE);
            }
        }
        return mode;
    }

    private static ParamsDto getParams(Scanner scanner) {
        System.out.println("Выбран режим шифрования (1).");
        System.out.print("Введите путь к файлу: ");
        String sourcePath = scanner.nextLine();

        System.out.print("Введите название нового файла (без расширения): ");
        String targetPath = scanner.nextLine();

        System.out.print("Введите секретный ключ: ");
        String key = scanner.nextLine();

        return new ParamsDto(sourcePath, targetPath, key, Cipher.Algorithm.CAESAR);
    }
}
