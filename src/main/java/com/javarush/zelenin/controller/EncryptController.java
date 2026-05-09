package com.javarush.zelenin.controller;

import com.javarush.zelenin.scenario.Encrypt;

import java.util.Scanner;

public class EncryptController {
    private final Encrypt encrypt;

    public EncryptController(Encrypt encrypt) {
        this.encrypt = encrypt;
    }

    //TODO убрать scanner с этого уровня
    public void handleEncryption(Scanner scanner) {
        System.out.println("Выбран режим шифрования (1).");
        System.out.print("Введите путь к файлу: ");

        encrypt.execute(scanner);
    }
}
