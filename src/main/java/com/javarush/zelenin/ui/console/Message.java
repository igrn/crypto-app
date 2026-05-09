package com.javarush.zelenin.ui.console;

public interface Message {
    String SELECT_MODE = "Выберите режим работы: ";

    String MODE_SELECTION_MENU = "Доступны следующие режимы работы:\n" + """
        1. Шифрование
        2. Расшифровка с ключом
        3. Brute force
        4. Статистический анализ
        0. Выход
        """ + SELECT_MODE;

    String NUMBER_FORMAT_EXCEPTION = "[WARN] Необходимо ввести целое число от 0 до 4.";

}
