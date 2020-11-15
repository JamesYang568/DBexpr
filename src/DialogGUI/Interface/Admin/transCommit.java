package DialogGUI.Interface.Admin;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class transCommit extends JPanel {
	private JTextField clientT;
	private JTextField driverT;
	private JTextField carT;

	/**
	 * Create the panel.
	 */
	public transCommit() {
		setLayout(null);
		
		JLabel title = new JLabel("业务报表");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("宋体", Font.BOLD, 30));
		title.setBounds(212, 15, 176, 69);
		add(title);
		
		JLabel clientL = new JLabel("顾客ID号");
		clientL.setFont(new Font("宋体", Font.PLAIN, 22));
		clientL.setBounds(87, 121, 120, 34);
		add(clientL);
		
		JLabel driverL = new JLabel("司机ID号");
		driverL.setFont(new Font("宋体", Font.PLAIN, 22));
		driverL.setBounds(87, 192, 120, 34);
		add(driverL);
		
		JLabel carL = new JLabel("汽车注册号");
		carL.setFont(new Font("宋体", Font.PLAIN, 22));
		carL.setBounds(87, 264, 120, 34);
		add(carL);
		
		clientT = new JTextField();
		clientT.setFont(new Font("宋体", Font.PLAIN, 22));
		clientT.setBounds(266, 121, 148, 34);
		add(clientT);
		clientT.setColumns(10);
		
		driverT = new JTextField();
		driverT.setFont(new Font("宋体", Font.PLAIN, 22));
		driverT.setColumns(10);
		driverT.setBounds(266, 197, 148, 34);
		add(driverT);
		
		carT = new JTextField();
		carT.setFont(new Font("宋体", Font.PLAIN, 22));
		carT.setColumns(10);
		carT.setBounds(266, 269, 148, 34);
		add(carT);
		
		JPanel TrasP = new JPanel();
		TrasP.setBounds(0, 367, 614, 278);
		add(TrasP);
		TrasP.setLayout(null);
		
		JButton createBnt = new JButton("生成业务单");
		createBnt.setFont(new Font("宋体", Font.PLAIN, 20));
		createBnt.setBounds(457, 309, 142, 43);
		add(createBnt);
		
		JButton button = new JButton("查询申请");
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(459, 118, 123, 43);
		add(button);

	}
}
