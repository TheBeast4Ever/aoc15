package org.beast4ever.aoc.aoc2k15.day07;

public abstract class LogicalGate {

    public abstract Signal executeInstruction(Signal... inputSignals);

    public abstract Integer getPriorityLevel();
}

