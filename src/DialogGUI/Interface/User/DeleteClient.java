package DialogGUI.Interface.User;

import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DialogGUI.Help.InputParse;
import DialogGUI.Interface.Signin;
import DialogGUI.Interface.welcome;
import entity.Client;
import handle.DataProcessing;
import handle.Delete_SQL_sen;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DeleteClient extends JPanel {
    private JTextField tid;
    private JPasswordField tpassword;

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

        tid = new JTextField();
        tid.setFont(new Font("宋体", Font.PLAIN, 22));
        tid.setBounds(337, 102, 157, 32);
        add(tid);
        tid.setColumns(10);

        JLabel lpassword = new JLabel("密码");
        lpassword.setFont(new Font("宋体", Font.PLAIN, 22));
        lpassword.setBounds(102, 202, 121, 35);
        add(lpassword);

        tpassword = new JPasswordField();
        lpassword.setFont(new Font("宋体", Font.PLAIN, 22));
        tpassword.setBounds(337, 202, 157, 32);
        add(tpassword);

        JButton SubBnt = new JButton("确定");
        SubBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int id = Integer.parseInt(InputParse.parseID(tid.getText()));
                String password = new String(tpassword.getPassword());
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
        tid.setText("");
        tpassword.setText("");
    }
}
