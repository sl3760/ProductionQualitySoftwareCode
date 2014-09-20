package nyu.edu.pqs.shuo.canvas;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The View is an implementation of Listener interface which is an observer for
 * the OBSERVER PATTERN. It uses Java's AWT Swing library for creating the
 * layout and frames.
 * 
 * @author Shuo
 * 
 */
public class CanvasView implements CanvasListener {
	private final CanvasModel model;
	private final CanvasPanel panel = new CanvasPanel();

	public CanvasView(CanvasModel model) {
		this.model = model;
		JFrame f = new JFrame("Canvas");
		JPanel mainPanel = new JPanel(new BorderLayout());

		mainPanel.add(panel, BorderLayout.CENTER);

		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				drawEvent(e);
			}
		});

		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				stopEvent();
			}
		});

		f.add(mainPanel);
		f.setSize(400, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		model.addListener(this);
	}

	/**
	 * update the canvas
	 */
	@Override
	public void updateCanvas(CanvasModel Model) {
		panel.updateCanvasPanel(Model);
	}

	/**
	 * It is called when the mouse is dragged.
	 * 
	 * @param e
	 */
	public void drawEvent(MouseEvent e) {
		model.addPointToPolyLine(new Point(e.getX(), e.getY()));
	}

	/**
	 * It is called when the mouse is released.
	 */
	public void stopEvent() {
		model.curPolyLineEnd();
	}
}
