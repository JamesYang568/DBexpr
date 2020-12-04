package DialogGUI.Interface.User;

import entity.Client;
import handle.DataProcessing;
import handle.Search_SQL_sen;
import handle.Update_SQL_sen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class UpdateClient extends JPanel {
    private final JTextField nameT;
    private final JTextField companyT;
    private final JTextField telT;
    private final JTextField addrT;
    private final JTextField zipcodeT;
    private final JPasswordField passwordT;

    /**
     * Create the panel.
     */
    public UpdateClient(int client_id) {
        setLayout(null);

        JLabel title = new JLabel("修改信息");
        title.setFont(new Font("宋体", Font.PLAIN, 26));
        title.setBounds(300, 0, 123, 44);
        add(title);

        JLabel Ipassword = new JLabel("密码");
        Ipassword.setFont(new Font("宋体", Font.PLAIN, 22));
        Ipassword.setBounds(87, 57, 98, 34);
        add(Ipassword);

        JLabel Iname = new JLabel("姓名");
        Iname.setFont(new Font("宋体", Font.PLAIN, 22));
        Iname.setBounds(87, 114, 98, 34);
        add(Iname);

        JLabel Icompany = new JLabel("单位");
        Icompany.setFont(new Font("宋体", Font.PLAIN, 22));
        Icompany.setBounds(87, 175, 98, 34);
        add(Icompany);

        JLabel Itel = new JLabel("电话");
        Itel.setFont(new Font("宋体", Font.PLAIN, 22));
        Itel.setBounds(87, 234, 72, 36);
        add(Itel);

        JLabel Iaddr = new JLabel("地址");
        Iaddr.setFont(new Font("宋体", Font.PLAIN, 22));
        Iaddr.setBounds(87, 297, 72, 34);
        add(Iaddr);

        JLabel Izipcode = new JLabel("邮编");
        Izipcode.setFont(new Font("宋体", Font.PLAIN, 22));
        Izipcode.setBounds(87, 355, 72, 34);
        add(Izipcode);

        // 初始化文本框
        {
            nameT = new JTextField();
            nameT.setFont(new Font("宋体", Font.PLAIN, 22));
            nameT.setColumns(15);
            nameT.setBounds(261, 114, 247, 34);
            add(nameT);
            nameT.setColumns(10);

            companyT = new JTextField();
            companyT.setFont(new Font("宋体", Font.PLAIN, 22));
            companyT.setColumns(15);
            companyT.setBounds(261, 175, 247, 34);
            add(companyT);
            companyT.setColumns(10);

            telT = new JTextField();
            telT.setFont(new Font("宋体", Font.PLAIN, 22));
            telT.setColumns(15);
            telT.setBounds(261, 236, 247, 34);
            add(telT);
            telT.setColumns(10);

            addrT = new JTextField();
            addrT.setFont(new Font("宋体", Font.PLAIN, 22));
            addrT.setColumns(15);
            addrT.setBounds(261, 292, 247, 34);
            add(addrT);
            addrT.setColumns(10);

            zipcodeT = new JTextField();
            zipcodeT.setFont(new Font("宋体", Font.PLAIN, 22));
            zipcodeT.setColumns(15);
            zipcodeT.setBounds(261, 355, 247, 34);
            add(zipcodeT);
            zipcodeT.setColumns(10);

            passwordT = new JPasswordField();
            passwordT.setFont(new Font("宋体", Font.PLAIN, 22));
            passwordT.setColumns(15);
            passwordT.setBounds(261, 57, 247, 34);
            add(passwordT);
        }

        JButton SubBnt = new JButton("确定");
        SubBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = client_id;
                String password = new String(passwordT.getPassword());
                String name = nameT.getText();
                String company = companyT.getText();
                String addr = addrT.getText();
                String tel = telT.getText();
                String zipcode = zipcodeT.getText();
                Client client = new Client(id, password, name, company, tel, addr, zipcode);
                boolean flag;
                try {
                    flag = DataProcessing.updateClient(client);
                    if (flag) {
                        DataProcessing.update(Update_SQL_sen.client_password(id, password));
                    } else {
                        title.setText("修改失败，请重新尝试");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                reset();
            }
        });
        SubBnt.setFont(new Font("宋体", Font.PLAIN, 22));
        SubBnt.setBounds(122, 443, 113, 44);
        add(SubBnt);

        JButton CanBnt = new JButton("取消");
        CanBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        CanBnt.setFont(new Font("宋体", Font.PLAIN, 22));
        CanBnt.setBounds(412, 443, 113, 44);
        add(CanBnt);

        JButton SBnt = new JButton("查询");
        SBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try { //这里一定不会查出错误
                    Client client = Objects.requireNonNull(DataProcessing.searchClient(Search_SQL_sen.get_a_client(client_id)))[0];
                    nameT.setText(client.getName());
                    companyT.setText(client.getCompany());
                    addrT.setText(client.getAddr());
                    telT.setText(client.getTel());
                    zipcodeT.setText(client.getZipcode());

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        SBnt.setFont(new Font("宋体", Font.PLAIN, 22));
        SBnt.setBounds(609, 52, 113, 44);
        add(SBnt);

    }

    private void reset() {
        passwordT.setText("");
        nameT.setText("");
        companyT.setText("");
        addrT.setText("");
        telT.setText("");
        zipcodeT.setText("");
    }

}
