package model;

import view.Edge;

import java.util.List;

public interface IGame {
   List<Edge> makeMove(List<Edge> state);

   void setDepth(int level);

   void createPlayer(int level);

   int getAIScore();

   int getUserScore();
}
