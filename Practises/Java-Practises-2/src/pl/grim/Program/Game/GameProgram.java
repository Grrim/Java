package pl.grim.Program.Game;

public class GameProgram extends AbstractGameProgram{

    public GameProgram(String name, String action, String gameTitle) {
        super(name, action, gameTitle);
    }

    @Override
    public void clickPlay() {
        System.out.println("Click start button");
    }

    @Override
    public void startGame() {
        System.out.println("Start playing video game");
    }

    @Override
    public void leaveGame() {
        System.out.println("Leaving video game");
    }
}
