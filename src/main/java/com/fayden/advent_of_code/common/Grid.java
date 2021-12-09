package com.fayden.advent_of_code.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.Arrays;
import java.util.function.Function;

@Slf4j
@Data
public class Grid<T> {
    private int sizeX;
    private int sizeY;
    private Object[][] gridInstance;

    public Grid(int sizeX, int sizeY, T initialValue) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        gridInstance = new Object[sizeY][sizeX];
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                gridInstance[y][x] = initialValue;
            }
        }
    }

    public Grid(String[] input, String fieldSeparator) {
        if (input.length == 0) throw new IllegalArgumentException("No input");
        this.sizeX = input[0].split(fieldSeparator).length;
        this.sizeY = input.length;
        gridInstance = new Object[sizeY][sizeX];
        for (int y = 0; y < sizeY; y++) {
            String[] row = input[y].split(fieldSeparator);
            gridInstance[y] = Arrays.copyOf(row, row.length);
        }
    }

    public <R> Grid<R> convert(Function<T, R> convertTo) {
        Grid<R> rGrid = new Grid<R>(this.sizeX, this.sizeY, null);
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                try {
                    R convertedValue = convertTo.apply(get(x, y));
                    rGrid.set(x, y, convertedValue);
                } catch (Exception e) {
                    log.info("valeur {} incompatible", get(x, y));
                    throw e;
                }
            }
        }
        return rGrid;
    }

    public T get(int x, int y) {
        areXAndYInGrid(x, y);
        return (T) gridInstance[y][x];
    }

    public T get(Point p) {
        return get(p.x, p.y);
    }

    public void set(int x, int y, T value) {
        areXAndYInGrid(x, y);
        gridInstance[y][x] = value;
    }

    private void areXAndYInGrid(int x, int y) {
        if (x >= sizeX || x < 0 || y >= sizeY || y < 0) {
            throw new IllegalArgumentException("x, y not inside grid");
        }
    }


    public boolean pointIsIn(Point p) {
        return p.x < sizeX && p.x >= 0 && p.y < sizeY && p.y >= 0;
    }
}
