package nyu.edu.pqs.shuo.connect4.model;

import nyu.edu.pqs.shuo.connect4.player.GameMode;
import nyu.edu.pqs.shuo.connect4.player.Player;
import nyu.edu.pqs.shuo.connect4.player.PlayerFactory;
import nyu.edu.pqs.shuo.connect4.player.PlayerType;
import nyu.edu.pqs.shuo.connect4.view.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the model class that contains the game logic (i.e. rules) and the
 * data (i.e. the board). This class demonstrates the SINGLETON FACTORY PATTERN.
 * There has to be always a single instance of the Model and this is done by
 * keeping the constructor private. The public static method getModelInstance
 * makes sure to keep a single instance of the class at all times. This class is
 * together with the View, they implement the OBSERVER PATTERN.
 * 
 * @author Shuo
 * 
 */
public class Model {

	private List<Listener> listeners = new ArrayList<Listener>();
	private Color[][] board = new Color[6][7];
	private int[] rows = new int[7];
	private int row;
	private GameMode mode;
	private Player redPlayer;
	private Player yellowPlayer;
	private Color turn;

	private static final Model model = new Model();

	private Model() {
	}

	/**
	 * This method is used to return the only one instance of the Model.
	 * 
	 * @return
	 */
	public static Model getModelInstance() {
		return model;
	}

	/**
	 * listener is associated with the model
	 * 
	 * @param listerner
	 */
	public void register(Listener listerner) {
		listeners.add(listerner);
	}

	/**
	 * listener is disassociated with the model.
	 * 
	 * @param listener
	 */
	public void remove(Listener listener) {
		listeners.remove(listener);
	}

