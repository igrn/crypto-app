package com.javarush.zelenin.ui.console;

import com.javarush.zelenin.controller.AppControllers;
import com.javarush.zelenin.dto.Params;
import com.javarush.zelenin.dto.Result;
import com.javarush.zelenin.dto.Result.Code;

public class ConsoleRunner {
    private final ConsoleMenu consoleMenu;
    private final AppControllers controllers;

    public ConsoleRunner(ConsoleMenu consoleMenu, AppControllers controllers) {
        this.consoleMenu = consoleMenu;
        this.controllers = controllers;
    }

    public void run() {
        Mode mode = consoleMenu.selectMode();
        Params params = consoleMenu.buildParams(mode);

        Result result = switch (mode) {
            case ENCRYPT -> controllers.cipherController().handleEncryption(params);
            case DECRYPT -> controllers.cipherController().handleDecryption(params);
            case BRUTEFORCE -> controllers.analyzerController().handleBruteForce(params);
            case ANALYZE -> controllers.analyzerController().handleAnalysis(params);
            case EXIT -> new Result(Code.EXIT, Message.RESULT_EXIT);
        };
        consoleMenu.printResult(result);
    }
}
