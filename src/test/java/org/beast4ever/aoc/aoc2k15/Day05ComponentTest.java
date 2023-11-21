package org.beast4ever.aoc.aoc2k15;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class Day05ComponentTest {
    @Autowired
    Day05Component day05Component;

    @Test
    public void whenInputTestInline_thenRuleThreeVoyelsOK() throws IOException {
        Boolean expectedResponse = Boolean.TRUE;
        List<String> inlineTestInput = Arrays.asList("aei", "xazegov", "aeiouaeiouaeiou");
        Day05Component.Rule threeVoyelsRule = new Day05Component.AtLeastThreeVoyelsRule();

        inlineTestInput.stream().forEach(input -> {
            Boolean response = threeVoyelsRule.isSatisfied(input);
            Assertions.assertEquals(expectedResponse, response);
        });
    }

    @Test
    public void whenInputTestInline_thenRuleOneLetterTwiceOK() throws IOException {
        Boolean expectedResponse = Boolean.TRUE;
        List<String> inlineTestInput = Arrays.asList("xx", "abcdde", "aabbccdd");
        Day05Component.Rule threeVoyelsRule = new Day05Component.AtLeastOneLetterTwiceInRowRule();

        inlineTestInput.stream().forEach(input -> {
            Boolean response = threeVoyelsRule.isSatisfied(input);
            Assertions.assertEquals(expectedResponse, response);
        });
    }

    @Test
    public void whenInputTestInline_thenRuleForbiddenChunkOK() throws IOException {
        Boolean expectedResponse = Boolean.TRUE;
        List<String> inlineTestInput = Arrays.asList("polux", "david", "xor");
        Day05Component.Rule threeVoyelsRule = new Day05Component.DontContainsForbiddenChunksRule();

        inlineTestInput.stream().forEach(input -> {
            Boolean response = threeVoyelsRule.isSatisfied(input);
            Assertions.assertEquals(expectedResponse, response);
        });
    }

    @Test
    public void whenInputTestData01_thenResolveFirstStar() throws IOException {
        Day05Component.WORDTYPE expectedResponse = Day05Component.WORDTYPE.NICE;

        Day05Component.WORDTYPE response = day05Component.evaluateWordTypeSimpleRules("ugknbfddgicrmopn");
        Assertions.assertEquals(expectedResponse, response);

    }
    @Test
    public void whenInputTestData02_thenResolveFirstStar() throws IOException {
        Day05Component.WORDTYPE expectedResponse = Day05Component.WORDTYPE.NICE;

        Day05Component.WORDTYPE response = day05Component.evaluateWordTypeSimpleRules("aaa");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData03_thenResolveFirstStar() throws IOException {
        Day05Component.WORDTYPE expectedResponse = Day05Component.WORDTYPE.NAUGHTY;

        Day05Component.WORDTYPE response = day05Component.evaluateWordTypeSimpleRules("jchzalrnumimnmhp");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData04_thenResolveFirstStar() throws IOException {
        Day05Component.WORDTYPE expectedResponse = Day05Component.WORDTYPE.NAUGHTY;

        Day05Component.WORDTYPE response = day05Component.evaluateWordTypeSimpleRules("haegwjzuvuyypxyu");
        Assertions.assertEquals(expectedResponse, response);

    }

    @Test
    public void whenInputTestData05_thenResolveFirstStar() throws IOException {
        Day05Component.WORDTYPE expectedResponse = Day05Component.WORDTYPE.NAUGHTY;

        Day05Component.WORDTYPE response = day05Component.evaluateWordTypeSimpleRules("dvszwmarrgswjxmb");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenInputReal_thenResolveFirstStar() throws IOException {
        String expectedResponse = "238";

        String response = day05Component.resolveFirstStar("input-day05-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenInputTestInline_thenRuleTwoLettersOK() throws IOException {
        Boolean expectedResponse = Boolean.TRUE;
        List<String> inlineTestInput = Arrays.asList("xyxy", "aabcdefgaa");
        Day05Component.Rule twoLettersRule = new Day05Component.TwoLettersAppearsTwice();

        inlineTestInput.stream().forEach(input -> {
            Boolean response = twoLettersRule.isSatisfied(input);
            Assertions.assertEquals(expectedResponse, response);
        });
    }

    @Test
    public void whenInputTestInline_thenRuleTwoLettersKO() throws IOException {
        Boolean expectedResponse = Boolean.FALSE;
        List<String> inlineTestInput = Arrays.asList("aaa");
        Day05Component.Rule twoLettersRule = new Day05Component.TwoLettersAppearsTwice();

        inlineTestInput.stream().forEach(input -> {
            Boolean response = twoLettersRule.isSatisfied(input);
            Assertions.assertEquals(expectedResponse, response);
        });
    }

    @Test
    public void whenInputTestInline_thenRuleOneLetterOK() throws IOException {
        Boolean expectedResponse = Boolean.TRUE;
        List<String> inlineTestInput = Arrays.asList("xyx", "abcdefeghi", "aaa");
        Day05Component.Rule oneLetterRule = new Day05Component.OneLetterRepeatsWithOneLetterBetween();

        inlineTestInput.stream().forEach(input -> {
            Boolean response = oneLetterRule.isSatisfied(input);
            Assertions.assertEquals(expectedResponse, response);
        });
    }

    @Test
    public void whenInputTestData01_thenResolveSecondStar() throws IOException {
        Day05Component.WORDTYPE expectedResponse = Day05Component.WORDTYPE.NICE;

        Day05Component.WORDTYPE response = day05Component.evaluateWordTypeAdvancedRules("qjhvhtzxzqqjkmpb");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenInputTestData02_thenResolveSecondStar() throws IOException {
        Day05Component.WORDTYPE expectedResponse = Day05Component.WORDTYPE.NICE;

        Day05Component.WORDTYPE response = day05Component.evaluateWordTypeAdvancedRules("xxyxx");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenInputTestData03_thenResolveSecondStar() throws IOException {
        Day05Component.WORDTYPE expectedResponse = Day05Component.WORDTYPE.NAUGHTY;

        Day05Component.WORDTYPE response = day05Component.evaluateWordTypeAdvancedRules("uurcxstgmygtbstg");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenInputTestData04_thenResolveSecondStar() throws IOException {
        Day05Component.WORDTYPE expectedResponse = Day05Component.WORDTYPE.NAUGHTY;

        Day05Component.WORDTYPE response = day05Component.evaluateWordTypeAdvancedRules("ieodomkazucvgmuy");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenInputRealData_thenResolveSecondStar() throws IOException {
        String expectedResponse = "69";

        String response = day05Component.resolveSecondStar("input-day05-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }
}
