package DialogGUI.Interface.Admin;

import DialogGUI.Help.InputParse;
import entity.Car;
import entity.Driver;
import handle.DataProcessing;
import handle.ParseEntity;
import handle.Search_SQL_sen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;

//注意此类不能被继承  内聚程度较高
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
    private boolean c_or_i = false; // 修改还是插入，默认为插入

    /**
     * Create the frame.
     */
    public Insert_Change_Item(JFrame outer) {
        setTitle("增改资源");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1455, 859);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel titleL = new JLabel("增改资源");
        titleL.setHorizontalAlignment(SwingConstants.CENTER);
        titleL.setFont(new Font("宋体", Font.BOLD, 45));
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
                    setComp_visible(true);
                } else {
                    nameL.setText("姓名");
                    dateL.setText("参加工作时间");
                    salaryL.setText("工资");
                    setComp_visible(false);
                }
            }
        });
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"司机", "汽车"}));
        comboBox.setSelectedIndex(select);
        comboBox.setFont(new Font("宋体", Font.PLAIN, 35));
        comboBox.setBounds(356, 57, 228, 52);
        MP.add(comboBox);

        //添加窗口响应
        this.windowLis(outer);

        // 初始化标签和文本框
        {
            JLabel selectItem = new JLabel("选择资源类型");
            selectItem.setHorizontalAlignment(SwingConstants.CENTER);
            selectItem.setFont(new Font("宋体", Font.PLAIN, 35));
            selectItem.setBounds(114, 57, 228, 52);
            MP.add(selectItem);

            JLabel idL = new JLabel("ID号");
            idL.setFont(new Font("宋体", Font.PLAIN, 35));
            idL.setBounds(647, 57, 144, 52);
            MP.add(idL);

            idT = new JTextField();
            idT.setFont(new Font("宋体", Font.PLAIN, 35));
            idT.setBounds(806, 55, 238, 58);
            MP.add(idT);
            idT.setColumns(10);

            nameL = new JLabel("姓名");
            nameL.setFont(new Font("宋体", Font.PLAIN, 35));
            nameL.setBounds(156, 165, 144, 52);
            MP.add(nameL);

            dateL = new JLabel("参加工作时间"); // TODO 这里不能直接输入格式问题
            dateL.setFont(new Font("宋体", Font.PLAIN, 35));
            dateL.setBounds(117, 276, 222, 52);
            MP.add(dateL);

            salaryL = new JLabel("工资");
            salaryL.setFont(new Font("宋体", Font.PLAIN, 35));
            salaryL.setBounds(156, 398, 144, 52);
            MP.add(salaryL);

            priceL = new JLabel("价格");
            priceL.setFont(new Font("宋体", Font.PLAIN, 35));
            priceL.setBounds(156, 506, 144, 52);
            MP.add(priceL);

            licenseL = new JLabel("牌照号");
            licenseL.setFont(new Font("宋体", Font.PLAIN, 35));
            licenseL.setBounds(647, 165, 144, 52);
            MP.add(licenseL);

            mileL = new JLabel("运行公里");
            mileL.setFont(new Font("宋体", Font.PLAIN, 35));
            mileL.setBounds(647, 276, 144, 52);
            MP.add(mileL);

            maintainL = new JLabel("维修日期");
            maintainL.setFont(new Font("宋体", Font.PLAIN, 35));
            maintainL.setBounds(647, 398, 144, 52);
            MP.add(maintainL);

            hourL = new JLabel("运行小时");
            hourL.setFont(new Font("宋体", Font.PLAIN, 35));
            hourL.setBounds(647, 506, 144, 52);
            MP.add(hourL);

            nameT = new JTextField();
            nameT.setFont(new Font("宋体", Font.PLAIN, 35));
            nameT.setColumns(10);
            nameT.setBounds(351, 163, 238, 58);
            MP.add(nameT);

            dateT = new JTextField();
            dateT.setFont(new Font("宋体", Font.PLAIN, 35));
            dateT.setColumns(10);
            dateT.setBounds(351, 270, 238, 58);
            MP.add(dateT);

            salaryT = new JTextField();
            salaryT.setFont(new Font("宋体", Font.PLAIN, 35));
            salaryT.setColumns(10);
            salaryT.setBounds(351, 396, 238, 58);
            MP.add(salaryT);

            priceT = new JTextField();
            priceT.setFont(new Font("宋体", Font.PLAIN, 35));
            priceT.setColumns(10);
            priceT.setBounds(351, 504, 238, 58);
            MP.add(priceT);

            licenseT = new JTextField();
            licenseT.setFont(new Font("宋体", Font.PLAIN, 35));
            licenseT.setColumns(10);
            licenseT.setBounds(806, 163, 238, 58);
            MP.add(licenseT);

            mileT = new JTextField();
            mileT.setFont(new Font("宋体", Font.PLAIN, 35));
            mileT.setColumns(10);
            mileT.setBounds(806, 274, 238, 58);
            MP.add(mileT);

            maintainT = new JTextField();
            maintainT.setFont(new Font("宋体", Font.PLAIN, 35));
            maintainT.setColumns(10);
            maintainT.setBounds(806, 396, 238, 58);
            MP.add(maintainT);

            hourT = new JTextField();
            hourT.setFont(new Font("宋体", Font.PLAIN, 35));
            hourT.setColumns(10);
            hourT.setBounds(806, 504, 238, 58);
            MP.add(hourT);
        }
        setComp_visible(false);

        JButton FBnt = new JButton("查找");
        FBnt.setFont(new Font("宋体", Font.PLAIN, 40));
        FBnt.setBounds(1147, 25, 180, 112);
        MP.add(FBnt);

        JButton CommitBnt = new JButton("确定");
        CommitBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ids = InputParse.parseID(idT.getText());
                // 如果输入了id
                if (!ids.equals("")) {
                    try {
                        // 判断选择项目
                        if (select == 0) {
                            int id = Integer.parseInt(ids);
                            String name = nameT.getText();
                            String date = InputParse.parseDate(dateT.getText());
                            double salary = Double.parseDouble(salaryT.getText());
                            //输入的日期格式正确
                            if (date != null) {
                                //判断是插入还是修改
                                if (c_or_i) {
                                    boolean v = DataProcessing.updateDriver(new Driver(id, name, Date.valueOf(date), salary));
                                    if (v)  // 执行情况
                                        JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                                    else {
                                        JOptionPane.showMessageDialog(null, "修改失败！", "出错了", JOptionPane.ERROR_MESSAGE);
                                        reset();
                                    }
                                } else {
                                    boolean v = DataProcessing.insertDriver(new Driver(id, name, Date.valueOf(date), salary));
                                    if (v)
                                        JOptionPane.showMessageDialog(null, "插入成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                                    else {
                                        JOptionPane.showMessageDialog(null, "插入失败！", "出错了", JOptionPane.ERROR_MESSAGE);
                                        reset();
                                    }
                                }
                            }
                            // 如果日期格式错误，则直接返回
                        } else {
                            int id = Integer.parseInt(ids);
                            String type = nameT.getText();
                            String date = InputParse.parseDate(dateT.getText());
                            double price = Double.parseDouble(priceT.getText());
                            double rate = Double.parseDouble(salaryT.getText());
                            String license = licenseT.getText().trim();
                            double mile = Double.parseDouble(mileT.getText());
                            double hour = Double.parseDouble(hourT.getText());
                            String maintain = InputParse.parseDate(maintainT.getText());
                            //检查日期是否正确
                            if (date != null && maintain != null) {
                                if (c_or_i) {
                                    boolean v = DataProcessing.updateCar(new Car(id, type, license, Date.valueOf(date), price,
                                            Date.valueOf(maintain), mile, hour, rate));
                                    if (v)
                                        JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                                    else {
                                        JOptionPane.showMessageDialog(null, "修改失败！", "出错了", JOptionPane.ERROR_MESSAGE);
                                        reset();
                                    }
                                } else {
                                    boolean v = DataProcessing.insertCar(new Car(id, type, license, Date.valueOf(date), price,
                                            Date.valueOf(maintain), mile, hour, rate));
                                    if (v)
                                        JOptionPane.showMessageDialog(null, "插入成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                                    else {
                                        JOptionPane.showMessageDialog(null, "插入失败！", "出错了", JOptionPane.ERROR_MESSAGE);
                                        reset();
                                    }
                                }
                            }
                        }
                    } catch (SQLException ep) {
                        ep.printStackTrace();
                    } catch (IllegalArgumentException xp) {
                        xp.printStackTrace();
                        JOptionPane.showMessageDialog(null, "日期格式错误！", "出错了", JOptionPane.INFORMATION_MESSAGE);
                    }
                    reset();  //点击结束最后的操作
                }
            }
        });
        CommitBnt.setFont(new Font("宋体", Font.PLAIN, 45));
        CommitBnt.setBounds(1178, 582, 180, 112);
        MP.add(CommitBnt);

        FBnt.addActionListener(new ActionListener() {// 说明要修改  解释请看JOptionPane.showMessageDialog 中文部分
            public void actionPerformed(ActionEvent e) {
                String ids = InputParse.parseID(idT.getText());
                if (!ids.equals("")) {
                    idT.setEditable(false);
                    c_or_i = true;
                    try {
                        int id = Integer.parseInt(ids);
                        if (select == 0) {
                            Driver[] data = DataProcessing.searchDriver(Search_SQL_sen.get_a_driver(id));
                            if (data == null) {  //todo 这里可以抽象成一个函数
                                JOptionPane.showMessageDialog(null, "没有查到此司机", "未查到", JOptionPane.INFORMATION_MESSAGE);
                                idT.setText("");
                                idT.setEditable(true);
                            } else {
                                Driver driver = data[0];
                                nameT.setText(driver.getName());
                                dateT.setText(ParseEntity.ParseDate2S(driver.getEnroll_date()));
                                salaryT.setText(Double.toString(driver.getSalary()));
                            }
                        } else {
                            setComp_visible(true);
                            Car[] data = DataProcessing.searchCar(Search_SQL_sen.get_a_car(id));
                            if (data == null) {
                                JOptionPane.showMessageDialog(null, "没有查到此车辆", "未查到", JOptionPane.INFORMATION_MESSAGE);
                                idT.setText("");
                                idT.setEditable(true);
                            } else {
                                Car car = data[0];
                                nameT.setText(car.getType());
                                dateT.setText(ParseEntity.ParseDate2S(car.getPurchase_date()));
                                salaryT.setText(Double.toString(car.getRent_rate()));
                                maintainT.setText(ParseEntity.ParseDate2S((car.getMaintain_date())));
                                licenseT.setText(car.getLicense());
                                priceT.setText(Double.toString(car.getPrice()));
                                mileT.setText(Double.toString(car.getMile()));
                                hourT.setText(Double.toString(car.getWorking_time()));
                            }

                        }
                    } catch (SQLException ep) {
                        ep.printStackTrace();
                    }
                }
            }
        });

        setVisible(true);
    }

    private void reset() {
        this.salaryT.setText("");
        this.idT.setText("");
        this.priceT.setText("");
        this.hourT.setText("");
        this.maintainT.setText("");
        this.mileT.setText("");
        this.licenseT.setText("");
        this.maintainT.setText("");
        this.nameT.setText("");
        this.dateT.setText("");
    }

    public void setComp_visible(boolean flag) {
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

    private void windowLis(JFrame outer){
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                outer.setExtendedState(JFrame.NORMAL);
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }
}
