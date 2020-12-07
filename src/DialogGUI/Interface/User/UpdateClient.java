package DialogGUI.Interface.User;

import entity.Client;
import handle.DataProcessing;
import handle.Search_SQL_sen;
import handle.Update_SQL_sen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class UpdateClient extends JPanel {
	private final JTextField nameT;
	private final JTextField companyT;
	private final JTextField telT;
	private final JTextField addrT;
	private final JTextField zipcodeT;
	private final JPasswordField passwordT;

	/**
	 * Create the panel.
	 */
	public UpdateClient(int client_id) {
		setLayout(null);

		JLabel title = new JLabel("修改信息");
		title.setFont(new Font("宋体", Font.PLAIN, 45));
		title.setBounds(648, 0, 364, 83);
		add(title);

		JLabel Ipassword = new JLabel("密码");
		Ipassword.setFont(new Font("宋体", Font.PLAIN, 35));
		Ipassword.setBounds(218, 107, 167, 55);
		add(Ipassword);

		JLabel Iname = new JLabel("姓名");
		Iname.setFont(new Font("宋体", Font.PLAIN, 35));
		Iname.setBounds(218, 190, 167, 55);
		add(Iname);

		JLabel Icompany = new JLabel("单位");
		Icompany.setFont(new Font("宋体", Font.PLAIN, 35));
		Icompany.setBounds(218, 270, 167, 55);
		add(Icompany);

		JLabel Itel = new JLabel("电话");
		Itel.setFont(new Font("宋体", Font.PLAIN, 35));
		Itel.setBounds(218, 352, 167, 55);
		add(Itel);

		JLabel Iaddr = new JLabel("地址");
		Iaddr.setFont(new Font("宋体", Font.PLAIN, 35));
		Iaddr.setBounds(218, 434, 167, 55);
		add(Iaddr);

		JLabel Izipcode = new JLabel("邮编");
		Izipcode.setFont(new Font("宋体", Font.PLAIN, 35));
		Izipcode.setBounds(218, 516, 167, 55);
		add(Izipcode);

		// 初始化文本框
		{
			nameT = new JTextField();
			nameT.setFont(new Font("宋体", Font.PLAIN, 35));
			nameT.setBounds(399, 190, 675, 55);
			add(nameT);
			nameT.setColumns(10);

			companyT = new JTextField();
			companyT.setFont(new Font("宋体", Font.PLAIN, 35));
			companyT.setBounds(399, 270, 675, 55);
			add(companyT);
			companyT.setColumns(10);

			telT = new JTextField();
			telT.setFont(new Font("宋体", Font.PLAIN, 35));
			telT.setBounds(399, 352, 675, 55);
			add(telT);
			telT.setColumns(10);

			addrT = new JTextField();
			addrT.setFont(new Font("宋体", Font.PLAIN, 35));
			addrT.setBounds(399, 434, 675, 55);
			add(addrT);
			addrT.setColumns(10);

			zipcodeT = new JTextField();
			zipcodeT.setFont(new Font("宋体", Font.PLAIN, 35));
			zipcodeT.setBounds(399, 516, 675, 55);
			add(zipcodeT);
			zipcodeT.setColumns(10);

			passwordT = new JPasswordField();
			passwordT.setFont(new Font("宋体", Font.PLAIN, 35));
			passwordT.setBounds(399, 107, 675, 55);
			add(passwordT);
			passwordT.setColumns(10);

			nameT.setEnabled(false);
			companyT.setEnabled(false);
			telT.setEnabled(false);
			addrT.setEnabled(false);
			zipcodeT.setEnabled(false);
			passwordT.setEnabled(false);
		}

		JButton SubBnt = new JButton("确定");
		SubBnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = client_id;
				String password = new String(passwordT.getPassword());
				String name = nameT.getText();
				String company = companyT.getText();
				String addr = addrT.getText();
				String tel = telT.getText();
				String zipcode = zipcodeT.getText();
				Client client = new Client(id, password, name, company, tel, addr, zipcode);
				boolean flag;
				try {
					flag = DataProcessing.updateClient(client);
					if (flag) {
						DataProcessing.update(Update_SQL_sen.client_password(id, password));
					} else {
						title.setText("修改失败，请重新尝试");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
				reset();
			}
		});
		SubBnt.setFont(new Font("宋体", Font.PLAIN, 50));
		SubBnt.setBounds(275, 661, 184, 97);
		add(SubBnt);

		JButton CanBnt = new JButton("取消");
		CanBnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		CanBnt.setFont(new Font("宋体", Font.PLAIN, 50));
		CanBnt.setBounds(993, 661, 184, 97);
		add(CanBnt);

		JButton SBnt = new JButton("查询");
		SBnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { // 这里一定不会查出错误
					Client client = Objects
							.requireNonNull(DataProcessing.searchClient(Search_SQL_sen.get_a_client(client_id)))[0];
					nameT.setText(client.getName());
					companyT.setText(client.getCompany());
					addrT.setText(client.getAddr());
					telT.setText(client.getTel());
					zipcodeT.setText(client.getZipcode());

					passwordT.setEnabled(true);
					nameT.setEnabled(true);
					companyT.setEnabled(true);
					telT.setEnabled(true);
					addrT.setEnabled(true);
					zipcodeT.setEnabled(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		SBnt.setFont(new Font("宋体", Font.PLAIN, 50));
		SBnt.setBounds(1164, 87, 185, 97);
		add(SBnt);

	}

	private void reset() {
		nameT.setText("");
		companyT.setText("");
		telT.setText("");
		addrT.setText("");
		zipcodeT.setText("");
		passwordT.setText("");

		nameT.setEnabled(false);
		companyT.setEnabled(false);
		telT.setEnabled(false);
		addrT.setEnabled(false);
		zipcodeT.setEnabled(false);
		passwordT.setEnabled(false);
	}

}
