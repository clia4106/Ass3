package invaders.entities;

import invaders.GameObject;
import invaders.physics.Moveable;
import invaders.rendering.Renderable;
import invaders.strategy.CannonballStrategy;

/**
 * abstraction of all cannonballs
 */
public interface Cannonball extends GameObject, CannonballStrategy , Renderable, Moveable {
}
