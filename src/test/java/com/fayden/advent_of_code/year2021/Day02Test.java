package com.fayden.advent_of_code.year2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Day02Test {
    @Nested
    class TestPart2{
        @Test
        void testDeplacer() {
            // GIVEN
            final ArrayList<String> input = new ArrayList<>();
            input.add("forward 5");
            input.add("down 5");
            input.add("forward 8");
            input.add("up 3");
            input.add("down 8");
            input.add("forward 2");
            final Day02.Part2.SousMarin sousMarin = new Day02.Part2.SousMarin();               // WHEN
            // WHEN
            for (String commande : input) {
                sousMarin.deplacer(commande);
            }
            // THEN
            Assertions.assertEquals(900, sousMarin.getHorizontal() * sousMarin.getProfondeur());
        }
    }
}