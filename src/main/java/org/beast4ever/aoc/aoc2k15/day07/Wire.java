package org.beast4ever.aoc.aoc2k15.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Wire {
    private Signal carriedSignal;

    private List<LogicalGate> outputGates = new ArrayList<LogicalGate>();

    private String name;

    public Signal getCarriedSignal() {
        return carriedSignal;
    }

    public void setCarriedSignal(Signal carriedSignal) {
        this.carriedSignal = carriedSignal;
    }

    public String getName() {
        return name;
    }

    public List<LogicalGate> getOutputGates() {
        return outputGates;
    }

    public void addOutputGate(LogicalGate outputGate) {
        if (!outputGates.contains(outputGate)) {
            outputGates.add(outputGate);
        }
    }

    public Wire(String name) {
        this.name = name;
    }

    public Wire() {
        this.name = "NO_NAME";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wire wire = (Wire) o;
        return Objects.equals(name, wire.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

