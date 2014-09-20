package test;

import static org.junit.Assert.*;

import java.awt.Color;

import nyu.edu.pqs.shuo.connect4.player.HumanPlayer;
import nyu.edu.pqs.shuo.connect4.player.PlayerType;

import org.junit.Test;

public class HumanPlayerTest {

	HumanPlayer humanPlayer = new HumanPlayer(Color.red);

	@Test
	public void testGetColor() {
		Color expectedColor = Color.red;
		Color actualColor = humanPlayer.getColor();
		assertEquals(expectedColor, actualColor);
	}
	
	@Test
	public void testGetPlayerType(){
		PlayerType expectedType=PlayerType.HUMAN;
		PlayerType actualType=humanPlayer.getPlayerType();
		assertEquals(expectedType, actualType);
	}
	
	@Test
	public void testSetColumnForMove(){
		humanPlayer.setColumnForMove(2);
		int expectedCol=2;
		int actualCol=humanPlayer.getColunmForMove();
		assertEquals(expectedCol, actualCol);
	}
	
	@Test
	public void testToString(){
		humanPlayer.setColumnForMove(1);
		String expected="HumanPlayer Color: RED Column: 1";
		String actual=humanPlayer.toString();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEquals(){
		HumanPlayer humanPlayer1 = new HumanPlayer(Color.red);
		HumanPlayer humanPlayer2 = new HumanPlayer(Color.red);
		humanPlayer1.setColumnForMove(3);
		humanPlayer2.setColumnForMove(3);
		assertTrue(humanPlayer1.equals(humanPlayer2));
	}
	
	@Test
	public void testNotEquals(){
		HumanPlayer humanPlayer1 = new HumanPlayer(Color.red);
		HumanPlayer humanPlayer2 = new HumanPlayer(Color.red);
		humanPlayer1.setColumnForMove(3);
		humanPlayer2.setColumnForMove(4);
		assertTrue(!humanPlayer1.equals(humanPlayer2));
	}

}
