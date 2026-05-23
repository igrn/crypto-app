package com.javarush.zelenin.controller;

import com.javarush.zelenin.dto.Params;
import com.javarush.zelenin.dto.Result;
import com.javarush.zelenin.dto.Result.Code;
import com.javarush.zelenin.exception.AppException;
import com.javarush.zelenin.service.AnalyzerService;
import com.javarush.zelenin.service.CipherService;

/**
 * A controller for handling analysis tasks.
 */
public class AnalyzerController {
    private final AnalyzerService analyzerService;
    private final CipherService cipherService;

    public AnalyzerController(AnalyzerService analyzerService,
                              CipherService cipherService) {
        this.analyzerService = analyzerService;
        this.cipherService = cipherService;
    }

    /**
     * Handles the process of brute-force.
     * @param params a {@link Params} object with the required parameters
     * @return {@link Result} of the task execution (either {@code OK} or {@code ERROR})
     */
    public Result handleBruteForce(Params params) {
        try {
            Params paramsWithKey = analyzerService.handleBruteForce(params);
            return cipherService.handleDecryption(paramsWithKey);
        } catch (AppException e) {
            return new Result(Code.ERROR, e.getMessage());
        }
    }

    /**
     * Handles the process of frequency analysis.
     * @param params a {@link Params} object with the required parameters
     * @return {@link Result} of the task execution (either {@code OK} or {@code ERROR})
     */
    public Result handleAnalysis(Params params) {
        try {
            Params paramsWithKey = analyzerService.handleAnalysis(params);
            return cipherService.handleDecryption(paramsWithKey);
        } catch (AppException e) {
            return new Result(Code.ERROR, e.getMessage());
        }
    }
}
