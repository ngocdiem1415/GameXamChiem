package model;

import java.util.List;

public class Minimax {
    public int minimax(boolean minimax, Node state, int depth){
        depth = 2;
        if((depth == 0 ) || state.isOver()){
            return state.her();
        }
        if (minimax){
            int maxher = Integer.MIN_VALUE;
            for (Node child: state.listChill()) {
                int her = minimax(false, child, depth-1);
                maxher = Math.max(her, maxher);
            }
            return maxher;
        }else{
            int minHer = Integer.MAX_VALUE;
            for (Node child: state.listChill()) {
                int her = minimax(true, child, depth-1);
                minHer = Math.min(her, minHer);
            }
            return minHer;
        }
    }

    public static void main(String[] args) {
        Minimax minimax = new Minimax();
        Node currentState = new Node(); // Khởi tạo trạng thái ban đầu

        int depth = 3; // Độ sâu tìm kiếm
        boolean isMaximizing = true; // Người chơi đầu tiên là Maximizing player

        int result = minimax.minimax( isMaximizing, currentState, depth);
        System.out.println("Best evaluation score: " + result);
    }

}
