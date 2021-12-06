package com.fayden.advent_of_code.year2021.days;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Day06Test {

    @Test
    void part2() {
        Day06 day06 = new Day06();
        assertThat(day06.combienDIndividuAuBoutDeJour(4, 1)).isEqualTo(1);
        assertThat(day06.combienDIndividuAuBoutDeJour(4, 2)).isEqualTo(1);
        assertThat(day06.combienDIndividuAuBoutDeJour(4, 3)).isEqualTo(1);
        assertThat(day06.combienDIndividuAuBoutDeJour(4, 4)).isEqualTo(1);
        assertThat(day06.combienDIndividuAuBoutDeJour(4, 5)).isEqualTo(2);
        assertThat(day06.combienDIndividuAuBoutDeJour(4, 5)).isEqualTo(2);
        assertThat(day06.combienDIndividuAuBoutDeJour(4, 11)).isEqualTo(2);
        assertThat(day06.combienDIndividuAuBoutDeJour(4, 12)).isEqualTo(3);
        assertThat(day06.combienDIndividuAuBoutDeJour(4, 12)).isEqualTo(3);
        assertThat(day06.combienDIndividuAuBoutDeJour(4, 20)).isEqualTo(5);
        assertThat(day06.combienDIndividuAuBoutDeJour(4, 21)).isEqualTo(7);

        assertThat(day06.combienDIndividuAuBoutDeJour(0, 1)).isEqualTo(2);
        assertThat(day06.combienDIndividuAuBoutDeJour(0, 16)).isEqualTo(5);
        assertThat(day06.combienDIndividuAuBoutDeJour(0, 17)).isEqualTo(7);
        assertThat(day06.combienDIndividuAuBoutDeJour(1, 18)).isEqualTo(7);
        assertThat(day06.combienDIndividuAuBoutDeJour(2, 18)).isEqualTo(5);
        assertThat(day06.combienDIndividuAuBoutDeJour(3, 18)).isEqualTo(5);
        assertThat(day06.combienDIndividuAuBoutDeJour(4, 18)).isEqualTo(4);
        assertThat(day06.combienDIndividuAuBoutDeJour(5, 18)).isEqualTo(4);
        assertThat(day06.combienDIndividuAuBoutDeJour(6, 18)).isEqualTo(4);
        assertThat(day06.combienDIndividuAuBoutDeJour(7, 18)).isEqualTo(4);
    }
}