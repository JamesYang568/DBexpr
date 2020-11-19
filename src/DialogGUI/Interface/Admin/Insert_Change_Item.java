package DialogGUI.Interface.Admin;

import entity.Car;
import entity.Driver;
import handle.DataProcessing;
import handle.Search_SQL_sen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.Date;

//注意此类不能被继承，因为覆写的setVisible不是window理解
public final class Insert_Change_Item extends JFrame {
    private final JTextField idT;
    private final JTextField nameT;
    private final JTextField dateT;
    private final JTextField salaryT;
    private final JTextField mileT;
    private final JTextField maintainT;
    private final JTextField hourT;
    private final JTextField licenseT;
    private final JTextField priceT;

    private final JLabel nameL;
    private final JLabel dateL;
    private final JLabel salaryL;
    private final JLabel priceL;
    private final JLabel licenseL;
    private final JLabel mileL;
    private final JLabel maintainL;
    private final JLabel hourL;

    private int select = 0;
    private boolean c_or_i = false;  //修改还是插入，默认为插入

    /**
     * Create the frame.
     */
    public Insert_Change_Item() {
        setTitle("增改资源");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 819, 600);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel titleL = new JLabel("增改资源");
        titleL.setHorizontalAlignment(SwingConstants.CENTER);
        titleL.setFont(new Font("宋体", Font.BOLD, 32));
        contentPane.add(titleL, BorderLayout.NORTH);

        JPanel MP = new JPanel();
        contentPane.add(MP, BorderLayout.CENTER);
        MP.setLayout(null);

