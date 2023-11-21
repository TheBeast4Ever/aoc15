package org.beast4ever.aoc.aoc2k15;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class Day05Component extends DayResolutionComponent {

    @Autowired
    private FileUtilityParser fileUtilityParser;

    public static enum WORDTYPE {
        UNKNOWN, NICE, NAUGHTY
    };

    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        List<String> words = fileUtilityParser.readFileSplitByLines(inputFilePath);

        return "" + words.stream().filter(word -> evaluateWordTypeSimpleRules(word) == WORDTYPE.NICE).count();
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
        List<String> words = fileUtilityParser.readFileSplitByLines(inputFilePath);

        return "" + words.stream().filter(word -> evaluateWordTypeAdvancedRules(word) == WORDTYPE.NICE).count();
    }

    public WORDTYPE evaluateWordTypeSimpleRules(String word) {
        List<Rule> rules = Arrays.asList(new AtLeastThreeVoyelsRule(), new AtLeastOneLetterTwiceInRowRule(), new DontContainsForbiddenChunksRule());
        Long nbOfRulesSatisfied = rules.stream().filter(rule -> {
            Boolean isSatisfied = rule.isSatisfied(word);
            if (!isSatisfied) {
                log.warn("Unsatisfied rule, {}, on {}", rule.getDescription(), word);
            }
            return isSatisfied;
        }).count();
        if (nbOfRulesSatisfied == rules.size()) {
            return WORDTYPE.NICE;
        } else {
            return WORDTYPE.NAUGHTY;
        }
    }

    public WORDTYPE evaluateWordTypeAdvancedRules(String word) {
        List<Rule> rules = Arrays.asList(new TwoLettersAppearsTwice(), new OneLetterRepeatsWithOneLetterBetween());
        Long nbOfRulesSatisfied = rules.stream().filter(rule -> {
            Boolean isSatisfied = rule.isSatisfied(word);
            if (!isSatisfied) {
                log.warn("Unsatisfied rule, {}, on {}", rule.getDescription(), word);
            }
            return isSatisfied;
        }).count();
        if (nbOfRulesSatisfied == rules.size()) {
            return WORDTYPE.NICE;
        } else {
            return WORDTYPE.NAUGHTY;
        }
    }

    public interface Rule {
        Boolean isSatisfied(String entry);

        String getDescription();
    }

    public static class AtLeastThreeVoyelsRule implements Rule {

        private Boolean isVoyel (Integer asciiCode) {
            return (asciiCode == 97) // a : 97
                    || (asciiCode == 101) // e : 101
                    || (asciiCode == 105) // i : 105
                    || (asciiCode == 111) // o : 111
                    || (asciiCode == 117); // u : 117
        }

        @Override
        public Boolean isSatisfied(String entry) {
            return entry.chars().filter(c -> isVoyel(c)).count() >= 3;
        }

        @Override
        public String getDescription() {
            return "At least 3 voyels";
        }
    }

    public static class AtLeastOneLetterTwiceInRowRule implements Rule {
        @Override
        public Boolean isSatisfied(String entry) {
            for (int i=0; i < entry.length() - 1; i++) {
                if (entry.charAt(i) == entry.charAt(i+1)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String getDescription() {
            return "At least one letter that appears twice in a row";
        }
    }


    public static class DontContainsForbiddenChunksRule implements Rule {
        public final List<String> forbiddenChunks = Arrays.asList("ab", "cd", "pq", "xy");

        @Override
        public Boolean isSatisfied(String entry) {
            return forbiddenChunks.stream().filter(forbiddenChunk -> !entry.contains(forbiddenChunk)).count() == forbiddenChunks.size();
        }

        @Override
        public String getDescription() {
            return "Does not contain forbidden chunks" ;
        }
    }

    public static class TwoLettersAppearsTwice implements Rule {
        @Override
        public Boolean isSatisfied(String entry) {
            for (int i=0; i < entry.length() - 1; i++) {
                String twoLettersSequence = entry.substring(i, i+2);
                if (i+2 < entry.length() && entry.substring(i+2).contains(twoLettersSequence)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String getDescription() {
            return "2 letters appears twice";
        }
    }

    public static class OneLetterRepeatsWithOneLetterBetween implements Rule {
        @Override
        public Boolean isSatisfied(String entry) {
            for(int i=0; i < entry.length() - 1; i++) {
                Character currentChar = entry.charAt(i);
                if (i+2 < entry.length() && entry.charAt(i+2) == currentChar) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String getDescription() {
            return "at least one letter which repeats with exactly one letter between them";
        }
    }
}
