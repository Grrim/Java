package pl.grim.Program.Game;

import pl.grim.Program.AbstractProgram;
import pl.grim.Program.ProgramType;

public abstract class AbstractGameProgram extends AbstractProgram implements Game {
    protected String gameTitle;

    public AbstractGameProgram(String name, String action, String gameTitle) {
        super(name, action);

        this.gameTitle = gameTitle;
    }

    @Override
    public ProgramType programType() {
        return ProgramType.GAME;
    }
}
