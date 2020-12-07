package DialogGUI.Interface.Admin;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;

public class OtherStaff extends JPanel {

	/**
	 * Create the panel.
	 */
	public OtherStaff() {
		setLayout(null);
		JButton SearchBnt = new JButton("查询信息");
		SearchBnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ServerConsole serverConsole = (ServerConsole) getTopLevelAncestor();
				new SearchALL(serverConsole);
				serverConsole.setState(JFrame.ICONIFIED);
				//TODO 最小化，最大化
			}
		});
		SearchBnt.setFont(new Font("宋体", Font.PLAIN, 65));
		SearchBnt.setBounds(199, 285, 516, 263);
		add(SearchBnt);
		
		JButton Insert_ChangeBnt = new JButton("增改车辆和司机");
		Insert_ChangeBnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServerConsole serverConsole = (ServerConsole) getTopLevelAncestor();
				new Insert_Change_Item(serverConsole);
				serverConsole.setState(JFrame.ICONIFIED);
				//TODO 最小化，最大化
			}
		});
		Insert_ChangeBnt.setFont(new Font("宋体", Font.PLAIN, 65));
		Insert_ChangeBnt.setBounds(782, 285, 516, 263);
		add(Insert_ChangeBnt);

	}
}
