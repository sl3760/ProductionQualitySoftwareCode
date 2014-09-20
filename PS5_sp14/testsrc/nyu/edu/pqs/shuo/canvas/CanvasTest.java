package nyu.edu.pqs.shuo.canvas;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CanvasTest {

	CanvasModel model = CanvasModel.getModelInstance();
	
	@Test
	public void testAddPolyLine(){
		PolyLine line = new PolyLine();
		line.addPoint(new Point(1,1));
		line.addPoint(new Point(2,1));
		model.addPolyLine(line);
		ArrayList<PolyLine> lines = new ArrayList<PolyLine>();
		lines.add(line);
    assertEquals(model.getPolyLines(),lines);
	}
	
	@Test
	public void testRemovePolyLine(){
		PolyLine line1 = new PolyLine();
		PolyLine line2 = new PolyLine();
		line1.addPoint(new Point(1,1));
		line2.addPoint(new Point(2,6));
		model.addPolyLine(line1);
		model.addPolyLine(line2);
		model.removePolyLine(line1);
		ArrayList<PolyLine> lines = new ArrayList<PolyLine>();
		lines.add(line2);
    assertEquals(model.getPolyLines(),lines);
	}

	@Test
	public void testAddPointToPolyLine(){
		assertNull(model.getCurPolyLine());
		Point point1 = new Point(1,2);
		Point point2 = new Point(2,1);
		model.addPointToPolyLine(point1);
		model.addPointToPolyLine(point2);
		assertNotNull(model.getCurPolyLine());
		PolyLine line = new PolyLine();
		line.addPoint(point1);
		line.addPoint(point2);
		assertEquals(model.getCurPolyLine(),line);
	}
	
	@Test
	public void testCurPolyLineEnd(){
		assertNull(model.getCurPolyLine());
		Point point = new Point(1,2);
		model.addPointToPolyLine(point);
		assertNotNull(model.getCurPolyLine());
		PolyLine line = new PolyLine();
		line.addPoint(point);
		assertEquals(model.getCurPolyLine(),line);
		model.curPolyLineEnd();
		assertNull(model.getCurPolyLine());
	}
	
}
