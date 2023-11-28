package org.beast4ever.aoc.aoc2k15.day07;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RShiftGate extends LogicalGate {
    private int pad;

    public RShiftGate(int lpad) {
        this.pad = lpad;
    }

    @Override
    public Signal executeInstruction(Signal... signals) {
        if (signals.length != 1) {
            log.error("Incorrect number of input signals for " + this.getClass().getSimpleName() + " : " + signals.length);
            return null;
        }
        Signal inputSignal = signals[0];
        int inputSignalDecimalValue = inputSignal.getDecimalRepresentation();
        int outputSignalDecimalValue = inputSignalDecimalValue >> pad;
        Signal outputSignal = new Signal(outputSignalDecimalValue);
        log.debug("Execute RShift instruction on {} with pad {}. Result is : {}", inputSignal, pad, outputSignal);
        return outputSignal;
    }
    @Override
    public Integer getPriorityLevel() {
        return 1;
    }
}
