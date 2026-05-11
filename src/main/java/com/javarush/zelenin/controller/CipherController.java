package com.javarush.zelenin.controller;

import com.javarush.zelenin.dto.Params;
import com.javarush.zelenin.service.CipherService;

public class CipherController {
    private final CipherService cipherService;

    public CipherController(CipherService cipherService) {
        this.cipherService = cipherService;
    }

    public void handleEncryption(Params params) {
        try {
            cipherService.handleEncryption(params);
        } catch (NumberFormatException e) {

        }
    }

    public void handleDecryption(Params params) {}
}
