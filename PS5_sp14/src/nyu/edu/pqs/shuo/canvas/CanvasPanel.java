package nyu.edu.pqs.shuo.canvas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Class representing the canvas panel, subclass of JPanel
 * 
 * @author Shuo
 * 
 */
public class CanvasPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private CanvasModel model;

	public CanvasPanel() {
		setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (model != null && model.getPolyLines() != null) {
			for (PolyLine line : model.getPolyLines()) {
				List<Point> points = line.getPoints();
				int[] XPoints = new int[points.size()];
				int[] YPoints = new int[points.size()];
				int i = 0;
				for (Point p : points) {
					XPoints[i] = p.getX();
					YPoints[i] = p.getY();
					i++;
				}
				g.drawPolyline(XPoints, YPoints, XPoints.length);
			}
		}
	}

	public void updateCanvasPanel(CanvasModel model) {
		this.model = model;
		repaint();
	}
}