	/**
	 * It is called when the round ends and initialize the parameters.
	 */
	public void init() {
		for (int i = 0; i < 7; i++) {
			rows[i] = 0;
		}
		turn = Color.red;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				board[i][j] = null;
			}
		}
	}

	/**
	 * It is called when the player makes a move.
	 * 
	 * @param col
	 */
	public void drop(int col) {
		if (rows[col] >= 6) {
			throw new IllegalArgumentException("This column has been full!");
		}
		if (mode == GameMode.SINGLE) {
			redPlayer.setColumnForMove(col);
			redPlayer.move(this);
			yellowPlayer.move(this);
		} else if (mode == GameMode.DOUBLE) {
			if (turn == Color.red) {
				redPlayer.setColumnForMove(col);
				redPlayer.move(this);
				turn = Color.yellow;
			} else {
				yellowPlayer.setColumnForMove(col);
				yellowPlayer.move(this);
				turn = Color.red;
			}
		}
	}

	/**
	 * It is called by the player.
	 * 
	 * @param color
	 * @param col
	 */
	public void makeMove(Color color, int col) {
		row = rows[col]++;
		board[row][col] = color;
		fireGameMoveEvent(row, col, color);
		if (judgeWin(row, col)) {
			fireGameWinEvent(color);
		}
		if (isBoardFull()) {
			fireGameTieEvent();
		}
	}

	/**
	 * It is called to create two players or create a player and a computer player
	 * according to the mode the player chooses.
	 * 
	 * @param mode
	 */
	public void createPlayers(GameMode mode) {
		if (mode == GameMode.SINGLE) {
			redPlayer = PlayerFactory.createPlayer(PlayerType.HUMAN, Color.red);
			yellowPlayer = PlayerFactory.createPlayer(PlayerType.COMPUTER,
					Color.yellow);
		} else if (mode == GameMode.DOUBLE) {
			redPlayer = PlayerFactory.createPlayer(PlayerType.HUMAN, Color.red);
			yellowPlayer = PlayerFactory.createPlayer(PlayerType.HUMAN, Color.yellow);
		}
		turn = Color.red;
		this.mode = mode;
	}

	/**
	 * This method notifies all the viewers of the model that the player has chose
	 * the mode.
	 */
	public void fireSetGameBoardEvent() {
		for (Listener listener : listeners) {
			listener.setGameBoard();
		}
	}

	/**
	 * This method notifies all the viewers of the model that the game has
	 * started.
	 */
	public void fireGameStartEvent() {
		for (Listener listener : listeners) {
			listener.gameStart();
		}
	}

	/**
	 * This method notifies all the viewers of the model that the game has
	 * restarted.
	 */
	public void fireGameRestartEvent() {
		this.init();
		for (Listener listener : listeners) {
			listener.gameRestart();
			listener.gameStart();
		}
	}

	/**
	 * This method notifies all the viewers of the model that the player has made
	 * a move.
	 * 
	 * @param row
	 * @param col
	 * @param color
	 */
	public void fireGameMoveEvent(int row, int col, Color color) {
		for (Listener listener : listeners) {
			listener.gameMove(row, col, color);
		}
	}

	/**
	 * This method notifies all the viewers of the model that the player has won
	 * this game.
	 * 
	 * @param color
	 */
	public void fireGameWinEvent(Color color) {
		for (Listener listener : listeners) {
			listener.gameWin(color);
		}
	}

	/**
	 * This method notifies all the viewers of the model that the game is a tie.
	 */
	public void fireGameTieEvent() {
		for (Listener listener : listeners) {
			listener.gameTie();
		}
	}

	/**
	 * This method notifies all the viewers of the model that the game has exited.
	 */
	public void fireGameExitEvent() {
		for (Listener listener : listeners) {
			listener.gameExit();
		}
	}

	/**
	 * Check if there is a horizontal win that includes the row,col combination.
	 * It is called at every stage of the game to check if horizontal win has
	 * occurred and therefore requires the row,col combination
	 * 
	 * @param row
	 * @param col
	 * @return true if horizontal win else false
	 */
	public boolean checkHorizontal(int row, int col) {
		int win = 0;
		int i = col;
		Color c = board[row][col];
		while (i <= 6 && board[row][i] == c && win < 4) {
			win++;
			i++;
		}
		i = col;
		while (i > 0 && board[row][i - 1] == c && win < 4) {
			win++;
			i--;
		}
		if (win == 4) {
			return true;
		}
		return false;
	}

	/**
	 * Check if there is a vertical win that includes the row,col combination. It
	 * is called at every stage of the game to check if vertical win has occurred
	 * and therefore requires the row,col combination
	 * 
	 * @param row
	 * @param col
	 * @return true if vertical win else false
	 */
	public boolean checkVertical(int row, int col) {
		int win = 0;
		int i = row;
		Color c = board[row][col];
		while (i <= 5 && board[i][col] == c && win < 4) {
			win++;
			i++;
		}
		i = row;
		while (i > 0 && board[i - 1][col] == c && win < 4) {
			win++;
			i--;
		}
		if (win == 4) {
			return true;
		}
		return false;
	}

	/**
	 * Check if there is a diagonal win that includes the row,col combination. It
	 * is called at every stage of the game to check if a diagonal win has
	 * occurred and therefore requires the row,col combination
	 * 
	 * @param row
	 * @param col
	 * @return true if diagonal win else false
	 */
	public boolean checkDiagonal(int row, int col) {
		int winForward = 0;
		int winBackward = 0;
		int i = row;
		int j = col;
		Color c = board[i][j];
		// go downward forward
		while (i < 6 && j < 7 && board[i][j] == c && winForward < 4) {
			winForward++;
			i++;
			j++;
		}
		i = row;
		j = col;
		// go upward backward
		while (i > 0 && j > 0 && board[i - 1][j - 1] == c && winForward < 4) {
			winForward++;
			i--;
			j--;
		}
		i = row;
		j = col;
		// go upward forward
		while (i > 0 && j < 6 && board[i - 1][j + 1] == c && winBackward < 4) {
			winBackward++;
			i--;
			j++;
		}
		i = row;
		j = col;
		// go downward backward
		while (i < 6 && j >= 0 && board[i][j] == c && winBackward < 4) {
			winBackward++;
			i++;
			j--;
		}
		if (winForward == 4 || winBackward == 4) {
			return true;
		}
		return false;
	}

	/**
	 * Check if there is a winner horizontally, vertically or diagonally with the
	 * row,col.
	 * 
	 * @param row
	 * @param col
	 * @return true if there is a win else false.
	 */
	public boolean judgeWin(int row, int col) {
		if (checkHorizontal(row, col)) {
			return true;
		} else if (checkVertical(row, col)) {
			return true;
		} else if (checkDiagonal(row, col)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if the board is full
	 * 
	 * @return true if board full else false.
	 */
	public boolean isBoardFull() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (board[i][j] == null) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * It is called by the computer player to check if there is a winner
	 * horizontally, vertically or diagonally with the row,col when playing with
	 * computer.
	 * 
	 * @param col
	 * @param color
	 * @return true if there is win else false.
	 */
	public boolean isWin(int col, Color color) {
		if (col < 0 || col > 6) {
			throw new IllegalArgumentException("col " + col);
		}
		int i = rows[col];
		if (i >= 6) {
			return false;
		}
		board[i][col] = color;
		if (judgeWin(i, col)) {
			board[i][col] = null;
			return true;
		} else {
			board[i][col] = null;
			return false;
		}
	}

	/**
	 * It is called by the computer player to check if it is legal to drop in this
	 * col.
	 * 
	 * @param col
	 * @return true if drop is legal else false.
	 */
	public boolean canDrop(int col) {
		if (col < 0 || col > 6) {
			throw new IllegalArgumentException("col " + col);
		}
		int i = rows[col];
		i++;
		if (i >= 6) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * This is just for test.
	 * @param row
	 * @param col
	 * @param color
	 */
	public void setBoard(int row, int col, Color color){
		this.board[row][col]=color;
	}

	/**
	 * This is just for test.
	 * @param col
	 * @param row
	 */
	public void setRows(int col, int row){
		rows[col]=row;
	}
}
