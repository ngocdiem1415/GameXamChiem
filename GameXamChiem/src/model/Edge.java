package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Edge {
	boolean isHorizontal;
	private Dot startDot, endDot;
	int width;
	int height;
	Color currentColor;
	final Color CONNECTED_COLOR = Color.BLACK;
	final Color ORIGINAL_COLOR = Color.GRAY;
	final static int WEIGHT = 15;
	boolean isActivated;

	public Edge(boolean isHorizontal, Dot startDot, Dot endDot) {
		this.isHorizontal = isHorizontal;
		this.startDot = startDot;
		this.endDot = endDot;
		this.currentColor = ORIGINAL_COLOR;
		this.isActivated = false;
		if (isHorizontal()) {
			this.width = MainModel.horizontalGap;
			this.height = WEIGHT;
		} else {
			this.width = WEIGHT;
			this.height = MainModel.verticalGap;
		}
	}

	public Edge(boolean isHorizontal, Dot startDot, Dot endDot, boolean isActivated) {
		this.isHorizontal = isHorizontal;
		this.startDot = startDot;
		this.endDot = endDot;
		this.isActivated = isActivated;
	}

	// Phương thức kiểm tra một tọa độ có nằm trong bán kính của Edge hay không
	public boolean contains(Point p) {
		int offset = Dot.RADIUS;
		// Nếu edge nằm ngang
		if (isHorizontal) {
			if (p.x > startDot.getX() + offset) {
				return (p.x - startDot.getX() - offset) <= width - offset * 2
						&& Math.abs(p.y - startDot.getY()) <= height / 2;
			}
			return false;
		} else {
			if (p.y > startDot.getY() + offset) {
				return (p.y - startDot.getY() - offset) <= height - offset * 2
						&& Math.abs(p.x - startDot.getX()) <= width / 2;
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
		g.fillRect(startDot.getX(), startDot.getY() - height / 2, width, height);
	}

	public void drawVertical(Graphics g) {
		g.fillRect(startDot.getX() - width / 2, startDot.getY(), width, height);
	}

	public boolean isActivated() {
		return isActivated;
	}

	public Dot getStartDot() {
		return startDot;
	}

	public void setStartDot(Dot startDot) {
		this.startDot = startDot;
	}

	public Dot getEndDot() {
		return endDot;
	}

	public void setEndDot(Dot endDot) {
		this.endDot = endDot;
	}

	public boolean isHorizontal() {
		return this.isHorizontal;
	}

	public int getWeight() {
		return this.WEIGHT;
	}

	public void setWidth(int value) {
		this.width = value;
	}

	public void setHeight(int value) {
		this.height = value;
	}

	public void active() {
		this.isActivated = true;
		this.currentColor = CONNECTED_COLOR;
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public Color getCONNECTED_COLOR() {
		return CONNECTED_COLOR;
	}

	public Color getORIGINAL_COLOR() {
		return ORIGINAL_COLOR;
	}

	public Edge getCopy() {
		return new Edge(this.isHorizontal, this.startDot, this.endDot, this.isActivated);
	}

	@Override
	public String toString() {
		return "s=d" + MainModel.indexOfDot(startDot) + ", hor = " + isHorizontal + ", act = " + isActivated;
	}

}
