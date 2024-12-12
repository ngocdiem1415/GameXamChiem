package view;

import cotroller.IController;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Observable;

public class GameInterface extends JFrame {
    JPanel containerPanel, topPanel, centerPanel, scorePanel;
    JButton menuBtn, settingBtn;
    JLabel playerNameLabel;
    Matrix matrixPl;
    public final static int SIZE = 700;
    IController control;
    String nameUser;
    int sizeboard;
    int level;
    private Observable obs;

    public GameInterface(Observable obs,IController control, String nameUser, int size,int level, int numberOfPlayer) {
        this.obs = obs;
        this.control = control;
        this.nameUser = nameUser;
        this.sizeboard = size;
        this.level = level;
        Font font_arial_24 = new Font("Arial", Font.PLAIN, 24);

        // Top Panel
        menuBtn = new JButton("Menu");
        menuBtn.setFont(font_arial_24);
        settingBtn = new JButton("Setting");
        settingBtn.setFont(font_arial_24);
        LineBorder topBorder = new LineBorder(Color.BLUE, 5);
        topPanel = new JPanel(new BorderLayout());
//        topPanel.setBorder(topBorder);
//        topPanel.add(menuBtn, BorderLayout.EAST);
//        topPanel.add(settingBtn, BorderLayout.WEST);
//        topPanel.setOpaque(false);

        // Center Panel
        playerNameLabel = new JLabel(nameUser+ "'s Player ", JLabel.CENTER);
        LineBorder labelBorder = new LineBorder(Color.ORANGE, 5);
        playerNameLabel.setBorder(labelBorder);
        playerNameLabel.setFont(font_arial_24);
        matrixPl = new Matrix(obs,this.control,size,size, this);
        centerPanel = new JPanel(new BorderLayout());
        LineBorder centerBorder = new LineBorder(Color.RED, 5);
        centerPanel.setBorder(centerBorder);
//        centerPanel.add(playerNameLabel, BorderLayout.NORTH);
        centerPanel.add(matrixPl, BorderLayout.CENTER);
        centerPanel.setOpaque(false);
        topPanel.add(playerNameLabel, BorderLayout.NORTH);
        topPanel.setOpaque(false);

        // Score Panel
        scorePanel = new JPanel(new BorderLayout());
//        for (int i = 0; i < numberOfPlayer; i++) {
//            JLabel playerScoreLabel = new JLabel("0", JLabel.CENTER);
//            playerScoreLabel.setFont(font_arial_24);
//            scorePanel.add(playerScoreLabel);
//        }
        int aiScore = control.getAIScore();
        int userScore = control.getHumanScore();
        JLabel AIScoreLabel = new JLabel(aiScore + " :AI");
        AIScoreLabel.setFont(font_arial_24);
        JLabel UserScoreLabel = new JLabel(nameUser + ": " + userScore);
        UserScoreLabel.setFont(font_arial_24);
        scorePanel.add(AIScoreLabel, BorderLayout.EAST);
        scorePanel.add(UserScoreLabel, BorderLayout.WEST);

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

    public void setNamePlayer() {
        playerNameLabel.setName("AI's Player ");
        System.out.println("Da doi lai ten");
    }
}
