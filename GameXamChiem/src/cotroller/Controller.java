package cotroller;

import model.IGame;
import view.Edge;
import view.Login;

import java.util.List;

public class Controller implements IController{
    IGame model;
    Login login;

    public Controller(IGame model) {
        this.model = model;
        this.login = new Login(this);
        login.setVisible(true);
    }

    @Override
    public void currentState(List<Edge> edges) {
        model.setState(edges);
    }
}
