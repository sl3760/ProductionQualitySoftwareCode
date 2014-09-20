package nyu.edu.pqs.shuo.connect4.view;

import nyu.edu.pqs.shuo.connect4.model.Model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * The View is an implementation of Listener interface which is an observer for
 * the OBSERVER PATTERN. It uses Java's AWT Swing library for creating the
 * layout and frames. It contains methods to start, restart, exit the game and
 * listeners for events from the model.
 * 
 * @author Shuo
 * 
 */
public class View implements Listener, ActionListener {

	private Model model;
	private Color playerView;
	private int row = 6;
	private int col = 7;
	private JFrame menuFrame = new JFrame("Connect4");
	private JFrame gameFrame = new JFrame("Connect4");
	private JPanel gameBoardPanel = new JPanel(new GridLayout(row, col));
	private JPanel dropButtonPanel = new JPanel();

	private JButton startButton = new JButton("Start");
	private JButton restartButton = new JButton("Restart");
	private JButton exitButton = new JButton("Exit");
	private JPanel buttonPanel = new JPanel();
	private JButton[] dropButtons = new JButton[col];
	private JPanel[][] boardPanels = new JPanel[row][col];
	private Border grayLine = BorderFactory.createLineBorder(Color.GRAY);
	private JTextArea text = new JTextArea();
	private JTextField infoText = new JTextField();
	private JTextArea resultText = new JTextArea();
	private JScrollPane scollPane = new JScrollPane(infoText);
	private String redTurn = "Now Red Color Player Turn!";
	private String yellowTurn = "Now Yellow Color Player Turn!";

	public View(Model model, Color color) {
		this.model = model;
		this.model.register(this);
		this.playerView = color;
		text.setText("Welcome to Connect4 Game!");
		Font x = new Font("Serif", 1, 24);
		text.setFont(x);
		this.setGameBoard();
		this.buttonsWithActionListeners();
	}

	/**
	 * This method will add action listeners to the buttons in the game
	 */
	private void buttonsWithActionListeners() {
		startButton.addActionListener(this);
		restartButton.addActionListener(this);
		exitButton.addActionListener(this);
	}

	/**
	 * It is started if the user choose the game mode and provides a menu view
	 * including start or restart and exit.
	 */
	@Override
	public void setGameBoard() {
		dropButtons = new JButton[col];
		startButton.setPreferredSize(new Dimension(150, 100));
		restartButton.setPreferredSize(new Dimension(150, 100));
		exitButton.setPreferredSize(new Dimension(150, 100));
		buttonPanel.add(startButton);
		buttonPanel.add(exitButton);
		menuFrame.add(text, BorderLayout.CENTER);
		menuFrame.add(buttonPanel, BorderLayout.SOUTH);
		menuFrame.setVisible(true);
		menuFrame.setSize(500, 500);
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * It is called if the user clicks the start button on the menu view.
	 */
	@Override
	public void gameStart() {
		menuFrame.dispose();
		dropButtonPanel.setLayout(new GridLayout(0, col));
		for (int i = 0; i < col; i++) {
			JButton dropButton = new JButton("Drop");
			dropButton.addActionListener(this);
			dropButtonPanel.add(dropButton);
			dropButtons[i] = dropButton;
		}
		gameBoardPanel.setBackground(Color.GRAY);
		boardPanels = new JPanel[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				JPanel panel = new JPanel();
				panel.setBorder(grayLine);
				boardPanels[i][j] = panel;
				gameBoardPanel.add(boardPanels[i][j]);
			}
		}
		if (this.playerView == Color.red) {
			gameFrame.setTitle("Connect4--Red Player View");
		} else {
			gameFrame.setTitle("Connect4--Yellow Player View");
		}
		Font font = new Font("Serif", 1, 24);
		infoText.setText(redTurn);
		infoText.setFont(font);
		gameFrame.add(scollPane, BorderLayout.SOUTH);
		gameFrame.add(gameBoardPanel, BorderLayout.CENTER);
		gameFrame.setVisible(true);
		gameFrame.setSize(500, 500);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.add(dropButtonPanel, BorderLayout.NORTH);
		gameFrame.setVisible(true);
		if (this.playerView == Color.red) {
			gameFrame.setEnabled(true);
		} else {
			gameFrame.setEnabled(false);
		}

	}

	/**
	 * This method is called when a valid move takes place. This is used by the
	 * model to notify the view to set the panel at row,col with the color
	 * 
	 */
	@Override
	public void gameMove(int row, int col, Color color) {
		boardPanels[this.row - row - 1][col].setBackground(color);
		if (color == Color.red) {
			infoText.setText(yellowTurn);
			if (this.playerView == Color.red) {
				gameFrame.setEnabled(false);
			} else {
				gameFrame.setEnabled(true);
			}
		} else {
			infoText.setText(redTurn);
			if (this.playerView == Color.yellow) {
				gameFrame.setEnabled(false);
			} else {
				gameFrame.setEnabled(true);
			}
		}
		if (row == 5) {
			dropButtons[col].removeActionListener(this);
		}
	}

	/**
	 * It is called when any player wins in this game.
	 */
	@Override
	public void gameWin(Color color) {
		for (JButton button : dropButtons) {
			button.removeActionListener(this);
		}
		String result;
		if (color == Color.red) {
			result = "Red Color Player Wins!";
		} else {
			result = "Yellow Color Player Wins!";
		}
		resultText.setText(result);
		Font x = new Font("Serif", 1, 40);
		resultText.setFont(x);
		buttonPanel.remove(startButton);
		buttonPanel.add(restartButton);
		menuFrame.remove(text);
		menuFrame.add(resultText, BorderLayout.CENTER);
		menuFrame.setVisible(true);
	}

	/**
	 * It is called when the board is full, which means this round is a tie.
	 */
	@Override
	public void gameTie() {
		for (JButton button : dropButtons) {
			button.removeActionListener(this);
		}
		String result = "Tie!";
		resultText.setText(result);
		Font x = new Font("Serif", 1, 40);
		resultText.setFont(x);
		buttonPanel.remove(startButton);
		buttonPanel.add(restartButton);
		menuFrame.remove(text);
		menuFrame.add(resultText, BorderLayout.CENTER);
		menuFrame.setVisible(true);
	}

	/**
	 * It is called when the player chooses to restart the game.
	 */
	@Override
	public void gameRestart() {
		gameFrame.dispose();
		dropButtonPanel.removeAll();
		gameBoardPanel.removeAll();
	}

	/**
	 * It is called when the player chooses to exit the game.
	 */
	@Override
	public void gameExit() {
		model.remove(this);
		System.exit(0);
	}

	/**
	 * This method is called when any of the buttons is pressed in the game which
	 * button was pressed is decided by any of the if conditions
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startButton) {
			model.fireGameStartEvent();
		} else if (e.getSource() == restartButton) {
			model.fireGameRestartEvent();
		} else if (e.getSource() == exitButton) {
			model.fireGameExitEvent();
		} else {
			for (int i = 0; i < col; i++) {
				if (e.getSource() == dropButtons[i]) {
					model.drop(i);
				}
			}
		}

	}

}
