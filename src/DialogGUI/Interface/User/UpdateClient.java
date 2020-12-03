package DialogGUI.Interface.User;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DialogGUI.Help.InputParse;
import entity.Client;
import handle.DataProcessing;
import handle.Search_SQL_sen;
import handle.Update_SQL_sen;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateClient extends JPanel {
	private JTextField tname;
	private JTextField tcompany;
	private JTextField ttel;
	private JTextField taddr;
	private JTextField tzipcode;
	private JPasswordField tpassword;

	/**
	 * Create the panel.
	 */
	public UpdateClient(int client_id) {
		setLayout(null);

		JLabel title = new JLabel("修改信息");
		title.setFont(new Font("宋体", Font.PLAIN, 26));
		title.setBounds(300, 0, 123, 44);
		add(title);

		JLabel Ipassword = new JLabel("密码");
		Ipassword.setFont(new Font("宋体", Font.PLAIN, 22));
		Ipassword.setBounds(87, 57, 98, 34);
		add(Ipassword);

		JLabel Iname = new JLabel("姓名");
		Iname.setFont(new Font("宋体", Font.PLAIN, 22));
		Iname.setBounds(87, 114, 98, 34);
		add(Iname);

		JLabel Icompany = new JLabel("单位");
		Icompany.setFont(new Font("宋体", Font.PLAIN, 22));
		Icompany.setBounds(87, 175, 98, 34);
		add(Icompany);

		JLabel Itel = new JLabel("电话");
		Itel.setFont(new Font("宋体", Font.PLAIN, 22));
		Itel.setBounds(87, 234, 72, 36);
		add(Itel);

		JLabel Iaddr = new JLabel("地址");
		Iaddr.setFont(new Font("宋体", Font.PLAIN, 22));
		Iaddr.setBounds(87, 297, 72, 34);
		add(Iaddr);

		JLabel Izipcode = new JLabel("邮编");
		Izipcode.setFont(new Font("宋体", Font.PLAIN, 22));
		Izipcode.setBounds(87, 355, 72, 34);
		add(Izipcode);

		tname = new JTextField();
		tname.setFont(new Font("宋体", Font.PLAIN, 22));
		tname.setColumns(15);
		tname.setBounds(261, 114, 247, 34);
		add(tname);
		tname.setColumns(10);

		tcompany = new JTextField();
		tcompany.setFont(new Font("宋体", Font.PLAIN, 22));
		tcompany.setColumns(15);
		tcompany.setBounds(261, 175, 247, 34);
		add(tcompany);
		tcompany.setColumns(10);

		ttel = new JTextField();
		ttel.setFont(new Font("宋体", Font.PLAIN, 22));
		ttel.setColumns(15);
		ttel.setBounds(261, 236, 247, 34);
		add(ttel);
		ttel.setColumns(10);

		taddr = new JTextField();
		taddr.setFont(new Font("宋体", Font.PLAIN, 22));
		taddr.setColumns(15);
		taddr.setBounds(261, 292, 247, 34);
		add(taddr);
		taddr.setColumns(10);

		tzipcode = new JTextField();
		tzipcode.setFont(new Font("宋体", Font.PLAIN, 22));
		tzipcode.setColumns(15);
		tzipcode.setBounds(261, 355, 247, 34);
		add(tzipcode);
		tzipcode.setColumns(10);

		tpassword = new JPasswordField();
		tpassword.setFont(new Font("宋体", Font.PLAIN, 22));
		tpassword.setColumns(15);
		tpassword.setBounds(261, 57, 247, 34);
		add(tpassword);

		JButton SubBnt = new JButton("确定");
		SubBnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = client_id;
				String password = new String(tpassword.getPassword());
				String name = tname.getText();
				String company = tcompany.getText();
				String addr = taddr.getText();
				String tel = ttel.getText();
				String zipcode = tzipcode.getText();
				Client client = new Client(id, password, name, company, tel, addr, zipcode);
				boolean flag;
				try {
					flag=DataProcessing.updateClient(client);
					if (flag) {
						DataProcessing.update(Update_SQL_sen.client_password(id, password));
					}else {
						title.setText("修改失败，请重新尝试");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
				reset();
			}
		});
		SubBnt.setFont(new Font("宋体", Font.PLAIN, 22));
		SubBnt.setBounds(122, 443, 113, 44);
		add(SubBnt);

		JButton CanBnt = new JButton("取消");
		CanBnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		CanBnt.setFont(new Font("宋体", Font.PLAIN, 22));
		CanBnt.setBounds(412, 443, 113, 44);
		add(CanBnt);
		
		JButton SBnt = new JButton("查询");
		SBnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Client[] client=DataProcessing.searchClient(Search_SQL_sen.get_a_client(client_id));
					tname.setText(client[0].getName());
					tcompany.setText(client[0].getCompany());
					taddr.setText(client[0].getAddr());
					ttel.setText(client[0].getTel());
					tzipcode.setText(client[0].getZipcode());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		SBnt.setFont(new Font("宋体", Font.PLAIN, 22));
		SBnt.setBounds(609, 52, 113, 44);
		add(SBnt);

	}

	private void reset() {
		tpassword.setText("");
		tname.setText("");
		tcompany.setText("");
		taddr.setText("");
		ttel.setText("");
		tzipcode.setText("");
	}
	
}
