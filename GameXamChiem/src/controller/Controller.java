package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

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

    LoginView loginView;
    HomeView homeView;
    GameView gameView;
    Matrix matrixPanel;

    public Controller(MainModel model, MainView view) {
        this.model = model;
        this.view = view;
        init();
    }

    private void init() {
        loginView = view.getLoginView();
        homeView = view.getHomeView();

        loginView.getBtnPlayGame().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.transformToHomeView();
            }
        });

        homeView.getBtnStart().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                 * 1. Kiểm tra nếu user name để trống thì hiển thị thông báo và không làm gì cả
                 */
                String userName = homeView.getNameTF().getText();
                if (userName.equalsIgnoreCase("")) {
                    homeView.showMessage("Please enter a name before starting!");
                    return;
                }

                /*
                 * 2. Nếu user name hợp lệ thì thực hiện các bước tiếp theo:
                 * 2.1. Lấy ra user name
                 * 2.2. Lấy ra size
                 * 2.3. Lấy ra level
                 * 2.4. Khởi tạo Game View truyền vào 3 tham số trên
                 */
                homeView.setUserName(userName);
                int size = getSizeFromView();
                int level = getLevelFromView();
                view.initGameView(userName, size, level);
                gameView = view.getGameView();
                matrixPanel = gameView.getMatrixPanel();

                /*
                 * 3. Khởi tạo Model truyền vào tham số size và level để phục vụ xử lý logic với:
                 * 3.1. Size: Dùng để khởi tạo danh sách Dots, Edges
                 * 3.2. Level: Dùng phục vụ cho thuật toán minimax
                 * 3.3. Danh sách Squares cũng sẽ được khởi tạo là một danh sách rỗng
                 */
                initModel(size, level);

                /*
                 * 4. Sau khi model được khởi tạo thì gửi Dots, Edges, Squares cho Matrix Panel.
                 * Mục đích:
                 * - Matrix Panel sẽ tham chiếu đến các danh sách này
                 * - Mỗi khi có sự thay đổi Dots, Edges, Squares từ Model, Matrix Panel cũng sẽ thấy được thay đổi đó
                 */
                view.updateMatrixPanel(model.getDots(), model.getEdges(), model.getSquares());

                /*
                 * 5. Hoàn tất thiết lập mọi thứ thì chạy Game View để chơi game
                 */
                view.transformToGameView();


                /*
                 * 6. Thêm sự kiện nếu người chơi nhấp chuột vào Matrix Panel trong lúc chơi
                 */
                matrixPanel.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        // Nếu AI đang đi thì không cho người chơi thực hiện hành động
                        if (model.isAITurn()) {
                            return;
                        }
                        /*
                         * 6.1. Nếu tọa độ nhấp chuột nằm trong phạm vi của một cạnh thì:
                         * 6.1.1. Kiểm tra cạnh đó đã kích hoạt hay chưa
                         * 6.1.2. Nếu chưa kích hoạt thì sẽ gọi Model thực hiện đi cạnh đó
                         * 6.1.3. Vẽ lại Matrix Panel sau khi cạnh đó được kích hoạt, bao gồm:
                         * - Vẽ lại cạnh
                         * - Vẽ lại ô vuông (nếu được tạo)
                         * - Vẽ lại điểm số
                         */
                        try {
                            for (Edge edge : model.getEdges()) {
                                if (edge.contains(e.getPoint()) && edge.isActivated() == false) {
                                    model.makeMove(edge);
                                    updateView();
                                    break;
                                }
                            }

                            /*
                             * 6.2. Nếu người chơi thực hiện nước đi mà không tạo ô vuông thì lượt tiếp theo là của AI
                             * 6.2.1. Gọi model thực hiện nước đi cho AI
                             * 6.2.2. Vẽ lại Matrix Panel
                             */

                            if (model.isAITurn()) {
                                // Tạo luồng riêng cho AI
                                Thread aiThread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(2000); // Tạo hiệu ứng chờ
                                            while (model.isAITurn()) {
                                                model.makeAIMove();
                                                updateView();
                                            }
                                        } catch (InterruptedException ex) {
                                            System.err.println("Luồng bị gián đoạn");
                                            Thread.currentThread().interrupt(); // Khôi phục trạng thái ngắt
                                        }
                                    }
                                });
                                aiThread.start();
                            }
                        } catch (Exception ex) {
                            // Xử lý ngoại lệ chung
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    private void updateView() {
        gameView.displayScore(model.getUserScore(), model.getAIScore());
        gameView.displayPlayerName(model.getCurrentToken());
        matrixPanel.repaint();
        if ( model.endGame()){
            int isWinner = model.isWinner();
            view.endGame(isWinner);
        }
    }

    private void initModel(int size, int level) {
        model.setOffset(matrixPanel.getOffset());
        model.setHorizontalGap(matrixPanel.getHorizontalGap());
        model.setVerticalGap(matrixPanel.getVerticalGap());
        model.setMatrixSize(size);
        model.setDepthMinimax(level);
    }

    private int getSizeFromView() {
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
        return size;
    }

    private int getLevelFromView() {
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
        return level;
    }
}
