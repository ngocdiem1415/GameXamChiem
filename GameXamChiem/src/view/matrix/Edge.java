package matrix;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Edge {
	Point start, end;
	boolean isHorizontal;
	Color color = Color.LIGHT_GRAY;
	int width;
	int height;
	final static int WEIGHT = 25;

	public Edge(boolean isHorizontal, Point p) {
		this.start = p;
		this.isHorizontal = isHorizontal;
		if (isHorizontal) {
			width = Matrix.GAP;
			height = WEIGHT;
			this.end = new Point(p.x + width, p.y);
		} else {
			width = WEIGHT;
			height = Matrix.GAP;
			this.end = new Point(p.x, p.y - height);
		}
	}

	// Phương thức kiểm tra một tọa độ có nằm trong bán kính của Edge hay không
	public boolean contains(Point p) {
		int offset = Dot.RADIUS;
		// Nếu edge nằm ngang
		if(isHorizontal) {
			if(p.x > start.x + offset) {
				return (p.x - start.x - offset) <= width - offset * 2 && Math.abs(p.y - start.y) <= height / 2;				
			}
			return false;
		} else {
			if(p.y > start.y + offset) {
				return (p.y - start.y - offset) <= height - offset * 2 && Math.abs(p.x - start.x) <= width / 2;				
			}
			return false;
		}
	}

	public void draw(Graphics g) {
		if (this.isHorizontal) {
			drawHorizontal(g);
		} else {
			drawVertical(g);
		}
	}

	// Phương thức vẽ Edge
	public void drawHorizontal(Graphics g) {
		g.fillRect(start.x, start.y - height / 2, width, height);
	}

	public void drawVertical(Graphics g) {
		g.fillRect(start.x - width / 2, start.y, width, height);
	}
}
