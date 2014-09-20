package test;
import static org.junit.Assert.*;

import java.awt.Color;

import nyu.edu.pqs.shuo.connect4.model.Model;

import org.junit.Test;

public class ModelTest {

	Model model = Model.getModelInstance();

	@Test
	public void testIsBoardFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 7; j++) {
				model.setBoard(i, j, Color.red);
			}
		}
		assertTrue(!model.isBoardFull());
		for (int i = 3; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				model.setBoard(i, j, Color.yellow);
			}
		}
		assertTrue(model.isBoardFull());
	}

	@Test
	public void testCheckHorizontal() {
		model.setBoard(5, 1, Color.red);
		model.setBoard(5, 2, Color.red);
		model.setBoard(5, 3, Color.red);
		model.setBoard(5, 4, Color.red);
		assertTrue(model.checkHorizontal(5, 4));
		model.setBoard(2, 1, Color.red);
		model.setBoard(2, 2, Color.yellow);
		model.setBoard(2, 3, Color.red);
		model.setBoard(2, 4, Color.red);
		assertTrue(!model.checkHorizontal(2, 4));
	}

	@Test
	public void testCheckVertical() {
		model.setBoard(1, 2, Color.red);
		model.setBoard(2, 2, Color.red);
		model.setBoard(3, 2, Color.red);
		model.setBoard(4, 2, Color.red);
		assertTrue(model.checkVertical(1, 2));
		model.setBoard(1, 4, Color.red);
		model.setBoard(2, 4, Color.yellow);
		model.setBoard(3, 4, Color.red);
		model.setBoard(4, 4, Color.red);
		assertTrue(!model.checkVertical(4, 4));
	}

	@Test
	public void testCheckDiagonal() {
		model.setBoard(1, 1, Color.red);
		model.setBoard(2, 2, Color.red);
		model.setBoard(3, 3, Color.red);
		model.setBoard(4, 4, Color.red);
		assertTrue(model.checkDiagonal(1, 1));
		model.setBoard(1, 5, Color.yellow);
		model.setBoard(2, 4, Color.yellow);
		model.setBoard(3, 3, Color.yellow);
		model.setBoard(4, 2, Color.red);
		assertTrue(!model.checkDiagonal(3,3));
	}

	@Test
	public void testJudgeWinWithHorizontal() {
		model.setBoard(5, 1, Color.red);
		model.setBoard(5, 2, Color.red);
		model.setBoard(5, 3, Color.red);
		model.setBoard(5, 4, Color.red);
		assertTrue(model.judgeWin(5, 2));
	}

	@Test
	public void testJudgeWinWithVertical() {
		model.setBoard(1, 2, Color.red);
		model.setBoard(2, 2, Color.red);
		model.setBoard(3, 2, Color.red);
		model.setBoard(4, 2, Color.red);
		assertTrue(model.judgeWin(4, 2));
	}

	@Test
	public void testJudgeWinWithDiagonal() {
		model.setBoard(1, 1, Color.red);
		model.setBoard(2, 2, Color.red);
		model.setBoard(3, 3, Color.red);
		model.setBoard(4, 4, Color.red);
		assertTrue(model.judgeWin(4, 4));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIsWinWithNegativeColumn() {
		model.isWin(-1, Color.red);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIsWinWithGreaterColumn() {
		model.isWin(10, Color.red);
	}

	@Test
	public void testIsWinWithFullColumn() {
		model.setRows(1, 5);
		assertTrue(!model.isWin(1, Color.red));

	}
	
	@Test
	public void testIsWinWithNo() {
		model.setRows(5, 5);
		model.setBoard(2, 2, Color.red);
		model.setBoard(3, 3, Color.red);
		model.setBoard(4, 4, Color.yellow);
		assertTrue(!model.isWin(1, Color.red));
	}
	
	@Test
	public void testIsWinWithYes() {
		model.setRows(5, 5);
		model.setBoard(2, 2, Color.red);
		model.setBoard(3, 3, Color.red);
		model.setBoard(4, 4, Color.red);
		assertTrue(model.isWin(1, Color.red));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCanDropWithNegativeColumn() {
		model.canDrop(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCanDropWithGreaterColumn() {
		model.canDrop(10);
	}
	
	@Test
	public void testCanDropWithYes() {
		model.setRows(1, 5);
		model.setRows(2, 4);
		assertTrue(!model.canDrop(1));
		assertTrue(model.canDrop(2));

	}

}
