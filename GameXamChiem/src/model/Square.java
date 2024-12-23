package model;

import java.awt.Color;

import view.Matrix;

public class Square {
	private Dot dot;
	private int width = MainModel.horizontalGap;
	private int height = MainModel.verticalGap;
	private Color color = Color.RED;
	
	public Square(Dot dot) {
		this.dot = dot;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Dot getDot() {
		return dot;
	}

	public void setDot(Dot dot) {
		this.dot = dot;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
