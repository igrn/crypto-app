package com.javarush.zelenin.controller;

public record ControllerRegistry(CipherController cipherController,
                                 AnalyzerController analyzerController) {
}
