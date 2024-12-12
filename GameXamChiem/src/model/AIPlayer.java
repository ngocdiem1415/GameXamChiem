package model;

import javax.swing.*;

public class AIPlayer implements User{
    public static final int TOKEN = 0;
    private int score;
    private int depth; //độ sâu của thuật toán minimax

    public AIPlayer() {
        this.score = 0;

    }

    public AIPlayer(int depth){
        this.score =0;
        this.depth = depth;
    }

    @Override
    public int getToken() {
        return TOKEN;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

