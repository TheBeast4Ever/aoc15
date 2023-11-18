package org.beast4ever.aoc.aoc2k15;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k15.DayResolutionComponent;
import org.beast4ever.aoc.aoc2k15.FileUtilityParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.IntStream;

@Component
@Slf4j
public class Day04Component extends DayResolutionComponent {
    @Autowired
    private FileUtilityParser fileUtilityParser;

    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        String secret = fileUtilityParser.readFirstLine(inputFilePath);
        return "" + findLowestPositiveNumber(secret, 5);
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
        String secret = fileUtilityParser.readFirstLine(inputFilePath);
        return "" + findLowestPositiveNumber(secret, 6);
    }

    private Long findLowestPositiveNumber(String secret, Integer nbOfLeadingZeros) {
        Long lowestPositiveNumber = 0l;
        StringBuilder leadingZeroStr = new StringBuilder();

        IntStream.range(0, nbOfLeadingZeros).forEach( i -> leadingZeroStr.append("0"));

        for (Long l=0l; l<=Long.MAX_VALUE; l++) {
            String phraseToHash = secret + l;
            String md5Hash = getMd5(phraseToHash);
            if (md5Hash.startsWith(leadingZeroStr.toString())) {
                lowestPositiveNumber = l;
                break;
            }
        }

        return lowestPositiveNumber;
    }

    private String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
