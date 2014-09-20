package nyu.edu.pqs.shuo.connect4.player;

import java.awt.Color;

import nyu.edu.pqs.shuo.connect4.model.Model;

import java.security.SecureRandom;

/**
 * ComputerPlayer is an implementation of the Player interface. It is used as
 * the AI for playing single player games.
 * 
 * @author Shuo
 * 
 */
public class ComputerPlayer implements Player {

	private Color playerColor;
	private int dropCol;

	public ComputerPlayer(Color color) {
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
	 * This method is where the Player makes its move.
	 */
	@Override
	public void move(Model model) {
		if (model == null) {
			throw new IllegalArgumentException("connect4 " + null);
		}
		int i = 0;
		while (i < 7) {
			if (model.isWin(i, playerColor)) {
				setColumnForMove(i);
				model.makeMove(playerColor, dropCol);
				break;
			}
			i++;
		}
		if (i == 7) {
			int j = 0;
			while (j < 7) {
				if (model.isWin(j, Color.red)) {
					setColumnForMove(j);
					model.makeMove(playerColor, dropCol);
					break;
				}
				j++;
			}
			if (j == 7) {
				SecureRandom random = new SecureRandom();
				while (true) {
					int rand = random.nextInt(7);
					if (model.canDrop(rand)) {
						setColumnForMove(rand);
						model.makeMove(playerColor, dropCol);
						break;
					}
				}
			}
		}
	}

	/**
	 * This method returns the column of the last move made by the Computer player
	 */
	@Override
	public int getColunmForMove() {
		return this.dropCol;
	}

	/**
	 * This method returns the Color of itself
	 */
	@Override
	public Color getColor() {
		return playerColor;
	}

	/**
	 * This method returns the type of the player
	 */
	@Override
	public PlayerType getPlayerType() {
		return PlayerType.COMPUTER;
	}

	@Override
	public String toString() {
		if (this.playerColor == Color.red){
			return "ComputerPlayer Color: RED Column: " + this.dropCol;
		}else{
			return "ComputerPlayer Color: YELLOW Column: " + this.dropCol;
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
		if (!(obj instanceof ComputerPlayer)) {
			return false;
		}
		ComputerPlayer other = (ComputerPlayer) obj;
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
