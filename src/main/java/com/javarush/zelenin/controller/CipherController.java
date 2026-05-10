package com.javarush.zelenin.controller;

import com.javarush.zelenin.dto.ParamsDto;
import com.javarush.zelenin.service.CipherService;

public class CipherController {
    private final CipherService cipherService;

    public CipherController(CipherService cipherService) {
        this.cipherService = cipherService;
    }

    public void handleEncryption(ParamsDto paramsDto) {
        try {
            cipherService.handleEncryption(paramsDto);
        } catch (NumberFormatException e) {

        }
    }

    public void handleDecryption(ParamsDto paramsDto) {}
}
