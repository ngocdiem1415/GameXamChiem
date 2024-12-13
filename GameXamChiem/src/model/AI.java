package model;

import view.Edge;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class AI extends Observable implements IGame {
    AIPlayer ai;
    HumanPlayer human;
    Node bestState;


    public AI() {
        this.bestState = new Node();
    }

    @Override
    public List<Edge> makeMove(List<Edge> state) {
        Node startState = new Node();
        startState.setState(state);
        bestState = minimax(true, startState, 3,Integer.MIN_VALUE, Integer.MAX_VALUE);
//        System.out.println(bestState.getState()+"--------------");

        synchronized (this) {
            setChanged();
            notifyObservers();
        }

        return bestState.getState();
    }

    public List<Edge> getBestState(){
        return bestState.getState();
    }

    @Override
    public void setDepth(int level) {
        ai.setDepth(level);
    }

    @Override
    public void createPlayer(int level) {
        this.ai = new AIPlayer();
        this.human = new HumanPlayer();
        setDepth(level);
    }

    @Override
    public int getAIScore() {
        return ai.getScore();
    }

    @Override
    public int getUserScore() {
        return human.getScore();
    }

    public Node minimax(boolean isMaximizing, Node state, int depth, int alpha, int beta) {
        List<Node> listState = state.listChild();
        if ((depth == 0) || state.isOver()) {
            state.setHeuristicState(her(state));
            return state;
        }
        if (isMaximizing) {
            int maxHer = Integer.MIN_VALUE;
            for (Node child : listState) {
                Node evaluatedNode = minimax(false, child, depth - 1, alpha, beta);
                int her = evaluatedNode.getHeuristicState();

                // Cập nhật Node tốt nhất
                if (her > maxHer) {
                    maxHer = her;
                    bestState = child;
                }
                // Cập nhật alpha
                alpha = Math.max(alpha, her);
                // Cắt tỉa
                if (beta <= alpha) {
                    break;
                }
            }
            bestState.setHeuristicState(maxHer);
            return bestState;
        } else {
            int minHer = Integer.MAX_VALUE;
            for (Node child : listState) {
                Node evaluatedNode = minimax(true, child, depth - 1, alpha, beta);
                int her = evaluatedNode.getHeuristicState();

                if (her < minHer) {
                    minHer = her;
                    bestState = child; // Lưu Node con tốt nhất
                }
                // Cập nhật beta
                beta = Math.min(beta, her);
                // Cắt tỉa
                if (beta <= alpha) {
                    break;
                }
            }
            bestState.setHeuristicState(minHer);
            return bestState;
        }
    }

    private int her(Node state) {
        return Math.abs(ai.getScore() - human.getScore());
    }

    public static void main(String[] args) {
        AIPlayer ai = new AIPlayer();
        ai.setScore(2);

        Node currentState = new Node(); // Khởi tạo trạng thái ban đầu
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            edgeList.add(new Edge());
        }
        edgeList.get(1).setActived(true);
        edgeList.get(4).setActived(true);
        edgeList.get(2).setActived(true);
        edgeList.get(8).setActived(true);
        currentState.setState(edgeList);

        int depth = 3; // Độ sâu tìm kiếm
        boolean isMaximizing = true; // Người chơi đầu tiên là Maximizing player

    }

}
