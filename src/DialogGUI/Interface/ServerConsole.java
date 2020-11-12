package DialogGUI.Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ServerConsole extends JFrame {
    private static ServerConsole instance = null;

    private JPanel contentPane;
    private JPanel CAPanel;
    private JPanel CTPanel;
    private JPanel DEPanel;
    private JPanel FAPanel;

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
        setTitle("管理员");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 853, 611);
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
        tabbedPane.setSelectedIndex(0);
    }

    private JPanel C_A_Client() {
        this.CAPanel = new JPanel();
        this.CAPanel.setLayout(null);

        return this.CAPanel;
    }

    private JPanel CommitTs() {
        this.CTPanel = new JPanel();
        this.CTPanel.setLayout(null);
        return this.CTPanel;
    }

    private JPanel Delete() {
        this.DEPanel = new JPanel();
        this.DEPanel.setLayout(null);
        return this.DEPanel;
    }

    private JPanel findAvailable() {
        this.FAPanel = new JPanel();
        this.FAPanel.setLayout(null);
        return this.FAPanel;
    }

}
