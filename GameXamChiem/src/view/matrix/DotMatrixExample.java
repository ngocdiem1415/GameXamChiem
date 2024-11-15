package matrix;

import javax.swing.JFrame;

public class DotMatrixExample extends JFrame {
	Matrix dotPanel = new Matrix(4, 4);
	
	public DotMatrixExample() {
		setTitle("Dots");
		setSize(700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(dotPanel);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		DotMatrixExample frame = new DotMatrixExample();
	}
}
