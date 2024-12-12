package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Edge {
	Dot start, end;
	boolean isHorizontal;
	boolean actived = false;
	
	Color color = Color.GRAY;
	int width;
	int height;
	final static int WEIGHT = 15;

	public Edge() {
	}

	public Edge(Edge edge) {
		this.start = edge.start;
		this.end = edge.end;
		this.isHorizontal = edge.isHorizontal;
		this.actived = edge.actived;
		this.color = new Color(edge.color.getRGB(), true);;
		this.width = edge.width;
		this.height = edge.height;
	}

	public Edge(boolean isHorizontal, Dot start, Dot end) {
		this.start = start;
		this.end = end;
		this.isHorizontal = isHorizontal;
		if (isHorizontal) {
			width = Matrix.horizontalGap;
			height = WEIGHT;
		} else {
			width = WEIGHT;
			height = Matrix.verticalGap; //Matrix.verticalGap;
		}
	}

	// Phương thức kiểm tra một tọa độ có nằm trong bán kính của Edge hay không
	public boolean contains(Point p) {
		int offset = Dot.RADIUS;
		// Nếu edge nằm ngang
		if(isHorizontal) {
			if(p.x > start.getX() + offset) {
				return (p.x - start.getX() - offset) <= width - offset * 2 && Math.abs(p.y - start.getY()) <= height / 2;				
			}
			return false;
		} else {
			if(p.y > start.getY() + offset) {
				return (p.y - start.getY() - offset) <= height - offset * 2 && Math.abs(p.x - start.getX()) <= width / 2;				
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
		g.fillRect(start.getX(), start.getY() - height / 2, width, height);
	}

	public void drawVertical(Graphics g) {
		g.fillRect(start.getX() - width / 2, start.getY(), width, height);
	}

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public Dot getStart() {
		return start;
	}

	public void setStart(Dot start) {
		this.start = start;
	}

	public Dot getEnd() {
		return end;
	}

	public void setEnd(Dot end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "Edge{" +
				"actived=" + actived + "}";
	}
}
