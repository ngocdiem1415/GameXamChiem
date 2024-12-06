package model;

import view.Edge;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Minimax extends Observable {
    AIPlayer ai;
    HumanPlayer human;
    Node bestMove;

    public Minimax(AIPlayer ai, HumanPlayer human) {
        this.ai = ai;
        this.human = human;
        this.bestMove = new Node();
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
                    bestMove = child;
                }
            }
            return bestMove;
        }else{
            int minHer = Integer.MAX_VALUE;
            for (Node child: listState) {
                Node evaluatedNode = minimax(true, child, depth-1);
                int her = evaluatedNode.getHeuristicState();

                if (her < minHer) {
                    minHer = her;
                    bestMove = child; // Lưu Node con tốt nhất
                }
            }
            return bestMove;
        }
    }

    private int her(Node state) {
        return Math.abs(ai.getScore() - human.getScore());
    }

    public static void main(String[] args) {
        AIPlayer ai = new AIPlayer();
        ai.setScore(2);
        HumanPlayer human = new HumanPlayer();
        human.setScore(5);
        Minimax minimax = new Minimax(ai, human);

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

        Node result = minimax.minimax( isMaximizing, currentState, depth);
        System.out.println("Best evaluation score: " + result.toString());
    }

}
