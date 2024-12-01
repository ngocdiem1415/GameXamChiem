package view;

import cotroller.IController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameInterface extends JFrame {
    JPanel containerPanel, topPanel, centerPanel, scorePanel;
    JButton menuBtn, settingBtn;
    JLabel playerNameLabel;
    Matrix matrixPl;
    public final static int SIZE = 700;
    IController control;

    public GameInterface(IController control, int size, int numberOfPlayer) {
        this.control = control;
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
//        matrixPanel = new JPanel(new GridLayout(size, size));
////        matrixPanel.setOpaque(false);
////        matrixPanel.setPreferredSize(new Dimension(600, 600));
////        for (int i = 0; i < size; i++) {
////            for (int j = 0; j < size; j++) {
////                JRadioButton btn = new JRadioButton();
////                btn.setOpaque(false);
////                matrixPanel.add(btn);
////            }
////        }
        matrixPl = new Matrix(this.control,size,size);
        centerPanel = new JPanel(new BorderLayout());
        LineBorder centerBorder = new LineBorder(Color.RED, 5);
        centerPanel.setBorder(centerBorder);
        centerPanel.add(playerNameLabel, BorderLayout.NORTH);
        centerPanel.add(matrixPl, BorderLayout.CENTER);
        centerPanel.setOpaque(false);

        // Score Panel
        scorePanel = new JPanel(new GridLayout(1, 4));
        for (int i = 0; i < numberOfPlayer; i++) {
            JLabel playerScoreLabel = new JLabel("0", JLabel.CENTER);
            playerScoreLabel.setFont(font_arial_24);
            scorePanel.add(playerScoreLabel);
        }

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

}
