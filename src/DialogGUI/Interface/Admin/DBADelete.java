package DialogGUI.Interface.Admin;

import entity.*;
import handle.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class DBADelete extends JPanel {
    private JTable table;
    private JTextField idT;
    private String[][] data;
    private int flag;  //车辆1 司机2 顾客3
    private int id;

    /**
     * Create the panel.
     */
    public DBADelete() {
        setLayout(null);

        JPanel titleP = new JPanel();
        titleP.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        titleP.setBounds(0, 0, 632, 82);
        add(titleP);
        titleP.setLayout(null);

        JLabel titleL = new JLabel("删除");
        titleL.setBounds(279, 26, 64, 35);
        titleL.setFont(new Font("宋体", Font.BOLD, 30));
        titleP.add(titleL);

        JPanel MP = new JPanel();
        MP.setBounds(148, 81, 484, 582);
        add(MP);
        MP.setLayout(null);

        JLabel idL = new JLabel("ID号");
        idL.setFont(new Font("宋体", Font.PLAIN, 24));
        idL.setBounds(56, 32, 76, 46);
        MP.add(idL);

        table = new JTable();
        table.setFont(new Font("宋体", Font.PLAIN, 22));
        table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        table.setBounds(15, 104, 454, 413);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        table.setRowHeight(30);
        MP.add(table);
        // MP.add(table.getTableHeader(), BorderLayout.PAGE_START);  //TODO 这里表头

        idT = new JTextField();
        idT.setFont(new Font("宋体", Font.PLAIN, 22));
        idT.setBounds(128, 39, 184, 34);
        MP.add(idT);
        idT.setColumns(15);

        JButton SearchBnt = new JButton("查询");
        SearchBnt.setEnabled(false);
        SearchBnt.setFont(new Font("宋体", Font.PLAIN, 20));
        SearchBnt.setBounds(346, 40, 123, 35);
        MP.add(SearchBnt);

        JButton DeleteBnt = new JButton("删除");
        DeleteBnt.setEnabled(false);
        DeleteBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (flag == 1) {
                        DataProcessing.delete(Delete_SQL_sen.delete_car(id));
                    } else if (flag == 2)
                        DataProcessing.delete(Delete_SQL_sen.delete_driver(id));
                    else
                        DataProcessing.delete(Delete_SQL_sen.delete_client(id));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        DeleteBnt.setFont(new Font("宋体", Font.PLAIN, 22));
        DeleteBnt.setBounds(187, 532, 123, 44);
        MP.add(DeleteBnt);

        JPanel SelectP = new JPanel();
        SelectP.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        SelectP.setBounds(0, 81, 148, 582);
        add(SelectP);
        SelectP.setLayout(null);

        SearchBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idT.getText());  //todo 是否能直接响应  不能的话就先出来空白，之后直接一起做
                try {
                    if (flag == 1) {
                        Car car = DataProcessing.searchCar(Search_SQL_sen.get_a_car(id))[0];
                        ParseCar(car);
                    } else if (flag == 2) {
                        Driver driver = DataProcessing.searchDriver(Search_SQL_sen.get_a_driver(id))[0];
                        ParseDriver(driver);
                    } else {
                        Client c = DataProcessing.searchClient(Search_SQL_sen.get_a_client(id))[0];
                        ParseClient(c);
                    }
                } catch (SQLException error) {
                    error.printStackTrace();
                }
                DeleteBnt.setEnabled(true);
            }
        });

        JButton CBnt = new JButton("车辆");
        CBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                flag = 1;
                setCarT();
                SearchBnt.setEnabled(true);
            }
        });
        CBnt.setBounds(31, 72, 85, 39);
        CBnt.setFont(new Font("宋体", Font.PLAIN, 26));
        SelectP.add(CBnt);

        JButton DBnt = new JButton("司机");
        DBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flag = 2;
                setDriverT();
                SearchBnt.setEnabled(true);
            }
        });
        DBnt.setFont(new Font("宋体", Font.PLAIN, 26));
        DBnt.setBounds(31, 250, 85, 39);
        SelectP.add(DBnt);

        JButton ClBnt = new JButton("客户");
        ClBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flag = 3;
                setClientT();
                SearchBnt.setEnabled(true);
            }
        });
        ClBnt.setFont(new Font("宋体", Font.PLAIN, 26));
        ClBnt.setBounds(31, 416, 85, 39);
        SelectP.add(ClBnt);

    }

    private void setCarT() {
        data = new String[][]{
                {"车辆型号", null},
                {"车牌照", null},
                {"购入日期", null},
                {"价格", null},
                {"维修日期", null},
                {"运行公里", null},
                {"运行小时", null},
                {"租金率", null},
        };
        table.setModel(new DefaultTableModel(
                data, new String[]{"\u6807\u7B7E", "\u503C"}
        ) {
            public boolean isCellEditable(int row, int column) {//表格不允许被编辑
                return false;
            }
        });
    }

    private void setDriverT() {
        data = new String[][]{
                {"姓名", null},
                {"参加工作年月", null},
                {"基本工资", null},
        };
        table.setModel(new DefaultTableModel(
                data, new String[]{"\u6807\u7B7E", "\u503C"}
        ) {
            public boolean isCellEditable(int row, int column) {//表格不允许被编辑
                return false;
            }
        });
    }

    private void setClientT() {
        data = new String[][]{
                {"客户姓名", null},
                {"客户单位", null},
                {"客户电话", null},
                {"客户地址", null},
                {"邮编", null},
        };
        table.setModel(new DefaultTableModel(
                data, new String[]{"\u6807\u7B7E", "\u503C"}
        ) {
            public boolean isCellEditable(int row, int column) {//表格不允许被编辑
                return false;
            }
        });
    }

    /**
     * 这里对data进行的是内封装，耦合程度比较大，但操作起来更方便，这导致本类是不安全的
     */
    private void ParseCar(Car car) {
        data[0][1] = car.getType();
        data[1][1] = car.getLicense();
        data[2][1] = car.getPurchase_date().toString();
        data[3][1] = Double.toString(car.getPrice());
        data[4][1] = car.getMaintain_date().toString();
        data[5][1] = Double.toString(car.getMile());
        data[6][1] = Double.toString(car.getWorking_time());
        data[7][1] = Double.toString(car.getRent_rate());
    }

    private void ParseDriver(Driver driver) {
        data[0][1] = driver.getName();
        data[1][1] = driver.getEnroll_date().toString();
        data[2][1] = Double.toString(driver.getSalary());
    }

    private void ParseClient(Client client) {
        data[0][1] = client.getName();
        data[1][1] = client.getCompany();
        data[2][1] = client.getTel();
        data[3][1] = client.getAddr();
        data[4][1] = client.getZipcode();
    }
}
