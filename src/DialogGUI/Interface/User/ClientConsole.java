package DialogGUI.Interface.User;

import CStool.ClientPlug;
import DialogGUI.Interface.ChangeClient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ClientConsole extends JFrame {
    private JPanel contentPane;
    private JPanel FAPanel;// 查询已有业务
    private JPanel SBPanel;// 提交放前所选业务
    private JPanel CAPanel;// 修改自己信息

    private int client_id;
    private ClientPlug cSocket;

    /**
     * Create the frame.
     */
    public ClientConsole(int id) {
        this.client_id = id;
        setTitle("客户端");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 853, 666);
        contentPane = new JPanel();
        contentPane.setToolTipText("");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("宋体", Font.PLAIN, 22));
        contentPane.add(tabbedPane);

        tabbedPane.add("选择业务", Submit());
        tabbedPane.add("查询已有业务", FindAvailable());
        tabbedPane.add("修改信息", C_A_Client());
        tabbedPane.setSelectedIndex(0);
        setVisible(true);
    }

    private JPanel Submit() {
        this.SBPanel = new transSubmit(this.client_id);
        return this.SBPanel;
    }

    private JPanel C_A_Client() {
        this.CAPanel = new ChangeClient();
        return this.CAPanel;
    }

    private JPanel FindAvailable() {
        this.FAPanel = new transFind(this.client_id);
        return this.FAPanel;
    }

    @Override
    protected void finalize() throws Throwable {
        cSocket.closeConnection();
        super.finalize();
    }
}
