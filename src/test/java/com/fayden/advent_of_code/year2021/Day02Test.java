package com.fayden.advent_of_code.year2021;

import com.fayden.advent_of_code.year2021.days.Day02;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class Day02Test {
    @Nested
    class TestPart2 {
        @Test
        void testPart2() {
            // WHEN
            final Object o = new Day02().part2();
            // THEN
            assertThat(o).isEqualTo(900);
        }

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
            final Day02.SousMarinPart2 sousMarin = new Day02.SousMarinPart2();               // WHEN
            // WHEN
            for (String commande : input) {
                sousMarin.deplacer(commande);
            }
            // THEN
            Assertions.assertEquals(900, sousMarin.getHorizontal() * sousMarin.getProfondeur());
        }
    }
}