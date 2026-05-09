package com.javarush.zelenin.controller;

import com.javarush.zelenin.scenario.Scenario;

import java.util.Scanner;

public class CipherController {
    private final Scenario encrypt;
    private final Scenario decrypt;

    public CipherController(Scenario encrypt, Scenario decrypt) {
        this.encrypt = encrypt;
        this.decrypt = decrypt;
    }

    //TODO убрать scanner с этого уровня
    public void handleEncryption(Scanner scanner) {
        System.out.println("Выбран режим шифрования (1).");
        System.out.print("Введите путь к файлу: ");

        encrypt.execute(scanner);
    }

    public void handleDecryption() {}
}
