package cotroller;

import view.Edge;

import java.util.List;

public interface IController {
    List<Edge> sendCurrentState(List<Edge> edges);

    void setDepth(int level);

    void createPlayer(int level);

    int getAIScore();

    int getHumanScore();
}
