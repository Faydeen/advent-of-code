package com.fayden.advent_of_code.year2021.days;

import com.fayden.advent_of_code.common.Grid;
import com.fayden.advent_of_code.year2021.Day2021;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class Day13 extends Day2021 {


    protected Day13() {
        super("13");
    }

    public static void main(String[] args) {
        new Day13().printParts();
    }


    @Override
    public Object part1() {
        final String[] split = getInput(1).split(System.lineSeparator() + System.lineSeparator());
        final Set<Point> points = Arrays.stream(split[0].split(System.lineSeparator()))
                .map(line -> line.split(","))
                .map(coordinate -> new Point(Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1])))
                .collect(Collectors.toSet());
        final List<FoldInstruction> foldInstructions = Arrays.stream(split[1].split(System.lineSeparator())).map(s -> s.substring("fold along ".length()))
                .map(s -> {
                    final String[] split1 = s.split("=");
                    return new FoldInstruction(FoldDirection.valueOf(split1[0].toUpperCase()), Integer.parseInt(split1[1]));

                }).toList();

        return foldInstructions.get(0).fold(points).size();
    }

    @Override
    public Object part2() {
        final String[] split = getInput(1).split(System.lineSeparator() + System.lineSeparator());
        Set<Point> points = Arrays.stream(split[0].split(System.lineSeparator()))
                .map(line -> line.split(","))
                .map(coordinate -> new Point(Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1])))
                .collect(Collectors.toSet());
        final List<FoldInstruction> foldInstructions = Arrays.stream(split[1].split(System.lineSeparator())).map(s -> s.substring("fold along ".length()))
                .map(s -> {
                    final String[] split1 = s.split("=");
                    return new FoldInstruction(FoldDirection.valueOf(split1[0].toUpperCase()), Integer.parseInt(split1[1]));

                }).toList();
        for (int i = 0; i < foldInstructions.size(); i++) {
            points = foldInstructions.get(i).fold(points);
        }

        print(points);
        return "ZUJUAFHP";
    }

    void print(Set<Point> points) {
        var maxX = points.stream().mapToInt(point -> point.x).max().orElseThrow() + 1;
        var maxY = points.stream().mapToInt(point -> point.y).max().orElseThrow() + 1;
        final Grid<String> stringGrid = new Grid<>(maxX, maxY, " ");
        points.forEach(point -> stringGrid.set(point, "*"));
        stringGrid.print("");
    }

    enum FoldDirection {
        X,
        Y;
    }

    record FoldInstruction(FoldDirection direction, int axeIndex) {
        Set<Point> fold(Set<Point> input) {
            final HashSet<Point> result = new HashSet<>();
            if (FoldDirection.X.equals(direction)) {
                input.forEach(point -> {
                    if (point.x <= axeIndex) result.add(point);
                    else result.add(new Point(axeIndex - (point.x - axeIndex), point.y));
                });
            } else {
                input.forEach(point -> {
                    if (point.y <= axeIndex) result.add(point);
                    else result.add(new Point(point.x, axeIndex - (point.y - axeIndex)));
                });
            }
            return result;
        }
    }
}
