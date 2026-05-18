package com.javarush.zelenin.dto;

public record Result(Code resultCode, String message) {

    public enum Code { OK, ERROR, EXIT }
}
