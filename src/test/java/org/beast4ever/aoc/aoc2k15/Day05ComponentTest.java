package org.beast4ever.aoc.aoc2k15;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class Day05ComponentTest {
    @Autowired
    Day05Component day05Component;

    @Test
    public void whenInputTestData01_thenResolveFirstStar() throws IOException {
        Day05Component.WORDTYPE expectedResponse = Day05Component.WORDTYPE.NICE;

        Day05Component.WORDTYPE response = day05Component.evaluateWordType("ugknbfddgicrmopn");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData02_thenResolveFirstStar() throws IOException {
        Day05Component.WORDTYPE expectedResponse = Day05Component.WORDTYPE.NICE;

        Day05Component.WORDTYPE response = day05Component.evaluateWordType("aaa");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData03_thenResolveFirstStar() throws IOException {
        Day05Component.WORDTYPE expectedResponse = Day05Component.WORDTYPE.NAUGHTY;

        Day05Component.WORDTYPE response = day05Component.evaluateWordType("jchzalrnumimnmhp");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData04_thenResolveFirstStar() throws IOException {
        Day05Component.WORDTYPE expectedResponse = Day05Component.WORDTYPE.NAUGHTY;

        Day05Component.WORDTYPE response = day05Component.evaluateWordType("haegwjzuvuyypxyu");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData05_thenResolveFirstStar() throws IOException {
        Day05Component.WORDTYPE expectedResponse = Day05Component.WORDTYPE.NAUGHTY;

        Day05Component.WORDTYPE response = day05Component.evaluateWordType("dvszwmarrgswjxmb");
        Assertions.assertEquals(expectedResponse, response);
    }
}
