package org.beast4ever.aoc.aoc2k15.day01;

import org.beast4ever.aoc.aoc2k15.DayResolutionComponent;
import org.beast4ever.aoc.aoc2k15.FileUtilityParser;
import org.beast4ever.aoc.aoc2k15.ForEachWithBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

@Component
public class Day01Component extends DayResolutionComponent {

    @Autowired
    private FileUtilityParser fileUtilityParser;

    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        String inputEntry = fileUtilityParser.readFirstLine(inputFilePath);
        String result = "" + browseBuildingFloors(inputEntry);
        return result;
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
        String inputEntry = fileUtilityParser.readFirstLine(inputFilePath);
        String result = "" + findFirstBasementPosition(inputEntry);
        return result;
    }

    private Long browseBuildingFloors(String inputEntry) {
        Long currentFloor = 0l;

        Long ascendingMoves = inputEntry.codePoints().mapToObj(c -> (char) c).filter(c -> c == '(').count();
        Long descendingMoves = inputEntry.codePoints().mapToObj(c -> (char) c).filter(c -> c == ')').count();;

        currentFloor = ascendingMoves - descendingMoves;

        return currentFloor;
    }

    private Long findFirstBasementPosition(String inputEntry) {
        AtomicReference<Long> finalCurrentFloor = new AtomicReference<>(0l);
        AtomicReference<Long> firstBasementPosition = new AtomicReference<>(0l);
        ForEachWithBreaker.forEach( inputEntry.codePoints().mapToObj(c -> (char) c), (currChar, breaker) -> {
            firstBasementPosition.getAndSet(firstBasementPosition.get() + 1);
            if (currChar == '(') {
                finalCurrentFloor.getAndSet(finalCurrentFloor.get() + 1);
            } else if (currChar == ')') {
                finalCurrentFloor.getAndSet(finalCurrentFloor.get() - 1);
            }
            if (finalCurrentFloor.get()<0) {
                breaker.stop();
            }
        });

        return firstBasementPosition.get();
    }
}
