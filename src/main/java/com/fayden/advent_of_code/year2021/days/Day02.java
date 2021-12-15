package com.fayden.advent_of_code.year2021.days;

import com.fayden.advent_of_code.year2021.Day2021;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Day02 extends Day2021 {

    public Day02() {
        super("02");
    }

    public static void main(String... args) {
        new Day02().printParts();
    }

    public Object part1() {
        final List<String> lines = getInputLines(1)
                .toList();
        final SousMarinPart1 sousMarinPart1 = new SousMarinPart1();
        for (String commande : lines) {
            sousMarinPart1.deplacer(commande);
        }
        log.debug("La position du sous-marin est :\n\t profondeur: {}\n\t horizontal: {}", sousMarinPart1.getProfondeur(), sousMarinPart1.getHorizontal());
        return sousMarinPart1.getHorizontal() * sousMarinPart1.getProfondeur();
    }

    public Object part2() {
        final List<String> lines = getInputLines(1)
                .toList();
        final SousMarinPart2 sousMarinPart2 = new SousMarinPart2();
        for (String commande : lines) {
            sousMarinPart2.deplacer(commande);
        }
        log.debug("La position du sous-marin est :\n\t profondeur: {}\n\t horizontal: {}", sousMarinPart2.getProfondeur(), sousMarinPart2.getHorizontal());
        return sousMarinPart2.getHorizontal() * sousMarinPart2.getProfondeur();
    }

    @Data
    public static class SousMarinPart1 {
        private int profondeur = 0;
        private int horizontal = 0;

        void deplacer(String commande) {
            final String[] s = commande.split(" ");
            String direction = s[0];
            int distance = Integer.parseInt(s[1]);
            switch (direction) {
                case "forward" -> horizontal += distance;
                case "down" -> profondeur += distance;
                case "up" -> profondeur -= distance;
                default -> throw new IllegalArgumentException("Commande " + direction + " inconnue");
            }
        }
    }

    @Data
    public static class SousMarinPart2 {
        private int profondeur = 0;
        private int horizontal = 0;
        private int cible = 0;

        public void deplacer(String commande) {
            final String[] s = commande.split(" ");
            String direction = s[0];
            int distance = Integer.parseInt(s[1]);
            switch (direction) {
                case "forward" -> {
                    horizontal += distance;
                    profondeur += cible * distance;
                }
                case "down" -> cible += distance;
                case "up" -> cible -= distance;
                default -> throw new IllegalArgumentException("Commande " + direction + " inconnue");
            }
        }
    }
}
