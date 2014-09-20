package nyu.edu.pqs.shuo.connect4.player;

import java.awt.Color;

/**
 * The PlayerFactory uses the FACTORY PATTERN for creating Player object based
 * on a PlayerType. It has a single method which takes a PlayerType and Color
 * object and creates the Player for that Color.
 * 
 * @author Shuo
 * 
 */
public class PlayerFactory {

	/**
	 * This method takes a PlayerType and Color and returns the appropriate
	 * Player.
	 * 
	 * @param type
	 * @param color
	 * @return Player object that is created or null if not
	 */
	public static Player createPlayer(PlayerType type, Color color) {
		if (type == null || color == null) {
			throw new IllegalArgumentException("player type, color " + type + " "
					+ color);
		}
		if (type == PlayerType.COMPUTER) {
			return new ComputerPlayer(color);
		} else if (type == PlayerType.HUMAN) {
			return new HumanPlayer(color);
		}
		return null;
	}
}
