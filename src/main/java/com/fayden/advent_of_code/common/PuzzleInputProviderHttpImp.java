package com.fayden.advent_of_code.common;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class PuzzleInputProviderHttpImp implements PuzzleInputProvider {
    private final static String PUZZLE_INPUT_URL = "https://adventofcode.com/%d/day/%s/input";
    // Have to login to get puzzle input !
    private final HttpClient httpClient;

    public PuzzleInputProviderHttpImp() {
        this.httpClient = HttpClient.newBuilder().build();
    }

    @Override
    public String getInput(int year, String dayNumber, int part) {
        try {
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(PUZZLE_INPUT_URL.formatted(year, Integer.parseInt(dayNumber))))
                    .GET()
                    .build();
            final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
