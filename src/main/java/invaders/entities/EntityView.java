package invaders.entities;

import javafx.scene.Node;
import invaders.rendering.Renderable;

/**
 * Game content abstract object
 */
public interface EntityView {
    /**
     * Update location information
     * @param xViewportOffset offset of x
     * @param yViewportOffset offset of y
     */
    void update(double xViewportOffset, double yViewportOffset);

    /**
     * Optional dyeable objects to match game content objects
     * @param entity Renderable objects
     * @return true means the match was successful, false means the match failed.
     */
    boolean matchesEntity(Renderable entity);

    /**
     * Mark for deletion
     */
    void markForDelete();

    /**
     * Get the node object on the page
     * @return
     */
    Node getNode();

    /**
     * Determine whether it has been deleted
     * @return true needs to be deleted, false does not need to be deleted
     */
    boolean isMarkedForDelete();

    /**
     * Get the renderable object corresponding to the content object
     * @return renderable object
     */
    Renderable getEntity();
}
