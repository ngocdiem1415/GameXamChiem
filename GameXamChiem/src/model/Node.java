package model;

import java.util.ArrayList;
import java.util.List;

public class Node {
	List<Edge> state;
	int heuristic;
	int aiScore, userScore;
	boolean hasNewSquare;

	public Node() {
	}

	public Node(List<Edge> state) {
		this.state = state;
		this.hasNewSquare = false;
	}

	public Node(List<Edge> state, int aiScore, int userScore) {
		this.state = state;
		this.aiScore = aiScore;
		this.userScore = userScore;
		this.hasNewSquare = false;
		updateHeuristic();
	}

	public boolean isOver() {
		for (Edge edge : state) {
			if (!edge.isActivated()) {
				return false;
			}
		}
		return true;
	}

	public void increaseAIScore(int point) {
		this.aiScore += point;
		updateHeuristic();
	}

	public void increaseUserScore(int point) {
		this.userScore += point;
		updateHeuristic();
	}

	public void updateHeuristic() {
		this.heuristic = calculateHeuristic();
	}

	public int calculateHeuristic() {
		return this.aiScore - this.userScore;
	}

	public void setHeuristic(int value) {
		this.heuristic = value;
	}

	public List<Edge> getState() {
		return this.state;
	}

	public void setState(List<Edge> state) {
		this.state = state;
	}

	public int getHeuristic() {
		return heuristic;
	}

	public int getAIScore() {
		return this.aiScore;
	}

	public int getUserScore() {
		return this.userScore;
	}

	@Override
	public String toString() {
		String st = "Node[\n";
		for (Edge edge : state) {
			st += edge.toString() + "\n";
		}
		st += "]";
		return st;
	}

	public void setScore(int aiScore, int userScore) {
		this.aiScore = aiScore;
		this.userScore = userScore;
		updateHeuristic();
	}

	public boolean isHasNewSquare() {
		return hasNewSquare;
	}

	public void setHasNewSquare(boolean hasNewSquare) {
		this.hasNewSquare = hasNewSquare;
	}
}
