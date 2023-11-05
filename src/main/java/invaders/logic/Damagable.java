package invaders.logic;

/**
 * hurt abstract
 */
public interface Damagable {

	/**
	 * Specific treatment for injuries
	 * @param amount value of damage taken
	 */
	public void takeDamage(double amount);

	/**
	 * Get remaining blood volume
	 * @return Remaining blood volume
	 */
	public double getHealth();

	/**
	 * Is it still alive?
	 * @return true alive false dead
	 */
	public boolean isAlive();

}
