package invaders.rendering;

import invaders.engine.GameEngine;
import invaders.entities.EntityView;
import invaders.physics.Vector2D;
import javafx.scene.image.Image;

import java.util.List;

/**
 * Represents something that can be rendered
 */
public interface Renderable{

    /**
     * Get the rendered image
     * @return Rendered image
     */
    public Image getImage();

    /**
     * Get width
     * @return width
     */
    public double getWidth();

    /**
     * get height
     * @return height
     */
    public double getHeight();

    /**
     * get position
     * @return position
     */
    public Vector2D getPosition();

    /**
     * get layer
     * @return layer
     */
    public Layer getLayer();

    /**
     * Collision logic processing
     * @param model game engine object
     * @param entityViews  Collection of content objects on all pages
     * @param renderable the object being collided
     */
    public void dealColliding(GameEngine model, List<EntityView> entityViews,Renderable renderable);


    /**
     * The set of available layers
     */
    public static enum Layer {
        BACKGROUND, FOREGROUND, EFFECT
    }


    /**
     * Clone a new entity object
     * @return cloned object
     */
    public Renderable clone();
}
