package matrix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Matrix extends JPanel {
	int row, col;
	final static int GAP = 150, OFFSET = 50;
	List<Dot> dots = new ArrayList<Dot>();
	List<Edge> edges = new ArrayList<Edge>();
	Color connectedColor = Color.BLUE;

	public Matrix(int row, int col) {
		this.row = row;
		this.col = col;

		// Khởi tạo danh sách dots
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int x = OFFSET + j * GAP;
				int y = OFFSET + i * GAP;
				dots.add(new Dot(new Point(x, y)));
			}
		}

		// Khởi tạo danh sách edges
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int dotIndex = col * i + j;
				Dot currentDot = dots.get(dotIndex);
				if (i == row - 1 && j == col - 1) {
					continue;
				}
				if (i == row - 1) {
					edges.add(new Edge(true, currentDot.point));
					continue;
				}
				if (j == col - 1) {
					edges.add(new Edge(false, currentDot.point));
					continue;
				}
				if (i == row - 1 && j == col - 1) {
					continue;
				} else {
					edges.add(new Edge(true, currentDot.point));
					edges.add(new Edge(false, currentDot.point));
				}
			}
		}
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				for (Edge edge : edges) {
					if(edge.contains(e.getPoint())) {
						edge.color = edge.color == connectedColor ? edge.color : connectedColor;
						repaint();
//						if(this.checkSquare(edge)) {
//							
//						}
						break;
					}
				}
			}

//			private boolean checkSquare(Edge edge) {
//				if(e.isHorizontal) {
//					List<Edge> topNeighbours = new ArrayList<Edge>();
//					List<Edge> bottomNeighbours = new ArrayList<Edge>();
//					//Tìm topNeighbours
//					for (Edge e : edges) {
//						if() {
//							
//						}
//					}
//				} else {
//					
//				}
//			}
		});
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Edge edge : edges) {
			g.setColor(edge.color);
			edge.draw(g);
		}

		g.setColor(Color.BLACK);
		for (Dot dot : dots) {
			dot.draw(g);
		}

	}
}
