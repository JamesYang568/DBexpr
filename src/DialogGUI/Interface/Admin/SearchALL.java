package DialogGUI.Interface.Admin;

import handle.ParseEntity;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * 这个类不存在数据库操作，因此是完全安全的
 * 动库操作在ParseEntity中封装
 */
public class SearchALL extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private ParseEntity parseEntity = new ParseEntity();

    /**
     * Create the frame.
     */
    public SearchALL() {
        setTitle("查询信息");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1013, 714);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JLabel title = new JLabel("查询某资源所有信息");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("宋体", Font.BOLD, 32));
        contentPane.add(title, BorderLayout.NORTH);

        JPanel MP = new JPanel();
        contentPane.add(MP, BorderLayout.CENTER);
        MP.setLayout(null);

        JButton CarBnt = new JButton("汽车");
        CarBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setCarT();
            }
        });
        CarBnt.setFont(new Font("宋体", Font.PLAIN, 24));
        CarBnt.setBounds(145, 32, 130, 48);
        MP.add(CarBnt);

        JButton DriverBnt = new JButton("司机");
        DriverBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setDriverT();
            }
        });
        DriverBnt.setFont(new Font("宋体", Font.PLAIN, 24));
        DriverBnt.setBounds(430, 32, 130, 48);
        MP.add(DriverBnt);

        JButton ClientBnt = new JButton("顾客");
        ClientBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setClientT();
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
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setSize(951, 501);
        scrollPane.setLocation(15, 95);
        MP.add(table);

        MP.add(scrollPane);
    }

    private void setCarT() {
        Vector columnNames = new Vector(Arrays.asList("ID号", "车辆型号", "租金率", "购入日期", "维修日期", "运行公里", "运行小时"));
        DefaultTableModel defaultModel = new DefaultTableModel(parseEntity.ParseCar(), columnNames) {
            public boolean isCellEditable(int row, int column) {//表格不允许被编辑
                return false;
            }
        };
        table.setModel(defaultModel);
    }

    private void setDriverT() {
        Vector columnNames = new Vector(Arrays.asList("ID号", "姓名", "参加工作时间"));
        DefaultTableModel defaultModel = new DefaultTableModel(parseEntity.ParseDriver(), columnNames) {
            public boolean isCellEditable(int row, int column) {//表格不允许被编辑
                return false;
            }
        };
        table.setModel(defaultModel);
    }

    private void setClientT() {
        Vector columnNames = new Vector(Arrays.asList("ID号", "姓名", "单位", "电话", "地址", "邮编"));
        DefaultTableModel defaultModel = new DefaultTableModel(parseEntity.ParseClient(), columnNames) {
            public boolean isCellEditable(int row, int column) {//表格不允许被编辑
                return false;
            }
        };
        table.setModel(defaultModel);
    }

}
