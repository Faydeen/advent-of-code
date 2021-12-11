package com.fayden.advent_of_code.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        Grid<R> rGrid = new Grid<>(this.sizeX, this.sizeY, null);
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
        isPointInGrid(p);
        return get(p.x, p.y);
    }

    public void set(int x, int y, T value) {
        areXAndYInGrid(x, y);
        gridInstance[y][x] = value;
    }

    public void set(Point p, T value) {
        isPointInGrid(p);
        gridInstance[p.y][p.x] = value;
    }

    private void areXAndYInGrid(int x, int y) {
        if (x >= sizeX || x < 0 || y >= sizeY || y < 0) {
            throw new IllegalArgumentException("x, y not inside grid");
        }
    }

    private void isPointInGrid(Point p) {
        areXAndYInGrid(p.x, p.y);
    }

    public boolean pointIsIn(Point p) {
        return p.x < sizeX && p.x >= 0 && p.y < sizeY && p.y >= 0;
    }

    public void print() {
        log.info("Printing grid:");
        for (int y = 0; y < sizeY; y++) {
            log.info(Arrays.stream(this.gridInstance[y]).map(v -> String.format("%3s", v.toString())).collect(Collectors.joining(" , ")));
        }
        log.info("-----------------------------------------------------");
    }

    public void foreach(Consumer<Point> consumer) {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                consumer.accept(new Point(x, y));
            }
        }
    }
}
