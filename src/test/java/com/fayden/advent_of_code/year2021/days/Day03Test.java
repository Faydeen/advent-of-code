package com.fayden.advent_of_code.year2021.days;

import com.fayden.advent_of_code.year2021.days.Day03;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


class Day03Test {
    @Nested
    class TestPart1 {
        @Test
        void testGetGammaRate() {
            // GIVEN
            final ArrayList<String> input = new ArrayList<>();
            input.add("00100");
            input.add("11110");
            input.add("10110");
            input.add("10111");
            input.add("10101");
            input.add("01111");
            input.add("00111");
            input.add("11100");
            input.add("10000");
            input.add("11001");
            input.add("00010");
            input.add("01010");
            final Day03.Diagnostic diagnostic = new Day03.Diagnostic(input);               // WHEN
            // WHEN
            final int gammaRate = diagnostic.getGammaRate();
            // THEN
            assertThat(gammaRate).isEqualTo(22);
        }
        @Test
        void testGetEpsilonRate() {
            // GIVEN
            final ArrayList<String> input = new ArrayList<>();
            input.add("00100");
            input.add("11110");
            input.add("10110");
            input.add("10111");
            input.add("10101");
            input.add("01111");
            input.add("00111");
            input.add("11100");
            input.add("10000");
            input.add("11001");
            input.add("00010");
            input.add("01010");
            final Day03.Diagnostic diagnostic = new Day03.Diagnostic(input);               // WHEN
            // WHEN
            final int gammaRate = diagnostic.getEpsilonRate();
            // THEN
            assertThat(gammaRate).isEqualTo(9);
        }
    }
    @Nested
    class TestPart2 {
        @Test
        void  testGetOxygenGeneratorRating() {
            // GIVEN
            final ArrayList<String> input = new ArrayList<>();
            input.add("00100");
            input.add("11110");
            input.add("10110");
            input.add("10111");
            input.add("10101");
            input.add("01111");
            input.add("00111");
            input.add("11100");
            input.add("10000");
            input.add("11001");
            input.add("00010");
            input.add("01010");
            final Day03.Diagnostic diagnostic = new Day03.Diagnostic(input);               // WHEN
            // WHEN
            final int oxygenGeneratorRating = diagnostic.getOxygenGeneratorRating();
            // THEN
            assertThat(oxygenGeneratorRating).isEqualTo(23);
        }
        @Test
        void testGetCo2ScrubberRating() {
            // GIVEN
            final ArrayList<String> input = new ArrayList<>();
            input.add("00100");
            input.add("11110");
            input.add("10110");
            input.add("10111");
            input.add("10101");
            input.add("01111");
            input.add("00111");
            input.add("11100");
            input.add("10000");
            input.add("11001");
            input.add("00010");
            input.add("01010");
            final Day03.Diagnostic diagnostic = new Day03.Diagnostic(input);               // WHEN
            // WHEN
            final int co2ScrubberRating = diagnostic.getCo2ScrubberRating();
            // THEN
            assertThat(co2ScrubberRating).isEqualTo(10);
        }
        @Test
        void testGetLifeSupportRating() {
            // GIVEN
            final ArrayList<String> input = new ArrayList<>();
            input.add("00100");
            input.add("11110");
            input.add("10110");
            input.add("10111");
            input.add("10101");
            input.add("01111");
            input.add("00111");
            input.add("11100");
            input.add("10000");
            input.add("11001");
            input.add("00010");
            input.add("01010");
            final Day03.Diagnostic diagnostic = new Day03.Diagnostic(input);               // WHEN
            // WHEN
            final int lifeSupportRating = diagnostic.getLifeSupportRating();
            // THEN
            assertThat(lifeSupportRating).isEqualTo(230);
        }
    }
}
