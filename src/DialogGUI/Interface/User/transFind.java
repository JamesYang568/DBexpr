package DialogGUI.Interface.User;

import DialogGUI.Help.TableParse;

import javax.swing.*;
import java.awt.*;

public class transFind extends JPanel {
    private JTable table;

    /**
     * Create the panel.
     */
    public transFind(int client_id) {
        setLayout(null);

        JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 44, 815, 524);
        add(scrollPane);

        table = new JTable();
        table.setFont(new Font("宋体", Font.PLAIN, 22));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);//调整列宽
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("宋体", Font.PLAIN, 20));
        scrollPane.setViewportView(table);
        TableParse.setTransactionT(table, client_id);

        JLabel titleL = new JLabel("查询业务");
        titleL.setHorizontalAlignment(SwingConstants.CENTER);
        titleL.setFont(new Font("宋体", Font.PLAIN, 30));
        titleL.setBounds(279, 0, 238, 48);
        add(titleL);

        setVisible(true);
    }
}
