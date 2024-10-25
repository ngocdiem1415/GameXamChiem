package matrix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Matrix extends JPanel {
	int row, col;
	static int gap = 150;
	List<Dot> dots = new ArrayList<Dot>();
	List<Edge> edges = new ArrayList<Edge>();

	public Matrix(int row, int col) {
		this.row = row;
		this.col = col;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				dots.add(new Dot(50 + j * gap, 50 + i * gap));
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				int dotIndex = col * i + j;
				Dot currentDot = dots.get(dotIndex);
				if (i == row - 1 && j == col - 1) {
					continue;
				}
				if (i == row - 1) {
					edges.add(new Edge(true, currentDot));
					continue;
				}
				if (j == col - 1) {
					edges.add(new Edge(false, currentDot));
					continue;
				}
				if (i == row - 1 && j == col - 1) {
					continue;
				} else {
					edges.add(new Edge(true, currentDot));
					edges.add(new Edge(false, currentDot));
				}
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);
		for (Edge edge : edges) {
			edge.draw(g);
		}

		g.setColor(Color.BLACK);
		for (Dot dot : dots) {
			dot.draw(g);
		}

	}
}
