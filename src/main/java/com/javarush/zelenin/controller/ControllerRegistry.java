package com.javarush.zelenin.controller;

public record ControllerRegistry(EncryptController encryptController,
                                 DecryptController decryptController,
                                 BruteForceController bruteForceController,
                                 StatAnalysisController statAnalysisController) {
}
