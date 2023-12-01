package org.beast4ever.aoc.aoc2k15;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k15.day07.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Slf4j
public class Day08ComponentTest {
    @Autowired
    Day08Component day08Component;


    @Test
    public void whenInputRealData_thenResolveFirstStar() throws IOException {
        String expectedResponse = "1333";

        String response = day08Component.resolveFirstStar("input-day08-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenInputRealData_thenResolveSecondStar() throws IOException {
        String expectedResponse = "2797";

        String response = day08Component.resolveSecondStar("input-day08-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }


    @Test
    public void whenInlineData_thenNbOfCharactersOfCodeOK() throws IOException {
        String inlineInput = """
                ""
                "abc"
                "aaa\\"aaa"
                "\\x27"
                """;
        List<Long> expectedResults = Arrays.asList(2l, 5l, 10l, 6l);
        List<Long> results = new ArrayList<Long>();
        inlineInput.lines().forEach(str -> results.add(day08Component.getNumberOfCharactersOfCode(str)));

        Assertions.assertEquals(expectedResults, results);
    }

    @Test
    public void whenInlineData_thenNbOfCharactersInMemoryOK() throws IOException {
        String inlineInput = """
                ""
                "abc"
                "aaa\\"aaa"
                "\\x27"
                """;
        List<Long> expectedResults = Arrays.asList(0l, 3l, 7l, 1l);
        List<Long> results = new ArrayList<Long>();
        inlineInput.lines().forEach(str -> results.add(day08Component.getNumberOfCharactersInMemory(str)));

        Assertions.assertEquals(expectedResults, results);
    }

    @Test
    public void whenInlineData_thenDecodeOK() throws IOException {
        String inlineInput = """
                ""
                "abc"
                "aaa\\"aaa"
                "\\x27"
                """;
        List<String> expectedResults = Arrays.asList("", "abc", "aaa\"aaa", "'");
        List<String> results = new ArrayList<String>();
        inlineInput.lines().forEach(str -> results.add(day08Component.decodeSantaString(str)));

        Assertions.assertEquals(expectedResults, results);
    }

    @Test
    public void whenInlineData_thenEncodeOK() throws IOException {
        String inlineInput = """
                ""
                "abc"
                "aaa\\"aaa"
                "\\x27"
                """;
        List<String> expectedResults = Arrays.asList("\"\\\"\\\"\"", "\"\\\"abc\\\"\"", "\"\\\"aaa\\\\\\\"aaa\\\"\"", "\"\\\"\\\\x27\\\"\"");
        List<String> results = new ArrayList<String>();
        inlineInput.lines().forEach(str -> results.add(day08Component.encodeSantaString(str)));

        Assertions.assertEquals(expectedResults, results);
    }
}
