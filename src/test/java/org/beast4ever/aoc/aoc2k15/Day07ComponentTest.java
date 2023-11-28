package org.beast4ever.aoc.aoc2k15;

import lombok.extern.slf4j.Slf4j;
import org.beast4ever.aoc.aoc2k15.day07.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class Day07ComponentTest {
    @Autowired
    Day07Component day07Component;


    @Test
    public void whenInputRealData_thenResolveFirstStar() throws IOException {
        String expectedResponse = "16076";

        String response = day07Component.resolveFirstStar("input-day07-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    public void whenInputRealData_thenResolveSecondStar() throws IOException {
        String expectedResponse = "2797";

        String response = day07Component.resolveSecondStar("input-day07-S1S2.txt");
        Assertions.assertEquals(expectedResponse, response);
    }


    @Test
    public void whenInlineData_thenAssemblyInstructionParsingOK() {
        String inlineInstructions =  """
                        123 -> x
                        x -> y
                        x AND y -> d
                        132 OR y -> e
                        x LSHIFT 2 -> f
                        y RSHIFT 14 -> g
                        NOT x -> h
                        NOT y -> i""";
        log.info("Instructions to parse \n{}: ", inlineInstructions);
        List<AssemblyInstruction> instrBooklet = new ArrayList<AssemblyInstruction>();

        inlineInstructions.lines().forEach(textInstr -> instrBooklet.add(new AssemblyInstruction(textInstr)));

        int totalInstructions = instrBooklet.size();
        long nbOfSignalInstructions = instrBooklet.stream().filter(instr -> instr.getGate() instanceof SignalGate).count();
        long nbOfAndInstructions = instrBooklet.stream().filter(instr -> instr.getGate() instanceof AndGate).count();
        long nbOfOrInstructions = instrBooklet.stream().filter(instr -> instr.getGate() instanceof OrGate).count();
        long nbOfLShiftInstructions = instrBooklet.stream().filter(instr -> instr.getGate() instanceof LShiftGate).count();
        long nbOfRShiftInstructions = instrBooklet.stream().filter(instr -> instr.getGate() instanceof RShiftGate).count();
        long nbOfNotInstructions = instrBooklet.stream().filter(instr -> instr.getGate() instanceof NotGate).count();

        AssemblyInstruction firstSignalInstruction = instrBooklet.stream().filter(instr -> instr.getGate() instanceof SignalGate).toList().get(0);
        AssemblyInstruction secondSignalInstruction = instrBooklet.stream().filter(instr -> instr.getGate() instanceof SignalGate).toList().get(1);


        Assertions.assertEquals(8, totalInstructions);
        Assertions.assertEquals(2, nbOfSignalInstructions);
        Assertions.assertEquals(1, nbOfAndInstructions);
        Assertions.assertEquals(1, nbOfOrInstructions);
        Assertions.assertEquals(1, nbOfLShiftInstructions);
        Assertions.assertEquals(1, nbOfRShiftInstructions);
        Assertions.assertEquals(2, nbOfNotInstructions);
        Assertions.assertTrue(AssemblyInstruction.isStaticSignalInput(firstSignalInstruction.getInputLabels().get(0)));
        Assertions.assertEquals(new Signal(123), firstSignalInstruction.getFixedSignalValue(firstSignalInstruction.getInputLabels().get(0)));
        Assertions.assertFalse(AssemblyInstruction.isStaticSignalInput(secondSignalInstruction.getInputLabels().get(0)));
    }

    @Test
    public void whenInlineData_thenCircuitOK() {
        String inlineInstructions =  """
                        123 -> x
                        456 -> y
                        x AND y -> d
                        x OR y -> e
                        x LSHIFT 2 -> f
                        y RSHIFT 2 -> g
                        NOT x -> h
                        NOT y -> i""";
        log.info("Instructions to parse \n{}: ", inlineInstructions);
        Circuit circuit = new Circuit();

        inlineInstructions.lines().forEach(textInstr -> circuit.performInstruction(new AssemblyInstruction(textInstr)));

        Assertions.assertEquals(72, circuit.getCarriedSignalOnSpecifiedWire("d").getDecimalRepresentation());
        Assertions.assertEquals(507, circuit.getCarriedSignalOnSpecifiedWire("e").getDecimalRepresentation());
        Assertions.assertEquals(492, circuit.getCarriedSignalOnSpecifiedWire("f").getDecimalRepresentation());
        Assertions.assertEquals(114, circuit.getCarriedSignalOnSpecifiedWire("g").getDecimalRepresentation());
        Assertions.assertEquals(65412, circuit.getCarriedSignalOnSpecifiedWire("h").getDecimalRepresentation());
        Assertions.assertEquals(65079, circuit.getCarriedSignalOnSpecifiedWire("i").getDecimalRepresentation());
        Assertions.assertEquals(123, circuit.getCarriedSignalOnSpecifiedWire("x").getDecimalRepresentation());
        Assertions.assertEquals(456, circuit.getCarriedSignalOnSpecifiedWire("y").getDecimalRepresentation());

    }

    @Test
    public void whenInlineData_thenAndGateOK() {
        Integer expectedResponse = 72;
        LogicalGate gate = new AndGate();
        Signal input1 = new Signal(123);
        Signal input2 = new Signal(456);
        Signal output = gate.executeInstruction(input1, input2);
        Assertions.assertEquals(expectedResponse, output.getDecimalRepresentation());
    }

    @Test
    public void whenInlineData_thenLShiftGateOK() {
        Integer expectedResponse = 492;
        LogicalGate gate = new LShiftGate(2);
        Signal input1 = new Signal(123);
        Signal output = gate.executeInstruction(input1);
        Assertions.assertEquals(expectedResponse, output.getDecimalRepresentation());
    }

    @Test
    public void whenInlineData_thenNotGateOK() {
        Integer expectedResponse = 65079;
        LogicalGate gate = new NotGate();
        Signal input1 = new Signal(456);
        Signal output = gate.executeInstruction(input1);
        Assertions.assertEquals(expectedResponse, output.getDecimalRepresentation());
    }

    @Test
    public void whenInlineData_thenOrGateOK() {
        Integer expectedResponse = 507;
        LogicalGate gate = new OrGate();
        Signal input1 = new Signal(123);
        Signal input2 = new Signal(456);
        Signal output = gate.executeInstruction(input1, input2);
        Assertions.assertEquals(expectedResponse, output.getDecimalRepresentation());
    }

    @Test
    public void whenInlineData_thenRshiftGateOK() {
        Integer expectedResponse = 114;
        LogicalGate gate = new RShiftGate(2);
        Signal input1 = new Signal(456);
        Signal output = gate.executeInstruction(input1);
        Assertions.assertEquals(expectedResponse, output.getDecimalRepresentation());
    }

    @Test
    public void whenInlineData_thenSignalGateOK() {
        Integer expectedResponse = 123;
        LogicalGate gate = new SignalGate();
        Signal input1 = new Signal(123);
        Signal output = gate.executeInstruction(input1);
        Assertions.assertEquals(expectedResponse, output.getDecimalRepresentation());
    }

}
