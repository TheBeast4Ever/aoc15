package org.beast4ever.aoc.aoc2k15;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class Day06ComponentTest {
    @Autowired
    Day06Component day06Component;

    @Test
    public void whenInputRealData_thenResolveFirstStar() throws IOException {
        String expectedResponse = "69";

        String response = day06Component.resolveFirstStar("input-day06-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenInputRealData_thenResolveSecondStar() throws IOException {
        String expectedResponse = "69";

        String response = day06Component.resolveSecondStar("input-day06-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

}
