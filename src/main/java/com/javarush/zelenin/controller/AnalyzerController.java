package com.javarush.zelenin.controller;

import com.javarush.zelenin.dto.Params;
import com.javarush.zelenin.service.AnalyzerService;
import com.javarush.zelenin.service.CipherService;

public class AnalyzerController {
    private final AnalyzerService analyzerService;
    private final CipherService cipherService;

    public AnalyzerController(AnalyzerService analyzerService,
                              CipherService cipherService) {
        this.analyzerService = analyzerService;
        this.cipherService = cipherService;
    }

    public void handleBruteForce(Params params) {
        Params paramsWithKey = analyzerService.handleBruteForce(params);
        cipherService.handleDecryption(paramsWithKey);
    }

    public void handleStatAnalysis() {}
}
