package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

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
