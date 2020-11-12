package DialogGUI.Interface;

import entity.Client;
import handle.DataProcessing;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.sql.SQLException;

public class Register extends JFrame {

    private JPanel contentPane;
    private JTextField t1;
    private JTextField t2;
    private JTextField t3;
    private JTextField t4;
    private JTextField t5;
    private JTextField t6;
    private JTextField t7;

    /**
     * Create the frame.
     */
    public Register() {
        setTitle("注册客户");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 689, 748);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel Title = new JLabel("注册");
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        Title.setFont(new Font("宋体", Font.BOLD, 32));
        contentPane.add(Title, BorderLayout.NORTH);

        JPanel MainP = new JPanel();
        contentPane.add(MainP, BorderLayout.CENTER);
        MainP.setLayout(null);

        JLabel L1 = new JLabel("密码");
        L1.setFont(new Font("宋体", Font.PLAIN, 24));
        L1.setHorizontalAlignment(SwingConstants.CENTER);
        L1.setBounds(167, 110, 91, 49);
        MainP.add(L1);

        JLabel L2 = new JLabel("客户号");
        L2.setFont(new Font("宋体", Font.PLAIN, 24));
        L2.setHorizontalAlignment(SwingConstants.CENTER);
        L2.setBounds(167, 34, 91, 49);
        MainP.add(L2);

        JLabel L3 = new JLabel("姓名");
        L3.setFont(new Font("宋体", Font.PLAIN, 24));
        L3.setHorizontalAlignment(SwingConstants.CENTER);
        L3.setBounds(167, 181, 91, 49);
        MainP.add(L3);

        JLabel L4 = new JLabel("单位");
        L4.setFont(new Font("宋体", Font.PLAIN, 24));
        L4.setHorizontalAlignment(SwingConstants.CENTER);
        L4.setBounds(167, 245, 91, 49);
        MainP.add(L4);

        JLabel L5 = new JLabel("电话");
        L5.setFont(new Font("宋体", Font.PLAIN, 24));
        L5.setHorizontalAlignment(SwingConstants.CENTER);
        L5.setBounds(167, 323, 91, 49);
        MainP.add(L5);

        JLabel L6 = new JLabel("地址");
        L6.setFont(new Font("宋体", Font.PLAIN, 24));
        L6.setHorizontalAlignment(SwingConstants.CENTER);
        L6.setBounds(167, 394, 91, 49);
        MainP.add(L6);

        JLabel L7 = new JLabel("邮编");
        L7.setFont(new Font("宋体", Font.PLAIN, 24));
        L7.setHorizontalAlignment(SwingConstants.CENTER);
        L7.setBounds(167, 469, 91, 49);
        MainP.add(L7);

        t1 = new JTextField();
        t1.setFont(new Font("宋体", Font.PLAIN, 22));
        t1.setBounds(320, 36, 200, 45);
        MainP.add(t1);
        t1.setColumns(20);

        t2 = new JTextField();
        t2.setFont(new Font("宋体", Font.PLAIN, 22));
        t2.setColumns(20);
        t2.setBounds(320, 110, 200, 45);
        MainP.add(t2);

        t3 = new JTextField();
        t3.setFont(new Font("宋体", Font.PLAIN, 22));
        t3.setColumns(20);
        t3.setBounds(320, 181, 200, 45);
        MainP.add(t3);

        t4 = new JTextField();
        t4.setFont(new Font("宋体", Font.PLAIN, 22));
        t4.setColumns(20);
        t4.setBounds(320, 245, 309, 45);
        MainP.add(t4);

        t5 = new JTextField();
        t5.setFont(new Font("宋体", Font.PLAIN, 22));
        t5.setColumns(20);
        t5.setBounds(320, 323, 309, 45);
        MainP.add(t5);

        t6 = new JTextField();
        t6.setFont(new Font("宋体", Font.PLAIN, 22));
        t6.setColumns(20);
        t6.setBounds(320, 394, 309, 45);
        MainP.add(t6);

        t7 = new JTextField();
        t7.setFont(new Font("宋体", Font.PLAIN, 22));
        t7.setColumns(20);
        t7.setBounds(320, 469, 200, 45);
        MainP.add(t7);

        JButton SBnt = new JButton("确定");
        SBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int id = Integer.parseInt(t1.getText());
                String name = t3.getText();
                String password = t2.getText();
                String company = t4.getText();
                String tel = t5.getText();
                String addr = t6.getText();
                String zipcode = t7.getText();
                Client client = new Client(id, password, name, company, tel, addr, zipcode);
                try {
                    if (!DataProcessing.searchClient(id, password)) {
                        DataProcessing.insertClient(client);
                    } else {
                        dispose();
                    }
                    new Signin();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        SBnt.setFont(new Font("宋体", Font.PLAIN, 24));
        SBnt.setBounds(109, 557, 154, 45);
        MainP.add(SBnt);

        JButton DBnt = new JButton("取消");
        DBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        DBnt.setFont(new Font("宋体", Font.PLAIN, 24));
        DBnt.setBounds(397, 557, 154, 45);
        MainP.add(DBnt);
    }
}
