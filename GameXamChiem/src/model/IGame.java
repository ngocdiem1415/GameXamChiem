package model;

import view.Edge;

import java.util.List;

public interface IGame {

    public List<Node> listChild();

    public boolean isOver();

    public List<Edge> getState();

    public void setState(List<Edge> state);
}
