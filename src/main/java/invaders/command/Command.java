package invaders.command;

import invaders.engine.GameEngine;
import invaders.engine.GameWindow;

/**
 * Operation command abstract class
 */
public interface Command {

    /**
     * Press the A key on the keyboard to command
     * @param window game render object
     * @param model game engine object
     */
    public void pressA(GameWindow window, GameEngine model);
    /**
     * Press the S key command on the keyboard
     * @param window game render object
     * @param model game engine object
     */
    public void pressS(GameWindow window, GameEngine model);
    /**
     * Press the D key on the keyboard to command
     * @param window game render object
     * @param model game engine object
     */
    public void pressD(GameWindow window, GameEngine model);
    /**
     * Press the F key on the keyboard to command
     * @param window game render object
     * @param model game engine object
     */
    public void pressF(GameWindow window, GameEngine model);

}
