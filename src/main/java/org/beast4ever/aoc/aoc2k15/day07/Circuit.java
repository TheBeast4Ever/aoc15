package org.beast4ever.aoc.aoc2k15.day07;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class Circuit {
    private Map<String, Wire> wires;

    Comparator<AssemblyInstruction> customComparator = Comparator.comparing(instr -> instr.getGate().getPriorityLevel());

    private List<AssemblyInstruction> instructionsBooklet;

    public Circuit() {

        wires = new HashMap<String, Wire>();
        instructionsBooklet = new ArrayList<AssemblyInstruction>();
    }


    public void performInstruction(AssemblyInstruction instruction) {
        if (!instructionsBooklet.contains(instruction)) {
            instructionsBooklet.add(instruction);
        }
        List<String> inputLabels = instruction.getInputLabels();
        List<Signal> inputSignals = new ArrayList<Signal>();
        inputLabels.stream().forEach(inputLabel -> {
            if (AssemblyInstruction.isStaticSignalInput(inputLabel)) {
                Signal signal = instruction.getFixedSignalValue(inputLabel);
                signal.setActive(true);
                inputSignals.add(signal);
            } else {
                Wire wireToLinkWithOutputGate = getAndCreateEventuallyWire(inputLabel);
                wireToLinkWithOutputGate.addOutputGate(instruction.getGate());
                Signal signal =  wireToLinkWithOutputGate.getCarriedSignal();
                inputSignals.add(signal);
                wires.put(inputLabel, wireToLinkWithOutputGate);
            }
        });
        String outputLabel = instruction.getOutputLabel();
        Wire wireToLinkWithInputGate = getAndCreateEventuallyWire(outputLabel);
        Signal previousOutputSignal = wireToLinkWithInputGate.getCarriedSignal();
        Signal outputSignal = executeEventuallyInstruction(inputSignals, instruction.getGate());
        wireToLinkWithInputGate.setCarriedSignal(outputSignal);
        wires.put(outputLabel, wireToLinkWithInputGate);
        refreshAllActiveSignalsFromWire(wireToLinkWithInputGate, previousOutputSignal, outputSignal);
    }

    public void overrideSignalValueOnSpecifiedWire(String wireLabel, Signal newSignal) {
        Wire wireToOverride = getAndCreateEventuallyWire(wireLabel);
        Signal previousSignal = wireToOverride.getCarriedSignal();
        wireToOverride.setCarriedSignal(newSignal);
        refreshAllActiveSignalsFromWire(wireToOverride, previousSignal, newSignal);
    }

    private Signal executeEventuallyInstruction(List<Signal> inputSignals, LogicalGate gate) {
        Boolean hasAllActiveInputs = Boolean.TRUE;
        Integer nbOfInputSignals = inputSignals.size();
        if (nbOfInputSignals == Math.toIntExact(inputSignals.stream().filter(inputSignal -> inputSignal != null && inputSignal.getActive()).count())) {
            return gate.executeInstruction(inputSignals.toArray(new Signal[0]));
        } else {
            return new Signal();
        }
    }

    private void refreshAllActiveSignalsFromWire(Wire wire, Signal previousSignal, Signal newSignal) {
        if (newSignal.getActive() && (previousSignal == null || !previousSignal.getActive() || !newSignal.equals(previousSignal))) {
            if (wire.getOutputGates()!=null) {
                List<AssemblyInstruction> instructionToExecuteToo = instructionsBooklet.stream().filter(instr -> instr.containsSpecifiedWireInput(wire.getName())).toList();
                if (!instructionToExecuteToo.isEmpty()) {
                    instructionToExecuteToo.forEach(instr -> performInstruction(instr));
                }
            }
        }
    }

    private Wire getAndCreateEventuallyWire(String wireName) {
        if (!wires.containsKey(wireName)) {
            wires.put(wireName, new Wire(wireName));
        }
        return wires.get(wireName);
    }

    public Signal getCarriedSignalOnSpecifiedWire(String wireName) {
        if (wires.containsKey(wireName)) {
            return wires.get(wireName).getCarriedSignal();
        } else {
            log.warn("Wire {} not found", wireName);
            return new Signal();
        }
    }
}