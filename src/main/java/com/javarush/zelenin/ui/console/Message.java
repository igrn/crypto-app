package com.javarush.zelenin.ui.console;

public interface Message {
    String WELCOME = "Welcome to CryptoApp!\n";
    String CURRENT_VERSION = "Current version: v1.0-SNAPSHOT\n";
    String SELECT_MODE = "Select mode: ";
    String LINE = "-".repeat(25) + "\n";

    String MODE_SELECTION_MENU = WELCOME + CURRENT_VERSION + LINE + """
            Available modes:
            1. Encryption
            2. Decryption
            3. Brute-force
            4. Analysis
            0. Exit
            """ + LINE + SELECT_MODE;

    String[] SELECTED_MODE = {
            "Exiting app.",
            "\nSelected encryption mode.",
            "\nSelected decryption mode.",
            "\nSelected brute-force mode.",
            "\nSelected analysis mode."
    };

    String PATH_OPTIONS = """
               - Full path (with file name)
               - File name only (default path = ./text)
               - Skip with ENTER (default file = ./text/%s.txt)
            """;
    String ENTER_PATH = "Enter path: ";
    String SOURCE_PATH = LINE + "1. Enter source path:\n" + PATH_OPTIONS + LINE + ENTER_PATH;
    String DESTINATION_PATH = LINE + "2. Enter destination path:\n" + PATH_OPTIONS + LINE + ENTER_PATH;
    String ENTER_KEY = LINE + "3. Enter key (default = 1): ";

    String INCORRECT_MODE_SELECTION = "A whole number from 0 to 4 is required.";

}
