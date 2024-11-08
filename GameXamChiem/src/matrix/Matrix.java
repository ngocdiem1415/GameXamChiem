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

import view.GameInterface;
import view.Login;

public class Matrix extends JPanel {
	int row, col;
	static int offset = 50, verticalGap, horizontalGap;
	List<Dot> dots = new ArrayList<Dot>();
	List<Edge> edges = new ArrayList<Edge>();
	Color connectedColor = Color.BLUE;
	int edgeWeight;

	public Matrix(int row, int col) {
		this.row = row;
		this.col = col;
		
		verticalGap = (Login.SIZE - offset*2 - Dot.RADIUS * 2)/row;
		horizontalGap = (Login.SIZE - offset*2 - Dot.RADIUS * 2)/(col-1);

		// Khởi tạo danh sách dots
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int x = offset + j * horizontalGap;
				int y = offset + i * verticalGap;
				dots.add(new Dot(new Point(x, y)));
			}
		}

		// Khởi tạo danh sách edges
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int dotIndex = col * i + j;
				Dot currentDot = dots.get(dotIndex);
				Dot endDot;
				if (i == row - 1 && j != col - 1) {
					endDot = dots.get(dotIndex + 1);
					edges.add(new Edge(true, currentDot, endDot));
					continue;
				}
				if (j == col - 1 && i != row - 1) {
					endDot = dots.get(dotIndex + col);
					edges.add(new Edge(false, currentDot, endDot));
					continue;
				}
				if (i == row - 1 && j == col - 1) {
					break;
				} else {
					endDot = dots.get(dotIndex + 1);
					edges.add(new Edge(true, currentDot, endDot));
					endDot = dots.get(dotIndex + col);
					edges.add(new Edge(false, currentDot, endDot));
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
