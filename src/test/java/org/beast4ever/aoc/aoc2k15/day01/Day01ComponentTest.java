package org.beast4ever.aoc.aoc2k15.day01;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class Day01ComponentTest {
    @Autowired
    Day01Component day01Component;

    @Test
    public void whenInputTestData01_thenResolveFirstStar() throws IOException {
        String expectedResponse = "0";

        String response = day01Component.resolveFirstStar("test-input-day01-01.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData02_thenResolveFirstStar() throws IOException {
        String expectedResponse = "0";

        String response = day01Component.resolveFirstStar("test-input-day01-02.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData03_thenResolveFirstStar() throws IOException {
        String expectedResponse = "3";

        String response = day01Component.resolveFirstStar("test-input-day01-03.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData04_thenResolveFirstStar() throws IOException {
        String expectedResponse = "3";

        String response = day01Component.resolveFirstStar("test-input-day01-04.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData05_thenResolveFirstStar() throws IOException {
        String expectedResponse = "3";

        String response = day01Component.resolveFirstStar("test-input-day01-05.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData06_thenResolveFirstStar() throws IOException {
        String expectedResponse = "-1";

        String response = day01Component.resolveFirstStar("test-input-day01-06.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData07_thenResolveFirstStar() throws IOException {
        String expectedResponse = "-1";

        String response = day01Component.resolveFirstStar("test-input-day01-07.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData08_thenResolveFirstStar() throws IOException {
        String expectedResponse = "-3";

        String response = day01Component.resolveFirstStar("test-input-day01-08.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData09_thenResolveFirstStar() throws IOException {
        String expectedResponse = "-3";

        String response = day01Component.resolveFirstStar("test-input-day01-09.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputRealData_thenResolveFirstStar() throws IOException {
        String expectedResponse = "74";

        String response = day01Component.resolveFirstStar("input-day01-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData01_thenResolveSecondStar() throws IOException {
        String expectedResponse = "1";

        String response = day01Component.resolveSecondStar("test-input-day01-10.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData02_thenResolveSecondStar() throws IOException {
        String expectedResponse = "5";

        String response = day01Component.resolveSecondStar("test-input-day01-11.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputRealData_thenResolveSecondStar() throws IOException {
        String expectedResponse = "1795";

        String response = day01Component.resolveSecondStar("input-day01-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

}
