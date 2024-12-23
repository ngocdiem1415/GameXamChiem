package progam;

import controller.Controller;
import model.MainModel;
import view.MainView;

public class Program {
	public static void main(String[] args) {
		MainModel model = new MainModel();
		MainView view = new MainView(); 
		Controller controller = new Controller(model, view);
	}
}
