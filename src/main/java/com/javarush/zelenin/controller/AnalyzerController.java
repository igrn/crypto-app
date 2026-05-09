package com.javarush.zelenin.controller;

import com.javarush.zelenin.scenario.Scenario;

public class AnalyzerController {
    private final Scenario bruteForce;
    private final Scenario analyze;

    public AnalyzerController(Scenario bruteForce, Scenario analyze) {
        this.bruteForce = bruteForce;
        this.analyze = analyze;
    }

    public void handleBruteForce() {}

    public void handleStatAnalysis() {}
}
