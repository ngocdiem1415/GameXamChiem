package view;

import javax.swing.*;
import javax.swing.border.LineBorder;

import model.Dot;
import model.Edge;

import java.awt.*;
import java.util.List;

public class GameView extends JFrame {
	JPanel containerPanel, topPanel, centerPanel, scorePanel;
	JButton menuBtn, settingBtn;
	JLabel playerNameLabel, aiScoreLabel, userScoreLabel;
	Matrix matrixPanel;
	String userName;
	int sizeboard;
	static int CURRENT_TOKEN;
	public final static int SIZE = 700;

	public GameView(String userName, int size) {
		this.userName = userName;
		this.sizeboard = size;
		init();
	}

	private void init() {
		Font font_arial_24 = new Font("Arial", Font.PLAIN, 24);

		// Top Panel
		menuBtn = new JButton("Menu");
		menuBtn.setFont(font_arial_24);
		settingBtn = new JButton("Setting");
		settingBtn.setFont(font_arial_24);
		LineBorder topBorder = new LineBorder(Color.BLUE, 5);
		topPanel = new JPanel(new BorderLayout());

		// Center Panel
		playerNameLabel = new JLabel("", JLabel.CENTER);
		LineBorder labelBorder = new LineBorder(Color.ORANGE, 5);
		playerNameLabel.setBorder(labelBorder);
		playerNameLabel.setFont(font_arial_24);
		matrixPanel = new Matrix(this.sizeboard);
		centerPanel = new JPanel(new BorderLayout());
		LineBorder centerBorder = new LineBorder(Color.RED, 5);
		centerPanel.setBorder(centerBorder);
		centerPanel.add(matrixPanel, BorderLayout.CENTER);
		centerPanel.setOpaque(false);
		topPanel.add(playerNameLabel, BorderLayout.NORTH);
		topPanel.setOpaque(false);

		// Score Panel
		scorePanel = new JPanel(new BorderLayout());
		aiScoreLabel = new JLabel("0 :AI");
		aiScoreLabel.setFont(font_arial_24);
		userScoreLabel = new JLabel(userName + ": 0");
		userScoreLabel.setFont(font_arial_24);
		scorePanel.add(aiScoreLabel, BorderLayout.EAST);
		scorePanel.add(userScoreLabel, BorderLayout.WEST);

		// Container Panel
		ImageIcon backgroundImage = new ImageIcon("/image/anh.jpg");
		containerPanel = new JPanel(new BorderLayout()) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};
		containerPanel.add(topPanel, BorderLayout.NORTH);
		containerPanel.add(centerPanel, BorderLayout.CENTER);
		containerPanel.add(scorePanel, BorderLayout.SOUTH);

		// Frame
		setTitle("Dots and Boxes");
		setSize(SIZE, SIZE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setContentPane(containerPanel);
		setVisible(true);
	}
	
	public void displayPlayerName(int playerToken) {
		switch (playerToken) {
		case 0:
			playerNameLabel.setText("AI's turn");
			break;
		case 1:
			playerNameLabel.setText(userName + "'s turn");
			break;
		default:
			break;
		}
	}
	
	public void displayScore(int userScore, int aiScore) {
		aiScoreLabel.setText(aiScore + "\t:AI");
		userScoreLabel.setText("User:\t" + userScore);
	}

	public Matrix getMatrixPanel() {
		return this.matrixPanel;
	}
}
