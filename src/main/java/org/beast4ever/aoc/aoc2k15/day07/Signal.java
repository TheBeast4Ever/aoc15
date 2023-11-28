package org.beast4ever.aoc.aoc2k15.day07;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class Signal {
    public static final int BITLENTGTH = 16;

    public Boolean isActive = Boolean.FALSE;

    private String binaryRepresentation;

    public Signal(int decimalValue) {
        binaryRepresentation = StringUtils.leftPad(Integer.toBinaryString(decimalValue), BITLENTGTH, "0");
        binaryRepresentation = binaryRepresentation.substring(binaryRepresentation.length()-BITLENTGTH);
        isActive = true;
    }

    public Signal() {
        isActive = false;
    }

    public int getDecimalRepresentation() {
       return Integer.parseInt(binaryRepresentation, 2);
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Signal{" +
                "binaryRepresentation='" + binaryRepresentation + '\'' +
                "decimalRepresentation='" + getDecimalRepresentation() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Signal signal = (Signal) o;
        return Objects.equals(binaryRepresentation, signal.binaryRepresentation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(binaryRepresentation);
    }
}
