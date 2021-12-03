package com.fayden.advent_of_code.year2021.days;

import com.fayden.advent_of_code.year2021.Day2021;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Day01 extends Day2021 {
    public Day01() {
        super("01");
    }

    public static void main(String... args) {
        new Day01().printParts();
    }

    public Object part1() {
        final List<Integer> lines = loadFile(1)
                .map(Integer::valueOf)
                .toList();
        return nombreDeFoisQueLaProfondeurAugmente(lines);
    }

    public Object part2() {
        final List<Integer> lines = loadFile(1)
                .map(Integer::valueOf)
                .toList();
        int window = 3;

        return nombreDeFoisQueLaProfondeurAugmente(lines, window);
    }

    private int nombreDeFoisQueLaProfondeurAugmente(List<Integer> lines) {
        int result = 0;
        for (int i = 1; i < lines.size(); i++) {
            if (lines.get(i) > lines.get(i - 1)) {
                result++;
            }
        }
        return result;
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
