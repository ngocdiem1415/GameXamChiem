package matrix;

import javax.swing.JFrame;

public class DotMatrixExample extends JFrame {
	Matrix dotPanel = new Matrix(4, 5);
	
	public DotMatrixExample() {
		setTitle("Dots");
		setSize(600, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(dotPanel);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		DotMatrixExample frame = new DotMatrixExample();
	}
}
