package view;

import cotroller.IController;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class HomePage extends JFrame {
    private JButton btnStart;
    ImagePanel imagePanel;
    private JPanel titlePN, optionPanel, mainPanel;
    IController control;
    JLabel nameLb, lb1;
    JTextField nameTF;
    private Observable obs;

    public HomePage(Observable obs, IController control) throws HeadlessException {
        this.obs = obs;
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
        getContentPane().setLayout(new BorderLayout());

        // ImagePanel
        imagePanel = new ImagePanel("src/image/background.png");
        imagePanel.setLayout(new BorderLayout());
        setContentPane(imagePanel);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        titlePN = new JPanel();
        lb1 = new JLabel("OPTIONS");
        lb1.setFont(new Font("Arial", Font.BOLD, 25));
        titlePN.add(lb1);
        titlePN.setOpaque(false);

        nameLb = new JLabel("Player name: ");
        nameLb.setFont(new Font("Arial", Font.BOLD, 20));
        nameLb.setBounds(50, 232, 409, 44);

        nameTF = new JTextField();
        nameTF.setFont(new Font("Arial", Font.BOLD, 20));
        nameTF.setHorizontalAlignment(SwingConstants.LEFT);
        nameTF.setColumns(8);
        nameTF.setBounds(170, 232, 409, 44);

        optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(4, 1, 10, 10));
        optionPanel.setOpaque(false);

        // Tạo JComboBox với các lựa chọn
        JLabel lb2 = new JLabel("Board Size");
        lb2.setFont(new Font("Arial", Font.BOLD, 20));
        String[] items = {"4x4", "6x6", "8x8"};
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        comboBox.setForeground(Color.BLUE);

//        chon muc do chơi
        // Tạo JComboBox với các lựa chọn
        JLabel lb3 = new JLabel("Level");
        lb3.setFont(new Font("Arial", Font.BOLD, 20));
        String[] item1 = {"Dễ", "Vừa", "Khó"};
        JComboBox<String> comboBox1 = new JComboBox<>(item1);
        comboBox1.setFont(new Font("Arial", Font.PLAIN, 16));
        comboBox1.setForeground(Color.BLUE);

//        // Start Button
        btnStart = new JButton("Start");
        btnStart.setBackground(new Color(34, 177, 76)); // Màu xanh lá
        btnStart.setForeground(Color.WHITE);
        btnStart.setFont(new Font("Arial", Font.BOLD, 20));
        btnStart.setPreferredSize(new Dimension(150, 50));
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameUser = nameTF.getText().trim().toUpperCase();
                if (nameUser.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a name before starting!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return; // Dừng lại nếu tên trống
                }

                setVisible(false); // Ẩn frame hiện tại
                int size = 4;
                switch (comboBox.getSelectedIndex()) {
                    case 0:size = 4; break;
                    case 1:size = 6; break;
                    case 2:size = 8; break;
                }
                int level = 2;
                switch (comboBox1.getSelectedIndex()) {
                    case 0:level = 2; break;
                    case 1:level = 3; break;
                    case 2:level = 4; break;
                }
//                System.out.println(nameUser+ size + level);
                control.createPlayer(level);
                new GameInterface(obs,control,nameUser,size, level, 1);
            }
        });
        optionPanel.add(nameLb);
        optionPanel.add(nameTF);
        optionPanel.add(lb2);
        optionPanel.add(comboBox);
        optionPanel.add(lb3);
        optionPanel.add(comboBox1);
        optionPanel.add(btnStart);
        mainPanel.add(titlePN, BorderLayout.NORTH);
        mainPanel.add(optionPanel, BorderLayout.CENTER);
        mainPanel.setOpaque(false); // Để hiển thị hình nền phía sau

        // Tạo panel để căn giữa optionPanel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false); // Để hiển thị hình nền phía sau
        centerPanel.add(mainPanel);

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
