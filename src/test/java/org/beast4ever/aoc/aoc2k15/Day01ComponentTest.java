package org.beast4ever.aoc.aoc2k15;
import org.beast4ever.aoc.aoc2k15.Day01Component;
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
        Long expectedResponse = 0l;

        Long response = day01Component.browseBuildingFloors("(())");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData02_thenResolveFirstStar() throws IOException {
        Long expectedResponse = 0l;

        Long response = day01Component.browseBuildingFloors("()()");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData03_thenResolveFirstStar() throws IOException {
        Long expectedResponse = 3l;

        Long response = day01Component.browseBuildingFloors("(((");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData04_thenResolveFirstStar() throws IOException {
        Long expectedResponse = 3l;

        Long response = day01Component.browseBuildingFloors("(()(()(");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData05_thenResolveFirstStar() throws IOException {
        Long expectedResponse = 3l;

        Long response = day01Component.browseBuildingFloors("))(((((");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData06_thenResolveFirstStar() throws IOException {
        Long expectedResponse = -1l;

        Long response = day01Component.browseBuildingFloors("())");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData07_thenResolveFirstStar() throws IOException {
        Long expectedResponse = -1l;

        Long response = day01Component.browseBuildingFloors("))(");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData08_thenResolveFirstStar() throws IOException {
        Long expectedResponse = -3l;
        Long response = day01Component.browseBuildingFloors(")))");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData09_thenResolveFirstStar() throws IOException {
        Long expectedResponse = -3l;

        Long response = day01Component.browseBuildingFloors(")())())");
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
        Long expectedResponse = 1l;

        Long response = day01Component.findFirstBasementPosition(")");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData02_thenResolveSecondStar() throws IOException {
        Long expectedResponse = 5l;

        Long response = day01Component.findFirstBasementPosition("()()))");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputRealData_thenResolveSecondStar() throws IOException {
        String expectedResponse = "1795";

        String response = day01Component.resolveSecondStar("input-day01-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);

    }

}
