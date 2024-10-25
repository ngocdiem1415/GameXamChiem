package matrix;

import java.awt.Graphics;

public class Edge {
	int x, y; 
	int horizontalLength;
	int verticalLength;
	boolean isHorizontal;
	Dot startDot;

	public Edge(boolean isHorizontal, Dot startDot) {
		this.x = (startDot.x + horizontalLength) / 2;
		this.y = (startDot.y + verticalLength) / 2;
		this.startDot = startDot;
		this.isHorizontal = isHorizontal;
		if(isHorizontal) {
			horizontalLength = Matrix.gap;
			verticalLength = 10;
		} else {
			horizontalLength = 10;
			verticalLength = Matrix.gap;
		}
	}

	// Phương thức kiểm tra một tọa độ có nằm trong bán kính của Dot hay không
//	public boolean contains(int x2, int y2) {
//		// Xét theo kích thước chiều ngang
//		if (Math.abs(x2 - this.x) - Dot.radius < width / 2 && Math.abs(y2 - this.y) <= weight / 2) {
//			return true;
//		}
//		return false;
//	}
	
	public void draw(Graphics g) {
		if(this.isHorizontal) {
			drawHorizontal(g);
		}
		else {
			drawVertical(g);
		}
	}

	// Phương thức tạo ra một Dot trên màn hình
	public void drawHorizontal(Graphics g) {
		g.fillRect(startDot.x, startDot.y, horizontalLength, verticalLength);
	}
	
	public void drawVertical(Graphics g) {
		g.fillRect(startDot.x - horizontalLength/2, startDot.y, horizontalLength, verticalLength);
	}
}
