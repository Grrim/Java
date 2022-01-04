package pl.grim.Program;

public abstract class AbstractProgram implements Program{
    protected String name;
    protected String action;

    public AbstractProgram(String name, String action) {
        this.name = name;
        this.action = action;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String whatsDoing() {
        return action;
    }

}
