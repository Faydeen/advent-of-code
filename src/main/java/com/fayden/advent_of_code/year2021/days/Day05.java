package com.fayden.advent_of_code.year2021.days;

import com.fayden.advent_of_code.common.Line;
import com.fayden.advent_of_code.year2021.Day2021;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
public class Day05 extends Day2021 {

    public Day05() {
        super("05");
    }

    public static void main(String... args) {
        new Day05().printParts();
    } // x,y ->

    public Object part1() {
        Predicate<Line> filterLineByHorizontalOrVerticale = line -> line.isHorizontal() || line.isVertical();
        return loadFile(1).map(Line::new)
                .filter(filterLineByHorizontalOrVerticale)
                .flatMap(Line::getSegment)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream()
                .filter(aLong -> aLong > 1)
                .count();
    }

    public Object part2() {
        Predicate<Line> filterLineByHorizontalOrVerticalOrDiagonale = line -> line.isHorizontal() || line.isVertical() || line.isDiagonal();
        return loadFile(1).map(Line::new)
                .filter(filterLineByHorizontalOrVerticalOrDiagonale)
                .flatMap(Line::getSegment)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream()
                .filter(aLong -> aLong > 1)
                .count();
    }
}
