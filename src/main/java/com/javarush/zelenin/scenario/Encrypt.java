package com.javarush.zelenin.scenario;

import com.javarush.zelenin.algorithm.cipher.CaesarCipher;
import com.javarush.zelenin.util.io.FileManager;

import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Encrypt implements Scenario {

    @Override
    public void execute(Scanner scanner) {


        FileManager fileManager = new FileManager();
        String filePath = scanner.nextLine();

        //TODO добавить обработку дефолтного файла (text/text.txt)
        try (Stream<String> lines = fileManager.readFile(filePath)) {
            CaesarCipher cipher = new CaesarCipher();

            System.out.print("Введите секретный ключ: ");
            int key = Integer.parseInt(scanner.nextLine());
            Stream<String> encryptedLines = lines.map(line -> cipher.encrypt(line, key));

            System.out.print("Введите название нового файла (без расширения): ");
            String encryptedFilePath = FileManager.constructOutputPath(filePath, scanner.nextLine()).toString();
            System.out.println("Зашифрованный файл будет создан по следующему пути: " + encryptedFilePath);

            //TODO файл уже существует
            fileManager.writeFile(encryptedLines, encryptedFilePath);


        } catch (IOException e) {
            throw new RuntimeException(e); //TODO прописать исключения
        } catch (NumberFormatException e) {
            System.out.println("[WARN] Необходимо ввести целое число ."); //TODO в каких пределах?
            System.out.print("Введите секретный ключ: ");
        } catch (IllegalArgumentException e) {
            System.out.println("Введите название нового файла (без расширения): ");
        }
    }
}
