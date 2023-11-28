package org.beast4ever.aoc.aoc2k15.day07;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SignalGate extends LogicalGate {

    @Override
    public Signal executeInstruction(Signal... signals) {
        if (signals.length != 1) {
            log.error("Incorrect number of input signals for " + this.getClass().getSimpleName() + " : " + signals.length);
            return null;
        }
        log.debug("Execute Signal instruction with value {}", signals[0].getDecimalRepresentation());
        return signals[0];
    }

    @Override
    public Integer getPriorityLevel() {
        return 0;
    }
}
