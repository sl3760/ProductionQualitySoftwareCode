package nyu.edu.pqs.shuo.canvas;

/**
 * This class is the entrance to the game.
 * 
 * @author Shuo
 * 
 */

public class CanvasApp {
	/**
	 * The method will start this game.
	 */
	public void go() {
		CanvasModel model = CanvasModel.getModelInstance();
		new CanvasView(model);
		new CanvasView(model);
		new CanvasView(model);
		new CanvasView(model);
		new CanvasView(model);
	}

	public static void main(String[] args) {
		new CanvasApp().go();
	}
}
