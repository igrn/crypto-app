package com.javarush.zelenin.scenario;

import java.util.Scanner;

@FunctionalInterface
public interface Scenario {

    void execute(Scanner scanner); //TODO поменять параметры на request DTO
}
