package nyu.edu.pqs.shuo.canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the model class that contains the logic and the data. This class
 * demonstrates the SINGLETON FACTORY PATTERN.There has to be always a single
 * instance of the CanvasModel and this is done by keeping the constructor
 * private. The public static method getModelInstance makes sure to keep a
 * single instance of the class at all times. This class is together with the
 * View, they implement the OBSERVER PATTERN.
 * 
 * @author Shuo
 * 
 */

public class CanvasModel {
	private static CanvasModel model;
	private List<CanvasListener> listeners = new ArrayList<CanvasListener>();
	private List<PolyLine> polyLines = new ArrayList<PolyLine>();
	private PolyLine curPolyLine;

	private CanvasModel() {
	}

	/**
	 * This method is used to return the only one instance of the CanvasModel.
	 * 
	 * @return
	 */
	public static CanvasModel getModelInstance() {
		if (model == null) {
			return new CanvasModel();
		}
		return model;
	}

	/**
	 * listener is associated with the model
	 * 
	 * @param listener
	 */
	public void addListener(CanvasListener listener) {
		listeners.add(listener);
	}

	/**
	 * listener is not associated with the model
	 * 
	 * @param listener
	 */
	public void removeListener(CanvasListener listener) {
		listeners.remove(listener);
	}

	/**
	 * add a line to the model
	 * 
	 * @param line
	 */
	public void addPolyLine(PolyLine line) {
		polyLines.add(line);
	}

	/**
	 * remove a line from the model
	 * 
	 * @param line
	 */
	public void removePolyLine(PolyLine line) {
		polyLines.remove(line);
	}

	/**
	 * for test
	 * 
	 * @return
	 */
	public List<PolyLine> getPolyLines() {
		return new ArrayList<PolyLine>(polyLines);
	}

	/**
	 * add a point to the current line
	 * 
	 * @param p
	 */
	public void addPointToPolyLine(Point p) {
		if (curPolyLine == null) {
			curPolyLine = new PolyLine();
			addPolyLine(curPolyLine);
		}
		curPolyLine.addPoint(p);
		fireUpdateCanvasEvent();
	}

	/**
	 * It is called when the mouse released
	 */
	public void curPolyLineEnd() {
		curPolyLine = null;
		fireUpdateCanvasEvent();
	}

	/**
	 * for test
	 * 
	 * @return
	 */
	public PolyLine getCurPolyLine() {
		return curPolyLine;
	}

	/**
	 * It is called when user draws on the canvas
	 */
	public void fireUpdateCanvasEvent() {
		for (CanvasListener listener : listeners) {
			listener.updateCanvas(this);
		}
	}

}
