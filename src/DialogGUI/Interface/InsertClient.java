package DialogGUI.Interface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import entity.Client;
import handle.DataProcessing;
import handle.Search_SQL_sen;

public class InsertClient extends JPanel {
    private JTextField tid;
    private JPasswordField tpassword;
    private JTextField tname;
    private JTextField tcompany;
    private JTextField ttel;
    private JTextField taddr;
    private JTextField tzipcode;

    /**
     * Create the panel.
     * 要求这个面板的大小必须要有 630*580  TODO
     */
    public InsertClient(int flag) {
        setLayout(null);

        JLabel title = new JLabel("添加客户");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 26));
        title.setEnabled(true);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setBounds(64, 15, 508, 41);
        add(title);

        JLabel lpassword = new JLabel("密码");
        lpassword.setHorizontalAlignment(SwingConstants.CENTER);
        lpassword.setFont(new Font("宋体", Font.PLAIN, 22));
        lpassword.setBounds(82, 136, 133, 41);
        add(lpassword);

        JLabel lid = new JLabel("输入客户号");
        lid.setFont(new Font("宋体", Font.PLAIN, 22));
        lid.setBounds(82, 80, 133, 41);
        add(lid);

        JLabel lname = new JLabel("客户姓名");
        lname.setFont(new Font("宋体", Font.PLAIN, 22));
        lname.setBounds(82, 199, 133, 41);
        add(lname);

        JLabel lcompany = new JLabel("客户单位");
        lcompany.setFont(new Font("宋体", Font.PLAIN, 22));
        lcompany.setBounds(82, 255, 133, 41);
        add(lcompany);

        JLabel ltel = new JLabel("客户电话");
        ltel.setFont(new Font("宋体", Font.PLAIN, 22));
        ltel.setBounds(82, 325, 133, 41);
        add(ltel);

        JLabel laddr = new JLabel("客户地址");
        laddr.setFont(new Font("宋体", Font.PLAIN, 22));
        laddr.setBounds(82, 394, 133, 41);
        add(laddr);

        JLabel lzipcode = new JLabel("邮编");
        lzipcode.setHorizontalAlignment(SwingConstants.CENTER);
        lzipcode.setFont(new Font("宋体", Font.PLAIN, 22));
        lzipcode.setBounds(82, 460, 133, 41);
        add(lzipcode);

        tid = new JTextField();
        tid.setFont(new Font("宋体", Font.PLAIN, 22));
        tid.setBounds(283, 80, 174, 41);
        add(tid);
        tid.setColumns(15);

        tpassword = new JPasswordField();
        tpassword.setFont(new Font("宋体", Font.PLAIN, 20));
        tpassword.setBounds(283, 139, 174, 41);
        add(tpassword);

        tname = new JTextField();
        tname.setFont(new Font("宋体", Font.PLAIN, 22));
        tname.setColumns(15);
        tname.setBounds(283, 199, 174, 41);
        add(tname);

        tcompany = new JTextField();
        tcompany.setFont(new Font("宋体", Font.PLAIN, 22));
        tcompany.setColumns(15);
        tcompany.setBounds(283, 255, 313, 41);
        add(tcompany);

        ttel = new JTextField();
        ttel.setFont(new Font("宋体", Font.PLAIN, 22));
        ttel.setColumns(15);
        ttel.setBounds(283, 325, 212, 41);
        add(ttel);

        taddr = new JTextField();
        taddr.setFont(new Font("宋体", Font.PLAIN, 22));
        taddr.setColumns(30);
        taddr.setBounds(283, 394, 313, 41);
        add(taddr);

        tzipcode = new JTextField();
        tzipcode.setFont(new Font("宋体", Font.PLAIN, 22));
        tzipcode.setColumns(15);
        tzipcode.setBounds(283, 460, 174, 41);
        add(tzipcode);

        JButton SubBnt = new JButton("提交");
        SubBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(tid.getText());
                String password = new String(tpassword.getPassword());
                String name = tname.getText();
                String company = tcompany.getText();
                String addr = taddr.getText();
                String tel = ttel.getText();
                String zipcode = tzipcode.getText();
                Client client = new Client(id, password, name, company, tel, addr, zipcode);
                try {
                    if (!DataProcessing.insertClient(client) && flag == 1)
                        title.setText("插入失败，请重新尝试");
                    else if (flag == 0) {
                        Register register = (Register) getTopLevelAncestor(); //返回父类容器
                        register.dispose();
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "成功保存", "提示", JOptionPane.INFORMATION_MESSAGE);
                reset();
            }
        });
        SubBnt.setFont(new Font("宋体", Font.PLAIN, 22));
        SubBnt.setBounds(246, 521, 142, 44);
        add(SubBnt);

        JButton CanBnt = new JButton("清空");
        CanBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        CanBnt.setFont(new Font("宋体", Font.PLAIN, 22));
        CanBnt.setBounds(491, 521, 109, 44);
        add(CanBnt);
    }

    private void reset() {
        tid.setText("");
        tpassword.setText("");
        tname.setText("");
        tcompany.setText("");
        taddr.setText("");
        ttel.setText("");
        tzipcode.setText("");
    }
}
