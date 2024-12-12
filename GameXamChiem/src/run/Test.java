package run;

import cotroller.Controller;
import cotroller.IController;
import model.AI;
import model.AIPlayer;
import model.HumanPlayer;


public class Test {
    public static void main(String[] args) {
        AIPlayer ai = new AIPlayer();
        HumanPlayer human = new HumanPlayer();
        IController control = new Controller(new AI(ai, human));
    }
}
