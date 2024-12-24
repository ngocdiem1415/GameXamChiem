package view;

import java.util.List;

import model.Dot;
import model.Edge;
import model.Square;

public class MainView {
	private LoginView loginView;
	private HomeView homeView;
	private GameView gameView;
	
	public MainView() {
		loginView = new LoginView();
		homeView = new HomeView();
		loginView.setVisible(true);
	}
	
	public void transformToHomeView() {
		loginView.setVisible(false);
		homeView.setVisible(true);
	}

	public void initGameView(String userName, int size, int level) {
		gameView = new GameView(userName, size);
		gameView.displayPlayerName(1);
		gameView.displayScore(0, 0);
	}
	
	public void updateMatrixPanel(List<Dot> dotList, List<Edge> edgeList, List<Square> square) {
		gameView.getMatrixPanel().updateDots(dotList);
		gameView.getMatrixPanel().updateEdges(edgeList);
		gameView.getMatrixPanel().updateSquares(square);
	}
	
	public void transformToGameView() {
		homeView.setVisible(false);
		gameView.setVisible(true);
	}
	
	public LoginView getLoginView() {
		return loginView;
	}

	public HomeView getHomeView() {
		return homeView;
	}

	public GameView getGameView() {
		return gameView;
	}
}
