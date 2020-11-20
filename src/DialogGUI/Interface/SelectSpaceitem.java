package DialogGUI.Interface;

import DialogGUI.Help.TableParse;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 查询空闲资源在管理员和用户共用
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
        titleP.setBounds(0, 0, 627, 84);
        add(titleP);
        titleP.setLayout(null);

        JLabel title = new JLabel("查询");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(268, 15, 89, 54);
        title.setFont(new Font("宋体", Font.BOLD, 30));
        titleP.add(title);

        JPanel SelectP = new JPanel();
        SelectP.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        SelectP.setBounds(0, 82, 141, 580);
        add(SelectP);
        SelectP.setLayout(null);

        JButton DriverBnt = new JButton("司机");
        DriverBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                TableParse.setDriverT(table);
            }
        });
        DriverBnt.setBounds(32, 377, 85, 59);
        DriverBnt.setFont(new Font("宋体", Font.PLAIN, 26));
        SelectP.add(DriverBnt);

        JButton CarBnt = new JButton("汽车");
        CarBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableParse.setCarT(table);
            }
        });
        CarBnt.setBounds(32, 88, 85, 53);
        CarBnt.setFont(new Font("宋体", Font.PLAIN, 26));
        SelectP.add(CarBnt);

        table = new JTable();
        table.setFont(new Font("宋体", Font.PLAIN, 22));
        table.setBounds(173, 99, 423, 445);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("宋体", Font.PLAIN, 20));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setSize(490, 580);
        scrollPane.setLocation(137, 82);
        scrollPane.setViewportView(table);
        add(scrollPane);

    }
}
