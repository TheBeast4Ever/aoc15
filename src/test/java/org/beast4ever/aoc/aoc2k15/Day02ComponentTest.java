package org.beast4ever.aoc.aoc2k15;

import org.beast4ever.aoc.aoc2k15.Day02Component;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class Day02ComponentTest {

    @Autowired
    Day02Component day02Component;

    @Test
    public void whenInputTestData01_thenResolveFirstStar() throws IOException {
        String expectedResponse = "58";

        String response = day02Component.resolveFirstStar("test-input-day02-01.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData02_thenResolveFirstStar() throws IOException {
        String expectedResponse = "43";

        String response = day02Component.resolveFirstStar("test-input-day02-02.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputRealData_thenResolveFirstStar() throws IOException {
        String expectedResponse = "1606483";

        String response = day02Component.resolveFirstStar("input-day02-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData01_thenResolveSecondStar() throws IOException {
        String expectedResponse = "34";

        String response = day02Component.resolveSecondStar("test-input-day02-01.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData02_thenResolveSecondStar() throws IOException {
        String expectedResponse = "14";

        String response = day02Component.resolveSecondStar("test-input-day02-02.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputRealData_thenResolveSecondStar() throws IOException {
        String expectedResponse = "3842356";

        String response = day02Component.resolveSecondStar("input-day02-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);

    }
}
