package view;

import cotroller.IController;

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
	static int offset = 50, verticalGap, horizontalGap;
	List<Dot> dots = new ArrayList<Dot>();
	List<Edge> edges = new ArrayList<Edge>();
	List<Square> squares = new ArrayList<Square>();
	Color connectedColor = Color.BLACK;
	int edgeWeight;
	int widthScreen = GameInterface.SIZE;
	int heightScreen = 550;
	IController control;

	public Matrix(IController control, int row, int col) {
		this.row = row;
		this.col = col;
		this.control = control;

		verticalGap = (heightScreen - offset * 2 - Dot.RADIUS * 2) / (row - 1);
		horizontalGap = (widthScreen - offset * 2 - Dot.RADIUS * 2) / (col - 1);

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
					if (edge.contains(e.getPoint()) && edge.actived == false) {
						edge.color = edge.color == connectedColor ? edge.color : connectedColor;
						edge.actived = true;
						repaint();
						checkSquare(edge);
						control.currentState(edges);
						break;
					}
				}
			}
		});
	}

	private void checkSquare(Edge edge) {
		Dot start = edge.start;
		Dot end = edge.end;
		// Nếu là cạnh ngang
		if (edge.isHorizontal) {
			// Xét ô vuông bên trên
			Dot topStart = findDotByPoint(new Point(edge.start.getX(), edge.start.getY() - verticalGap));
			if (topStart != null) {
				System.out.println("co dot nam tren");
				Dot topEnd = findDotByPoint(new Point(edge.end.getX(), edge.end.getY() - verticalGap));
				List<Edge> edgeList = findListEdgeByDots(start, end, topStart, topEnd);
				boolean hasSquare = true;
				for (Edge e : edgeList) {
					if (!e.actived) {
						System.out.println("Chua tao o vuong");
						hasSquare = false;
						break;
					}
				}
				if (hasSquare == true) {
					System.out.println("Da tao o vuong");
					Square square = new Square(topStart);
					squares.add(square);
					repaint();
				}
			} else {
				System.out.println("Khong co dot ben tren");
			}

			// Xét ô vuông bên dưới
			Dot bottomStart = findDotByPoint(new Point(edge.start.getX(), edge.start.getY() + verticalGap));
			if (bottomStart != null) {
				System.out.println("co dot nam tren");
				Dot bottomEnd = findDotByPoint(new Point(edge.end.getX(), edge.end.getY() + verticalGap));
				List<Edge> edgeList = findListEdgeByDots(start, end, bottomStart, bottomEnd);
				boolean hasSquare = true;
				for (Edge e : edgeList) {
					if (!e.actived) {
						System.out.println("Chua tao o vuong");
						hasSquare = false;
						break;
					}
				}
				if (hasSquare == true) {
					System.out.println("Da tao o vuong");
					Square square = new Square(start);
					squares.add(square);
					repaint();
				}
			} else {
				System.out.println("Khong co dot ben duoi");
			}
		}

		// Nếu là cạnh dọc
		else {
			// Xét ô vuông bên trái
			Dot leftStart = findDotByPoint(new Point(edge.start.getX() - horizontalGap, edge.start.getY()));
			if (leftStart != null) {
				System.out.println("co dot ben trai");
				Dot leftEnd = findDotByPoint(new Point(edge.end.getX() - horizontalGap, edge.end.getY()));
				List<Edge> edgeList = findListEdgeByDots(start, end, leftStart, leftEnd);
				boolean hasSquare = true;
				for (Edge e : edgeList) {
					if (!e.actived) {
						System.out.println("Chua tao o vuong");
						hasSquare = false;
						break;
					}
				}
				if (hasSquare == true) {
					System.out.println("Da tao o vuong");
					Square square = new Square(leftStart);
					squares.add(square);
					repaint();
				}
			} else {
				System.out.println("Khong co dot ben trai");
			}

			// Xét ô vuông bên phải
			Dot rightStart = findDotByPoint(new Point(edge.start.getX() + horizontalGap, edge.start.getY()));
			if (rightStart != null) {
				System.out.println("co dot ben trai");
				Dot rightEnd = findDotByPoint(new Point(edge.end.getX() + horizontalGap, edge.end.getY()));
				List<Edge> edgeList = findListEdgeByDots(start, end, rightStart, rightEnd);
				boolean hasSquare = true;
				for (Edge e : edgeList) {
					if (!e.actived) {
						System.out.println("Chua tao o vuong");
						hasSquare = false;
						break;
					}
				}
				if (hasSquare == true) {
					System.out.println("Da tao o vuong");
					Square square = new Square(start);
					squares.add(square);
					repaint();
				}
			} else {
				System.out.println("Khong co dot ben trai");
			}
		}
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
			if ((edge.start == d1 && edge.end == d2) || (edge.start == d2 && edge.end == d1)) {
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
			g.setColor(square.color);
			g.fillRect(square.dot.getX(), square.dot.getY(), square.width, square.height);
		}
		for (Edge edge : edges) {
			g.setColor(edge.color);
			edge.draw(g);
		}

		g.setColor(Color.BLACK);
		for (Dot dot : dots) {
			dot.draw(g);
		}
	}

	public static void main(String[] args) {
		DotMatrixExample frame = new DotMatrixExample();
	}
}
