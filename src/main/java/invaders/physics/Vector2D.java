package invaders.physics;

/**
 * A utility class for storing position information
 */
public class Vector2D {

	/**
	 * x coordinate
	 */
	private double x;
	/**
	 * y coordinate
	 */
	private double y;

	public Vector2D(double x, double y){
		this.x = x;
		this.y = y;
	}

	public double getX(){
		return this.x;
	}

	public double getY(){
		return this.y;
	}

	public void setX(double x){
		this.x = x;
	}

	public void setY(double y){
		this.y = y;
	}
}
