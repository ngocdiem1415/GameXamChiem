package view;

import cotroller.IController;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    private JButton btnStart;
    ImagePanel imagePanel;
    private JPanel optionPanel;
    IController control;

    public HomePage(IController control) throws HeadlessException {
        this.control = control;
        init();
    }

    private void init() {
        setTitle("Dots And Boxes");
        setLookAndFeel();
        setBounds(100, 100, 600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(null);

        // ImagePanel
        imagePanel = new ImagePanel("src/image/background.png");
        imagePanel.setLayout(new BorderLayout());
        setContentPane(imagePanel);

        optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(4, 1, 10, 10));
        optionPanel.setOpaque(false);

        JLabel lb1 = new JLabel("OPTIONS");
        lb1.setFont(new Font("Arial", Font.BOLD, 25));
        // Tạo JComboBox với các lựa chọn
        JLabel lb2 = new JLabel("Board Size");
        lb2.setFont(new Font("Arial", Font.BOLD, 20));
        String[] items = {"4x4", "6x6", "8x8", "10x10"};
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        comboBox.setForeground(Color.BLUE);

//        // Start Button
        btnStart = new JButton("Start");
        btnStart.setBackground(new Color(34, 177, 76)); // Màu xanh lá
        btnStart.setForeground(Color.WHITE);
        btnStart.setFont(new Font("Arial", Font.BOLD, 20));
        btnStart.setPreferredSize(new Dimension(150, 50));
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Ẩn frame hiện tại
                int value = comboBox.getSelectedIndex();
                switch (value) {
                    case 0:
                        new GameInterface(control, 4, 1).setVisible(true);
                        break;
                    case 1:
                        new GameInterface(control, 6, 1).setVisible(true);
                        break;
                    case 2:
                        new GameInterface(control, 8, 1).setVisible(true);
                        break;
                    case 3:
                        new GameInterface(control, 10, 1).setVisible(true);
                        break;
                }
            }
        });
        optionPanel.add(lb1);
        optionPanel.add(lb2);
        optionPanel.add(comboBox);
        optionPanel.add(btnStart);

        // Tạo panel để căn giữa optionPanel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false); // Để hiển thị hình nền phía sau
        centerPanel.add(optionPanel);

        // Thêm centerPanel vào trung tâm của imagePanel
        imagePanel.add(centerPanel, BorderLayout.CENTER);
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(NimbusLookAndFeel.class.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }

}
