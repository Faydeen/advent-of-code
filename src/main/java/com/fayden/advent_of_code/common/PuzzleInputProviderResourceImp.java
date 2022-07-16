package com.fayden.advent_of_code.common;

import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class PuzzleInputProviderResourceImp implements PuzzleInputProvider {

    public String getResourceFilePath(int year, String dayNumber, int part) {
        return year + "/day" + dayNumber + "_" + part + ".txt";
    }


    @Override
    public String getInput(int year, String dayNumber, int part) {
        try {
            return Resources.toString(ClassLoader.getSystemResource(getResourceFilePath(year, dayNumber, part)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
