package DialogGUI.Interface;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DialogGUI.Interface.User.Register;

import java.awt.event.*;

public class welcome extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    welcome frame = new welcome();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public welcome() {
        setTitle("汽车租赁系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 655, 433);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel WarmWelCome = new JLabel("欢迎使用中兴公司车辆租赁系统");
        WarmWelCome.setForeground(Color.DARK_GRAY);
        WarmWelCome.setHorizontalAlignment(SwingConstants.CENTER);
        WarmWelCome.setFont(new Font("宋体", Font.PLAIN, 32));
        contentPane.add(WarmWelCome, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JButton signinB = new JButton("登录");
        signinB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Signin();
            }
        });
        signinB.setBounds(236, 98, 145, 52);
        signinB.setFont(new Font("宋体", Font.PLAIN, 26));
        panel.add(signinB);

        JButton registerB = new JButton("注册");
        registerB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Register();
            }
        });
        registerB.setFont(new Font("宋体", Font.PLAIN, 26));
        registerB.setBounds(236, 178, 145, 52);
        panel.add(registerB);
    }
}
