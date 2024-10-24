package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

public class GameInterface extends JFrame {
	JPanel containerPanel, topPanel, centerPanel, matrixPanel, scorePanel;
	JButton menuBtn, settingBtn;
	JLabel playerNameLabel;

	public GameInterface(int size, int numberOfPlayer) {
		Font font_arial_24 = new Font("Arial", Font.PLAIN, 24);

		// Top Panel
		menuBtn = new JButton("Menu");
		menuBtn.setFont(font_arial_24);
		settingBtn = new JButton("Setting");
		settingBtn.setFont(font_arial_24);
		LineBorder topBorder = new LineBorder(Color.BLUE, 5);
		topPanel = new JPanel(new BorderLayout());
		topPanel.setBorder(topBorder);
		topPanel.add(menuBtn, BorderLayout.EAST);
		topPanel.add(settingBtn, BorderLayout.WEST);
		topPanel.setOpaque(false);

		// Center Panel
		playerNameLabel = new JLabel("Player's name", JLabel.CENTER);
		LineBorder labelBorder = new LineBorder(Color.ORANGE, 5);
		playerNameLabel.setBorder(labelBorder);
		playerNameLabel.setFont(font_arial_24);
		// Matrix Panel
		matrixPanel = new JPanel(new GridLayout(size, size));
		matrixPanel.setOpaque(false);
		matrixPanel.setPreferredSize(new Dimension(600, 600));
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				JRadioButton btn = new JRadioButton();
				btn.setOpaque(false);
				matrixPanel.add(btn);
			}
		}
		centerPanel = new JPanel(new BorderLayout());
		LineBorder centerBorder = new LineBorder(Color.RED, 5);
		centerPanel.setBorder(centerBorder);
		centerPanel.add(playerNameLabel, BorderLayout.NORTH);
		centerPanel.add(matrixPanel);
		centerPanel.setOpaque(false);

		// Score Panel
		scorePanel = new JPanel(new GridLayout(1, 4));
		for (int i = 0; i < numberOfPlayer; i++) {
			JLabel playerScoreLabel = new JLabel("0", JLabel.CENTER);
			playerScoreLabel.setFont(font_arial_24);
			scorePanel.add(playerScoreLabel);
		}

		// Container Panel
		ImageIcon backgroundImage = new ImageIcon("C:\\Users\\truon\\Downloads\\images (1).jfif");
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
		setSize(800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setContentPane(containerPanel);
		setVisible(true);
	}

	public static void main(String[] args) {
		GameInterface frame = new GameInterface(8, 4);
	}
}
