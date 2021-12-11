package com.fayden.advent_of_code.common;

import java.awt.*;
import java.util.Arrays;
import java.util.Collection;

public enum Direction {
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST;

    public static Collection<Direction> cardinalPoint() {
        return Arrays.asList(NORTH, SOUTH, EAST, WEST);
    }

    public static Collection<Direction> eightDirection() {
        return Arrays.asList(
                NORTH,
                NORTH_EAST,
                EAST,
                SOUTH_EAST,
                SOUTH,
                SOUTH_WEST,
                WEST,
                NORTH_WEST
        );
    }

    public Point move(Point currentLocation) {
        switch (this) {
            case SOUTH:
                return new Point(currentLocation.x, currentLocation.y + 1);
            case NORTH:
                return new Point(currentLocation.x, currentLocation.y - 1);
            case EAST:
                return new Point(currentLocation.x + 1, currentLocation.y);
            case WEST:
                return new Point(currentLocation.x - 1, currentLocation.y);
            case NORTH_EAST:
                return new Point(currentLocation.x + 1, currentLocation.y - 1);
            case SOUTH_EAST:
                return new Point(currentLocation.x + 1, currentLocation.y + 1);
            case SOUTH_WEST:
                return new Point(currentLocation.x - 1, currentLocation.y + 1);
            case NORTH_WEST:
                return new Point(currentLocation.x - 1, currentLocation.y - 1);
        }
        throw new IllegalStateException("Non-existent Direction: " + this);
    }
}
