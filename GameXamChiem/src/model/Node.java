package model;

import view.Dot;
import view.Edge;

import java.util.ArrayList;
import java.util.List;

public class Node implements IGame {
    List<Edge> state;

    public Node() {
    }

    public Node(List<Edge> state) {
        this.state = state;
    }

    public List<Node> listChild() {
        List<Node> listNode = new ArrayList<>();
        for (int i = 0; i < state.size(); i++) {
            if ( !state.get(i).isActived()) {
                List<Edge> newState = new ArrayList<>();
                for (Edge edge : state) {
                    newState.add(new Edge(edge));
                }
                newState.get(i).setActived(true);

                Node temp = new Node(newState);
                listNode.add(temp);
            }
        }
        return listNode;
    }

    public boolean isOver() {
        for (Edge edge : state) {
            if (!edge.isActived()) {
                return false;
            }
        }
        return true;
    }

    public List<Edge> getState() {
        return this.state;
    }

    public void setState(List<Edge> state) {
        this.state = state;
        System.out.println(state.toString());
    }

//    public static void main(String[] args) {
//        List<Edge> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            list.add(new Edge());
//        }
//        list.get(1).setActived(true);
//        Node test = new Node(list);
//        for (Node e:test.listChild()) {
//            System.out.println(e.getState().toString());
//            System.out.println("---------------------");
//        };
//
//
//    }
}
