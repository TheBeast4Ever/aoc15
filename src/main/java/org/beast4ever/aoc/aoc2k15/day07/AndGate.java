package org.beast4ever.aoc.aoc2k15.day07;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AndGate extends LogicalGate {

    public Signal executeInstruction(Signal... signals) {
        if (signals.length != 2) {
            log.error("Incorrect number of input signals for " + this.getClass().getSimpleName() + " : " + signals.length);
            return null;
        }
        Signal input1Signal = signals[0];
        Signal input2Signal = signals[1];
        int input1SignalDecimalValue = input1Signal.getDecimalRepresentation();
        int input2SignalDecimalValue = input2Signal.getDecimalRepresentation();
        int outputSignalDecimalValue = input1SignalDecimalValue & input2SignalDecimalValue;
        Signal outputSignal = new Signal(outputSignalDecimalValue);
        log.debug("Execute AND instruction on {} AND {}. Result is : {}", input1SignalDecimalValue, input2SignalDecimalValue, outputSignal);
        return outputSignal;
    }

    @Override
    public Integer getPriorityLevel() {
        return 1;
    }
}
