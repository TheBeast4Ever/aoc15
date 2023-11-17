package org.beast4ever.aoc.aoc2k15.day03;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k15.DayResolutionComponent;
import org.beast4ever.aoc.aoc2k15.FileUtilityParser;
import org.beast4ever.aoc.aoc2k15.day02.Day02Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Component
@Slf4j
public class Day03Component extends DayResolutionComponent {
    @Autowired
    private FileUtilityParser fileUtilityParser;

    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        String inputEntry = fileUtilityParser.readFirstLine(inputFilePath);
        Map<Point, House> houses = new HashMap<Point, House>();
        Point currentSantaPosition = new Point(0,0);
        houses.put(currentSantaPosition, new House(currentSantaPosition, true));

        for (int i=0; i<inputEntry.length(); i++) {
            currentSantaPosition = currentSantaPosition.moveTo(inputEntry.charAt(i));
            if (!houses.containsKey(currentSantaPosition)) {
                houses.put(currentSantaPosition, new House(currentSantaPosition, true));
            }
        }


        Long nbOfHousesWithAtLeastOnePresent = houses.values().stream().filter(h -> h.isPresentDelivered).count();

       return "" + nbOfHousesWithAtLeastOnePresent;
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
        String inputEntry = fileUtilityParser.readFirstLine(inputFilePath);
        Map<Point, House> houses = new HashMap<Point, House>();
        Point currentSantaPosition = new Point(0,0);
        Point currentRoboSantaPosition = new Point(0,0);
        houses.put(currentSantaPosition, new House(currentSantaPosition, true));

        for (int i=0; i<inputEntry.length(); i++) {
            if ((i % 2)==0) {
                currentSantaPosition = currentSantaPosition.moveTo(inputEntry.charAt(i));
                if (!houses.containsKey(currentSantaPosition)) {
                    houses.put(currentSantaPosition, new House(currentSantaPosition, true));
                }
            } else {
                currentRoboSantaPosition = currentRoboSantaPosition.moveTo(inputEntry.charAt(i));
                if (!houses.containsKey(currentRoboSantaPosition)) {
                    houses.put(currentRoboSantaPosition, new House(currentRoboSantaPosition, true));
                }
            }

        }


        Long nbOfHousesWithAtLeastOnePresent = houses.values().stream().filter(h -> h.isPresentDelivered).count();

        return "" + nbOfHousesWithAtLeastOnePresent;
    }

    private class House {
        private Point location;

        private Boolean isPresentDelivered;

        public House(Point point, boolean b) {
            location = point ;
            isPresentDelivered = b;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof House) {
                return ((House) obj).getLocation().equals(this.location);
            } else {
                return false;
            }
        }

        public Point getLocation() {
            return location;
        }

        public void setLocation(Point location) {
            this.location = location;
        }

        public Boolean getPresentDelivered() {
            return isPresentDelivered;
        }

        public void setPresentDelivered(Boolean presentDelivered) {
            isPresentDelivered = presentDelivered;
        }
    }

    public record Point(int x, int y) {
        Point moveTo(int direction) {
            if (direction == 62) {
                return new Point(x+1,y);
            } else if (direction == 94) {
                return new Point(x,y+1);
            } else if (direction == 60) {
                return new Point(x-1,y);
            } else if (direction == 118) {
                return new Point(x,y-1);
            } else {
                log.warn("unrecognized direction, ascii code : " + direction);
                return new Point(x,y);
            }
        }
    }
}
