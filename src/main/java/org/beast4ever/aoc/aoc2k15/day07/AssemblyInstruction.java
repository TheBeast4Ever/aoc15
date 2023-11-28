package org.beast4ever.aoc.aoc2k15.day07;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

@Slf4j
public class AssemblyInstruction {

    public static final String FIXED_SIGNAL_INPUT_LABEL1 = "Fixed value 1 : ";

    public static final String FIXED_SIGNAL_INPUT_LABEL2 = "Fixed value 2 : ";

    private List<String> inputLabels = new ArrayList<String>();

    private LogicalGate gate;

    private String outputLabel;

    private String textInstruction;

    public static Boolean isStaticSignalInput(String inputLabel) {
        return (inputLabel.startsWith(FIXED_SIGNAL_INPUT_LABEL1) || inputLabel.startsWith(FIXED_SIGNAL_INPUT_LABEL2));
    }

    public Signal getFixedSignalValue(String inputLabel) {
        if (inputLabels.contains(inputLabel) && isStaticSignalInput(inputLabel)) {
            int index = inputLabels.indexOf(inputLabel);
            return new Signal(Integer.parseInt(inputLabels.get(index).substring(FIXED_SIGNAL_INPUT_LABEL1.length())));
        } else {
            log.error("Unrecognized fixed signal input {}", inputLabel);
            return null;
        }
    }

    public List<String> getInputLabels() {
        return inputLabels;
    }

    public LogicalGate getGate() {
        return gate;
    }

    public String getOutputLabel() {
        return outputLabel;
    }

    public String getTextInstruction() {
        return textInstruction;
    }

    /**
     * AND : x AND y -> z means that the bitwise AND of wire x and wire y is provided to wire z.
     * LSHIFT : p LSHIFT 2 -> q means that the value from wire p is left-shifted by 2 and then provided to wire q.
     * NOT : NOT e -> f means that the bitwise complement of the value from wire e is provided to wire f.
     * OR :  x OR y -> z means that the bitwise OR of wire x and wire y is provided to wire z.
     * RSHIFT : p RSHIFT 1 -> q means that the value from wire p is right-shifted by 2 and then provided to wire q.
     * SIGNAL : 123 -> x means that the signal 123 is provided to wire x.
     * @param structuredTextInstruction
     */
    public AssemblyInstruction(String structuredTextInstruction) {
        textInstruction = structuredTextInstruction;
        StringTokenizer strTokenize = new StringTokenizer(structuredTextInstruction, " ");
        if (structuredTextInstruction.contains("AND")) {
            inputLabels.add(computeLabel(strTokenize.nextToken()));
            strTokenize.nextToken(); // AND word
            inputLabels.add(computeLabel(strTokenize.nextToken()));
            strTokenize.nextToken(); // ->
            outputLabel = strTokenize.nextToken();
            gate = new AndGate();
        } else if (structuredTextInstruction.contains("LSHIFT")) {
            inputLabels.add(computeLabel(strTokenize.nextToken()));
            strTokenize.nextToken(); // LSHIFT word
            Integer shiftValue = Integer.parseInt(strTokenize.nextToken());
            strTokenize.nextToken(); // ->
            outputLabel =  strTokenize.nextToken();
            gate = new LShiftGate(shiftValue);
        } else if (structuredTextInstruction.contains("NOT")) {
            strTokenize.nextToken(); // NOT
            inputLabels.add(computeLabel(strTokenize.nextToken()));
            strTokenize.nextToken(); // ->
            outputLabel =  strTokenize.nextToken();
            gate = new NotGate();
        } else if (structuredTextInstruction.contains("OR")) {
            inputLabels.add(computeLabel(strTokenize.nextToken()));
            strTokenize.nextToken(); // OR word
            inputLabels.add(computeLabel(strTokenize.nextToken()));
            strTokenize.nextToken(); // ->
            outputLabel = strTokenize.nextToken();
            gate = new OrGate();
        } else if (structuredTextInstruction.contains("RSHIFT")) {
            inputLabels.add(computeLabel(strTokenize.nextToken()));
            strTokenize.nextToken(); // RSHIFT word
            Integer shiftValue = Integer.parseInt(strTokenize.nextToken());
            strTokenize.nextToken(); // ->
            outputLabel =  strTokenize.nextToken();
            gate = new RShiftGate(shiftValue);
        } else { // SIGNAL Instruction
            inputLabels.add(computeLabel(strTokenize.nextToken()));
            strTokenize.nextToken(); // ->
            outputLabel =  strTokenize.nextToken();
            gate = new SignalGate();
        }
    }

    private String computeLabel(String inputText) {
        if (inputText.matches("^[0-9].*")) {
            if (inputLabels.isEmpty()) {
                return FIXED_SIGNAL_INPUT_LABEL1 + inputText;
            } else {
                return FIXED_SIGNAL_INPUT_LABEL2 + inputText;
            }
        } else {
            return inputText;
        }
    }

    public Boolean containsSpecifiedWireInput(String name) {
        return inputLabels.contains(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssemblyInstruction that = (AssemblyInstruction) o;
        return Objects.equals(textInstruction, that.textInstruction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textInstruction);
    }

    @Override
    public String toString() {
        return textInstruction;
    }
}
