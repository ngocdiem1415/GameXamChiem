package model;

import view.Edge;

import java.util.ArrayList;
import java.util.List;

public class AI implements IGame{
    AIPlayer ai;
    HumanPlayer human;
    Node bestState;


    public AI(AIPlayer ai, HumanPlayer human) {
        this.ai = ai;
        this.human = human;
        this.bestState = new Node();
    }

    @Override
    public List<Edge> makeMove(List<Edge> state) {
        Node startState = new Node();
        startState.setState(state);
        Node bestState = minimax(true, startState, 3);
        return bestState.getState();
    }

    public Node minimax(boolean isMaximizing, Node state, int depth){
        List<Node> listState = state.listChild();
        if((depth == 0 ) || state.isOver()){
            state.setHeuristicState(her(state));
            return state;
        }
        if (isMaximizing){
            int maxHer = Integer.MIN_VALUE;
            for (Node child: listState) {
                Node evaluatedNode = minimax(false, child, depth-1);
                int her = evaluatedNode.getHeuristicState();

                // Cập nhật Node tốt nhất
                if (her > maxHer) {
                    maxHer = her;
                    bestState = child;
                }
            }
            return bestState;
        }else{
            int minHer = Integer.MAX_VALUE;
            for (Node child: listState) {
                Node evaluatedNode = minimax(true, child, depth-1);
                int her = evaluatedNode.getHeuristicState();

                if (her < minHer) {
                    minHer = her;
                    bestState = child; // Lưu Node con tốt nhất
                }
            }
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
        for (int i=0; i < 10; i++ ) {
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
