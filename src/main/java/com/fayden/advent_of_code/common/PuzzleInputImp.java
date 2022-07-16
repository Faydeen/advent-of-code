package com.fayden.advent_of_code.common;

import java.util.Arrays;
import java.util.List;

public class PuzzleInputImp implements PuzzleInput {
    private final PuzzleInputProvider puzzleInputProvider;

    public PuzzleInputImp() {
        this.puzzleInputProvider = new PuzzleInputProviderResourceImp();
    }

    @Override
    public String getContentAsString(int year, String dayNumber, int part) {
        return puzzleInputProvider.getInput(year, dayNumber, part);
    }

    @Override
    public List<String> getContentAsLines(int year, String dayNumber, int part) {
        return Arrays.asList(puzzleInputProvider.getInput(year, dayNumber, part).split(System.lineSeparator()));
    }
}
