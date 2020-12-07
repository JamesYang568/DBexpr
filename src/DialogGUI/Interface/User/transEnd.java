package DialogGUI.Interface.User;

import DialogGUI.Help.InputParse;
import entity.*;
import handle.DataProcessing;
import handle.Search_SQL_sen;
import handle.Update_SQL_sen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class transEnd extends JPanel {
	private final JTextField idT;
	private final JTextField carnameT;
	private final JTextField drivernameT;
	private final JTextField localT;
	private final JTextField dateT;
	private final JTextField timeT;
	private final JTextField mileT;
	private Transaction transaction;
	/**
	 * Create the panel.
	 */
	public transEnd() {
		setLayout(null);

		JLabel title = new JLabel("结束业务");
		title.setFont(new Font("宋体", Font.PLAIN, 45));
		title.setBounds(606, 0, 263, 75);
		add(title);

		JLabel Iid = new JLabel("ID号");
		Iid.setFont(new Font("宋体", Font.PLAIN, 35));
		Iid.setBounds(432, 84, 153, 54);
		add(Iid);

		JLabel Icarname = new JLabel("车牌号");
		Icarname.setFont(new Font("宋体", Font.PLAIN, 35));
		Icarname.setBounds(397, 166, 153, 54);
		add(Icarname);

		JLabel Idrivername = new JLabel("司机姓名");
		Idrivername.setFont(new Font("宋体", Font.PLAIN, 35));
		Idrivername.setBounds(364, 246, 153, 54);
		add(Idrivername);

		JLabel Ilocal = new JLabel("目的地");
		Ilocal.setFont(new Font("宋体", Font.PLAIN, 35));
		Ilocal.setBounds(397, 332, 153, 54);
		add(Ilocal);

		JLabel Idate = new JLabel("日期");
		Idate.setFont(new Font("宋体", Font.PLAIN, 35));
		Idate.setBounds(432, 424, 153, 54);
		add(Idate);

		JLabel Itime = new JLabel("运行时间");
		Itime.setFont(new Font("宋体", Font.PLAIN, 35));
		Itime.setBounds(364, 513, 153, 54);
		add(Itime);

		JLabel Imile = new JLabel("运行里程");
		Imile.setFont(new Font("宋体", Font.PLAIN, 35));
		Imile.setBounds(364, 608, 153, 54);
		add(Imile);

		{
			idT = new JTextField();
			idT.setFont(new Font("宋体", Font.PLAIN, 35));
			idT.setBounds(645, 88, 387, 47);
			add(idT);
			idT.setColumns(10);

			carnameT = new JTextField();
			carnameT.setFont(new Font("宋体", Font.PLAIN, 35));
			carnameT.setBounds(645, 170, 387, 47);
			add(carnameT);
			carnameT.setColumns(10);

			drivernameT = new JTextField();
			drivernameT.setFont(new Font("宋体", Font.PLAIN, 35));
			drivernameT.setBounds(645, 250, 387, 47);
			add(drivernameT);
			drivernameT.setColumns(10);

			localT = new JTextField();
			localT.setFont(new Font("宋体", Font.PLAIN, 35));
			localT.setBounds(645, 336, 387, 47);
			add(localT);
			localT.setColumns(10);

			dateT = new JTextField();
			dateT.setFont(new Font("宋体", Font.PLAIN, 35));
			dateT.setBounds(645, 428, 387, 47);
			add(dateT);
			dateT.setColumns(10);

			timeT = new JTextField();
			timeT.setFont(new Font("宋体", Font.PLAIN, 35));
			timeT.setBounds(645, 517, 387, 47);
			add(timeT);
			timeT.setColumns(10);

			mileT = new JTextField();
			mileT.setFont(new Font("宋体", Font.PLAIN, 35));
			mileT.setBounds(645, 612, 387, 47);
			add(mileT);
			mileT.setColumns(10);
			
			carnameT.setEnabled(false);
			drivernameT.setEnabled(false);
			localT.setEnabled(false);
			dateT.setEnabled(false);
			timeT.setEnabled(false);
			mileT.setEnabled(false);

		}

		JButton SBnt = new JButton("查询");
		SBnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int id=Integer.parseInt(InputParse.parseID(idT.getText()));
					//用输入的ID查业务
					transaction=Objects.requireNonNull(
							DataProcessing.searchTransaction(Search_SQL_sen.get_a_TR_by_id(id)))[0];
					localT.setText(transaction.getLocal());
					dateT.setText(InputParse.ParseDate2S(transaction.getDate()));
					timeT.setText(Double.toString(transaction.getTimes()));
					mileT.setText(Double.toString(transaction.getTimes()));
					//用car_id查车牌号
					Car car =Objects.requireNonNull(
							DataProcessing.searchCar(Search_SQL_sen.get_a_car(transaction.getCar_id())))[0];
					carnameT.setText(car.getLicense());
					//用driver_id查司机姓名
					Driver driver =Objects.requireNonNull(
							DataProcessing.searchDriver(Search_SQL_sen.get_a_driver(transaction.getDriver_id())))[0];
					drivernameT.setText(driver.getName());
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		SBnt.setFont(new Font("宋体", Font.PLAIN, 50));
		SBnt.setBounds(1129, 69, 176, 75);
		add(SBnt);

		JButton SubBnt = new JButton("确定结束");
		SubBnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//释放掉执行该业务的汽车和司机
					DataProcessing.update(Update_SQL_sen.car_set_available(transaction.getCar_id(), 1));
					DataProcessing.update(Update_SQL_sen.driver_set_available(transaction.getDriver_id(), 1));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "您已结束该业务！", "提示", JOptionPane.INFORMATION_MESSAGE);
				reset();
			}
		});
		SubBnt.setFont(new Font("宋体", Font.PLAIN, 50));
		SubBnt.setBounds(1111, 655, 233, 121);
		add(SubBnt);

	}
	
	private void reset() {
		idT.setText("");
		carnameT.setText("");
		drivernameT.setText("");
		localT.setText("");
		dateT.setText("");
		timeT.setText("");
		mileT.setText("");
	}
	
}
