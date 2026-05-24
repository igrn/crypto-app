package com.javarush.zelenin.ui.console;

import java.io.File;

interface Message {
    String WELCOME = "Welcome to CryptoApp!\n";
    String CURRENT_VERSION = "Current version: v1.0-SNAPSHOT\n";
    String LINE = "-".repeat(25) + "\n";

    String SELECT_MODE = "Select mode: ";
    String MODE_OPTIONS = WELCOME + CURRENT_VERSION + LINE + """
            Available modes:
            1. Encryption
            2. Decryption
            3. Brute-force
            4. Analysis
            0. Exit
            """ + LINE + SELECT_MODE;
    String SELECTED_MODE = "\nSelected %s mode.\n";

    String SELECT_ALGORITHM = "Select algorithm: ";
    String ALGORITHM_OPTIONS = LINE + """
            1. Select encryption algorithm:
               1) Caesar
            """ + LINE + SELECT_ALGORITHM;
    String PATH_OPTIONS = """
               - Full path (with file name)
               - File name only (default path = .%stext)
               - Skip with ENTER (default file = .%s%%s)
            """.formatted(File.separator, File.separator);
    String ENTER_PATH = "Enter path: ";
    String SOURCE_PATH = LINE + "2. Enter source path:\n" + PATH_OPTIONS + LINE + ENTER_PATH;
    String DESTINATION_PATH = LINE + "3. Enter destination path:\n" + PATH_OPTIONS + LINE + ENTER_PATH;
    String ENTER_KEY = LINE + "4. Enter key (default = %s): ";

    String RESULT_OK = "\nTask successful.\nOutput file: %s";
    String RESULT_ERROR = "\nTask failed.\n%s";
    String RESULT_EXIT = "Application closed.";

    String INVALID_SELECTION = "Invalid selection.\n";
    String INVALID_MODE = INVALID_SELECTION + SELECT_MODE;
    String INVALID_ALGORITHM = INVALID_SELECTION + SELECT_ALGORITHM;

}
