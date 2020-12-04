package DialogGUI.Interface.User;

import DialogGUI.Help.InputParse;
import DialogGUI.Help.TableParse;
import entity.Transaction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class transSubmit extends JPanel {
    private final JTextField dateT;
    private final JTextField localT;
    private final JTextField timesT;
    private final JTable table;
    private final JTextField milesT;

    /**
     * Create the panel.
     */
    public transSubmit(int c_id) {
        setLayout(null);

        JLabel title = new JLabel("选择业务界面");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("宋体", Font.BOLD, 30));
        title.setBounds(298, 30, 223, 44);
        add(title);

        JLabel dateL = new JLabel("开始时间");
        dateL.setFont(new Font("宋体", Font.PLAIN, 22));
        dateL.setBounds(28, 384, 110, 34);
        add(dateL);

        JLabel localL = new JLabel("地点");
        localL.setFont(new Font("宋体", Font.PLAIN, 22));
        localL.setBounds(372, 383, 102, 36);
        add(localL);

        JLabel timesL = new JLabel("行驶时间");
        timesL.setFont(new Font("宋体", Font.PLAIN, 22));
        timesL.setBounds(372, 437, 102, 31);
        add(timesL);

        JLabel milesL = new JLabel("往返里程");
        milesL.setFont(new Font("宋体", Font.PLAIN, 22));
        milesL.setBounds(28, 435, 102, 34);
        add(milesL);

        {
            dateT = new JTextField();
            dateT.setFont(new Font("宋体", Font.PLAIN, 22));
            dateT.setBounds(152, 386, 125, 30);
            add(dateT);
            dateT.setColumns(10);

            localT = new JTextField();
            localT.setFont(new Font("宋体", Font.PLAIN, 22));
            localT.setBounds(490, 386, 125, 30);
            add(localT);
            localT.setColumns(10);

            timesT = new JTextField();
            timesT.setFont(new Font("宋体", Font.PLAIN, 22));
            timesT.setBounds(490, 437, 125, 30);
            add(timesT);
            timesT.setColumns(10);

            milesT = new JTextField();
            milesT.setFont(new Font("宋体", Font.PLAIN, 22));
            milesT.setBounds(152, 437, 127, 30);
            add(milesT);
            milesT.setColumns(10);
        }

        JPanel CarP = new JPanel();
        CarP.setBounds(0, 93, 814, 255);
        add(CarP);
        CarP.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 809, 255);
        CarP.add(scrollPane);

        table = new JTable();
        table.setFont(new Font("宋体", Font.PLAIN, 22));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 调整列宽
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("宋体", Font.PLAIN, 20));
        TableParse.setCarT(table);
        scrollPane.setViewportView(table);

        JButton submitBnt = new JButton("提交");
        submitBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String date = InputParse.parseDate(dateT.getText());
                    String local = localT.getText();
                    double miles = Double.parseDouble(milesT.getText());
                    double times = Double.parseDouble(timesT.getText());
                    String stringcar_id = table.getValueAt(table.getSelectedRow(), 0).toString();// 点击表格获得car_id
                    int car_id = Integer.parseInt(InputParse.parseID(stringcar_id));
                    Transaction transaction = new Transaction(Date.valueOf(date), car_id, local, miles, times, c_id);
                } catch (IllegalArgumentException ep) {
                    ep.printStackTrace();
                    JOptionPane.showMessageDialog(null, "日期格式错误！", "出错了", JOptionPane.INFORMATION_MESSAGE);
                }
                reset();
            }
        });
        submitBnt.setFont(new Font("宋体", Font.PLAIN, 22));
        submitBnt.setBounds(479, 514, 136, 42);
        add(submitBnt);
        setVisible(true);
    }

    private void reset() {
        this.dateT.setText("");
        this.localT.setText("");
        this.milesT.setText("");
        this.timesT.setText("");
    }
}
