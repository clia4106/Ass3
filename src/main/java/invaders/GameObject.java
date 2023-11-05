package invaders;

import invaders.engine.GameEngine;
import invaders.entities.EntityView;
import invaders.rendering.Renderable;

import java.util.List;

/**
 * contains basic methods that all GameObjects must implement
 */
public interface GameObject {

    /**
     * Data updates for all game objects every frame
     * @param model Game Engine
     * @param entityViews All view objects on the map
     * @return The Renderable object currently being added to the map
     */
    public List<Renderable> update(GameEngine model, List<EntityView> entityViews);
}
