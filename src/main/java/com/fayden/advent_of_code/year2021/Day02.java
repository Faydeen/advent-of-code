package com.fayden.advent_of_code.year2021;

import com.google.common.io.Resources;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class Day02 {

    static final String INPUT_FILE_PART_1 = "2021/day02_1.txt";

    public static void main(String... args) throws IOException {
        new Part1().run();
        log.info("\n--------------------------------------------------------------\n");
        new Part2().run();
    }

    public static class Part1 {
        void run() throws IOException {
            var lignes = Resources.readLines(ClassLoader.getSystemResource(INPUT_FILE_PART_1), StandardCharsets.UTF_8);
            final SousMarin sousMarin = new SousMarin();
            for (String commande : lignes) {
                sousMarin.deplacer(commande);
            }
            log.info("La position du sous-marin est :\n\t profondeur: {}\n\t horizontal: {}\n\tresultat: {}", sousMarin.getProfondeur(), sousMarin.getHorizontal(), sousMarin.getHorizontal() * sousMarin.getProfondeur());
        }

        @Data
        public static class SousMarin {
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
    }

    public static class Part2 {
        void run() throws IOException {
            var lignes = Resources.readLines(ClassLoader.getSystemResource(INPUT_FILE_PART_1), StandardCharsets.UTF_8);
            final SousMarin sousMarin = new SousMarin();
            for (String commande : lignes) {
                sousMarin.deplacer(commande);
            }
            log.info("La position du sous-marin est :\n\t profondeur: {}\n\t horizontal: {}\n\t resultat: {}", sousMarin.getProfondeur(), sousMarin.getHorizontal(), sousMarin.getHorizontal() * sousMarin.getProfondeur());
        }

        @Data
        public static class SousMarin {
            private int profondeur = 0;
            private int horizontal = 0;
            private int cible = 0;

            void deplacer(String commande) {
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


}
