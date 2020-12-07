package DialogGUI.Interface;

import DialogGUI.Help.TableParse;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 查询空闲资源查询管理员和用户共用这个界面，todo 需要增加一个选中的功能
 * <p>
 * 本类不存在动库操作，因此是安全的
 * ParseEntity中封装
 */
public class SelectSpaceitem extends JPanel {
    private JTable table;

    public SelectSpaceitem() {
        setLayout(null);

        JPanel titleP = new JPanel();
        titleP.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        titleP.setBounds(0, 0, 1464, 84);
        add(titleP);
        titleP.setLayout(null);

        JLabel title = new JLabel("空闲资源");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(633, 0, 240, 84);
        title.setFont(new Font("宋体", Font.BOLD, 50));
        titleP.add(title);

        JPanel SelectP = new JPanel();
        SelectP.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        SelectP.setBounds(0, 82, 141, 771);
        add(SelectP);
        SelectP.setLayout(null);

        JButton DriverBnt = new JButton("司机");
        DriverBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                TableParse.setDriverT(table);
            }
        });
        DriverBnt.setBounds(0, 442, 141, 89);
        DriverBnt.setFont(new Font("宋体", Font.PLAIN, 40));
        SelectP.add(DriverBnt);

        JButton CarBnt = new JButton("汽车");
        CarBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableParse.setCarT(table);
            }
        });
        CarBnt.setBounds(0, 180, 141, 89);
        CarBnt.setFont(new Font("宋体", Font.PLAIN, 40));
        SelectP.add(CarBnt);

        table = new JTable();
        table.setFont(new Font("宋体", Font.PLAIN, 35));
        table.setBounds(173, 99, 423, 445);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setRowHeight(60);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getTableHeader().setFont(new Font("宋体", Font.PLAIN, 35));
        JScrollPane scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setSize(1327, 771);
        scrollPane.setLocation(137, 82);
        scrollPane.setViewportView(table);
        add(scrollPane);
    }
}
