package com.fayden.advent_of_code.common;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Stream;

@Slf4j
public abstract class Day {
    private final int year;
    private final String dayNumber;

    private final PuzzleInput puzzleInput;

    protected Day(int year, String dayNumber) {
        this.year = year;
        this.dayNumber = dayNumber;
        this.puzzleInput = new PuzzleInputImp();
    }


    @SuppressWarnings("UnstableApiUsage")
    public String getInput(int part) {
        return puzzleInput.getContentAsString(year, dayNumber, part);

    }

    @SuppressWarnings("UnstableApiUsage")
    public Stream<String> getInputLines(int part) {
        return puzzleInput.getContentAsLines(this.year, this.dayNumber, part).stream();
    }

    @SuppressWarnings("UnstableApiUsage")
    public Grid<String> getInputAsGrid(int part) {
        List<String> strings = puzzleInput.getContentAsLines(this.year, this.dayNumber, part);
        return new Grid<>(strings.toArray(new String[0]), "");

    }

    @SuppressWarnings("UnstableApiUsage")
    public Stream<String> getInputOneLineSplit(int part, String separator) {
        return Stream.of(puzzleInput.getContentAsLines(this.year, this.dayNumber, part)
                .get(0)
                .split(","));
    }

    public abstract Object part1();

    public abstract Object part2();

    public void printParts() {
        log.info("Day {} - Part 1 : {}", dayNumber, part1());
        log.info("--------------------------------------------------------------------------------");
        log.info("Day {} - Part 2 : {}", dayNumber, part2());
    }
}
