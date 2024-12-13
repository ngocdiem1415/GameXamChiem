package cotroller;

import model.IGame;
import view.Edge;
import view.Login;

import java.util.List;
import java.util.Observable;

public class Controller implements IController{
    IGame model;
    Login login;

    public Controller(IGame model) {
        this.model = model;
        this.login = new Login((Observable) model, this);
        login.setVisible(true);
    }

    @Override
    public List<Edge> sendCurrentState(List<Edge> edges) {
        return model.makeMove(edges);
    }

    @Override
    public void setDepth(int level) {
        model.setDepth(level);
    }

    @Override
    public void createPlayer(int level) {
        model.createPlayer(level);
    }

    @Override
    public int getAIScore() {
        return model.getAIScore();
    }

    @Override
    public int getHumanScore() {
        return model.getUserScore();
    }
}
