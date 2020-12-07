package DialogGUI.Interface.Admin;

import DialogGUI.Help.InputParse;
import entity.Client;
import handle.DataProcessing;
import handle.Search_SQL_sen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

/**
 * 在客户自管理和管理员管理界面共用这个panel
 */
public class ChangeClient extends JPanel {
    private final JTextField idT;
    private final JTextField nameT;
    private final JTextField companyT;
    private final JTextField telT;
    private final JTextField addrT;
    private final JTextField zipcodeT;

    /**
     * Create the panel.
     * 要求这个面板的大小必须要有 630*580  TODO
     */
    public ChangeClient() {
        setLayout(null);

        JLabel title = new JLabel("修改客户信息");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 45));
        title.setEnabled(true);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setBounds(479, 13, 508, 62);
        add(title);

        JLabel lid = new JLabel("输入客户号");
        lid.setFont(new Font("宋体", Font.PLAIN, 35));
        lid.setBounds(166, 107, 206, 62);
        add(lid);

        JLabel lname = new JLabel("客户姓名");
        lname.setFont(new Font("宋体", Font.PLAIN, 35));
        lname.setBounds(197, 196, 157, 62);
        add(lname);

        JLabel lcompany = new JLabel("客户单位");
        lcompany.setFont(new Font("宋体", Font.PLAIN, 35));
        lcompany.setBounds(197, 286, 157, 62);
        add(lcompany);

        JLabel ltel = new JLabel("客户电话");
        ltel.setFont(new Font("宋体", Font.PLAIN, 35));
        ltel.setBounds(197, 361, 157, 62);
        add(ltel);

        JLabel laddr = new JLabel("客户地址");
        laddr.setFont(new Font("宋体", Font.PLAIN, 35));
        laddr.setBounds(197, 447, 157, 62);
        add(laddr);

        JLabel lzipcode = new JLabel("邮编");
        lzipcode.setHorizontalAlignment(SwingConstants.CENTER);
        lzipcode.setFont(new Font("宋体", Font.PLAIN, 35));
        lzipcode.setBounds(215, 532, 157, 62);
        add(lzipcode);

        // 初始化文本框
        {
            idT = new JTextField();
            idT.setFont(new Font("宋体", Font.PLAIN, 35));
            idT.setBounds(447, 108, 508, 62);
            add(idT);
            idT.setColumns(15);

            nameT = new JTextField();
            nameT.setEditable(false);
            nameT.setFont(new Font("宋体", Font.PLAIN, 35));
            nameT.setColumns(15);
            nameT.setBounds(447, 197, 508, 62);
            add(nameT);

            companyT = new JTextField();
            companyT.setEditable(false);
            companyT.setFont(new Font("宋体", Font.PLAIN, 35));
            companyT.setColumns(15);
            companyT.setBounds(447, 286, 508, 62);
            add(companyT);

            telT = new JTextField();
            telT.setEditable(false);
            telT.setFont(new Font("宋体", Font.PLAIN, 35));
            telT.setColumns(15);
            telT.setBounds(447, 362, 508, 62);
            add(telT);

            addrT = new JTextField();
            addrT.setEditable(false);
            addrT.setFont(new Font("宋体", Font.PLAIN, 35));
            addrT.setColumns(15);
            addrT.setBounds(447, 447, 508, 62);
            add(addrT);

            zipcodeT = new JTextField();
            zipcodeT.setEditable(false);
            zipcodeT.setFont(new Font("宋体", Font.PLAIN, 35));
            zipcodeT.setColumns(15);
            zipcodeT.setBounds(447, 533, 508, 62);
            add(zipcodeT);
        }

        JButton SBnt = new JButton("查询");
        SBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sid = InputParse.parseID(idT.getText());
                if (!sid.equals("")) {
                    int id = Integer.parseInt(sid);
                    try {
                        Client[] client = DataProcessing.searchClient(Search_SQL_sen.get_a_client(id));
                        if (client != null) {
                            nameT.setText(client[0].getName());
                            companyT.setText(client[0].getCompany());
                            addrT.setText(client[0].getAddr());
                            telT.setText(client[0].getTel());
                            zipcodeT.setText(client[0].getZipcode());
                            nameT.setEditable(true);
                            companyT.setEditable(true);
                            telT.setEditable(true);
                            addrT.setEditable(true);
                            zipcodeT.setEditable(true);
                            idT.setEditable(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "没有查到此客户", "未查到", JOptionPane.INFORMATION_MESSAGE);
                            idT.setText("");
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        SBnt.setFont(new Font("宋体", Font.PLAIN, 50));
        SBnt.setBounds(1014, 90, 176, 87);
        add(SBnt);

        JButton SubBnt = new JButton("提交");
        SubBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(InputParse.parseID(idT.getText()));
                String name = nameT.getText();
                String company = companyT.getText();
                String addr = addrT.getText();
                String tel = telT.getText();
                String zipcode = zipcodeT.getText();
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
        SubBnt.setFont(new Font("宋体", Font.PLAIN, 50));
        SubBnt.setBounds(323, 636, 176, 87);
        add(SubBnt);

        JButton CanBnt = new JButton("取消");
        CanBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        CanBnt.setFont(new Font("宋体", Font.PLAIN, 50));
        CanBnt.setBounds(1054, 636, 176, 87);
        add(CanBnt);

    }

    private void reset() {
        idT.setText("");
        idT.setEditable(true);
        nameT.setText("");
        nameT.setEditable(false);
        companyT.setText("");
        companyT.setEditable(false);
        addrT.setText("");
        addrT.setEditable(false);
        telT.setText("");
        telT.setEditable(false);
        zipcodeT.setText("");
        zipcodeT.setEditable(false);
    }
}
