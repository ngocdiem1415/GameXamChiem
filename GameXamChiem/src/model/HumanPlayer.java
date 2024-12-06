package model;

import javax.swing.*;

public class HumanPlayer implements IPlayer {
    public static final int TOKEN = 1;
    private int score;

    public HumanPlayer() {
        this.score = 0;

    }
//
//    @Override
//    public Point takeTurn(Board board) {
//        return null;
//    }

    @Override
    public void claimBox(Box box) {
//        box.setOwner(TOKEN); // đánh dấu quyền sở hữu
    }

    @Override
    public int getToken() {
        return TOKEN;
    }

    @Override
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
