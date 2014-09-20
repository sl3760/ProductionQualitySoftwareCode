package nyu.edu.pqs.shuo.canvas;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PolyLineTest {
	
	@Test
	public void testAddPoint() {
		PolyLine polyLine = new PolyLine();
		Point p = new Point(1,2);
		polyLine.addPoint(p);
		ArrayList<Point> line = new ArrayList<Point>();
		line.add(p);
		assertEquals(polyLine.getPoints(),line);
	}
	
	@Test
	public void testRemovePoint(){
		PolyLine polyLine = new PolyLine();
		Point p1 = new Point(1,2);
		Point p2 = new Point(2,1);
		polyLine.addPoint(p1);
		polyLine.addPoint(p2);
		polyLine.removePoint(p1);
		ArrayList<Point> line = new ArrayList<Point>();
		line.add(p2);
		assertEquals(polyLine.getPoints(),line);
	}
	
	@Test
	public void testEquals(){
		PolyLine polyLine1 = new PolyLine();
		PolyLine polyLine2 = new PolyLine();
		Point p1 = new Point(1,2);
		Point p2 = new Point(2,1);
		polyLine1.addPoint(p1);
		polyLine1.addPoint(p2);
		polyLine2.addPoint(p1);
		polyLine2.addPoint(p2);
		assertTrue(polyLine1.equals(polyLine2));
	}

}
