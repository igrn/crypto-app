package com.javarush.zelenin;

import com.javarush.zelenin.controller.*;
import com.javarush.zelenin.scenario.*;
import com.javarush.zelenin.ui.console.ConsoleRunner;

import java.util.Scanner;

public class CryptoApp {

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        ControllerRegistry controllers = new ControllerRegistry(
                new CipherController(new Encrypt(), new Decrypt()),
                new AnalyzerController(new BruteForce(), new Analyze())
        );
        new ConsoleRunner(userInput, controllers).run();
    }
}
