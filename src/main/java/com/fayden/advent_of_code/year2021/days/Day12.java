package com.fayden.advent_of_code.year2021.days;

import com.fayden.advent_of_code.year2021.Day2021;
import com.google.common.collect.ImmutableListMultimap;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Stream;

import static com.google.common.collect.ImmutableListMultimap.toImmutableListMultimap;

@Slf4j
public class Day12 extends Day2021 {
    public static final String START = "start";
    public static final String END = "end";

    protected Day12() {
        super("12");
    }

    public static void main(String[] args) {
        new Day12().printParts();
    }

    private ImmutableListMultimap<String, String> getInput() {
        return loadFile(1).map(e -> e.split("-")).flatMap(e -> Stream.of(new String[]{e[0], e[1]}, new String[]{e[1], e[0]})).collect(toImmutableListMultimap(e -> e[0], e -> e[1]));
    }

    @Override
    public Object part1() {
        return findNumberOfPath(START, getInput(), Set.of(START));
    }

    @Override
    public Object part2() {
        return findNumberOfPath2(START, getInput(), Map.of(START, 2));
    }

    private long findNumberOfPath(String s, ImmutableListMultimap<String, String> in, Set<String> visited) {
        if (END.equals(s)) return 1;
        long n = 0;
        List<String> reachable = in.get(s);
        for (String l : reachable) {
            if (!visited.contains(l)) {
                Set<String> newSet = new HashSet<>(visited);
                if (!l.toUpperCase().equals(l)) newSet.add(l);
                n += findNumberOfPath(l, in, newSet);
            }
        }
        return n;
    }

    private long findNumberOfPath2(String s, ImmutableListMultimap<String, String> in, Map<String, Integer> visited) {
        if (END.equals(s)) return 1;
        long n = 0;
        List<String> reachable = in.get(s);
        for (String l : reachable) {
            if ((!visited.containsKey(l) || visited.get(l) < 2) && visited.values().stream().filter(e -> e == 2).count() <= 2) {
                Map<String, Integer> newSet = new HashMap<>(visited);
                if (!l.toUpperCase().equals(l)) newSet.put(l, newSet.containsKey(l) ? newSet.get(l) + 1 : 1);
                n += findNumberOfPath2(l, in, newSet);
            }
        }
        return n;
    }

}
