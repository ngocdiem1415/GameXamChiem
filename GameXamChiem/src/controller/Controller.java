package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;

import model.Edge;
import model.MainModel;
import view.GameView;
import view.HomeView;
import view.LoginView;
import view.MainView;
import view.Matrix;

public class Controller {
	MainModel model;
	MainView view;

	public Controller(MainModel model, MainView view) {
		this.model = model;
		this.view = view;
		init();
	}

	private void init() {
		LoginView loginView = view.getLoginView();
		HomeView homeView = view.getHomeView();

		/*
		 * Ở giao diện LoginView, nếu nhấn nút "Play game" thì view sẽ chuyển sang
		 * HomeView
		 */
		loginView.getBtnPlayGame().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				view.transformToHomeView();
			}
		});

		/*
		 * Ở giao diện HomeView, nếu nhấn nút "Start" thì đầu tiên kiểm tra tên người
		 * dùng đã nhập chưa. Nếu chưa sẽ hiển thị ra thông báo. Nếu rồi sẽ lấy ra kích
		 * thước ma trận và độ khó trò chơi gửi cho model. Cuối cùng, view sẽ chuyển
		 * sang GameView với điểm của người chơi được khởi tạo là bằng 0
		 */
		homeView.getBtnStart().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// nameTF.getText().trim().toUpperCase()
				String nameTFText = homeView.getNameTF().getText();

				// Kiểm tra tên người chơi
				if (nameTFText.equalsIgnoreCase("")) {
					homeView.showMessage("Please enter a name before starting!");
				} else {
					String userName = nameTFText.trim();
					homeView.setUserName(userName); // Gán tên người chơi để hiển thị lên giao diện game

					// Lấy ra kích thước ma trận
					JComboBox<String> sizeComboBox = homeView.getSizeComboBox();
					int size;
					switch (sizeComboBox.getSelectedIndex()) {
					case 0:
						size = 5;
						break;
					case 1:
						size = 7;
						break;
					case 2:
						size = 9;
						break;
					default:
						size = 5;
						break;
					}

					// Lấy ra độ khó của trò chơi
					JComboBox<String> levelComboBox = homeView.getLevelComboBox();
					int level;
					switch (levelComboBox.getSelectedIndex()) {
					case 0:
						level = 2;
						break;
					case 1:
						level = 3;
						break;
					case 2:
						level = 4;
						break;
					default:
						level = 2;
						break;
					}

					view.initGameView(userName, size, level);
					GameView gameView = view.getGameView();
					Matrix matrixPanel = gameView.getMatrixPanel();
					model.setOffset(matrixPanel.getOffset());
					model.setHorizontalGap(matrixPanel.getHorizontalGap());
					model.setVerticalGap(matrixPanel.getVerticalGap());
					model.setMatrixSize(size);
					model.setDepthMinimax(level);
					view.updateMatrixPanel(model.getDots(), model.getEdges(), model.getSquares());
					view.transformToGameView();

					// Hiển thị lượt chơi và điểm số ban đầu
					gameView.displayPlayerName(model.getCurrentToken());
					gameView.displayScore(0, 0);

					// Thêm sự kiện nhấp chuột vào matrix ở GameView (Chỉ có user mới có sự kiện
					// click chuột)
					matrixPanel.addMouseListener(new MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							for (Edge edge : model.getEdges()) {
								if (edge.contains(e.getPoint()) && edge.isActivated() == false) {
									model.makeMove(edge);
									gameView.displayScore(model.getUserScore(), model.getAIScore());
									gameView.displayPlayerName(model.getCurrentToken());
									matrixPanel.updateSquares(model.getSquares());
									matrixPanel.repaint();
									if (model.isAITurn()) {
//										model.runAIMove();
										while (model.isAITurn()) {
											model.runAIMove(); // AI thực hiện nước đi
											gameView.displayScore(model.getUserScore(), model.getAIScore());
											gameView.displayPlayerName(model.getCurrentToken());
											matrixPanel.updateSquares(model.getSquares());
											matrixPanel.repaint();
										}
									}
									break;
								}
							}
//							System.out.println(matrixPanel.getSquares().size());
						};
					});
				}
			}
		});
	}
}
