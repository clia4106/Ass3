package invaders.command;

import invaders.engine.GameEngine;
import invaders.engine.GameWindow;

/**
 * Command implementation class
 */
public class CommandImpl implements Command{

    /**
     * Command execution object
     */
    private final Operation operation;

    public CommandImpl(Operation operation){
        this.operation = operation;
    }
    @Override
    public void pressA(GameWindow window, GameEngine model) {
        operation.clearAllFastCannonball(window,model);
    }

    @Override
    public void pressS(GameWindow window, GameEngine model) {
        operation.clearAllSlowCannonball(window,model);
    }

    @Override
    public void pressD(GameWindow window, GameEngine model) {
        operation.archive(window,model);
    }

    @Override
    public void pressF(GameWindow window, GameEngine model) {
        operation.LoadArchive(window,model);
    }
}
