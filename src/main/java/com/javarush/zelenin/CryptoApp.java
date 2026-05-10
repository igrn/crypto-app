package com.javarush.zelenin;

import com.javarush.zelenin.controller.*;
import com.javarush.zelenin.service.CipherService;
import com.javarush.zelenin.ui.console.ConsoleRunner;

import java.util.Scanner;

public class CryptoApp {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        ControllerRegistry controllers = new ControllerRegistry(
                new CipherController(new CipherService()),
                new AnalyzerController()
        );
        new ConsoleRunner(userInput, controllers).run();
    }
}
