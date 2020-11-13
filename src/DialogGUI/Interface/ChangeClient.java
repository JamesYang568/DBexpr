package DialogGUI.Interface;

import entity.Client;
import handle.DataProcessing;
import handle.Search_SQL_sen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class ChangeClient extends JPanel {
    private JTextField tid;
    private JTextField tname;
    private JTextField tcompany;
    private JTextField ttel;
    private JTextField taddr;
    private JTextField tzipcode;

    /**
     * Create the panel.
     * 要求这个面板的大小必须要有 630*580  TODO
     */
    public ChangeClient() {
        setLayout(null);

        JLabel title = new JLabel("修改客户信息");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 26));
        title.setEnabled(true);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setBounds(70, 15, 508, 41);
        add(title);

        JLabel lid = new JLabel("输入客户号");
        lid.setFont(new Font("宋体", Font.PLAIN, 22));
        lid.setBounds(82, 80, 133, 41);
        add(lid);

        JLabel lname = new JLabel("客户姓名");
        lname.setFont(new Font("宋体", Font.PLAIN, 22));
        lname.setBounds(82, 151, 133, 41);
        add(lname);

        JLabel lcompany = new JLabel("客户单位");
        lcompany.setFont(new Font("宋体", Font.PLAIN, 22));
        lcompany.setBounds(82, 218, 133, 41);
        add(lcompany);

        JLabel ltel = new JLabel("客户电话");
        ltel.setFont(new Font("宋体", Font.PLAIN, 22));
        ltel.setBounds(82, 288, 133, 41);
        add(ltel);

        JLabel laddr = new JLabel("客户地址");
        laddr.setFont(new Font("宋体", Font.PLAIN, 22));
        laddr.setBounds(82, 357, 133, 41);
        add(laddr);

        JLabel lzipcode = new JLabel("邮编");
        lzipcode.setHorizontalAlignment(SwingConstants.CENTER);
        lzipcode.setFont(new Font("宋体", Font.PLAIN, 22));
        lzipcode.setBounds(82, 428, 133, 41);
        add(lzipcode);

        tid = new JTextField();
        tid.setFont(new Font("宋体", Font.PLAIN, 22));
        tid.setBounds(283, 80, 174, 41);
        add(tid);
        tid.setColumns(15);

        tname = new JTextField();
        tname.setEditable(false);
        tname.setFont(new Font("宋体", Font.PLAIN, 22));
        tname.setColumns(15);
        tname.setBounds(283, 151, 174, 41);
        add(tname);

        tcompany = new JTextField();
        tcompany.setEditable(false);
        tcompany.setFont(new Font("宋体", Font.PLAIN, 22));
        tcompany.setColumns(15);
        tcompany.setBounds(283, 218, 287, 41);
        add(tcompany);

        ttel = new JTextField();
        ttel.setEditable(false);
        ttel.setFont(new Font("宋体", Font.PLAIN, 22));
        ttel.setColumns(15);
        ttel.setBounds(283, 288, 208, 41);
        add(ttel);

        taddr = new JTextField();
        taddr.setEditable(false);
        taddr.setFont(new Font("宋体", Font.PLAIN, 22));
        taddr.setColumns(15);
        taddr.setBounds(283, 357, 287, 41);
        add(taddr);

        tzipcode = new JTextField();
        tzipcode.setEditable(false);
        tzipcode.setFont(new Font("宋体", Font.PLAIN, 22));
        tzipcode.setColumns(15);
        tzipcode.setBounds(283, 428, 174, 41);
        add(tzipcode);

        JButton SBnt = new JButton("查询");
        SBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sid = tid.getText();
                if (!sid.equals("")) {
                    int id = Integer.parseInt(sid);
                    try {
                        Client[] client = DataProcessing.searchClient(Search_SQL_sen.get_a_client(id));
                        tname.setText(client[0].getName());
                        tcompany.setText(client[0].getCompany());
                        taddr.setText(client[0].getAddr());
                        ttel.setText(client[0].getTel());
                        tzipcode.setText(client[0].getZipcode());
                        tname.setEditable(true);
                        tcompany.setEditable(true);
                        ttel.setEditable(true);
                        taddr.setEditable(true);
                        tzipcode.setEditable(true);
                        tid.setEditable(false);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        SBnt.setFont(new Font("宋体", Font.PLAIN, 22));
        SBnt.setBounds(486, 80, 123, 41);
        add(SBnt);

        JButton SubBnt = new JButton("提交");
        SubBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(tid.getText());
                String name = tname.getText();
                String company = tcompany.getText();
                String addr = taddr.getText();
                String tel = ttel.getText();
                String zipcode = tzipcode.getText();
                Client client = new Client(id, "", name, company, tel, addr, zipcode);
                try {
                    if (!DataProcessing.updateClient(client))
                        title.setText("更新失败，请重新尝试");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                reset();
            }
        });
        SubBnt.setFont(new Font("宋体", Font.PLAIN, 22));
        SubBnt.setBounds(122, 503, 142, 44);
        add(SubBnt);

        JButton CanBnt = new JButton("取消");
        CanBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        CanBnt.setFont(new Font("宋体", Font.PLAIN, 22));
        CanBnt.setBounds(367, 503, 142, 44);
        add(CanBnt);

    }

    private void reset() {
        tid.setText("");
        tname.setText("");
        tname.setEditable(false);
        tcompany.setText("");
        tcompany.setEditable(false);
        taddr.setText("");
        taddr.setEditable(false);
        ttel.setText("");
        ttel.setEditable(false);
        tzipcode.setText("");
        tzipcode.setEditable(false);
    }
}
