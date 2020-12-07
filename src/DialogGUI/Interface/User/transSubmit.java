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
     * Create the panel   1450*880
     */
    public transSubmit(int c_id) {
        setLayout(null);

        JLabel title = new JLabel("选择业务界面");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("宋体", Font.BOLD, 45));
        title.setBounds(558, 13, 364, 78);
        add(title);

        JLabel dateL = new JLabel("开始时间");
        dateL.setFont(new Font("宋体", Font.PLAIN, 35));
        dateL.setBounds(14, 642, 168, 59);
        add(dateL);

        JLabel localL = new JLabel("地点");
        localL.setFont(new Font("宋体", Font.PLAIN, 35));
        localL.setBounds(713, 642, 136, 59);
        add(localL);

        JLabel timesL = new JLabel("行驶时间");
        timesL.setFont(new Font("宋体", Font.PLAIN, 35));
        timesL.setBounds(643, 736, 168, 59);
        add(timesL);

        JLabel milesL = new JLabel("往返里程");
        milesL.setFont(new Font("宋体", Font.PLAIN, 35));
        milesL.setBounds(14, 736, 168, 59);
        add(milesL);

        {
            dateT = new JTextField();
            dateT.setFont(new Font("宋体", Font.PLAIN, 35));
            dateT.setBounds(196, 642, 314, 52);
            add(dateT);
            dateT.setColumns(10);

            localT = new JTextField();
            localT.setFont(new Font("宋体", Font.PLAIN, 35));
            localT.setBounds(847, 646, 314, 52);
            add(localT);
            localT.setColumns(10);

            timesT = new JTextField();
            timesT.setFont(new Font("宋体", Font.PLAIN, 35));
            timesT.setBounds(847, 739, 314, 54);
            add(timesT);
            timesT.setColumns(10);

            milesT = new JTextField();
            milesT.setFont(new Font("宋体", Font.PLAIN, 35));
            milesT.setBounds(196, 740, 314, 52);
            add(milesT);
            milesT.setColumns(10);
        }

        JPanel CarP = new JPanel();
        CarP.setBounds(0, 93, 1450, 536);
        add(CarP);
        CarP.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 1450, 536);
        CarP.add(scrollPane);

        table = new JTable();
        table.setFont(new Font("宋体", Font.PLAIN, 35));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 调整列宽
        table.setRowHeight(60);
        table.getTableHeader().setFont(new Font("宋体", Font.PLAIN, 35));
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
        submitBnt.setFont(new Font("宋体", Font.PLAIN, 60));
        submitBnt.setBounds(1206, 646, 205, 155);
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
