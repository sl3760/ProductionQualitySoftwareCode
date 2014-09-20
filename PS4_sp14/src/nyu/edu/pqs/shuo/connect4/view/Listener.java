package nyu.edu.pqs.shuo.connect4.view;

import java.awt.Color;

/**
 * This is the interface for the View (observer) and declares methods that are
 * required to start the game and listen to messages from the Model.
 * 
 * @author Shuo
 * 
 */
public interface Listener {
	void setGameBoard();

	void gameStart();

	void gameMove(int row, int col, Color color);

	void gameWin(Color color);

	void gameTie();

	void gameRestart();

	void gameExit();
}
