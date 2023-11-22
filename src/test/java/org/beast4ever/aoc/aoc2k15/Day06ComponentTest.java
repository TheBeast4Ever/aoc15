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
    public void whenInputTestInline1_thenNbOfLightOK() throws IOException {
        Long expectedResponse = 1000000l;
        String inlineTestInput = "turn on 0,0 through 999,999";

        Day06Component.Instruction instruction = new Day06Component.Instruction(inlineTestInput);
        Day06Component.ChristmasLightGrid grid = new Day06Component.ChristmasLightGrid(1000, Boolean.FALSE);

        grid.followInstruction(instruction);

        Long response = grid.getSumOfBrightness();

        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenInputTestInline2_thenNbOfLightOK() throws IOException {
        Long expectedResponse = 1000l;
        String inlineTestInput = "toggle 0,0 through 999,0";

        Day06Component.Instruction instruction = new Day06Component.Instruction(inlineTestInput);
        Day06Component.ChristmasLightGrid grid = new Day06Component.ChristmasLightGrid(1000, Boolean.FALSE);

        grid.followInstruction(instruction);

        Long response = grid.getSumOfBrightness();

        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenInputTestInline3_thenNbOfLightOK() throws IOException {
        Long expectedResponse = 999996l;
        String inlineFirstTestInput = "turn on 0,0 through 999,999";
        String inlineSecondTestInput = "turn off 499,499 through 500,500";

        Day06Component.Instruction instruction1 = new Day06Component.Instruction(inlineFirstTestInput);
        Day06Component.Instruction instruction2 = new Day06Component.Instruction(inlineSecondTestInput);
        Day06Component.ChristmasLightGrid grid = new Day06Component.ChristmasLightGrid(1000, Boolean.FALSE);

        grid.followInstruction(instruction1);
        grid.followInstruction(instruction2);

        Long response = grid.getSumOfBrightness();

        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenInputRealData_thenResolveFirstStar() throws IOException {
        String expectedResponse = "569999";

        String response = day06Component.resolveFirstStar("input-day06-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenInputRealData_thenResolveSecondStar() throws IOException {
        String expectedResponse = "17836115";

        String response = day06Component.resolveSecondStar("input-day06-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

}
