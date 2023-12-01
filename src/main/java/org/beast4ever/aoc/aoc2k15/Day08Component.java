package org.beast4ever.aoc.aoc2k15;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class Day08Component extends DayResolutionComponent {

    @Autowired
    private FileUtilityParser fileUtilityParser;

    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        List<Long> results = new ArrayList<Long>();
        fileUtilityParser.readFileSplitByLines(inputFilePath).stream().forEach(santaString -> results.add(getDifferenceBetweenChararactersOfCodeAndMemory(santaString)));

        return "" + results.stream().mapToLong(Long::valueOf).sum();
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
        List<Long> results = new ArrayList<Long>();
        fileUtilityParser.readFileSplitByLines(inputFilePath).stream().forEach(santaString -> results.add(getDifferenceBetweenEncodedAndDecodedChararactersOfCode(santaString)));

        return "" + results.stream().mapToLong(Long::valueOf).sum();
    }

    public Long getDifferenceBetweenEncodedAndDecodedChararactersOfCode(String santaDecodedString) {
        String santaEncodedString = encodeSantaString(santaDecodedString);
        Long nbCharCodeEncoded = getNumberOfCharactersOfCode(santaEncodedString);
        Long nbCharCodeDecoded = getNumberOfCharactersOfCode(santaDecodedString);
        return nbCharCodeEncoded - nbCharCodeDecoded;
    }

    public String encodeSantaString(String santaString) {
        StringBuilder sbBuilder = new StringBuilder();

        return sbBuilder.toString();
    }

    public Long getDifferenceBetweenChararactersOfCodeAndMemory(String santaString) {
        Long nbCharCode = getNumberOfCharactersOfCode(santaString);
        Long nbCharMemory = getNumberOfCharactersInMemory(santaString);
        return nbCharCode - nbCharMemory;
    }

    public Long getNumberOfCharactersOfCode(String santaString) {
        return Long.valueOf(santaString.length());
    }

    public Long getNumberOfCharactersInMemory(String santaString) {
        return Long.valueOf(decodeSantaString(santaString).length());
    }

    public String decodeSantaString(String santaString) {
        StringBuilder decodedSantaString = new StringBuilder();

        if (santaString == null || santaString.isEmpty()) {
            return santaString;
        } else {
            Character firstChar = santaString.charAt(0);
            int nextFirstCharPosition = 1;
            if (!EscapeCharacterService.isEscapeCharacter(firstChar) && firstChar != '"') {
                decodedSantaString.append(firstChar);
            } else {
                if (firstChar != '"') {
                    nextFirstCharPosition++;
                    String escapedSequence = EscapeCharacterService.isolateEscapedCharacterString(santaString.substring(1));
                    if (!escapedSequence.isEmpty() && EscapeCharacterService.isSpecialHexadecimalMarker(escapedSequence.charAt(0))) {
                        if (escapedSequence.length()>2) {
                            nextFirstCharPosition=nextFirstCharPosition+2;
                            decodedSantaString.append(EscapeCharacterService.decodeHexadecimalNumber(escapedSequence.substring(1)));
                        } else {
                            decodedSantaString.append(escapedSequence);
                        }
                    } else {
                        decodedSantaString.append(escapedSequence);
                    }
                }
            }

            decodedSantaString.append(decodeSantaString(santaString.substring(nextFirstCharPosition)));

            return decodedSantaString.toString();

        }
    }


    private static class EscapeCharacterService {
        private static final Character ESCAPE_CHAR = '\\'; // Backslash

        private static final Character SPECIAL_FIRST_CHAR_HEXADECIMAL_NUMBER = 'x';

        private static final int SIZE_OF_HEXA_NUMBER = 2;

        private static final List<Character> LIST_OF_SIMPLIFIED_ESCAPED_CHARACTERS = Arrays.asList('\\', '\"', 'x');

        public static Boolean isEscapeCharacter(Character character) {
            return character == ESCAPE_CHAR;
        }

        public static Boolean isSpecialHexadecimalMarker(Character character) {
            return character == SPECIAL_FIRST_CHAR_HEXADECIMAL_NUMBER;
        }

        private static Boolean isSimplifiedEscapedCharacter(Character character) {
            return LIST_OF_SIMPLIFIED_ESCAPED_CHARACTERS.contains(character);
        }

        public static String isolateEscapedCharacterString(String stringContainingStringToExtract) {
            if (isSimplifiedEscapedCharacter(stringContainingStringToExtract.charAt(0))) {
                if (isSpecialHexadecimalMarker(stringContainingStringToExtract.charAt(0))) {
                    return stringContainingStringToExtract.substring(0, 1+SIZE_OF_HEXA_NUMBER);
                } else {
                    return stringContainingStringToExtract.substring(0, 1);
                }
            } else {
                return "";
            }
        }

        public static String decodeHexadecimalNumber(String str) {
            StringBuilder sb = new StringBuilder();

            sb.append((char) Integer.parseInt(str.substring(0, SIZE_OF_HEXA_NUMBER), 16));

            return sb.toString();
        }
    }
}
