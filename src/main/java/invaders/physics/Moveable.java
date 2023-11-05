package invaders.physics;

// represents something that can move up, down, left, right
public interface Moveable {

	/**
	 * Move up
	 */
	public void up();

	/**
	 * Move down
	 */

	public void down();

	/**
	 * Move left
	 */
	public void left();

	/**
	 * Move right
	 */
	public void right();
}
