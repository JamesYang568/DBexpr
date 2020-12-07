package DialogGUI.Interface.Admin;

import DialogGUI.Help.TableParse;
import entity.Driver;
import entity.Transaction;
import handle.DataProcessing;
import handle.Search_SQL_sen;
import handle.Update_SQL_sen;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class transCommit extends JPanel {
    private JTable table;
    private JComboBox<Integer> comboBox;

    /**
     * Create the panel.
     */
    public transCommit() {
        setLayout(null);

        JLabel title = new JLabel("业务报表");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("宋体", Font.BOLD, 45));
        title.setBounds(559, 0, 275, 69);
        add(title);

        JLabel driverL = new JLabel("选择司机ID号");
        driverL.setFont(new Font("宋体", Font.PLAIN, 35));
        driverL.setBounds(338, 93, 281, 55);
        add(driverL);

        comboBox = new JComboBox<>();
        comboBox.setFont(new Font("宋体", Font.PLAIN, 35));
        comboBox.setBounds(658, 93, 281, 55);
        add(comboBox);

        JButton createBnt = new JButton("提交业务");
        createBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int driver_id = comboBox.getSelectedIndex();
                int trans_id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                try {
                    DataProcessing.update(Update_SQL_sen.transaction_invalid(trans_id, driver_id));
                    DataProcessing.update(Update_SQL_sen.driver_set_available(driver_id, 0));
                    reload();
                    validate();
                } catch (SQLException er) {
                    er.printStackTrace();
                }
            }
        });
        createBnt.setFont(new Font("宋体", Font.PLAIN, 40));
        createBnt.setBounds(1199, 154, 239, 126);
        add(createBnt);

        JButton button = new JButton("查询申请");
        button.setFont(new Font("宋体", Font.PLAIN, 40));
        button.setBounds(56, 55, 239, 126);
        add(button);


        JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 292, 1426, 537);
        add(scrollPane);

        table = new JTable();
        table.setFont(new Font("宋体", Font.PLAIN, 35));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);//调整列宽
        table.setRowHeight(60);
        table.getTableHeader().setFont(new Font("宋体", Font.PLAIN, 35));
        scrollPane.setViewportView(table);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                reload();
            }
        });
    }

    private void reload() {
        try {
            Driver[] drivers = DataProcessing.searchDriver(Search_SQL_sen.get_available_driver());
            if (drivers != null) {
                for (Driver driver : drivers) {
                    comboBox.addItem(driver.getId());
                }
                TableParse.setTransactionT(table);
            } else {
                JOptionPane.showMessageDialog(null, "没有空闲司机了！", "sorry", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
