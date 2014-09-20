package nyu.edu.pqs.shuo.canvas;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

	@Test
	public void testGetX(){
		Point point = new Point(1,2);
		int expectedX=1;
		int actualX=point.getX();
		assertEquals(expectedX, actualX);
	}

	@Test
	public void testGetY(){
		Point point = new Point(1,2);
		int expectedX=2;
		int actualX=point.getY();
		assertEquals(expectedX, actualX);
	}
	
	@Test
	public void testToString(){
		Point point = new Point(1,2);
		String expected = "(1 , 2)";
		String actual = point.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEquals(){
		Point point1 = new Point(1,2);
		Point point2 = new Point(1,2);
		assertTrue(point1.equals(point2));
	}
	
	@Test
	public void testNotEquals(){
		Point point1 = new Point(1,2);
		Point point2 = new Point(2,2);
		assertFalse(point1.equals(point2));
	}
}
