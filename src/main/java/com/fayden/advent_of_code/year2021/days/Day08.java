package com.fayden.advent_of_code.year2021.days;

import com.fayden.advent_of_code.year2021.Day2021;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Slf4j
public class Day08 extends Day2021 {
    protected Day08() {
        super("08");
    }

    public static void main(String[] args) {
        new Day08().printParts();
    }

    @Override
    public Object part1() {
        List<Integer> uniqueDigitSize = Arrays.asList(2, 3, 4, 7);
        return getInputLines(1).map(s -> s.split(" \\| ")[1]).flatMap(s -> Arrays.stream(s.split(" ")))
                .filter(s -> uniqueDigitSize.contains(s.length()))
                .count();
    }

    @Override
    public Object part2() {
        return getInputLines(1).map(LineInput::new).mapToInt(LineInput::getOutput).sum();
    }

    @Data
    public static class LineInput {
        private static final List<String> DIGIT_SEGMENT_LETTER = Arrays.asList("a", "b", "c", "d", "e", "f", "g");
        private List<String> allInput;
        private String[] output;
        private Map<Integer, List<String>> segmentMatching;

        /**
         * Mapping segment
         * 0  ----
         * 3 |    | 4
         * |    |
         * 1  ----
         * 5 |    | 6
         * |    |
         * 2  ----
         */

        public LineInput(String inputString) {
            final String[] split = inputString.split(" \\| ");
            output = split[1].split(" ");
            allInput = Arrays.asList(split[0].split(" "));
            allInput = Stream.concat(Stream.of(split[0].split(" ")), Stream.of(output)).toList();
            analyseInput();
        }

        private void analyseInput() {
            segmentMatching = new HashMap<>();

            segmentMatching.put(1, allInputElementOfSize(2).toList().get(0));
            segmentMatching.put(4, allInputElementOfSize(4).toList().get(0));
            segmentMatching.put(7, allInputElementOfSize(3).toList().get(0));
            segmentMatching.put(8, DIGIT_SEGMENT_LETTER);
            segmentMatching.put(3,
                    allInputElementOfSize(5)
                            .filter(containsAll(segmentMatching.get(7)))
                            .findAny()
                            .orElse(new ArrayList<>())
            );
            segmentMatching.put(9,
                    allInputElementOfSize(6)
                            .filter(containsAll(segmentMatching.get(3)))
                            .findAny()
                            .orElse(new ArrayList<>())
            );
            segmentMatching.put(0,
                    allInputElementOfSize(6)
                            .filter(containsAll(segmentMatching.get(7)))
                            .filter(containsAll(segmentMatching.get(9)).negate())
                            .findAny()
                            .orElse(new ArrayList<>())
            );
            segmentMatching.put(6,
                    allInputElementOfSize(6)
                            .filter(containsAll(segmentMatching.get(0)).negate())
                            .filter(containsAll(segmentMatching.get(9)).negate())
                            .findAny()
                            .orElse(new ArrayList<>())
            );
            segmentMatching.put(5,
                    allInputElementOfSize(5)
                            .filter(isIncludeIn(segmentMatching.get(6)))
                            .findAny()
                            .orElse(new ArrayList<>())
            );
            segmentMatching.put(2,
                    allInputElementOfSize(5)
                            .filter(containsAll(segmentMatching.get(7)).negate())
                            .filter(isIncludeIn(segmentMatching.get(6)).negate())
                            .findAny()
                            .orElse(new ArrayList<>())
            );
        }

        private Predicate<List<String>> isIncludeIn(List<String> listToBeIncludedIn) {
            return listToBeIncludedIn::containsAll;
        }

        private Predicate<List<String>> containsAll(List<String> listToInculde) {
            return s -> s.containsAll(listToInculde);
        }

        private Stream<List<String>> allInputElementOfSize(int size) {
            return allInput
                    .stream()
                    .filter(hasLength(size))
                    .map(s -> s.split(""))
                    .map(Arrays::asList);
        }

        private Predicate<? super String> hasLength(int size) {
            return s -> s.length() == size;
        }

        public int getOutput() {
            return Integer.parseInt(
                    String.valueOf(findingCorrespondance(output[0])) +
                            findingCorrespondance(output[1]) +
                            findingCorrespondance(output[2]) +
                            findingCorrespondance(output[3])
            );
        }

        private Integer findingCorrespondance(String strInput) {
            final List<Integer> entries = segmentMatching
                    .entrySet()
                    .stream()
                    .filter(integerListEntry ->
                            integerListEntry.getValue().size() == strInput.length() &&
                                    integerListEntry.getValue()
                                            .containsAll(
                                                    List.of(strInput.split(""))))
                    .map(Map.Entry::getKey)
                    .toList();
            if (entries.size() != 1) throw new IllegalArgumentException("Unable to find matching number");
            return entries.get(0);
        }
    }

}
