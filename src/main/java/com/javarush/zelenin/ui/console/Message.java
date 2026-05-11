package com.javarush.zelenin.ui.console;

public interface Message {
    String WELCOME = "Добро пожаловать в CryptoApp!\n";
    String CURRENT_VERSION = "Текущая версия: v1.0-SNAPSHOT\n";
    String SELECT_MODE = "Выберите режим работы: ";
    String LINE = "-".repeat(25) + "\n";

    String MODE_SELECTION_MENU = WELCOME + CURRENT_VERSION + LINE + """
            Доступны следующие режимы работы:
            1. Шифрование
            2. Расшифровка с ключом
            3. Brute force
            4. Статистический анализ
            0. Выход
            """ + LINE + SELECT_MODE;

    String INCORRECT_MODE_SELECTION = "Необходимо ввести целое число от 0 до 4.";

    String[] SELECTED_MODE = {
            "Выход из приложения.",
            "\nВыбран режим шифрования.",
            "\nВыбран режим расшифровки.",
            "\nВыбран режим brute force.",
            "\nВыбран режим статистического анализа."
    };

    String SOURCE_PATH = LINE + """
            1. Введите путь к оригинальному файлу:
               - Путь целиком (с названием файла)
               - Название файла (поиск в папке ./text)
               - Пропуск через ENTER (файл по-умолчанию ./text/%s)
            """ + LINE + "Введите путь: ";

    String DESTINATION_PATH = LINE + """
            2. Введите путь к создаваемому файлу:
               - Путь целиком (с названием файла)
               - Название файла (будет создан в папке ./text)
               - Пропуск через ENTER (файл по-умолчанию ./text/%s)
            """ + LINE + "Введите путь: ";

    String[][] DEFAULTS = {
            { "SOURCE", "text.txt", "encrypted.txt", "encrypted.txt", "encrypted.txt" },
            { "DESTINATION", "encrypted.txt", "decrypted.txt", "bruteforce.txt", "analyzed.txt" }
    };

    String SECRET_KEY = LINE + "3. Введите секретный ключ (ENTER = 1): ";

}
