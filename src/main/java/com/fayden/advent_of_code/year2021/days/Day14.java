package com.fayden.advent_of_code.year2021.days;

import com.fayden.advent_of_code.year2021.Day2021;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class Day14 extends Day2021 {


    protected Day14() {
        super("14");
    }

    public static void main(String[] args) {
        new Day14().printParts();
    }


    @Override
    public Object part1() {
        return executeSteps(10);
    }

    private String applyInstructionOnPairs(Map<String, String> instructions, String strs) {
        final StringBuilder stringBuilder = new StringBuilder()
                .append(strs.charAt(0));
        for (int i = 0; i < strs.length() - 1; i++) {
            final String pair = strs.substring(i, i + 2);
            if (instructions.containsKey(pair)) {
                stringBuilder.append(instructions.get(pair)).append(strs.charAt(i + 1));
            } else {
                stringBuilder.append(strs.charAt(i + 1));
            }

        }
        return stringBuilder.toString();
    }

    @Override
    public Object part2() {
        return null;
    }

    private long executeSteps(int steps) {
        final String[] split = getInput(1).split(System.lineSeparator() + System.lineSeparator());
        String strs = split[0];
        Map<String, String> instructions = Arrays
                .stream(split[1].split(System.lineSeparator()))
                .map(s -> s.split(" -> "))
                .collect(Collectors.toMap(e -> e[0], e -> e[1]));

        for (int i = 0; i < steps; i++) {
            strs = applyInstructionOnPairs(instructions, strs);
        }
        final Map<String, Long> collect = Arrays.stream(strs.split("")).collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        final long min = collect.values().stream().mapToLong(value -> value).min().orElseThrow();
        final long max = collect.values().stream().mapToLong(value -> value).max().orElseThrow();

        return max - min;
    }
}
