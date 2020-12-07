package DialogGUI.Interface.User;

import DialogGUI.Help.TableParse;

import javax.swing.*;
import java.awt.*;

public class transFind extends JPanel {
    private final JTable table;
    private final int client_id;

    /**
     * Create the panel.
     */
    public transFind(int client_id) {
        setLayout(null);
        this.client_id = client_id;
        JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 71, 1482, 809);
        add(scrollPane);

        table = new JTable();
        table.setFont(new Font("宋体", Font.PLAIN, 35));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);//调整列宽
        table.setRowHeight(60);
        table.getTableHeader().setFont(new Font("宋体", Font.PLAIN, 35));
        scrollPane.setViewportView(table);
        TableParse.setTransactionT(table, client_id);

        JLabel titleL = new JLabel("查询业务");
        titleL.setHorizontalAlignment(SwingConstants.CENTER);
        titleL.setFont(new Font("宋体", Font.PLAIN, 45));
        titleL.setBounds(552, 0, 356, 73);
        add(titleL);

        setVisible(true);
    }

    public void reloadData() {
        TableParse.setTransactionT(table, this.client_id);
        validate();
    }
}
