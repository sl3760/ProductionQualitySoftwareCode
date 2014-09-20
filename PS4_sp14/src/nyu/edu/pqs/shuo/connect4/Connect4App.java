package nyu.edu.pqs.shuo.connect4;

import nyu.edu.pqs.shuo.connect4.view.*;

/**
 * This class is the entrance to the game.
 * @author Shuo
 *
 */
public class Connect4App {
	
	/**
	 * The method will start this game.
	 */
	public void startGame(){
		new MainView();
	}
	
	public static void main(String[] args){
		new Connect4App().startGame();
	}
}
