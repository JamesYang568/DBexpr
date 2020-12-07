package DialogGUI.Interface;

import DialogGUI.Help.InputParse;
import DialogGUI.Interface.Admin.ServerConsole;
import DialogGUI.Interface.User.ClientConsole;
import handle.DataProcessing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Signin extends JFrame {

	private JPanel contentPane;
	private JTextField IDF;
	private JPasswordField PWF;

	/**
	 * Create the frame.
	 */
	public Signin() {
		setTitle("登录");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 887, 576);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Toplabel = new JLabel("登录");
		Toplabel.setFont(new Font("宋体", Font.BOLD, 40));
		Toplabel.setBounds(390, 13, 100, 74);
		contentPane.add(Toplabel);

		JPanel mainpanel = new JPanel();
		mainpanel.setBounds(0, 82, 869, 311);
		contentPane.add(mainpanel);
		mainpanel.setLayout(null);

		JLabel wrongL = new JLabel("账户或密码错误");
		wrongL.setForeground(Color.RED);
		wrongL.setBounds(285, 203, 447, 82);
		wrongL.setVisible(false);
		mainpanel.add(wrongL);
		wrongL.setFont(new Font("宋体", Font.PLAIN, 40));

		JLabel IDL = new JLabel("账户");
		IDL.setBounds(126, 30, 104, 47);
		IDL.setFont(new Font("宋体", Font.PLAIN, 35));
		mainpanel.add(IDL);

		IDF = new JTextField();
		IDF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				wrongL.setVisible(false);
			}
		});
		IDF.setBounds(286, 30, 357, 47);
		IDF.setFont(new Font("宋体", Font.PLAIN, 35));
		mainpanel.add(IDF);
		IDF.setColumns(15);

		JLabel PWL = new JLabel("密码");
		PWL.setBounds(126, 111, 104, 47);
		PWL.setFont(new Font("宋体", Font.PLAIN, 35));
		mainpanel.add(PWL);

		PWF = new JPasswordField();
		PWF.setBounds(286, 111, 357, 47);
		PWF.setColumns(10);
		PWF.setFont(new Font("宋体", Font.PLAIN, 35));
		mainpanel.add(PWF);

		JButton SB = new JButton("确定");
		SB.addActionListener(e -> {
			int id = Integer.parseInt(InputParse.parseID(IDF.getText()));
			String password = new String(PWF.getPassword());
			int flag = 0;
			try {
				flag = who(id, password);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (flag == 1) {
				new ClientConsole(id);
				dispose();
			} else if (flag == 2) {
				ServerConsole.getInstance();
				dispose();
			} else {
				wrongL.setVisible(true);
				IDF.setText("");
				PWF.setText("");
			}
		});
		SB.setFont(new Font("宋体", Font.PLAIN, 40));
		SB.setBounds(137, 434, 164, 55);
		contentPane.add(SB);

		JButton SD = new JButton("取消");
		SD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IDF.setText("");
				PWF.setText("");
			}
		});
		SD.setFont(new Font("宋体", Font.PLAIN, 40));
		SD.setBounds(532, 434, 164, 55);
		contentPane.add(SD);

		setVisible(true);
	}

	private int who(int id, String pw) throws SQLException {
		if (id == 88888)
			return 2;
		if (DataProcessing.searchClient(id, pw) && !pw.equals(""))
			return 1;
		else
			return 3;
	}

	@Override
	protected void finalize() throws Throwable {
		DataProcessing.disconnectFromDB();
		super.finalize();
	}
}
