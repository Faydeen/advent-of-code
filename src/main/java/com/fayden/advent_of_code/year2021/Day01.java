package com.fayden.advent_of_code.year2021;

import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class Day01 {

    static final String INPUT_FILE_PART_1 = "2021/day01_1.txt";

    public static void main(String... args) throws IOException {
        new Part1().run();
        new Part2().run();
    }

    public static class Part1 {
        void run() throws IOException {
            var lines = Resources.readLines(ClassLoader.getSystemResource(INPUT_FILE_PART_1), StandardCharsets.UTF_8)
                    .stream()
                    .map(Integer::valueOf)
                    .toList();
            var result = nombreDeFoisQueLaProfondeurAugmente(lines);
            log.info("Nombre de fois que la profondeur a augmenté: {}", result);
        }

        int nombreDeFoisQueLaProfondeurAugmente(List<Integer> lines) {
            int result = 0;
            for (int i = 1; i < lines.size(); i++) {
                if (lines.get(i) > lines.get(i - 1)) {
                    result++;
                }
            }
            return result;
        }
    }

    public static class Part2 {
        void run() throws IOException {
            var lines = Resources.readLines(ClassLoader.getSystemResource(INPUT_FILE_PART_1), StandardCharsets.UTF_8)
                    .stream()
                    .map(Integer::valueOf)
                    .toList();
            int window = 3;
            var result = nombreDeFoisQueLaProfondeurAugmente(lines, window);
            log.info("Nombre de fois que la profondeur a augmenté en utilisant une fenetre de {}: {}", window, result);
        }

        int nombreDeFoisQueLaProfondeurAugmente(List<Integer> lines, int window) {
            int result = 0;
            for (int i = 1; i < lines.size() - window + 1; i++) {
                if (sommeDesProfondeurs(lines, i, window) > sommeDesProfondeurs(lines, i - 1, window)) {
                    result++;
                }
            }
            return result;
        }

        int sommeDesProfondeurs(List<Integer> lines, int index, int window) {
            int result = 0;
            for (int i = 0; i < window; i++) {
                result += lines.get(index + i);
            }
            return result;
        }
    }
}
