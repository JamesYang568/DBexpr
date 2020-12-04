package DialogGUI.Interface.Admin;

import CStool.ServerDataGSocket;
import CStool.ServerPlug;
import DialogGUI.Interface.InsertClient;
import DialogGUI.Interface.SelectSpaceitem;
import handle.DataProcessing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class ServerConsole extends JFrame {
    private static ServerConsole instance = null;

    private JPanel contentPane;
    private JPanel CAPanel;  //增改用户
    private JPanel CTPanel;  //租赁业务处理，需要进行进程通信
    private JPanel DEPanel;  //删除三个实体
    private JPanel FAPanel;  //查询空闲资源
    private JPanel OtherPanel;  //包括查询所有信息和增加汽车、司机信息

    private InsertClient insertClient;  // TODO: 2020/11/22 应该将判断标记内聚在panel中
    private ChangeClient changeClient;

    //private ServerPlug server;
    //private ServerDataGSocket server;

    /**
     * Create the frame.
     */
    public static ServerConsole getInstance() {
        if (instance == null) {
            instance = new ServerConsole();
        }
        return instance;
    }

    private ServerConsole() {
        //this.server = ServerDataGSocket.getInstance();
        setTitle("管理员");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 670, 772);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("宋体", Font.PLAIN, 22));
        contentPane.add(tabbedPane, BorderLayout.CENTER);
        tabbedPane.add("租赁业务", CommitTs());
        tabbedPane.add("查询空闲资源", findAvailable());
        tabbedPane.add("增改用户", C_A_Client());
        tabbedPane.add("删除司机车辆", Delete());
        tabbedPane.add("其他", otherStaff());
        tabbedPane.setSelectedIndex(0);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {  //TODO 有没有必要，这个要测试
                System.out.println("Server Closed");
                DataProcessing.disconnectFromDB();
            }
        });
        setVisible(true);
    }

    private JPanel C_A_Client() {
        this.CAPanel = new JPanel();
        this.CAPanel.setLayout(new BorderLayout(0, 0));
        JPanel selectPanel = new JPanel();
        this.CAPanel.add(selectPanel, BorderLayout.NORTH);
        selectPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        insertClient = new InsertClient(1);
        changeClient = new ChangeClient();
        insertClient.setEnabled(false);
        insertClient.setVisible(false);
        changeClient.setEnabled(false);
        changeClient.setVisible(false);

        //CAPanel.add(insertClient, BorderLayout.CENTER);
        //CAPanel.add(changeClient, BorderLayout.CENTER);

        JButton addclientBnt = new JButton("增加用户");
        addclientBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) { //也可以尝试remove方法
                CAPanel.add(insertClient, BorderLayout.CENTER);
                insertClient.setVisible(true);
                insertClient.setEnabled(true);
                changeClient.setEnabled(false);
                changeClient.setVisible(false);
            }
        });
        addclientBnt.setFont(new Font("宋体", Font.PLAIN, 26));
        selectPanel.add(addclientBnt);

        JLabel blod = new JLabel("           ");
        blod.setHorizontalAlignment(SwingConstants.CENTER);
        blod.setFont(new Font("宋体", Font.PLAIN, 24));
        selectPanel.add(blod);

        JButton chaclientBnt = new JButton("修改用户");
        chaclientBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CAPanel.add(changeClient, BorderLayout.CENTER);
                insertClient.setVisible(false);
                insertClient.setEnabled(false);
                changeClient.setEnabled(true);
                changeClient.setVisible(true);
            }
        });
        chaclientBnt.setFont(new Font("宋体", Font.PLAIN, 26));
        selectPanel.add(chaclientBnt);

        return this.CAPanel;
    }

    private JPanel CommitTs() {
        this.CTPanel = new transCommit();
        return this.CTPanel;
    }

    private JPanel Delete() {
        this.DEPanel = new DBADelete();
        return this.DEPanel;
    }

    private JPanel findAvailable() {
        this.FAPanel = new SelectSpaceitem();
        return this.FAPanel;
    }

    private JPanel otherStaff() {
        this.OtherPanel = new OtherStaff();
        return this.OtherPanel;
    }

    public void finalize() {
        DataProcessing.disconnectFromDB();
    }
}
