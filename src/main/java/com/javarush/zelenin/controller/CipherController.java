package com.javarush.zelenin.controller;

import com.javarush.zelenin.dto.Params;
import com.javarush.zelenin.dto.Result;
import com.javarush.zelenin.dto.Result.Code;
import com.javarush.zelenin.exception.AppException;
import com.javarush.zelenin.service.CipherService;

/**
 * A controller for handling encryption tasks.
 */
public class CipherController {
    private final CipherService cipherService;

    public CipherController(CipherService cipherService) {
        this.cipherService = cipherService;
    }

    /**
     * Handles the process of encryption.
     * @param params a {@link Params} object with the required parameters
     * @return {@link Result} of the task execution (either {@code OK} or {@code ERROR})
     */
    public Result handleEncryption(Params params) {
        try {
            return cipherService.handleEncryption(params);
        } catch (AppException e) {
            return new Result(Code.ERROR, e.getMessage());
        }
    }

    /**
     * Handles the process of decryption.
     * @param params a {@link Params} object with the required parameters
     * @return {@link Result} of the task execution (either {@code OK} or {@code ERROR})
     */
    public Result handleDecryption(Params params) {
        try {
            return cipherService.handleDecryption(params);
        } catch (AppException e) {
            return new Result(Code.ERROR, e.getMessage());
        }
    }
}
