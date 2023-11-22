package org.beast4ever.aoc.aoc2k15;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

@Component
@Slf4j
public class Day06Component extends DayResolutionComponent {

    @Autowired
    private FileUtilityParser fileUtilityParser;

    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        ChristmasLightGrid grid = new ChristmasLightGrid(1000, Boolean.FALSE);
        List<String> instructionsList = fileUtilityParser.readFileSplitByLines(inputFilePath);
        instructionsList.stream().forEach(textInstruction -> {
            Instruction instruction = new Instruction(textInstruction);
            grid.followInstruction(instruction);
        });

        return "" + grid.getSumOfBrightness();
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
        ChristmasLightGrid grid = new ChristmasLightGrid(1000, Boolean.TRUE);
        List<String> instructionsList = fileUtilityParser.readFileSplitByLines(inputFilePath);
        instructionsList.stream().forEach(textInstruction -> {
            Instruction instruction = new Instruction(textInstruction);
            grid.followInstruction(instruction);
        });

        return "" + grid.getSumOfBrightness();
    }

    private record Coords(int x, int y){};

    public static class ChristmasLightGrid {
        private Light[][] arrayOfLights;

        private Boolean hasBrightnessLights = Boolean.FALSE;

        public ChristmasLightGrid(Integer size, Boolean hasBrightnessLights) {
            this.hasBrightnessLights = hasBrightnessLights;
            if (!hasBrightnessLights) {
                arrayOfLights = new SimpleLight[size][size];
            } else {
                arrayOfLights = new AdvancedLight[size][size];
            }

            for(int x=0; x<size; x++) {
                for (int y=0; y<size; y++) {
                    if (!hasBrightnessLights) {
                        arrayOfLights[x][y] = new SimpleLight();
                    } else {
                        arrayOfLights[x][y] = new AdvancedLight();
                    }
                }
            }
        }

        public Long getSumOfBrightness() {
            if (!hasBrightnessLights) {
                return Stream.of(arrayOfLights).flatMap(Stream::of).toList().stream().filter(light -> light.getBrightness()>0).count();
            } else {
                return Stream.of(arrayOfLights).flatMap(Stream::of).toList().stream().map(light -> light.getBrightness()).reduce(0l, Long::sum);
            }
        }

        public void followInstruction(Instruction instruction) {
            Coords from = instruction.getStartsFrom();
            Coords to = instruction.getEndsTo();
            for(int x=from.x(); x <= to.x(); x++) {
                for(int y=from.y(); y <= to.y(); y++) {
                    if (arrayOfLights[x][y] == null) {
                        log.error("Null entry : {},{}", x, y);
                    } else {
                        switch (instruction.action) {
                            case TURN_ON -> arrayOfLights[x][y].turnOn();
                            case TURN_OFF -> arrayOfLights[x][y].turnOff();
                            case TOGGLE -> arrayOfLights[x][y].toggle();
                        }
                    }

                }
            }
        }
    }

    public static class Instruction {
        public static enum ACTIONS {
            TURN_ON, TURN_OFF, TOGGLE
        };

        private ACTIONS action;
        private Coords startsFrom;
        private Coords endsTo;

        public ACTIONS getAction() {
            return action;
        }

        public Coords getStartsFrom() {
            return startsFrom;
        }

        public Coords getEndsTo() {
            return endsTo;
        }

        public Instruction(String textCommand) {
            StringTokenizer strTokenize = new StringTokenizer(textCommand, " ");

            if (strTokenize.countTokens() == 4) { // Toggle command
                action = ACTIONS.TOGGLE;
                strTokenize.nextToken(); // Toggle word at position 1
                startsFrom = parseCoordsText(strTokenize.nextToken()); // Start coords at position 2
                strTokenize.nextToken(); // Through word at position 3
                endsTo = parseCoordsText(strTokenize.nextToken()); // Ends coords at position 4
            } else if  (strTokenize.countTokens() == 5) {
                strTokenize.nextToken(); // Turn word at position 1
                String onOrOffWord = strTokenize.nextToken(); // Turn action at position 2
                if (onOrOffWord.equals("on")) {
                    action = ACTIONS.TURN_ON;
                } else if (onOrOffWord.equals("off")) {
                    action = ACTIONS.TURN_OFF;
                } else {
                    log.error ("unrecognized action : " + onOrOffWord);
                }
                startsFrom = parseCoordsText(strTokenize.nextToken()); // Start coords at position 3
                strTokenize.nextToken(); // Through text at position 4
                endsTo = parseCoordsText(strTokenize.nextToken()); // Ends coords at position 5
            } else {
                log.error("Text command unparsable : " + textCommand);
            }
        }

        private Coords parseCoordsText (String coordsText) {
            StringTokenizer strTokenize = new StringTokenizer(coordsText, ",");
            if (strTokenize.countTokens()==2) {
                Integer x = Integer.parseInt(strTokenize.nextToken());
                Integer y = Integer.parseInt(strTokenize.nextToken());
                return new Coords(x, y);
            } else {
                log.error("Unrecognized coordinates : " + coordsText);
                return null;
            }

        }
    }

    public interface Light {
        public Long getBrightness();

        public void turnOn();

        public void turnOff();

        public void toggle();
    }

    public static class SimpleLight implements Light {
        private Boolean on = Boolean.FALSE;
        public void turnOn() {
            on = Boolean.TRUE;
        }
        public void turnOff() {
            on = Boolean.FALSE;
        }

        public void toggle() {
            if (on) {
                turnOff();
            } else {
                turnOn();
            }
        }

        public Long getBrightness() {
            if (on) {
                return 1l;
            } else {
                return 0l;
            }
        }
    }

    public static class AdvancedLight implements Light {
        private Long brightnessLevel = 0l;
        public void turnOn() {
            brightnessLevel++;
        }
        public void turnOff() {
            if (brightnessLevel>0) {
                brightnessLevel--;
            }
        }

        public void toggle() {
            brightnessLevel=brightnessLevel+2;
        }

        public Long getBrightness() {
           return brightnessLevel;
        }
    }
}
