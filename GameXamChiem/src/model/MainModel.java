package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainModel {
	static List<Dot> dots = new LinkedList<Dot>();
	List<Edge> edges = new LinkedList<Edge>();
	int matrixSize;
	List<Square> squares = new ArrayList<Square>();

	int depth;
	int aiScore, userScore;
	final int AI_TOKEN = 0, USER_TOKEN = 1;
	int currentToken = USER_TOKEN;
	Node currentNode;
	boolean isAITurn;

	static int horizontalGap, verticalGap, offset;

	public void setMatrixSize(int size) {
		this.matrixSize = size;
		initDots();
		initEdges();
		currentNode = new Node(edges, aiScore, userScore);
//		System.out.println(currentNode.toString());
//		System.out.println(dots.size());
//		System.out.println(edges.size());
	}

	public void setDepthMinimax(int level) {
		this.depth = level;
	}

	private void initDots() {
		for (int i = 0; i < matrixSize; i++) {
			for (int j = 0; j < matrixSize; j++) {
				int x = offset + j * horizontalGap;
				int y = offset + i * verticalGap;
				Dot dot = new Dot(new Point(x, y));
				dots.add(dot);
//				System.out.println("Dot d" + dots.indexOf(dot) + " = new Dot(new Point(" + dot.getX() + ", " + dot.getY() + "));");
			}
		}

	}

	private void initEdges() {
		Dot startDot, endDot;
		for (int i = 0; i < this.matrixSize; i++) {
			for (int j = 0; j < this.matrixSize; j++) {
				int indexOfStartDot = i * this.matrixSize + j;
				startDot = dots.get(indexOfStartDot);
				if (i == this.matrixSize - 1 && j != this.matrixSize - 1) { // Nếu Dot nằm dưới cùng ma trận thì chỉ tạo
																			// mỗi cạnh ngang
					endDot = dots.get(indexOfStartDot + 1); // Dot end là dot nằm kế bên Dot hiện tại
					edges.add(new Edge(true, startDot, endDot));
//					System.out.println("Edge e" + " = new Edge(true, " + "d" + indexOfStartDot + ", " + dots.indexOf(endDot) + ");");
					continue;
				}
				if (j == this.matrixSize - 1 && i != this.matrixSize - 1) { // Nếu Dot nằm bên phải cùng ma trận thì chỉ
																			// tạo cạnh dọc
					endDot = dots.get(indexOfStartDot + this.matrixSize); // Dot end là dot nằm phía dưới Dot hiện tại
																			// nên chỉ cần
					// cộng thêm số lượng cột là ra
					edges.add(new Edge(false, startDot, endDot));
//					System.out.println("Edge e" + " = new Edge(false, " + "d" + indexOfStartDot + ", " + dots.indexOf(endDot) + ");");
					continue;
				}
				if (i == this.matrixSize - 1 && j == this.matrixSize - 1) { // Nếu như là Dot nằm cuối cùng của ma trận
																			// thì không tạo cạnh nữa
					break;
				} else {
					endDot = dots.get(indexOfStartDot + 1);
					edges.add(new Edge(true, startDot, endDot));
//					System.out.println("Edge e" + " = new Edge(true, " + "d" + indexOfStartDot + ", " + dots.indexOf(endDot) + ");");
					endDot = dots.get(indexOfStartDot + this.matrixSize);
					edges.add(new Edge(false, startDot, endDot));
//					System.out.println("Edge e" + " = new Edge(false, " + "d" + indexOfStartDot + ", " + dots.indexOf(endDot) + ");");
				}
			}
		}
	}

	public List<Dot> getDots() {
		return dots;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public List<Square> getSquares() {
		return squares;
	}

	public int getAIScore() {
		return this.aiScore;
	}

	public int getUserScore() {
		return this.userScore;
	}

	public int getCurrentToken() {
		return this.currentToken;
	}

	public boolean isAITurn() {
		return this.isAITurn;
	}

	public Node minimax(boolean isMaximizing, Node node, int depth, int alpha, int beta) {
		if ((depth == 0) || node.isOver()) {
			return node;
		}
		if (isMaximizing) {
			Node bestNode = null;
			List<Node> children = getChildrenOfNode(isMaximizing, node);
			int maxHeuristic = Integer.MIN_VALUE;
			for (Node child : children) {
				Node evaluatedNode = minimax(false, child, depth - 1, alpha, beta);
				int heuristic = evaluatedNode.getHeuristic();

				// Cập nhật Node tốt nhất
				if (heuristic > maxHeuristic) {
					maxHeuristic = heuristic;
					bestNode = child;
				}
				// Cập nhật alpha
				alpha = Math.max(alpha, heuristic);
				// Cắt tỉa
				if (beta <= alpha) {
					break;
				}
			}
			node.setHeuristic(maxHeuristic);
			return bestNode;
		} else {
			Node bestNode = null;
			List<Node> children = getChildrenOfNode(isMaximizing, node);
			int minHeuristic = Integer.MAX_VALUE;
			for (Node child : children) {
				Node evaluatedNode = minimax(true, child, depth - 1, alpha, beta);
				int heuristic = evaluatedNode.getHeuristic();

				if (heuristic < minHeuristic) {
					minHeuristic = heuristic;
					bestNode = child;
				}
				// Cập nhật beta
				beta = Math.min(beta, heuristic);
				// Cắt tỉa
				if (beta <= alpha) {
					break;
				}
			}
			node.setHeuristic(minHeuristic);
			return bestNode;
		}
	}

	public List<Node> getChildrenOfNode(boolean isMaximizing, Node node) {
		List<Node> children = new ArrayList<>();

		// Kích hoạt thêm một cạnh
		for (int i = 0; i < node.state.size(); i++) {
			if (node.state.get(i).isActivated() == false) {
				List<Edge> stateOfChild = new ArrayList<>();
				for (Edge edge : node.state) {
					stateOfChild.add(edge.getCopy());
				}
				Node child = new Node(stateOfChild);
				stateOfChild.get(i).active();
				int point = checkSquare(false, child, stateOfChild.get(i));
				if (isMaximizing == true) {
					child.setScore(node.getAIScore() + point, node.getUserScore());
				} else {
					child.setScore(node.getAIScore(), node.getUserScore() + point);
				}
				children.add(child);
			}
		}
		return children;
	}

	private void addPointForCurrentPlayer(int point) {
		switch (currentToken) {
		case 0:
			this.aiScore += point;
			break;
		case 1:
			this.userScore += point;
		default:
			break;
		}
	}

	private void updateCurrentNode() {
		currentNode = new Node(edges, aiScore, userScore);
	}

	public void makeMove(Edge edge) {
		edge.active();
		updateCurrentNode();
		int point = checkSquare(true, currentNode, edge);
		if (point > 0) {
			addPointForCurrentPlayer(point);
		} else {
			changeTurn();
		}
	}

	private void changeTurn() {
		switch (currentToken) {
		case 0:
			currentToken = USER_TOKEN;
			isAITurn = false;
			break;
		case 1:
			currentToken = AI_TOKEN;
			isAITurn = true;
			break;
		default:
			break;
		}
	}

	public void runAIMove() {
		Node bestNode = minimax(true, currentNode, depth, Integer.MIN_VALUE, Integer.MAX_VALUE);
		List<Edge> currentList = currentNode.getState();
		List<Edge> bestList = bestNode.getState();
		Edge bestEdge;
		for (int i = 0; i < edges.size(); i++) {
			if (currentList.get(i).isActivated() != bestList.get(i).isActivated()) {
				bestEdge = currentList.get(i);
				makeMove(bestEdge);
//				System.out.println("h =" + currentNode.getHeuristic() + "\n" + bestEdge.toString());
//				System.out.println("===========================================================");
//				System.out.println(bestEdge.toString());
				break;
			}
		}
	}

	/*
	 * Phương thức lấy ra điểm được được cộng (nghĩa là lấy ra số lượng ô vuông được
	 * tạo thành sau khi kích hoạt thêm một cạnh cho trạng thái của một node hiện
	 * tại)
	 */
	private int checkSquare(boolean isReal, Node node, Edge edge) {
		int point;
		if (edge.isHorizontal) {
			point = checkTopSquare(isReal, node, edge) + checkBottomSquare(isReal, node, edge);
		} else {
			point = checkLeftSquare(isReal, node, edge) + checkRightSquare(isReal, node, edge);
		}
		return point;
	}

	private int checkTopSquare(boolean isReal, Node node, Edge edge) {
		Dot startDot = edge.getStartDot();
		Dot endDot = edge.getEndDot();
		if (dots.indexOf(startDot) < matrixSize) {
			return 0;
		} else {
			Dot topStartDot = dots.get(dots.indexOf(startDot) - matrixSize);
			Dot topEndDot = dots.get(dots.indexOf(endDot) - matrixSize);
			for (Edge e : node.getState()) {
				if ((e.getStartDot() == topStartDot && e.getEndDot() == topEndDot && !e.isActivated())
						|| (e.getStartDot() == topStartDot && e.getEndDot() == startDot && !e.isActivated())
						|| (e.getStartDot() == topEndDot && e.getEndDot() == endDot && !e.isActivated())) {
					return 0;
				}
			}
			if(isReal) {
				squares.add(new Square(topStartDot));
			}
			return 1;
		}
	}

	private int checkBottomSquare(boolean isReal, Node node, Edge edge) {
		Dot startDot = edge.getStartDot();
		Dot endDot = edge.getEndDot();
		if (dots.indexOf(startDot) >= matrixSize * (matrixSize - 1)) {
			return 0;
		} else {
			Dot bottomStartDot = dots.get(dots.indexOf(startDot) + matrixSize);
			Dot bottomEndDot = dots.get(dots.indexOf(endDot) + matrixSize);
			for (Edge e : node.getState()) {
				if ((e.getStartDot() == bottomStartDot && e.getEndDot() == bottomEndDot && !e.isActivated())
						|| (e.getStartDot() == startDot && e.getEndDot() == bottomStartDot && !e.isActivated())
						|| (e.getStartDot() == endDot && e.getEndDot() == bottomEndDot && !e.isActivated())) {
					return 0;
				}
			}
			if(isReal) {
				squares.add(new Square(startDot));
			}
			return 1;
		}
	}

	private int checkLeftSquare(boolean isReal, Node node, Edge edge) {
		Dot startDot = edge.getStartDot();
		Dot endDot = edge.getEndDot();
		if (dots.indexOf(startDot) % matrixSize == 0) {
			return 0;
		} else {
			Dot leftStartDot = dots.get(dots.indexOf(startDot) - 1);
			Dot leftEndDot = dots.get(dots.indexOf(endDot) - 1);
			for (Edge e : node.getState()) {
				if ((e.getStartDot() == leftStartDot && e.getEndDot() == leftEndDot && !e.isActivated())
						|| (e.getStartDot() == leftStartDot && e.getEndDot() == startDot && !e.isActivated())
						|| (e.getStartDot() == leftEndDot && e.getEndDot() == endDot && !e.isActivated())) {
					return 0;
				}
			}
			if(isReal) {
				squares.add(new Square(leftStartDot));
			}
			return 1;
		}
	}

	private int checkRightSquare(boolean isReal, Node node, Edge edge) {
		Dot startDot = edge.getStartDot();
		Dot endDot = edge.getEndDot();
		if (dots.indexOf(startDot) % matrixSize == (matrixSize - 1)) {
			return 0;
		} else {
			Dot rightStartDot = dots.get(dots.indexOf(startDot) + 1);
			Dot rightEndDot = dots.get(dots.indexOf(endDot) + 1);
			for (Edge e : node.getState()) {
				if ((e.getStartDot() == rightStartDot && e.getEndDot() == rightEndDot && !e.isActivated())
						|| (e.getStartDot() == startDot && e.getEndDot() == rightStartDot && !e.isActivated())
						|| (e.getStartDot() == endDot && e.getEndDot() == rightEndDot && !e.isActivated())) {
					return 0;
				}
			}
			if(isReal) {
				squares.add(new Square(startDot));
			}
			return 1;
		}
	}

	public void setHorizontalGap(int value) {
		this.horizontalGap = value;
	}

	public void setVerticalGap(int value) {
		this.verticalGap = value;
	}

	public void setOffset(int value) {
		this.offset = value;
	}

	public int getDepth() {
		return depth;
	}
	
	private void setDots(List<Dot> dots) {
		this.dots = dots;
	}
	
	private void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	
	public static int indexOfDot(Dot d) {
		return dots.indexOf(d);
	}
	
	public static void main(String[] args) {
		Dot d0 = new Dot(new Point(50, 50));
		Dot d1 = new Dot(new Point(338, 50));
		Dot d2 = new Dot(new Point(626, 50));
		Dot d3 = new Dot(new Point(50, 263));
		Dot d4 = new Dot(new Point(338, 263));
		Dot d5 = new Dot(new Point(626, 263));
		Dot d6 = new Dot(new Point(50, 476));
		Dot d7 = new Dot(new Point(338, 476));
		Dot d8 = new Dot(new Point(626, 476));
		List<Dot> dots = new LinkedList<Dot>();
		dots.add(d0);
		dots.add(d1);
		dots.add(d2);
		dots.add(d3);
		dots.add(d4);
		dots.add(d5);
		dots.add(d6);
		dots.add(d7);
		dots.add(d8);

		Edge e0 = new Edge(true, d0, d1);
		Edge e1 = new Edge(false, d0, d3);
		Edge e2 = new Edge(true, d1, d2);
		Edge e3 = new Edge(false, d1, d4);
		Edge e4 = new Edge(false, d2, d5);
		Edge e5 = new Edge(true, d3, d4);
		Edge e6 = new Edge(false, d3, d6);
		Edge e7 = new Edge(true, d4, d5);
		Edge e8 = new Edge(false, d4, d7);
		Edge e9 = new Edge(false, d5, d8);
		Edge e10 = new Edge(true, d6, d7);
		Edge e11 = new Edge(true, d7, d8);
		List<Edge> edges = new LinkedList<Edge>();
		edges.add(e0);
		edges.add(e1);
		edges.add(e2);
		edges.add(e3);
		edges.add(e4);
		edges.add(e5);
		edges.add(e6);
		edges.add(e7);
		edges.add(e8);
		edges.add(e9);
		edges.add(e10);
		edges.add(e11);
		
		MainModel model = new MainModel();
		model.setMatrixSize(3);
		model.setDots(dots);
		model.setEdges(edges);
		edges.get(0).active();;
		edges.get(1).active();;
		edges.get(2).active();;
		
		Node node = new Node(edges, 0, 0);
		System.out.println(node.toString());
		Node bestNode = model.minimax(true, node, 2, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
}
