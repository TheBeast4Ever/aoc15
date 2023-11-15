package org.beast4ever.aoc.aoc2k15.day02;

import org.beast4ever.aoc.aoc2k15.DayResolutionComponent;
import org.beast4ever.aoc.aoc2k15.FileUtilityParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.StringTokenizer;
import java.util.stream.Stream;

@Component
public class Day02Component extends DayResolutionComponent {
    @Autowired
    private FileUtilityParser fileUtilityParser;

    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        List<Long> wrappingPaperSurfaces = new ArrayList<Long>();
        List<String> boxesSpecifications = fileUtilityParser.readFileSplitByLines(inputFilePath);
        List<RightRectangularPrism> rightRectangularPrismList = new ArrayList<RightRectangularPrism>();

        boxesSpecifications.stream().forEach(boxSpec -> {
            StringTokenizer strTokenizer = new StringTokenizer(boxSpec, "x");
            if (strTokenizer.countTokens()==3) {
                rightRectangularPrismList.add(new RightRectangularPrism(Integer.parseInt(strTokenizer.nextToken()), Integer.parseInt(strTokenizer.nextToken()), Integer.parseInt(strTokenizer.nextToken())));
            }
        });

        rightRectangularPrismList.stream().forEach(rightRectangularPrism -> {
            wrappingPaperSurfaces.add(rightRectangularPrism.getSurfaceArea() + rightRectangularPrism.getSmallestSurfaceArea().getAsLong());
        });

        return "" + wrappingPaperSurfaces.stream().mapToLong(Long::valueOf).sum();
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
       return unimplementedResolution(inputFilePath);
    }

    record RightRectangularPrism(Integer length, Integer width, Integer height) {
        public Long getSurfaceArea() {
            return Long.valueOf(2*(length*width)+2*(length*height)+2*(width*height));
        }

        public OptionalLong getSmallestSurfaceArea() {
            return Stream.of(Long.valueOf(length*width), Long.valueOf(length*height), Long.valueOf(width*height)).mapToLong(Long::valueOf).min();
        }
    }
}
