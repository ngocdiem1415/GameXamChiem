package model;

import javax.swing.*;

public class AIPlayer{
    public static final int TOKEN = 0;
    private int score;
    private int depth; //độ sâu của thuật toán minimax

    public AIPlayer() {
        this.score = 0;
    }

    public int getToken() {
        return TOKEN;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDepth(int level) {
        depth = level;
//        System.out.println(depth);
    }
}

