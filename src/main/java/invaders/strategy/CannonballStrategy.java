package invaders.strategy;

/**
 * Cannonball movement speed strategy abstract
 */
public interface CannonballStrategy {

    /**
     * Cannonball base movement speed
     */
    static final  int baseSpeed = 2;

    /**
     * Get the cannonball movement speed
     * @return Cannonball moving speed
     */
    public int getSpeed();

}
