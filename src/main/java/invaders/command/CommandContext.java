package invaders.command;

import invaders.engine.GameEngine;
import invaders.engine.GameWindow;
import javafx.scene.input.KeyCode;

/**
 * command context object
 */
public class CommandContext {

    /**
     * command object
     */
    private Command command ;

    /**
     * command context instance object
     */
    private static final CommandContext _INSTACE = new CommandContext();

    /**
     * game render object
     */
    private GameWindow gameWindow;

    /**
     * game engine object
     */
    private GameEngine model ;

    /**
     * private constructor
     */
    private CommandContext(){};

    /**
     * Get singleton object
     * @return
     */
    public static CommandContext getInstance(){
        return _INSTACE;
    }


    /**
     * Initialize context
     * @param gameWindow game render object
     * @param model game engine object
     * @param command Specific command implementation object
     */
    public void init(GameWindow gameWindow,GameEngine model,Command command){
        this.gameWindow = gameWindow;
        this.model = model;
        this.command = command;
    }

    /**
     * Issue orders
     * @param keyCode Keyboard keys pressed
     */
    public void executeCommand(KeyCode keyCode){
        if(keyCode == KeyCode.A){
            command.pressA(gameWindow,model);
        }else if(keyCode == KeyCode.S){
            command.pressS(gameWindow,model);
        }else if(keyCode == KeyCode.D){
            command.pressD(gameWindow,model);
        }else if(keyCode == KeyCode.F){
            command.pressF(gameWindow,model);
        }
    }
}
