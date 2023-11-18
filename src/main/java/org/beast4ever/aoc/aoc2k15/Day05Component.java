package org.beast4ever.aoc.aoc2k15;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Day05Component extends DayResolutionComponent {

    public static enum WORDTYPE {
        NICE, NAUGHTY
    };

    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        return unimplementedResolution(inputFilePath);
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
        return unimplementedResolution(inputFilePath);
    }

    public WORDTYPE evaluateWordType(String word) {
        return WORDTYPE.NICE;
    }
}
