package com.fayden.advent_of_code.year2021.days;

import com.fayden.advent_of_code.year2021.Day2021;
import com.google.common.math.Quantiles;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;


@Slf4j
public class Day07 extends Day2021 {

    public Day07() {
        super("07");
    }

    public static void main(String... args) {
        new Day07().printParts();
    }

    public Object part1() {
        final long[] horizontalPosition = getInputOneLineSplit(1, ",").mapToLong(Long::parseLong).toArray();
        long median = Math.round(Quantiles.median().compute(horizontalPosition));
        return Arrays.stream(horizontalPosition).map(operand -> Math.abs(median - operand)).sum();
    }


    public Object part2() {
        final long[] horizontalPosition = getInputOneLineSplit(1, ",").mapToLong(Long::parseLong).toArray();
        final long average = (long) Math.floor(Arrays.stream(horizontalPosition).summaryStatistics().getAverage());
        return Arrays.stream(horizontalPosition)
                .map(operand -> Math.abs(average - operand))
                .map(n -> ((n * (n + 1)) / 2))
                .sum();
    }


}
