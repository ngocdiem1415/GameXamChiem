package matrix;

import java.awt.Graphics;
import java.awt.Point;

class Dot {
	Point point;
	final static int RADIUS = 20;

	public Dot(Point p) {
		super();
		this.point = p;
	}

//	// Phương thức kiểm tra một tọa độ có nằm trong bán kính của Dot hay không
//	public boolean contains(int x2, int y2) {
//		if (Math.sqrt(Math.pow(x2 - this.x, 2) + Math.pow(y2 - this.y, 2)) <= RADIUS)
//			return true;
//		return false;
//	}

	// Phương thức tạo ra một Dot trên màn hình
	public void draw(Graphics g) {
		g.fillOval(point.x - RADIUS, point.y - RADIUS, RADIUS * 2, RADIUS * 2);
	}
}
