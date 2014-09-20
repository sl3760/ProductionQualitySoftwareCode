package nyu.edu.pqs.shuo.canvas;

/**
 * Class representing a 2d point
 * 
 * @author Shuo
 * 
 */
public class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	@Override
	public String toString() {
		return "(" + x + " , " + y + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Point)) {
			return false;
		}
		Point other = (Point) obj;
		return this.x == other.getX() && this.y == other.getY();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result + this.x;
		result = prime * result + this.y;
		return result;
	}

}
