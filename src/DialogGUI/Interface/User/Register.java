package DialogGUI.Interface.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import DialogGUI.Interface.InsertClient;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Register extends JFrame {

    private JPanel contentPane;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;
    private JTextField t5;
    private JTextField t6;
    private JTextField t7;

    private JFrame welcome;

    /**
     * Create the frame.
     */
    public Register(JFrame welcome) {
        this.welcome = welcome;
        setTitle("注册客户");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 669, 690);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel Title = new JLabel("注册");
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        Title.setFont(new Font("宋体", Font.BOLD, 32));
        contentPane.add(Title, BorderLayout.NORTH);
        JPanel MainP = new InsertClient(0);
        //MainP.getParent().getParent().getParent().getParent().getParent();
        contentPane.add(MainP, BorderLayout.CENTER);
        setVisible(true);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                Window_extended();
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    public void Window_extended() {
        this.welcome.setExtendedState(JFrame.NORMAL);
    }
}
