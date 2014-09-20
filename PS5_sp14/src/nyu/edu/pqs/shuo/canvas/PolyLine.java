package nyu.edu.pqs.shuo.canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a poly line consists of multiple points
 * 
 * @author Shuo
 * 
 */
public class PolyLine {
	private List<Point> polyLine = new ArrayList<Point>();

	public void addPoint(Point p) {
		polyLine.add(p);
	}

	public void removePoint(Point p) {
		polyLine.remove(p);
	}

	public List<Point> getPoints() {
		return new ArrayList<Point>(polyLine);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PolyLine)) {
			return false;
		}
		PolyLine other = (PolyLine) obj;
		if (polyLine.size() != other.getPoints().size()) {
			return false;
		}
		for (int i = 0; i < polyLine.size(); i++) {
			if (!(polyLine.get(i).equals(other.getPoints().get(i)))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		for (int i = 0; i < polyLine.size(); i++) {
			result = prime * result + polyLine.get(i).hashCode();
		}
		return result;
	}
}
