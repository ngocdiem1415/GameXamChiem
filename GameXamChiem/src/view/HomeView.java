package view;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeView extends JFrame {
    private JButton btnStart;
    private JPanel titlePN, optionPanel, mainPanel;
    private JTextField nameTF;
    private JComboBox<String> sizeComboBox, levelComboBox;
    ImagePanel imagePanel;
    JLabel nameLb, lb1;
    
    String userName;

    public HomeView() {
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
        sizeComboBox = new JComboBox<>(items);
        sizeComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        sizeComboBox.setForeground(Color.BLUE);

//        chon muc do chơi
        // Tạo JComboBox với các lựa chọn
        JLabel lb3 = new JLabel("Level");
        lb3.setFont(new Font("Arial", Font.BOLD, 20));
        String[] item1 = {"Dễ", "Vừa", "Khó"};
        levelComboBox = new JComboBox<>(item1);
        levelComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        levelComboBox.setForeground(Color.BLUE);

//        // Start Button
        btnStart = new JButton("Start");
        btnStart.setBackground(new Color(34, 177, 76)); // Màu xanh lá
        btnStart.setForeground(Color.WHITE);
        btnStart.setFont(new Font("Arial", Font.BOLD, 20));
        btnStart.setPreferredSize(new Dimension(150, 50));
        
        optionPanel.add(nameLb);
        optionPanel.add(nameTF);
        optionPanel.add(lb2);
        optionPanel.add(sizeComboBox);
        optionPanel.add(lb3);
        optionPanel.add(levelComboBox);
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
    
    public void showMessage(String message) {
    	JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.WARNING_MESSAGE);
    }

	public JButton getBtnStart() {
		return btnStart;
	}
	
	public JTextField getNameTF() {
		return nameTF;
	}
	
	public String getUsername() {
		return userName;
	}
	
	public void setUserName(String name) {
		userName = name;
	}
	
	public JComboBox<String> getSizeComboBox() {
		return sizeComboBox;
	}
	
	public JComboBox<String> getLevelComboBox() {
		return levelComboBox;
	}
}
