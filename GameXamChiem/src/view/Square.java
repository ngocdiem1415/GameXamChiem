package view;

import java.awt.Color;

public class Square {
	Dot dot;
	int width = Matrix.horizontalGap;
	int height = Matrix.verticalGap;
	Color color = Color.RED;
	
	public Square(Dot dot) {
		super();
		this.dot = dot;
	}
	
	
}
