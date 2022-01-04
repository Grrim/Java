package pl.grim.Program.Calculator;

public class CalculatorProgram extends AbstractCalculatorProgram{
    public CalculatorProgram(String name, String action) {
        super(name, action);
    }

    @Override
    public void calculate() {
        System.out.println("Calculate result");
    }
}
