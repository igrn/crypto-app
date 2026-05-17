package com.javarush.zelenin.ui.console;

import com.javarush.zelenin.controller.AppControllers;
import com.javarush.zelenin.dto.Params;

public class ConsoleRunner {
    private final ConsoleMenu consoleMenu;
    private final AppControllers controllers;

    public ConsoleRunner(ConsoleMenu consoleMenu, AppControllers controllers) {
        this.consoleMenu = consoleMenu;
        this.controllers = controllers;
    }

    public void run() {
        int mode = consoleMenu.getMode();
        Params params = consoleMenu.getParams(mode);

        switch (mode) {
            case 1 -> controllers.cipherController().handleEncryption(params);
            case 2 -> controllers.cipherController().handleDecryption(params);
            case 3 -> controllers.analyzerController().handleBruteForce(params);
            case 4 -> controllers.analyzerController().handleAnalysis(params);
        }
    }
}
