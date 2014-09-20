package nyu.edu.pqs.shuo.connect4.player;

import java.awt.Color;

import nyu.edu.pqs.shuo.connect4.model.Model;

/**
 * HumanPlayer is an implementation of the Player interface. It is used as the
 * human for playing single player games or double player games.
 * 
 * @author Shuo
 * 
 */
public class HumanPlayer implements Player {

	private Color playerColor;
	private int dropCol;

	public HumanPlayer(Color color) {
		this.playerColor = color;
	}

	/**
	 * This method is used by the model to denote to the player object where the
	 * drop column move has to be made.
	 */
	@Override
	public void setColumnForMove(int col) {
		this.dropCol = col;
	}

	/**
	 * This method returns the column of the last move made by the Human player
	 */
	@Override
	public int getColunmForMove() {
		return this.dropCol;
	}

	/**
	 * This method is where the Player makes its move.
	 */
	@Override
	public void move(Model model) {
		if (model == null) {
			throw new IllegalArgumentException("connect4 " + null);
		}
		model.makeMove(playerColor, dropCol);
	}

	/**
	 * This method returns the Color for this player
	 */
	@Override
	public Color getColor() {
		return playerColor;
	}

	/**
	 * This method returns the type of the player which is HUMAN
	 */
	@Override
	public PlayerType getPlayerType() {
		return PlayerType.HUMAN;
	}

	@Override
	public String toString() {
		if (this.playerColor == Color.red) {
			return "HumanPlayer Color: RED Column: " + this.dropCol;
		} else {
			return "HumanPlayer Color: YELLOW Column: " + this.dropCol;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 17;
		result = prime * result
				+ ((playerColor == null) ? 0 : playerColor.hashCode());
		result = prime * result + dropCol;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof HumanPlayer)) {
			return false;
		}
		HumanPlayer other = (HumanPlayer) obj;
		if (playerColor == null) {
			if (other.playerColor != null) {
				return false;
			}
		} else {
			if (!playerColor.equals(other.playerColor)) {
				return false;
			}
		}
		if (dropCol != other.dropCol) {
			return false;
		}
		return true;
	}

}
