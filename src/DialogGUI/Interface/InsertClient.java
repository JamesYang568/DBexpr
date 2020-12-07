package DialogGUI.Interface;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

import DialogGUI.Help.InputParse;
import DialogGUI.Interface.User.Register;
import entity.Client;
import handle.DataProcessing;

/**
 * 在登录注册和管理员注册中共用了这个panel
 * flag =1为用户自己的注册
 * flag =0为管理员的顾客插入
 */
public class InsertClient extends JPanel {
    private final JTextField idT;
    private final JPasswordField passwordT;
    private final JTextField nameT;
    private final JTextField companyT;
    private final JTextField telT;
    private final JTextField addrT;
    private final JTextField zipcodeT;

    /**
     * Create the panel.
     * 要求这个面板的大小必须要有 630*580  TODO
     */
    public InsertClient(int flag) {
        setLayout(null);

        JLabel title = new JLabel("增加客户");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 45));
        title.setEnabled(true);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setBounds(483, 0, 508, 67);
        add(title);

        JLabel lpassword = new JLabel("密码");
        lpassword.setHorizontalAlignment(SwingConstants.CENTER);
        lpassword.setFont(new Font("宋体", Font.PLAIN, 35));
        lpassword.setBounds(311, 157, 212, 66);
        add(lpassword);

        JLabel lid = new JLabel("输入客户号");
        lid.setFont(new Font("宋体", Font.PLAIN, 35));
        lid.setBounds(288, 80, 212, 66);
        add(lid);

        JLabel lname = new JLabel("客户姓名");
        lname.setFont(new Font("宋体", Font.PLAIN, 35));
        lname.setBounds(311, 236, 212, 66);
        add(lname);

        JLabel lcompany = new JLabel("客户单位");
        lcompany.setFont(new Font("宋体", Font.PLAIN, 35));
        lcompany.setBounds(311, 307, 212, 66);
        add(lcompany);

        JLabel ltel = new JLabel("客户电话");
        ltel.setFont(new Font("宋体", Font.PLAIN, 35));
        ltel.setBounds(311, 386, 212, 66);
        add(ltel);

        JLabel laddr = new JLabel("客户地址");
        laddr.setFont(new Font("宋体", Font.PLAIN, 35));
        laddr.setBounds(311, 465, 212, 66);
        add(laddr);

        JLabel lzipcode = new JLabel("邮编");
        lzipcode.setHorizontalAlignment(SwingConstants.CENTER);
        lzipcode.setFont(new Font("宋体", Font.PLAIN, 35));
        lzipcode.setBounds(311, 538, 212, 66);
        add(lzipcode);

        //文本框初始化
        {
            idT = new JTextField();
            idT.setFont(new Font("宋体", Font.PLAIN, 35));
            idT.setBounds(654, 85, 466, 53);
            add(idT);
            idT.setColumns(15);

            passwordT = new JPasswordField();
            passwordT.setFont(new Font("宋体", Font.PLAIN, 35));
            passwordT.setBounds(654, 164, 466, 53);
            add(passwordT);

            nameT = new JTextField();
            nameT.setFont(new Font("宋体", Font.PLAIN, 35));
            nameT.setColumns(15);
            nameT.setBounds(654, 243, 466, 53);
            add(nameT);

            companyT = new JTextField();
            companyT.setFont(new Font("宋体", Font.PLAIN, 35));
            companyT.setColumns(15);
            companyT.setBounds(654, 322, 466, 53);
            add(companyT);

            telT = new JTextField();
            telT.setFont(new Font("宋体", Font.PLAIN, 35));
            telT.setColumns(15);
            telT.setBounds(654, 399, 466, 53);
            add(telT);

            addrT = new JTextField();
            addrT.setFont(new Font("宋体", Font.PLAIN, 35));
            addrT.setColumns(30);
            addrT.setBounds(654, 472, 466, 53);
            add(addrT);

            zipcodeT = new JTextField();
            zipcodeT.setFont(new Font("宋体", Font.PLAIN, 35));
            zipcodeT.setColumns(15);
            zipcodeT.setBounds(654, 551, 466, 53);
            add(zipcodeT);
        }

        JButton SubBnt = new JButton("提交");
        SubBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(InputParse.parseID(idT.getText()));
                String password = new String(passwordT.getPassword());
                String name = nameT.getText();
                String company = companyT.getText();
                String addr = addrT.getText();
                String tel = telT.getText();
                String zipcode = zipcodeT.getText();
                Client client = new Client(id, password, name, company, tel, addr, zipcode);
                try {
                    if (!DataProcessing.insertClient(client) && flag == 1)
                        JOptionPane.showMessageDialog(null, "插入失败，请重新尝试", "出错了", JOptionPane.ERROR_MESSAGE);
                    else if (flag == 0) {
                        Register register = (Register) getTopLevelAncestor(); //返回父类容器
                        //register.Window_extended();
                        register.dispose();
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                reset();
                JOptionPane.showMessageDialog(null, "成功保存", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        SubBnt.setFont(new Font("宋体", Font.PLAIN, 50));
        SubBnt.setBounds(465, 644, 198, 103);
        add(SubBnt);

        JButton CanBnt = new JButton("清空");
        CanBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        CanBnt.setFont(new Font("宋体", Font.PLAIN, 50));
        CanBnt.setBounds(1030, 644, 198, 103);
        add(CanBnt);
    }

    private void reset() {
        idT.setText("");
        passwordT.setText("");
        nameT.setText("");
        companyT.setText("");
        addrT.setText("");
        telT.setText("");
        zipcodeT.setText("");
    }
}
