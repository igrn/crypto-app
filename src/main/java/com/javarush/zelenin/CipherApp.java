package com.javarush.zelenin;

import java.util.Scanner;

public class CipherApp {

    public static void main(String[] args) {
        //Меню приложения
        System.out.println("Cipher App v1.0-SNAPSHOT");
        System.out.println("Доступны следующие режимы работы:");
        System.out.println("1. Шифрование");
        System.out.println("2. Расшифровка с ключом");
        System.out.println("3. Brute force");
        System.out.println("4. Статистический анализ");
        System.out.println("0. Выход");
        System.out.print("Выберите режим работы: ");

        //Выбор и валидация режима работы
        int code;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                code = Integer.parseInt(scanner.nextLine());
                if (code < 0 || code > 4) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("[WARN] Необходимо ввести целое число от 0 до 4.");
                System.out.print("Выберите режим работы: ");
            }
        }

        //Работа по выбранному сценарию
        switch (code) {
            //1. Режим шифрования
            case 1:
                System.out.println("Выбран режим шифрования.");
                break;
            //2. Режим расшифровки
            case 2:
                System.out.println("Выбран режим расшифровки.");
                break;
            //3. Режим brute force
            case 3:
                System.out.println("Выбран режим brute force.");
                break;
            //4. Режим статистического анализа
            case 4:
                System.out.println("Выбран режим статистического анализа.");
                break;
            //0. Выход из приложения
            default:
                System.out.println("Выход из приложения.");
        }
    }
}
