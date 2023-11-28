package org.beast4ever.aoc.aoc2k15;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k15.day07.AssemblyInstruction;
import org.beast4ever.aoc.aoc2k15.day07.Circuit;
import org.beast4ever.aoc.aoc2k15.day07.Signal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class Day07Component extends DayResolutionComponent {

    @Autowired
    private FileUtilityParser fileUtilityParser;

    @Override
    public String resolveFirstStar(String inputFilePath) throws IOException {
        Circuit circuit = new Circuit();
        fileUtilityParser.readFileSplitByLines(inputFilePath).stream().forEach(txtInstr -> circuit.performInstruction(new AssemblyInstruction(txtInstr)));

       return "" + circuit.getCarriedSignalOnSpecifiedWire("a").getDecimalRepresentation();
    }

    @Override
    public String resolveSecondStar(String inputFilePath) throws IOException {
        Circuit circuit = new Circuit();
        fileUtilityParser.readFileSplitByLines(inputFilePath).stream().forEach(txtInstr -> circuit.performInstruction(new AssemblyInstruction(txtInstr)));
        Signal signalProvidedByWireA = circuit.getCarriedSignalOnSpecifiedWire("a");
        circuit.overrideSignalValueOnSpecifiedWire("b", signalProvidedByWireA);

        return "" + circuit.getCarriedSignalOnSpecifiedWire("a").getDecimalRepresentation();
    }

}
