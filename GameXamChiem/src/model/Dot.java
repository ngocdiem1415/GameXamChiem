package model;

import java.awt.Graphics;
import java.awt.Point;

public class Dot {
	Point point;
	public final static int RADIUS = 12;
	
	public Dot(Point p) {
		this.point = p;
	}

	// Phương thức tạo ra một Dot trên màn hình
	public void draw(Graphics g) {
		g.fillOval(point.x - RADIUS, point.y - RADIUS, RADIUS * 2, RADIUS * 2);
	}
	
	public int getX() {
		return this.point.x;
	}
	
	public int getY() {
		return this.point.y;
	}
	
	public boolean isDotAt(Point p) {
		return this.point.x == p.x && this.point.y == p.y;
	}
	
	public void setPoint(int x, int y) {
		this.point = new Point(x, y);
	}

	@Override
	public String toString() {
		return "Dot [point=" + point.x + "," + point.y + "]";
	}
	
}
