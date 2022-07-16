package com.fayden.advent_of_code.common;

import java.util.List;

public interface PuzzleInput {
    String getContentAsString(int year, String dayNumber, int part);
    List<String> getContentAsLines(int year, String dayNumber, int part);
}
