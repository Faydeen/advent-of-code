package com.fayden.advent_of_code.year2021.days;

import com.fayden.advent_of_code.year2021.Day2021;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Day04 extends Day2021 {

    public Day04() {
        super("04");
    }

    public static void main(String... args) {
        new Day04().printParts();
    }

    public Object part1() {
        final List<String> lines = getInputLines(1).toList();
        final List<Integer> tirage = Arrays.stream(lines.get(0).split(",")).map(Integer::parseInt).toList();
        final ArrayList<BingoGrid> bingoGrids = generateBingoGrids(lines);
        return winningBingoBoardScore(tirage, bingoGrids);
    }

    public Object part2() {
        final List<String> lines = getInputLines(1).toList();
        final List<Integer> tirage = Arrays.stream(lines.get(0).split(",")).map(Integer::parseInt).toList();
        final ArrayList<BingoGrid> bingoGrids = generateBingoGrids(lines);
        return loosingBingoBoardScore(0, tirage, bingoGrids);
    }

    protected int loosingBingoBoardScore(int index, List<Integer> tirageList, List<BingoGrid> bingoGrids) {
        final Integer tirage = tirageList.get(index);
        bingoGrids.forEach(bingoGrid -> bingoGrid.validerNumero(tirage));
        final List<BingoGrid> uncompletedGridThisTurn = bingoGrids.stream().filter(bingoGrid -> !bingoGrid.isCompleted).toList();
        if (uncompletedGridThisTurn.isEmpty()) {
            return bingoGrids.get(0).computeResultat(tirage);
        }
        return loosingBingoBoardScore(index + 1, tirageList, uncompletedGridThisTurn);
    }

    protected int winningBingoBoardScore(List<Integer> tirage, List<BingoGrid> bingoGrids) {
        int resultat = 0;
        for (int i = 0; i < tirage.size(); i++) {
            for (BingoGrid bingoGrid : bingoGrids) {
                bingoGrid.validerNumero(tirage.get(i));
                if (bingoGrid.isCompleted && bingoGrid.score > resultat) {
                    resultat = bingoGrid.score;
                }
            }
            if (resultat != 0) break;
        }
        return resultat;
    }

    private ArrayList<BingoGrid> generateBingoGrids(List<String> lines) {
        // TODO later : find a beater way.
        // Some isue dealing with stream order.
        final List<String> gridsInput = lines.subList(1, lines.size());
        List<List<Integer>> grid = new ArrayList<>();
        int id = 0;
        final ArrayList<BingoGrid> bingoGrids = new ArrayList<>();
        for (int i = 0; i < gridsInput.size(); i++) {
            if (gridsInput.get(i).isEmpty()) {
                if (!grid.isEmpty()) {
                    bingoGrids.add(new BingoGrid(id, grid));
                    id++;
                }
                grid = new ArrayList<>();
            } else {
                grid.add(
                        Arrays.stream(gridsInput.get(i).split(" "))
                                .filter(s -> !s.isBlank())
                                .map(Integer::parseInt)
                                .toList()
                );
            }
        }
        return bingoGrids;
    }


    public static class BingoGrid {
        public static final int GRID_SIZE = 5;
        int gridId;
        BingoCell[][] grid;
        boolean isCompleted;
        int score;

        public BingoGrid(int gridId, List<List<Integer>> gridInput) {
            this.gridId = gridId;
            this.grid = new BingoCell[GRID_SIZE][GRID_SIZE];
            for (int i = 0; i < gridInput.size(); i++) {
                final List<Integer> line = gridInput.get(i);
                for (int j = 0; j < line.size(); j++) {
                    grid[i][j] = new BingoCell(line.get(j));
                }
            }
        }

        public boolean validerNumero(Integer numeroTire) {
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    grid[i][j].validerNumero(numeroTire);
                }
            }
            if (checkIfCompleted()) {
                this.isCompleted = true;
                this.score = computeResultat(numeroTire);
            }
            return this.isCompleted;

        }

        private int computeResultat(Integer numeroTire) {
            int sumOfAllUnmarkedCell = 0;
            for (int row = 0; row < GRID_SIZE; row++) {
                for (int column = 0; column < GRID_SIZE; column++) {
                    if (!grid[row][column].marked) {
                        sumOfAllUnmarkedCell += grid[row][column].value;
                    }
                }
            }
            return sumOfAllUnmarkedCell * numeroTire;
        }

        private boolean checkIfCompleted() {
            for (int i = 0; i < GRID_SIZE; i++) {
                if (checkIfLineIsCompleted(i)) return true;
                if (checkIfColumnIsCompleted(i)) return true;
            }
            return false;
        }

        private boolean checkIfColumnIsCompleted(int column) {
            boolean result = true;
            for (int row = 0; row < GRID_SIZE; row++) {
                result = result && grid[row][column].marked;
            }
            return result;
        }

        private boolean checkIfLineIsCompleted(int row) {
            boolean result = true;
            for (int column = 0; column < GRID_SIZE; column++) {
                result = result && grid[row][column].marked;
            }
            return result;
        }

        public void print() {
            log.info("----------------------------------------------------------------------------------------");

            log.info("Grid {}:  {}", gridId, checkIfCompleted() ? "COMPLETED" : "");
            for (int row = 0; row < GRID_SIZE; row++) {
                final BingoCell[] bingoCells = grid[row];
                final String displayableRow = Arrays.stream(bingoCells).map(bingoCell -> (bingoCell.marked ? "*" : " ") + String.format("%02d", bingoCell.value) + (bingoCell.marked ? "*" : " ")).collect(Collectors.joining(","));
                log.info(displayableRow);
            }
        }

        public static class BingoCell {
            boolean marked;
            Integer value;

            public BingoCell(Integer value) {
                this.value = value;
                this.marked = false;
            }

            public boolean validerNumero(Integer numero) {
                if (numero.equals(this.value)) {
                    this.marked = true;
                    return true;
                }
                return false;
            }
        }
    }
}
