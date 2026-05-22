package com.javarush.zelenin;

import com.javarush.zelenin.controller.AnalyzerController;
import com.javarush.zelenin.controller.AppControllers;
import com.javarush.zelenin.controller.CipherController;
import com.javarush.zelenin.service.AnalyzerService;
import com.javarush.zelenin.service.CipherService;
import com.javarush.zelenin.ui.console.ConsoleMenu;
import com.javarush.zelenin.ui.console.ConsoleRunner;

import java.util.Scanner;

public class CryptoApp {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        ConsoleMenu consoleMenu = new ConsoleMenu(userInput);
        new ConsoleRunner(consoleMenu, setupControllers()).run();
    }

    private static AppControllers setupControllers() {
        CipherService cipherService = new CipherService();
        AnalyzerService analyzerService = new AnalyzerService();
        return new AppControllers(
                new CipherController(cipherService),
                new AnalyzerController(analyzerService, cipherService)
        );
    }
}
