package com.fayden.advent_of_code.year2021.days;

import com.fayden.advent_of_code.year2021.Day2021;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

@Slf4j
public class Day10 extends Day2021 {
    protected Day10() {
        super("10");
    }

    public static void main(String[] args) {
        new Day10().printParts();
    }

    @Override
    public Object part1() {
        return loadFile(1)
                .map(s -> {
                    try {
                        whatIsMissing(s.toCharArray());
                        // TO IGNORE
                        return null;
                    } catch (IllegalChunkException e) {
                        return e.chunkDelimiter;
                    }
                })
                .filter(Objects::nonNull)
                .mapToInt(ChunkDelimiter::getErrorPoint)
                .sum();
    }

    @Override
    public Object part2() {
        final long[] longs = loadFile(1)
                .map(s -> {
                    try {
                        return whatIsMissing(s.toCharArray());
                    } catch (IllegalChunkException e) {
                        // TO IGNORE
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .mapToLong(this::computePoint)
                .sorted()
                .toArray();
        return longs[(longs.length - 1) / 2];
    }

    private long computePoint(Deque<ChunkDelimiter> deque) {
        long score = 0;
        while (!deque.isEmpty()) {
            final ChunkDelimiter last = deque.removeLast();
            score = score * 5 + last.getAutocompletePoint();
        }
        return score;
    }

    private Deque<ChunkDelimiter> whatIsMissing(char[] inputString) throws IllegalChunkException {
        final Deque<ChunkDelimiter> chunkDelimiterDeque = new LinkedList<>();
        for (char c : inputString) {
            if (ChunkDelimiter.isOpening(c)) {
                chunkDelimiterDeque.add(ChunkDelimiter.valueFromOpening(c));
            } else {
                final ChunkDelimiter peek = chunkDelimiterDeque.removeLast();
                if (peek.getClosing() != c) {
                    throw new IllegalChunkException(ChunkDelimiter.valueFromClosing(c));
                }
            }
        }
        return chunkDelimiterDeque;
    }

    public enum ChunkDelimiter {
        PARENTHESIS('(', ')', 3, 1),
        SQUARE_BRACKET('[', ']', 57, 2),
        BRACKET('{', '}', 1197, 3),
        CHEVRON('<', '>', 25137, 4);

        private final char opening;
        private final char closing;
        private final int errorPoint;
        private final int autocompletePoint;

        ChunkDelimiter(char opening, char closing, int errorPoint, int autocompletePoint) {
            this.opening = opening;
            this.closing = closing;
            this.errorPoint = errorPoint;
            this.autocompletePoint = autocompletePoint;
        }

        public static boolean isOpening(char character) {
            return Arrays.stream(values()).map(ChunkDelimiter::getOpening).anyMatch(s -> s == character);
        }

        public static ChunkDelimiter valueFromOpening(char character) {
            return Arrays.stream(values()).filter(chunkDelimiter -> chunkDelimiter.getOpening() == character).findFirst().orElseThrow(IllegalArgumentException::new);
        }

        public static ChunkDelimiter valueFromClosing(char character) {
            return Arrays.stream(values()).filter(chunkDelimiter -> chunkDelimiter.getClosing() == character).findFirst().orElseThrow(IllegalArgumentException::new);
        }

        public char getOpening() {
            return opening;
        }

        public char getClosing() {
            return closing;
        }

        public int getErrorPoint() {
            return errorPoint;
        }

        public int getAutocompletePoint() {
            return autocompletePoint;
        }
    }

    public static class IllegalChunkException extends Exception {
        final ChunkDelimiter chunkDelimiter;

        public IllegalChunkException(ChunkDelimiter chunkDelimiter) {
            this.chunkDelimiter = chunkDelimiter;
        }
    }
}
