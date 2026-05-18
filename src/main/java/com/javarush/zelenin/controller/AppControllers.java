package com.javarush.zelenin.controller;

public record AppControllers(CipherController cipherController,
                             AnalyzerController analyzerController,
                             ExitController exitController) {}
