package com.fayden.advent_of_code.year2021.days;

import com.fayden.advent_of_code.common.Direction;
import com.fayden.advent_of_code.common.Grid;
import com.fayden.advent_of_code.year2021.Day2021;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Day11 extends Day2021 {
    protected Day11() {
        super("11");
    }

    public static void main(String[] args) {
        new Day11().printParts();
    }

    @Override
    public Object part1() {
        var numberOfStepToSimulate = 100;
        final Grid<Integer> grid = getInputAsGrid(1)
                .convert(Integer::parseInt);

        int result = 0;
        for (int i = 0; i < numberOfStepToSimulate; i++) {
            result += nextStep(grid);
        }
        return result;
    }

    private int nextStep(Grid<Integer> grid) {

        AtomicInteger numberOfFlashThisStep = new AtomicInteger();

        // IncrementByOne
        grid.foreach(p -> grid.set(p, grid.get(p) + 1));

        // Than flash and increment neighboor
        grid.foreach(p -> hasFlashed(grid, p, numberOfFlashThisStep));

        return numberOfFlashThisStep.get();
    }

    private void hasFlashed(Grid<Integer> grid, Point p, AtomicInteger numberOfFlashThisStep) {
        final Integer value = grid.get(p);
        if (value > 9) {
            numberOfFlashThisStep.incrementAndGet();
            grid.set(p, 0);
            final List<Point> validNeighbor = Direction.eightDirection().stream()
                    .map(direction -> direction.move(p))
                    .filter(grid::pointIsIn).toList();
            validNeighbor.stream().filter(point -> grid.get(point) != 0).forEach(point -> grid.set(point, grid.get(point) + 1));
            validNeighbor.forEach(point -> hasFlashed(grid, point, numberOfFlashThisStep));
        }

    }

    @Override
    public Object part2() {
        final Grid<Integer> grid = getInputAsGrid(1)
                .convert(Integer::parseInt);

        int result = 0;
        AtomicBoolean hasAllFlashed = new AtomicBoolean(false);
        while(!hasAllFlashed.get()){
            nextStep(grid);
            hasAllFlashed.set(true);
            grid.foreach(p->{
                if(grid.get(p) != 0) hasAllFlashed.set(false);
            });
            result++;
        }
        return result;
    }
}