        JComboBox comboBox = new JComboBox();
        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                if (arg0.getStateChange() == ItemEvent.SELECTED)
                    select = comboBox.getSelectedIndex();
                if (select == 1) {
                    nameL.setText("车辆类型");
                    dateL.setText("购入日期");
                    salaryL.setText("租金率");
                    setVisible(true);
                }
            }
        });
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"司机", "汽车"}));
        comboBox.setSelectedIndex(select);
        comboBox.setFont(new Font("宋体", Font.PLAIN, 22));
        comboBox.setBounds(74, 51, 153, 41);
        MP.add(comboBox);

        // 初始化标签和文本框
        {
            JLabel selectItem = new JLabel("选择资源类型");
            selectItem.setHorizontalAlignment(SwingConstants.CENTER);
            selectItem.setFont(new Font("宋体", Font.PLAIN, 22));
            selectItem.setBounds(74, 15, 153, 34);
            MP.add(selectItem);

            JLabel idL = new JLabel("ID号");
            idL.setFont(new Font("宋体", Font.PLAIN, 22));
            idL.setBounds(310, 51, 91, 41);
            MP.add(idL);

            idT = new JTextField();
            idT.setFont(new Font("宋体", Font.PLAIN, 22));
            idT.setBounds(416, 51, 139, 41);
            MP.add(idT);
            idT.setColumns(10);

            nameL = new JLabel("姓名");
            nameL.setFont(new Font("宋体", Font.PLAIN, 22));
            nameL.setBounds(57, 124, 91, 41);
            MP.add(nameL);

            dateL = new JLabel("参加工作时间");  //TODO 这里不能直接输入格式问题
            dateL.setFont(new Font("宋体", Font.PLAIN, 22));
            dateL.setBounds(57, 201, 139, 41);
            MP.add(dateL);

            salaryL = new JLabel("工资");
            salaryL.setFont(new Font("宋体", Font.PLAIN, 22));
            salaryL.setBounds(57, 284, 91, 41);
            MP.add(salaryL);

            priceL = new JLabel("价格");
            priceL.setFont(new Font("宋体", Font.PLAIN, 22));
            priceL.setBounds(57, 369, 91, 41);
            MP.add(priceL);

            licenseL = new JLabel("牌照号");
            licenseL.setFont(new Font("宋体", Font.PLAIN, 22));
            licenseL.setBounds(417, 124, 91, 41);
            MP.add(licenseL);

            mileL = new JLabel("运行公里");
            mileL.setFont(new Font("宋体", Font.PLAIN, 22));
            mileL.setBounds(417, 191, 91, 41);
            MP.add(mileL);

            maintainL = new JLabel("维修日期");
            maintainL.setFont(new Font("宋体", Font.PLAIN, 22));
            maintainL.setBounds(417, 274, 91, 41);
            MP.add(maintainL);

            hourL = new JLabel("运行小时");
            hourL.setFont(new Font("宋体", Font.PLAIN, 22));
            hourL.setBounds(417, 369, 91, 41);
            MP.add(hourL);

            nameT = new JTextField();
            nameT.setFont(new Font("宋体", Font.PLAIN, 22));
            nameT.setColumns(10);
            nameT.setBounds(163, 124, 139, 41);
            MP.add(nameT);

            dateT = new JTextField();
            dateT.setFont(new Font("宋体", Font.PLAIN, 22));
            dateT.setColumns(10);
            dateT.setBounds(200, 201, 170, 41);
            MP.add(dateT);

            salaryT = new JTextField();
            salaryT.setFont(new Font("宋体", Font.PLAIN, 22));
            salaryT.setColumns(10);
            salaryT.setBounds(163, 284, 139, 41);
            MP.add(salaryT);

            priceT = new JTextField();
            priceT.setFont(new Font("宋体", Font.PLAIN, 22));
            priceT.setColumns(10);
            priceT.setBounds(163, 369, 139, 41);
            MP.add(priceT);

            licenseT = new JTextField();
            licenseT.setFont(new Font("宋体", Font.PLAIN, 22));
            licenseT.setColumns(10);
            licenseT.setBounds(554, 122, 139, 41);
            MP.add(licenseT);

            mileT = new JTextField();
            mileT.setFont(new Font("宋体", Font.PLAIN, 22));
            mileT.setColumns(10);
            mileT.setBounds(554, 191, 139, 41);
            MP.add(mileT);

            maintainT = new JTextField();
            maintainT.setFont(new Font("宋体", Font.PLAIN, 22));
            maintainT.setColumns(10);
            maintainT.setBounds(554, 274, 176, 41);
            MP.add(maintainT);

            hourT = new JTextField();
            hourT.setFont(new Font("宋体", Font.PLAIN, 22));
            hourT.setColumns(10);
            hourT.setBounds(554, 369, 139, 41);
            MP.add(hourT);
        }
        setVisible(false);

        JButton FBnt = new JButton("查找");
        FBnt.setFont(new Font("宋体", Font.PLAIN, 20));
        FBnt.setBounds(608, 52, 139, 41);
        MP.add(FBnt);

        JButton CommitBnt = new JButton("确定");
        CommitBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ids = idT.getText();
                if (!ids.equals("")) {
                    try {
                        if (select == 0) {
                            int id = Integer.parseInt(ids);
                            String name = nameT.getText();
                            String date = dateT.getText();
                            double salary = Double.parseDouble(salaryT.getText());
                            if (c_or_i)
                                DataProcessing.updateDriver(new Driver(id, name, Date.valueOf(date), salary));
                            DataProcessing.insertDriver(new Driver(id, name, Date.valueOf(date), salary));
                        } else {
                            int id = Integer.parseInt(ids);
                            String type = nameT.getText();
                            String date = dateT.getText();
                            double price = Double.parseDouble(priceT.getText());
                            double rate = Double.parseDouble(salaryT.getText());
                            String license = licenseT.getText();
                            double mile = Double.parseDouble(mileT.getText());
                            double hour = Double.parseDouble(hourT.getText());
                            String maintain = maintainT.getText();
                            if(c_or_i)
                                DataProcessing.updateCar(new Car(id, type, license, Date.valueOf(date),
                                        price, Date.valueOf(maintain), mile, hour, rate));
                            DataProcessing.insertCar(new Car(id, type, license, Date.valueOf(date),
                                    price, Date.valueOf(maintain), mile, hour, rate));
                        }
                    } catch (SQLException ep) {
                        ep.printStackTrace();
                    } catch (IllegalArgumentException xp) {
                        xp.printStackTrace();
                        JOptionPane.showMessageDialog(null, "日期格式错误！",
                                "出错了", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        CommitBnt.setFont(new Font("宋体", Font.PLAIN, 22));
        CommitBnt.setBounds(323, 441, 139, 41);
        MP.add(CommitBnt);

        FBnt.addActionListener(new ActionListener() {//说明要修改
            public void actionPerformed(ActionEvent e) {
                String ids = idT.getText();
                if (!ids.equals("")) {
                    idT.setEditable(false);
                    c_or_i = true;
                    try {
                        int id = Integer.parseInt(ids);
                        if (select == 0) {
                            Driver driver = DataProcessing.searchDriver(Search_SQL_sen.get_a_driver(id))[0];
                            nameT.setText(driver.getName());
                            dateT.setText(driver.getEnroll_date().toString());
                            salaryT.setText(Double.toString(driver.getSalary()));
                        } else {
                            setVisible(true);
                            Car car = DataProcessing.searchCar(Search_SQL_sen.get_a_car(id))[0];
                            nameT.setText(car.getType());
                            dateT.setText(car.getPurchase_date().toString());
                            salaryT.setText(Double.toString(car.getRent_rate()));
                            maintainT.setText(car.getMaintain_date().toString());
                            licenseT.setText(car.getLicense());
                            priceT.setText(Double.toString(car.getPrice()));
                            mileT.setText(Double.toString(car.getMile()));
                            hourT.setText(Double.toString(car.getWorking_time()));
                        }
                    } catch (SQLException ep) {
                        ep.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void setVisible(boolean flag) {
        priceL.setVisible(flag);
        priceT.setVisible(flag);
        licenseL.setVisible(flag);
        licenseT.setVisible(flag);
        mileL.setVisible(flag);
        mileT.setVisible(flag);
        maintainL.setVisible(flag);
        maintainT.setVisible(flag);
        hourL.setVisible(flag);
        hourT.setVisible(flag);
    }

    @Deprecated
    public void setEditable(boolean flag) {
        idT.setEditable(flag);
        priceT.setEditable(flag);
        nameT.setEditable(flag);
        dateT.setEditable(flag);
        salaryT.setEnabled(flag);
        mileT.setEditable(flag);
        maintainT.setEditable(flag);
        hourT.setEditable(flag);
        licenseT.setEditable(flag);
        priceT.setEditable(flag);
    }

    @Deprecated
    private int[] ParseDate(String date) {
        String[] temp = date.split(".");
        int[] rlt = new int[3];
        rlt[0] = Integer.parseInt(temp[0]);
        rlt[1] = Integer.parseInt(temp[1]);
        rlt[2] = Integer.parseInt(temp[2]);
        return rlt;
    }

}
