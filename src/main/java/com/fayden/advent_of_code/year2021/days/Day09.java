package com.fayden.advent_of_code.year2021.days;

import com.fayden.advent_of_code.common.Direction;
import com.fayden.advent_of_code.common.Grid;
import com.fayden.advent_of_code.year2021.Day2021;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Collections.reverseOrder;

@Slf4j
public class Day09 extends Day2021 {
    protected Day09() {
        super("09");
    }

    public static void main(String[] args) {
        new Day09().printParts();
    }

    @Override
    public Object part1() {
        Grid<Integer> grid = loadFileAsStringGrid(1)
                .convert(Integer::parseInt);

        List<PointValue> localLowPoint = findMinimumLocalInGrid(grid);
        return localLowPoint
                .stream()
                .mapToInt(PointValue::value)
                .sum();
    }

    private List<PointValue> findMinimumLocalInGrid(Grid<Integer> grid) {
        List<PointValue> localLowPoint = new ArrayList<>();
        for (int x = 0; x < grid.getSizeX(); x++) {
            for (int y = 0; y < grid.getSizeY(); y++) {
                boolean isMimimumLocal = true;
                final Integer value = grid.get(x, y);
                if (x - 1 >= 0 && grid.get(x - 1, y) <= value) {
                    isMimimumLocal = false;
                }
                if (x + 1 < grid.getSizeX() && grid.get(x + 1, y) <= value) {
                    isMimimumLocal = false;
                }
                if (y - 1 >= 0 && grid.get(x, y - 1) <= value) {
                    isMimimumLocal = false;
                }
                if (y + 1 < grid.getSizeY() && grid.get(x, y + 1) <= value) {
                    isMimimumLocal = false;
                }
                if (isMimimumLocal) {
                    localLowPoint.add(new PointValue(new Point(x, y), value + 1));
                }
            }
        }
        return localLowPoint;
    }

    @Override
    public Object part2() {
        Grid<Integer> grid = loadFileAsStringGrid(1)
                .convert(Integer::parseInt);
        List<PointValue> localLowPoint = findMinimumLocalInGrid(grid);
        return localLowPoint.stream()
                .map(pointValue -> computeBasinSizeRecursive(grid, pointValue.position(), new HashSet<Point>()))
                .sorted(reverseOrder())
                .limit(3)
                .reduce((a, b) -> a * b)
                .orElseThrow();
    }


    private int computeBasinSizeRecursive(Grid<Integer> grid, Point p, Set<Point> basinAlreadyEvaluated) {
        if (
                !grid.pointIsIn(p) ||
                        grid.get(p) == 9 ||
                        basinAlreadyEvaluated.contains(p))
            return 0;
        basinAlreadyEvaluated.add(p);
        return 1 + Stream.of(Direction.values()).mapToInt(direction -> computeBasinSizeRecursive(grid, direction.move(p), basinAlreadyEvaluated)).sum();
    }

    record PointValue(Point position, Integer value) {
    }

}
