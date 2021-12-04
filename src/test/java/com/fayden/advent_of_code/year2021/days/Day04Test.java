package com.fayden.advent_of_code.year2021.days;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day04Test {

    @Test
    void testPart1() {
        final List<Integer> tirage = Arrays.stream("7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1".split(",")).map(Integer::parseInt).toList();
        final ArrayList<Day04.BingoGrid> bingoGrids = new ArrayList<>();
        bingoGrids.add(
                new Day04.BingoGrid(0, gridFrom("""
                        22 13 17 11  0
                        8  2 23  4 24
                        21  9 14 16  7
                        6 10  3 18  5
                        1 12 20 15 19
                        """))
        );
        bingoGrids.add(
                new Day04.BingoGrid(1, gridFrom("""
                        3 15  0  2 22
                        9 18 13 17  5
                        19  8  7 25 23
                        20 11 10 24  4
                        14 21 16 12  6
                        """))
        );
        bingoGrids.add(
                new Day04.BingoGrid(2, gridFrom("""
                        14 21 17 24  4
                        10 16 15  9 19
                        18  8 23 26 20
                        22 11 13  6  5
                         2  0 12  3  7
                        """))
        );

        final int resultat = new Day04().winningBingoBoardScore(tirage, bingoGrids);
        assertThat(resultat).isEqualTo(4512);
    }

    @Test
    void testPart2() {
        final List<Integer> tirage = Arrays.stream("7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1".split(",")).map(Integer::parseInt).toList();
        final ArrayList<Day04.BingoGrid> bingoGrids = new ArrayList<>();
        bingoGrids.add(
                new Day04.BingoGrid(0, gridFrom("""
                        22 13 17 11  0
                        8  2 23  4 24
                        21  9 14 16  7
                        6 10  3 18  5W
                        1 12 20 15 19
                        """))
        );
        bingoGrids.add(
                new Day04.BingoGrid(1, gridFrom("""
                        3 15  0  2 22
                        9 18 13 17  5
                        19  8  7 25 23
                        20 11 10 24  4
                        14 21 16 12  6
                        """))
        );
        bingoGrids.add(
                new Day04.BingoGrid(2, gridFrom("""
                        14 21 17 24  4
                        10 16 15  9 19
                        18  8 23 26 20
                        22 11 13  6  5
                         2  0 12  3  7
                        """))
        );

        final int resultat = new Day04().loosingBingoBoardScore(0, tirage, bingoGrids);
        assertThat(resultat).isEqualTo(1924);
    }

    private List<List<Integer>> gridFrom(String input) {
        return Arrays.stream(input.split("\n"))
                .filter(s -> !s.isBlank())
                .map(s -> s.split(" "))
                .map(Arrays::asList)
                .map(strings1 -> strings1.stream().filter(s -> !s.isBlank()).toList())
                .map(strings1 -> strings1.stream().map(Integer::parseInt).toList())
                .toList();
    }
}