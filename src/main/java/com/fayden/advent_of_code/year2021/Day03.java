package com.fayden.advent_of_code.year2021;

import com.google.common.io.Resources;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Day03 {

    static final String INPUT_FILE_PART_1 = "2021/day03_1.txt";

    public static void main(String... args) throws IOException {
        new Part1().run();
        log.info("--------------------------------------------------------------");
        new Part2().run();
    }


    @Data
    public static class Diagnostic {

        private int gammaRate;
        private int epsilonRate;
        private int oxygenGeneratorRating;
        private int co2ScrubberRating;

        public Diagnostic(List<String> lines) {
            Integer diagnosticMaxLength = lines.stream().mapToInt(String::length).max().orElse(0);
            List<char[]> diagnostic = lines.stream().map(String::toCharArray).toList();
            setGammaAndEpsilonRate(diagnostic, diagnosticMaxLength);
            setOxygenRatting(diagnostic, diagnosticMaxLength);
            setCo2Ratting(diagnostic, diagnosticMaxLength);
        }

        private void setGammaAndEpsilonRate(List<char[]> diagnostic, Integer diagnosticMaxLength) {
            char[] resultGammaRate = new char[diagnosticMaxLength];
            char[] resultEpsilonRate = new char[diagnosticMaxLength];
            for (int i = 0; i < diagnosticMaxLength; i++) {
                int finalI = i;
                long numberOfOne = diagnostic.stream()
                        .map(chars -> chars[finalI])
                        .filter(character -> character.equals('1'))
                        .count();
                long numberOfZero = diagnostic.size() - numberOfOne;
                resultGammaRate[i] = numberOfOne >= numberOfZero ? '1' : '0';
                resultEpsilonRate[i] = numberOfOne >= numberOfZero ? '0' : '1';
            }
            gammaRate = Integer.valueOf(new String(resultGammaRate), 2);
            epsilonRate = Integer.valueOf(new String(resultEpsilonRate), 2);
        }

        private void setOxygenRatting(List<char[]> diagnostic, Integer diagnosticMaxLength) {
            List<char[]> listOfUsableValues = new ArrayList<>(diagnostic);
            for (int i = 0; i < diagnosticMaxLength; i++) {
                int finalI = i;
                long numberOfOne = listOfUsableValues.stream()
                        .map(chars -> chars[finalI])
                        .filter(character -> character.equals('1'))
                        .count();
                long numberOfZero = listOfUsableValues.size() - numberOfOne;
                if (listOfUsableValues.size() != 1) {
                    listOfUsableValues = listOfUsableValues
                            .stream()
                            .filter(
                                    chars -> chars[finalI] == (numberOfOne >= numberOfZero ? '1' : '0')
                            ).toList();
                } else {
                    break;
                }
            }
            oxygenGeneratorRating = Integer.valueOf(new String(listOfUsableValues.get(0)), 2);
        }

        private void setCo2Ratting(List<char[]> diagnostic, Integer diagnosticMaxLength) {
            List<char[]> listOfUsableValues = new ArrayList<>(diagnostic);
            for (int i = 0; i < diagnosticMaxLength; i++) {
                int finalI = i;
                long numberOfOne = listOfUsableValues.stream()
                        .map(chars -> chars[finalI])
                        .filter(character -> character.equals('1'))
                        .count();
                long numberOfZero = listOfUsableValues.size() - numberOfOne;
                if (listOfUsableValues.size() != 1) {
                    listOfUsableValues = listOfUsableValues
                            .stream()
                            .filter(
                                    chars -> chars[finalI] == (numberOfOne >= numberOfZero ? '0' : '1')
                            ).toList();
                } else {
                    break;
                }
            }
            co2ScrubberRating = Integer.valueOf(new String(listOfUsableValues.get(0)), 2);
        }

        public int getPowerConsumption() {
            return getGammaRate() * getEpsilonRate();
        }

        public int getLifeSupportRating() {
            return getOxygenGeneratorRating() * getCo2ScrubberRating();
        }
    }


    public static class Part1 {
        void run() throws IOException {
            @SuppressWarnings("UnstableApiUsage") var lines = Resources.readLines(ClassLoader.getSystemResource(INPUT_FILE_PART_1), StandardCharsets.UTF_8);
            final Diagnostic diagnostic = new Diagnostic(lines);
            log.info("Power consumption of the submarin: {}", diagnostic.getPowerConsumption());
        }
    }

    public static class Part2 {
        void run() throws IOException {
            @SuppressWarnings("UnstableApiUsage") var lines = Resources.readLines(ClassLoader.getSystemResource(INPUT_FILE_PART_1), StandardCharsets.UTF_8);
            final Diagnostic diagnostic = new Diagnostic(lines);
            log.info("Life support rating of the submarin: {}", diagnostic.getLifeSupportRating());
        }
    }
}
