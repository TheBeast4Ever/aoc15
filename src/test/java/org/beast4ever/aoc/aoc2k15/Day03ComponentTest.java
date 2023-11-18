package org.beast4ever.aoc.aoc2k15;

import org.beast4ever.aoc.aoc2k15.Day03Component;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class Day03ComponentTest {
    @Autowired
    Day03Component day03Component;

    @Test
    public void whenInputTestData01_thenResolveFirstStar() throws IOException {
        String expectedResponse = "2";

        String response = day03Component.resolveFirstStar("test-input-day03-01.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData02_thenResolveFirstStar() throws IOException {
        String expectedResponse = "4";

        String response = day03Component.resolveFirstStar("test-input-day03-02.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData03_thenResolveFirstStar() throws IOException {
        String expectedResponse = "2";

        String response = day03Component.resolveFirstStar("test-input-day03-03.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputRealData_thenResolveFirstStar() throws IOException {
        String expectedResponse = "2592";

        String response = day03Component.resolveFirstStar("input-day03-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData02_thenResolveSecondStar() throws IOException {
        String expectedResponse = "3";

        String response = day03Component.resolveSecondStar("test-input-day03-02.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData03_thenResolveSecondStar() throws IOException {
        String expectedResponse = "11";

        String response = day03Component.resolveSecondStar("test-input-day03-03.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData04_thenResolveSecondStar() throws IOException {
        String expectedResponse = "3";

        String response = day03Component.resolveSecondStar("test-input-day03-04.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputRealData_thenResolveSecondStar() throws IOException {
        String expectedResponse = "2360";

        String response = day03Component.resolveSecondStar("input-day03-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);

    }
}
