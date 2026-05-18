package com.javarush.zelenin.controller;

import com.javarush.zelenin.dto.Params;
import com.javarush.zelenin.dto.Result;
import com.javarush.zelenin.dto.Result.Code;
import com.javarush.zelenin.exception.AppException;
import com.javarush.zelenin.service.CipherService;

public class CipherController {
    private final CipherService cipherService;

    public CipherController(CipherService cipherService) {
        this.cipherService = cipherService;
    }

    public Result handleEncryption(Params params) {
        try {
            return cipherService.handleEncryption(params);
        } catch (AppException e) {
            return new Result(Code.ERROR, e.getMessage());
        }
    }

    public Result handleDecryption(Params params) {
        try {
            return cipherService.handleDecryption(params);
        } catch (AppException e) {
            return new Result(Code.ERROR, e.getMessage());
        }
    }
}
