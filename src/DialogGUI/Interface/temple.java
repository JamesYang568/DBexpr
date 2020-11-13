package DialogGUI.Interface;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class temple extends JPanel {
	private JTextField tid;
	private JTextField tname;
	private JTextField tcompany;
	private JTextField ttel;
	private JTextField taddr;
	private JTextField tzipcode;

	/**
	 * Create the panel.
	 */
	public temple() {
		setLayout(null);
		
		JLabel title = new JLabel("修改客户信息");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Dialog", Font.BOLD, 26));
		title.setEnabled(true);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setBounds(212, 15, 174, 41);
		add(title);
		
		JLabel lid = new JLabel("输入客户号");
		lid.setFont(new Font("宋体", Font.PLAIN, 22));
		lid.setBounds(82, 80, 133, 41);
		add(lid);
		
		JLabel lname = new JLabel("客户姓名");
		lname.setFont(new Font("宋体", Font.PLAIN, 22));
		lname.setBounds(82, 151, 133, 41);
		add(lname);
		
		JLabel lcompany = new JLabel("客户单位");
		lcompany.setFont(new Font("宋体", Font.PLAIN, 22));
		lcompany.setBounds(82, 218, 133, 41);
		add(lcompany);
		
		JLabel ltel = new JLabel("客户电话");
		ltel.setFont(new Font("宋体", Font.PLAIN, 22));
		ltel.setBounds(82, 288, 133, 41);
		add(ltel);
		
		JLabel laddr = new JLabel("客户地址");
		laddr.setFont(new Font("宋体", Font.PLAIN, 22));
		laddr.setBounds(82, 357, 133, 41);
		add(laddr);
		
		JLabel lzipcode = new JLabel("邮编");
		lzipcode.setHorizontalAlignment(SwingConstants.CENTER);
		lzipcode.setFont(new Font("宋体", Font.PLAIN, 22));
		lzipcode.setBounds(82, 428, 133, 41);
		add(lzipcode);
		
		tid = new JTextField();
		tid.setFont(new Font("宋体", Font.PLAIN, 22));
		tid.setBounds(283, 80, 174, 41);
		add(tid);
		tid.setColumns(15);
		
		tname = new JTextField();
		tname.setEditable(false);
		tname.setFont(new Font("宋体", Font.PLAIN, 22));
		tname.setColumns(15);
		tname.setBounds(283, 151, 174, 41);
		add(tname);
		
		tcompany = new JTextField();
		tcompany.setEditable(false);
		tcompany.setFont(new Font("宋体", Font.PLAIN, 22));
		tcompany.setColumns(15);
		tcompany.setBounds(283, 218, 174, 41);
		add(tcompany);
		
		ttel = new JTextField();
		ttel.setEditable(false);
		ttel.setFont(new Font("宋体", Font.PLAIN, 22));
		ttel.setColumns(15);
		ttel.setBounds(283, 288, 174, 41);
		add(ttel);
		
		taddr = new JTextField();
		taddr.setEditable(false);
		taddr.setFont(new Font("宋体", Font.PLAIN, 22));
		taddr.setColumns(15);
		taddr.setBounds(283, 357, 174, 41);
		add(taddr);
		
		tzipcode = new JTextField();
		tzipcode.setEditable(false);
		tzipcode.setFont(new Font("宋体", Font.PLAIN, 22));
		tzipcode.setColumns(15);
		tzipcode.setBounds(283, 428, 174, 41);
		add(tzipcode);
		
		JButton SBnt = new JButton("查询");
		SBnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tid.getText();

			}
		});
		SBnt.setFont(new Font("宋体", Font.PLAIN, 22));
		SBnt.setBounds(482, 80, 123, 41);
		add(SBnt);
		
		JButton SubBnt = new JButton("提交");
		SubBnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		SubBnt.setFont(new Font("宋体", Font.PLAIN, 22));
		SubBnt.setBounds(122, 503, 142, 44);
		add(SubBnt);
		
		JButton CanBnt = new JButton("取消");
		CanBnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		CanBnt.setFont(new Font("宋体", Font.PLAIN, 22));
		CanBnt.setBounds(367, 503, 142, 44);
		add(CanBnt);

	}
}
