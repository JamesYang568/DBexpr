package DialogGUI.Interface.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ClientConsole extends JFrame {
    private JPanel contentPane;
    private transFind FAPanel;// 查询已有业务
    private JPanel SBPanel;// 提交放前所选业务
    private JPanel CAPanel;// 修改自己信息
    private JPanel DEPanel;// 注销自己信息
    private int client_id;

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
        tabbedPane.add("注销账户信息",DeleteC());
        tabbedPane.setSelectedIndex(0);

        ChangeListener changeListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                if (index == 2){ //刷新
                    FAPanel.reloadData();
                }
            }
        };

        setVisible(true);
    }

    private JPanel Submit() {
        this.SBPanel = new transSubmit(this.client_id);
        return this.SBPanel;
    }

    private JPanel C_A_Client() {
        this.CAPanel = new UpdateClient(this.client_id);
        return this.CAPanel;
    }

    private JPanel FindAvailable() {
        this.FAPanel = new transFind(this.client_id);
        return this.FAPanel;
    }
    
    private JPanel DeleteC() {
    	this.DEPanel=new DeleteClient();
    	return this.DEPanel;
    }
}
