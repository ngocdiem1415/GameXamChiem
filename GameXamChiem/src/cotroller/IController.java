package cotroller;

import view.Edge;

import java.util.List;

public interface IController {
    List<Edge> sendCurrentState(List<Edge> edges);
}
