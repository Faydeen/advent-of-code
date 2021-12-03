package com.fayden.advent_of_code;

import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class Template {

    static final String INPUT_FILE_PART_1 = "YEAR/dayXX_Y.txt";

    public static void main(String... args) throws IOException {
        new Part1().run();
        log.info("--------------------------------------------------------------");
        new Part2().run();
    }

    public static class Part1 {
        void run() throws IOException {
            @SuppressWarnings("UnstableApiUsage") var lignes = Resources.readLines(ClassLoader.getSystemResource(INPUT_FILE_PART_1), StandardCharsets.UTF_8);

            log.info("resultat: {}", lignes.size());
        }


    }

    public static class Part2 {
        void run() throws IOException {
            @SuppressWarnings("UnstableApiUsage") var lignes = Resources.readLines(ClassLoader.getSystemResource(INPUT_FILE_PART_1), StandardCharsets.UTF_8);

            log.info("resultat: {}", lignes.size());
        }
    }


}
