package com.fayden.advent_of_code.year2021.days;

import com.fayden.advent_of_code.year2021.Day2021;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Slf4j
public class Day06 extends Day2021 {

    public Day06() {
        super("06");
    }

    public static void main(String... args) {
        new Day06().printParts();
    }

    public Object part1() {
        List<LanternFish> lanternfishList = Arrays.asList(getInputLines(1).toList().get(0).split(","))
                .stream()
                .map(Integer::parseInt)
                .map(LanternFish::new)
                .toList();
        List<LanternFish> lanternFish = new ArrayList<>(lanternfishList);
        for (int i = 0; i < 80; i++) {
            lanternFish = lanternFish.stream().flatMap(s -> s.spendADay().stream()).toList();
        }
        return lanternFish.size();
    }

    @Data
    class LanternFish {
        static final int DAY_TO_REPRODUCE = 6;
        static final int DAY_TO_REPRODUCE_FIRST_TIME = 8;
        int dayBeforeReproduction;

        public LanternFish() {
            this.dayBeforeReproduction = DAY_TO_REPRODUCE_FIRST_TIME;
        }

        public LanternFish(int dayBeforeReproduction) {
            this.dayBeforeReproduction = dayBeforeReproduction;
        }

        List<LanternFish> spendADay() {
            if (dayBeforeReproduction == 0) {
                return Arrays.asList(new LanternFish(DAY_TO_REPRODUCE), new LanternFish());
            } else {
                return List.of(new LanternFish(this.dayBeforeReproduction - 1));
            }
        }
    }

    public Object part2() {
        return Arrays.asList(getInputLines(1).toList().get(0).split(","))
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .mapToDouble(integerLongEntry -> integerLongEntry.getValue() * combienDIndividuAuBoutDeJour(integerLongEntry.getKey(), 256))
                .sum();
    }

    public long combienDIndividuAuBoutDeJour(int nombreDeJourInitialAvantPremierEnfant, int nombreDeJour) {
        long resultat = 0;
        long nombreReproductionIndividu = nombreDeReproduction(nombreDeJourInitialAvantPremierEnfant, nombreDeJour);
        for (int i = 0; i < nombreReproductionIndividu; i++) {
            resultat += combienDIndividuAuBoutDeJour(6, nombreDeJour - (nombreDeJourInitialAvantPremierEnfant + 1) - 2 - (7 * i));
        }
        return 1 + resultat;
    }

    private long nombreDeReproduction(int nombreDeJourInitialAvantPremierEnfant, int nombreDeJour) {
        return (long) Math.ceil((nombreDeJour - nombreDeJourInitialAvantPremierEnfant) / 7f);
    }
}
