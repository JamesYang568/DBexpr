package DialogGUI.Interface.User;

import DialogGUI.Help.InputParse;
import DialogGUI.Interface.welcome;
import handle.DataProcessing;
import handle.Delete_SQL_sen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DeleteClient extends JPanel {
    private final JTextField idT;
    private final JPasswordField passwordT;

    /**
     * Create the panel.
     */
    public DeleteClient() {
        setLayout(null);

        JLabel title = new JLabel("注销账户信息");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.BOLD, 26));
        title.setBounds(262, 13, 182, 47);
        add(title);

        JLabel Iid = new JLabel("输入客户号");
        Iid.setFont(new Font("宋体", Font.PLAIN, 22));
        Iid.setBounds(102, 101, 121, 35);
        add(Iid);

        idT = new JTextField();
        idT.setFont(new Font("宋体", Font.PLAIN, 22));
        idT.setBounds(337, 102, 157, 32);
        add(idT);
        idT.setColumns(10);

        JLabel lpassword = new JLabel("密码");
        lpassword.setFont(new Font("宋体", Font.PLAIN, 22));
        lpassword.setBounds(102, 202, 121, 35);
        add(lpassword);

        passwordT = new JPasswordField();
        lpassword.setFont(new Font("宋体", Font.PLAIN, 22));
        passwordT.setBounds(337, 202, 157, 32);
        add(passwordT);

        JButton SubBnt = new JButton("确定");
        SubBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int id = Integer.parseInt(InputParse.parseID(idT.getText()));
                String password = new String(passwordT.getPassword());
                boolean flag;
                try {
                    flag = DataProcessing.searchClient(id, password);
                    if (flag) {
                        DataProcessing.delete(Delete_SQL_sen.delete_client(id));
                        JOptionPane.showMessageDialog(null, "注销成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                        welcome welcome = new welcome();
                        welcome.setVisible(true);
                        ClientConsole clientConsole = (ClientConsole) getTopLevelAncestor();
                        clientConsole.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "账户或密码错误！", "提示", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        SubBnt.setFont(new Font("宋体", Font.PLAIN, 22));
        SubBnt.setBounds(102, 478, 143, 41);
        add(SubBnt);

        JButton CanBnt = new JButton("清空");
        CanBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        CanBnt.setFont(new Font("宋体", Font.PLAIN, 22));
        CanBnt.setBounds(383, 478, 143, 41);
        add(CanBnt);
    }

    private void reset() {
        idT.setText("");
        passwordT.setText("");
    }
}
