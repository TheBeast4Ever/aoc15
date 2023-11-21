package org.beast4ever.aoc.aoc2k15;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class Day06Component extends DayResolutionComponent {

    @Autowired
    private FileUtilityParser fileUtilityParser;

    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        return unimplementedResolution(inputFilePath);
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
       return unimplementedResolution(inputFilePath);
    }

}
