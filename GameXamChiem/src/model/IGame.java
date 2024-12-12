package model;

import view.Edge;

import java.util.List;

public interface IGame {
   List<Edge> makeMove(List<Edge> state);
}
