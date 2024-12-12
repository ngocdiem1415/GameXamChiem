package model;

import java.util.List;

public class User {
    int token;
    int score;
    List<User> listUser;

    public User(int token, int score) {
        this.token = token;
        this.score = score;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
