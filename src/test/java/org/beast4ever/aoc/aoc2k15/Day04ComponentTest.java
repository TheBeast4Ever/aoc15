package org.beast4ever.aoc.aoc2k15;

import org.beast4ever.aoc.aoc2k15.Day04Component;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class Day04ComponentTest {
    @Autowired
    Day04Component day04Component;

    @Test
    public void whenInputTestData01_thenResolveFirstStar() throws IOException {
        String expectedResponse = "609043";

        String response = day04Component.resolveFirstStar("test-input-day04-01.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData02_thenResolveFirstStar() throws IOException {
        String expectedResponse = "1048970";

        String response = day04Component.resolveFirstStar("test-input-day04-02.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputRealData_thenResolveFirstStar() throws IOException {
        String expectedResponse = "346386";

        String response = day04Component.resolveFirstStar("input-day04-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputRealData_thenResolveSecondStar() throws IOException {
        String expectedResponse = "9958218";

        String response = day04Component.resolveSecondStar("input-day04-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);

    }
}
