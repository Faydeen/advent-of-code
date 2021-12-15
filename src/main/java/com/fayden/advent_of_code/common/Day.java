package com.fayden.advent_of_code.common;

import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public abstract class Day {
    private final int year;
    private final String dayNumber;

    protected Day(int year, String dayNumber) {
        this.year = year;
        this.dayNumber = dayNumber;
    }

    public String getResourceFilePath(int part) {
        return this.year + "/day" + dayNumber + "_" + part + ".txt";
    }

    @SuppressWarnings("UnstableApiUsage")
    public String getInput(int part) {
        try {
            return Resources.toString(ClassLoader.getSystemResource(getResourceFilePath(part)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @SuppressWarnings("UnstableApiUsage")
    public Stream<String> getInputLines(int part) {
        try {
            return Resources.readLines(ClassLoader.getSystemResource(getResourceFilePath(part)), StandardCharsets.UTF_8)
                    .stream();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @SuppressWarnings("UnstableApiUsage")
    public Grid<String> getInputAsGrid(int part) {
        try {
            List<String> strings = Resources.readLines(ClassLoader.getSystemResource(getResourceFilePath(part)), StandardCharsets.UTF_8);
            return new Grid<>(strings.toArray(new String[0]), "");
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @SuppressWarnings("UnstableApiUsage")
    public Stream<String> getInputOneLineSplit(int part, String separator) {
        try {
            return Stream.of(Resources.readLines(ClassLoader.getSystemResource(getResourceFilePath(part)), StandardCharsets.UTF_8)
                    .get(0)
                    .split(","));
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public abstract Object part1();

    public abstract Object part2();

    public void printParts() {
        log.info("Day {} - Part 1 : {}", dayNumber, part1());
        log.info("--------------------------------------------------------------------------------");
        log.info("Day {} - Part 2 : {}", dayNumber, part2());
    }
}
