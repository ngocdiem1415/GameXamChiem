package model;

public class HumanPlayer{
    public static final int TOKEN = 0;
    private int score;

    public HumanPlayer() {
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
    }
}
