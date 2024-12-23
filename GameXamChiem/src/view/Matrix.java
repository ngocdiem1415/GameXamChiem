package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

import model.Dot;
import model.Edge;
import model.MainModel;
import model.Square;

public class Matrix extends JPanel {
	int size;
	int offset = 50, verticalGap, horizontalGap;
	List<Dot> dots = new ArrayList<Dot>();
	List<Edge> edges = new LinkedList<Edge>();
	List<Square> squares = new ArrayList<Square>();
	int edgeWeight;
	int widthScreen = GameView.SIZE;
	int heightScreen = 550;
	List<Edge> newListEdge = new ArrayList<>();
	GameView gameinterface;

	public Matrix(int size) {
		this.size = size;
		verticalGap = (heightScreen - offset * 2 - Dot.RADIUS * 2) / (size - 1);
		horizontalGap = (widthScreen - offset * 2 - Dot.RADIUS * 2) / (size - 1);
	}

	public void updateDots(List<Dot> dotList) {
		this.dots = dotList;
		repaint();
	}

	public void updateEdges(List<Edge> edgeList) {
		this.edges = edgeList;
		repaint();
	}

	public void updateSquares(List<Square> squareList) {
		this.squares = squareList;
		repaint();
	}

	private Dot findDotByPoint(Point p) {
		for (Dot dot : dots) {
			if (dot.isDotAt(p))
				return dot;
		}
		return null;
	}

	private Edge findEdgeByDots(Dot d1, Dot d2) {
		for (Edge edge : edges) {
			if ((edge.getStartDot() == d1 && edge.getEndDot() == d2)
					|| (edge.getStartDot() == d2 && edge.getEndDot() == d1)) {
				return edge;
			}
		}
		return null;
	}

	private List<Edge> findListEdgeByDots(Dot d1, Dot d2, Dot d3, Dot d4) {
		List<Edge> edgeList = new ArrayList<Edge>();
		List<Dot> dotList = new ArrayList<Dot>();
		dotList.add(d1);
		dotList.add(d2);
		dotList.add(d3);
		dotList.add(d4);
		for (int i = 0; i < dotList.size() - 1; i++) {
			for (int j = i + 1; j < dotList.size(); j++) {
				Edge e = findEdgeByDots(dotList.get(i), dotList.get(j));
				if (e != null) {
					edgeList.add(e);
				}
			}
		}
		return edgeList;
	}

	// Vẽ các dots và edges lên màn hình
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Square square : squares) {
			g.setColor(square.getColor());
			g.fillRect(square.getDot().getX(), square.getDot().getY(), square.getWidth(), square.getHeight());
		}

		for (Edge edge : edges) {
			g.setColor(edge.getCurrentColor());
			edge.draw(g);
		}

		g.setColor(Color.BLACK);
		for (Dot dot : dots) {
			dot.draw(g);
		}
	}

	public int getVerticalGap() {
		return verticalGap;
	}

	public int getHorizontalGap() {
		return horizontalGap;
	}

	public int getOffset() {
		return offset;
	}

	public List<Square> getSquares() {
		return this.squares;
	}
}
