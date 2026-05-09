package com.javarush.zelenin;

import com.javarush.zelenin.controller.*;
import com.javarush.zelenin.scenario.*;
import com.javarush.zelenin.ui.console.ConsoleRunner;

import java.util.Scanner;

public class CipherApp {

    public static void main(String[] args) {
        ControllerRegistry controllers = new ControllerRegistry(
                new EncryptController(new Encrypt()),
                new DecryptController(new Decrypt()),
                new BruteForceController(new BruteForce()),
                new StatAnalysisController(new Analyze())
        );

        Scanner userInput = new Scanner(System.in);
        new ConsoleRunner(userInput, controllers).run();
    }
}
