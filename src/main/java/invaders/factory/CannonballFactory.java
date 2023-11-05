package invaders.factory;

import invaders.entities.Cannonball;
import invaders.entities.LaserCannonball;
import invaders.entities.NormalCannonball;
import invaders.entities.PlayerCannonball;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;

/**
 * All shell production factory categories
 */
public class CannonballFactory  {


    /**
     * Create a cannonball object
     * @param type shell type
     * @param x x coordinate of the cannonball
     * @param y The y coordinate of the cannonball
     * @return Created cannonball entity object
     */
    public static Cannonball createCannonball(String type, double x, double y){
        Cannonball cannonball = null ;
        if("player".equals(type)){
            cannonball = new PlayerCannonball(new Vector2D(x,y));
        }else if("slow_straight".equals(type)){
            cannonball = new NormalCannonball(new Vector2D(x,y));
        }else if("fast_straight".equals(type)){
            cannonball = new LaserCannonball(new Vector2D(x,y));
        }
        return cannonball;
    }
}
