package view;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    private JComboBox<String> boardSizeBox;
    private JButton btnStart;
    ImagePanel imagePanel;
    private  JPanel optionPanel;

    public HomePage() throws HeadlessException {
        init();
        visible();
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
        JPanel startPanel = new JPanel(new FlowLayout());
        btnStart = new JButton("Start");
        btnStart.setBackground(new Color(34, 177, 76)); // Màu xanh lá
        btnStart.setForeground(Color.WHITE);
        btnStart.setFont(new Font("Arial", Font.BOLD, 20));
        btnStart.setPreferredSize(new Dimension(150, 50));

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

    public void visible() {
        this.setVisible(true);
    }


}
