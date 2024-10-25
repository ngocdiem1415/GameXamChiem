package matrix;

import java.awt.Graphics;

class Dot {
	int x, y;
	static int radius = 15;

	public Dot(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	// Phương thức kiểm tra một tọa độ có nằm trong bán kính của Dot hay không
	public boolean contains(int x2, int y2) {
		if (Math.sqrt(Math.pow(x2 - this.x, 2) + Math.pow(y2 - this.y, 2)) <= radius)
			return true;
		return false;
	}

	// Phương thức tạo ra một Dot trên màn hình
	public void draw(Graphics g) {
		g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
	}
}
