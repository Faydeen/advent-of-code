package com.fayden.advent_of_code.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Line {
    protected Point point1;
    protected Point point2;

    // Accept formated str as "x1,y1 -> x2,y2"
    public Line(String str) {
        final String[] coodordinates = str.split(" -> ");
        final String[] coordinate1 = coodordinates[0].split(",");
        final String[] coordinate2 = coodordinates[1].split(",");
        point1 = new Point(Integer.parseInt(coordinate1[0]), Integer.parseInt(coordinate1[1]));
        point2 = new Point(Integer.parseInt(coordinate2[0]), Integer.parseInt(coordinate2[1]));
    }

    public Stream<Point> getSegment() {
        if (!isHorizontal() && !isVertical() && !isDiagonal()) return Stream.empty();

        final ArrayList<Point> resultat = new ArrayList<>();
        // We don't know yet how to compute segment in other cases
        final int signumX = Integer.signum(point2.x - point1.x);
        final int signumY = Integer.signum(point2.y - point1.y);

        for (int i = 0; i <= Math.max(Math.abs(point2.x - point1.x), Math.abs(point2.y - point1.y)); i++) {
            resultat.add(new Point(point1.x + signumX * i, point1.y + signumY * i));
        }
        return resultat.stream();
    }

    public boolean isVertical() {
        return point1.x == point2.x;
    }

    public boolean isHorizontal() {
        return point1.y == point2.y;
    }

    public boolean isDiagonal() {
        return Math.abs(point1.x - point2.x) == Math.abs(point1.y - point2.y);
    }
}
