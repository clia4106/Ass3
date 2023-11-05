package invaders.command;

import invaders.GameObject;
import invaders.engine.GameEngine;
import invaders.engine.GameWindow;
import invaders.entities.*;
import invaders.rendering.Renderable;

import java.util.ArrayList;
import java.util.List;

/**
 * Specific command execution object
 */
public class Operation {

    /**
     * Game save duration
     */
    private int times = 0;

    /**
     * Renderable objects on the map when saving the game
     */
    private List<Renderable> renderables = new ArrayList<>();

    /**
     * The number of enemies killed when the game is saved
     */
    private int currentDeadEnemyCount = 0 ;

    /**
     * The number of bullets released by enemy when saving the game
     */
    private int currentEnemyCannonballCount = 0 ;

    /**
     * Clear all rapid bullets from the map
     * @param window game render object
     * @param model game engine object
     */
    public void clearAllFastCannonball(GameWindow window, GameEngine model) {
        List<EntityView> entityViews = window.getEntityViews();
        for (EntityView entityView : entityViews) {
            if(entityView.getEntity() instanceof LaserCannonball){
                entityView.markForDelete();
                model.getRenderables().remove(entityView.getEntity());
                model.currentEnemyCannonballCountDown();
                model.getPlayer().addScore(2);
            }
        }
    }
    /**
     * Clear all slow bullets from the map
     * @param window game render object
     * @param model game engine object
     */
    public void clearAllSlowCannonball(GameWindow window, GameEngine model) {
        List<EntityView> entityViews = window.getEntityViews();
        for (EntityView entityView : entityViews) {
            if(entityView.getEntity() instanceof NormalCannonball){
                entityView.markForDelete();
                model.getRenderables().remove(entityView.getEntity());
                model.currentEnemyCannonballCountDown();
                model.getPlayer().addScore(1);
            }
        }
    }

    /**
     * Archive specific operations
     * @param window game render object
     * @param model game engine object
     */
    public void archive(GameWindow window, GameEngine model) {
        Player player = null ;
        this.times = model.getTimes();
        this.currentDeadEnemyCount = model.getCurrentDeadEnemyCount();
        this.currentEnemyCannonballCount = model.getCurrentEnemyCannonballCount();
        renderables.clear();
        List<Renderable> list = model.getRenderables();
        for(Renderable renderable : list){
            if(renderable instanceof TextPrompt){
                TextPrompt textPrompt = (TextPrompt)renderable;
                if(textPrompt.getName().equals("playerLives")){
                    continue;
                }
            }
            Renderable clone = renderable.clone();
            if(clone!= null){
                renderables.add(clone);
                if(clone instanceof Player){
                    player = (Player)clone;
                }
            }
        }
        if(player != null){
            renderables.add(player.getLivesPrompt());
        }
    }

    /**
     * Specific operations for loading archives
     * @param window game render object
     * @param model game engine object
     */
    public void LoadArchive(GameWindow window, GameEngine model) {
        if(this.renderables.size() > 0 ){
            model.getRenderables().clear();
            model.getGameObjects().clear();
            window.removeAllEntityView();
            Player player = null ;
            for (Renderable renderable :renderables){
                Renderable clone = renderable.clone();
                if(clone != null){
                    if(renderable instanceof TextPrompt){
                        TextPrompt textPrompt = (TextPrompt)renderable;
                        if(textPrompt.getName().equals("playerLives")){
                            continue;
                        }
                    }
                    model.getRenderables().add(clone);
                    if(clone instanceof Bunker || clone instanceof Cannonball || clone instanceof Enemy || clone instanceof Player){
                        model.getGameObjects().add((GameObject) clone);
                    }
                    if(clone instanceof Player){
                        player = (Player) clone;
                        model.setPlayer(player);
                    }
                }
            }
            if(player != null){
                model.getRenderables().add(player.getLivesPrompt());
            }
            model.setTimes(this.times);
            model.setCurrentEnemyCannonballCount(this.currentEnemyCannonballCount);
            model.setCurrentDeadEnemyCount(this.currentDeadEnemyCount);
        }
    }


}
