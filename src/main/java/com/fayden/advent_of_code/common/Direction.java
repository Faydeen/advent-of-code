package com.fayden.advent_of_code.common;

import java.awt.*;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

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

        }
        throw new IllegalStateException("Non-existent Direction: " + this);
    }
}
