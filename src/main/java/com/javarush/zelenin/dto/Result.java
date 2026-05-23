package com.javarush.zelenin.dto;

/**
 * An object for storing the results of the application execution.
 * @param resultCode either {@code OK}, {@code ERROR}, or {@code EXIT}
 * @param message an accompanying message describing the results
 */
public record Result(Code resultCode, String message) {

    public enum Code { OK, ERROR, EXIT }
}
