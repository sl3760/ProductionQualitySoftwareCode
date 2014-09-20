package nyu.edu.pqs.shuo.connect4.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import nyu.edu.pqs.shuo.connect4.model.Model;
import nyu.edu.pqs.shuo.connect4.player.GameMode;

/**
 * Class presenting the entry view of the game, providing two options: play with
 * computer or two players play against each other.
 * 
 * @author Shuo
 * 
 */
public class MainView implements ActionListener {
	private JButton singlePlayerButton = new JButton("Single Player Mode");
	private JButton twoPlayerButton = new JButton("Two Players Mode");
	private JFrame selectionModeFrame = new JFrame("Connect4");
	private JTextArea text = new JTextArea();

	public MainView() {
		text.setText("Welcome to Connect4 Game!");
		Font x = new Font("Serif", 1, 24);
		text.setFont(x);
		this.createGameSelectMode();
		this.buttonsWithActionListeners();
	}

	/**
	 * This method will add action listeners to the buttons in the game
	 */
	private void buttonsWithActionListeners() {
		singlePlayerButton.addActionListener(this);
		twoPlayerButton.addActionListener(this);
	}

	/**
	 * This method provides a view for users to select a game mode(single or
	 * double) to start the game.
	 */
	public void createGameSelectMode() {
		JPanel gameSelectionPanel = new JPanel();
		JPanel selectionButtonPanel = new JPanel();
		singlePlayerButton.setPreferredSize(new Dimension(150, 100));
		twoPlayerButton.setPreferredSize(new Dimension(150, 100));
		selectionButtonPanel.add(singlePlayerButton);
		selectionButtonPanel.add(twoPlayerButton);
		gameSelectionPanel.add(selectionButtonPanel);
		selectionModeFrame.add(text, BorderLayout.CENTER);
		selectionModeFrame.add(gameSelectionPanel, BorderLayout.SOUTH);
		selectionModeFrame.setVisible(true);
		selectionModeFrame.setSize(500, 500);
		selectionModeFrame.setLocationRelativeTo(null);
		selectionModeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * This method is called when any of the buttons is pressed in the game which
	 * button was pressed is decided by any of the if conditions
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == singlePlayerButton) {
			selectionModeFrame.dispose();
			Model model = Model.getModelInstance();
			new View(model, Color.red);
			model.createPlayers(GameMode.SINGLE);
		}
		if (e.getSource() == twoPlayerButton) {
			selectionModeFrame.dispose();
			Model model = Model.getModelInstance();
			new View(model, Color.red);
			new View(model, Color.yellow);
			model.createPlayers(GameMode.DOUBLE);
		}
	}
}
