package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import nyu.edu.pqs.shuo.connect4.player.ComputerPlayer;
import nyu.edu.pqs.shuo.connect4.player.PlayerType;

import org.junit.Test;

public class ComputerPlayerTest {
	ComputerPlayer computerPlayer = new ComputerPlayer(Color.yellow);

	@Test
	public void testGetColor() {
		Color expectedColor = Color.yellow;
		Color actualColor = computerPlayer.getColor();
		assertEquals(expectedColor, actualColor);
	}
	
	@Test
	public void testGetPlayerType(){
		PlayerType expectedType=PlayerType.COMPUTER;
		PlayerType actualType=computerPlayer.getPlayerType();
		assertEquals(expectedType, actualType);
	}
	
	@Test
	public void testSetColumnForMove(){
		computerPlayer.setColumnForMove(2);
		int expectedCol=2;
		int actualCol=computerPlayer.getColunmForMove();
		assertEquals(expectedCol, actualCol);
	}
	
	@Test
	public void testToString(){
		computerPlayer.setColumnForMove(3);
		String expected="ComputerPlayer Color: YELLOW Column: 3";
		String actual=computerPlayer.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEquals(){
		ComputerPlayer computerPlayer1 = new ComputerPlayer(Color.yellow);
		ComputerPlayer computerPlayer2 = new ComputerPlayer(Color.yellow);
		computerPlayer1.setColumnForMove(3);
		computerPlayer2.setColumnForMove(3);
		assertTrue(computerPlayer1.equals(computerPlayer2));
	}
	
	@Test
	public void testNotEquals(){
		ComputerPlayer computerPlayer1 = new ComputerPlayer(Color.yellow);
		ComputerPlayer computerPlayer2 = new ComputerPlayer(Color.yellow);
		computerPlayer1.setColumnForMove(3);
		computerPlayer2.setColumnForMove(4);
		assertTrue(!computerPlayer1.equals(computerPlayer2));
	}

}
