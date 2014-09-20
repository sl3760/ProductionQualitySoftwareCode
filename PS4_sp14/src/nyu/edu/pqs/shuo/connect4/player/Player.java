package nyu.edu.pqs.shuo.connect4.player;

import java.awt.Color;

import nyu.edu.pqs.shuo.connect4.model.Model;

/**
 * The Player interface is used by any player. It declares the methods needed to
 * perform the Player roles such as play, setColumnForMove and few getter
 * methods to help other classes.
 * 
 * @author Shuo
 * 
 */
public interface Player {
	public void setColumnForMove(int col);

	public int getColunmForMove();

	public void move(Model model);

	public Color getColor();

	public PlayerType getPlayerType();
}
