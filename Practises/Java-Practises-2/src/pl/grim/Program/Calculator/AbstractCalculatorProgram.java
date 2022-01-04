package pl.grim.Program.Calculator;

import pl.grim.Program.AbstractProgram;
import pl.grim.Program.Program;
import pl.grim.Program.ProgramType;

public abstract class AbstractCalculatorProgram extends AbstractProgram implements Calculator {

    public AbstractCalculatorProgram(String name, String action) {
        super(name, action);
    }

    @Override
    public ProgramType programType() {
        return ProgramType.CALCULATOR;
    }
}
