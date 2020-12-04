package DialogGUI.Interface.Admin;

import DialogGUI.Help.TableParse;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * 这个类不存在数据库操作，因此是完全安全的
 * 动库操作在ParseEntity中封装
 * 对表的操作封装在TableParse中
 */
public class SearchALL extends JFrame {
    private JPanel contentPane;
    private JTable table;

    /**
     * Create the frame.
     */
    public SearchALL(JFrame outer) {
        setTitle("查询信息");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1013, 714);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel titleL = new JLabel("查询某资源所有信息");
        titleL.setHorizontalAlignment(SwingConstants.CENTER);
        titleL.setFont(new Font("宋体", Font.BOLD, 32));
        contentPane.add(titleL, BorderLayout.NORTH);

        JPanel MP = new JPanel();
        contentPane.add(MP, BorderLayout.CENTER);
        MP.setLayout(null);

        JButton CarBnt = new JButton("汽车");
        CarBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableParse.setCarT(table);
            }
        });
        CarBnt.setFont(new Font("宋体", Font.PLAIN, 24));
        CarBnt.setBounds(145, 32, 130, 48);
        MP.add(CarBnt);

        JButton DriverBnt = new JButton("司机");
        DriverBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableParse.setDriverT(table);
            }
        });
        DriverBnt.setFont(new Font("宋体", Font.PLAIN, 24));
        DriverBnt.setBounds(430, 32, 130, 48);
        MP.add(DriverBnt);

        JButton ClientBnt = new JButton("顾客");
        ClientBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableParse.setClientT(table);
            }
        });
        ClientBnt.setFont(new Font("宋体", Font.PLAIN, 24));
        ClientBnt.setBounds(711, 32, 130, 48);
        MP.add(ClientBnt);

        table = new JTable();
        table.setFont(new Font("宋体", Font.PLAIN, 22));
        table.setBounds(36, 110, 904, 470);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setRowHeight(30);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setFont(new Font("宋体", Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setSize(951, 501);
        scrollPane.setLocation(15, 95);
        scrollPane.setViewportView(table);
        MP.add(scrollPane);

        //添加窗口监听
        this.windowLis(outer);

        setVisible(true);
    }

    private void windowLis(JFrame outer) {
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                outer.setExtendedState(JFrame.NORMAL);
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

}
