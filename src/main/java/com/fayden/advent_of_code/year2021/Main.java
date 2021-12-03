package com.fayden.advent_of_code.year2021;

import com.fayden.advent_of_code.common.Day;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        for (int day = 1; day <= 25; day++) {
            log.info("Day {}: ", String.format("%02d", day));
            Day instance = (Day) Class.forName("com.fayden.advent_of_code.year2021.days.Day" + String.format("%02d", day)).getDeclaredConstructor().newInstance();
            instance.printParts();
            log.info("");
        }
    }
}
