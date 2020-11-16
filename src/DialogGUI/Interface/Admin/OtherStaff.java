package DialogGUI.Interface.Admin;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OtherStaff extends JPanel {

	/**
	 * Create the panel.
	 */
	public OtherStaff() {
		setLayout(null);
		JButton SearchBnt = new JButton("查询信息");
		SearchBnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SearchALL();
				//TODO
			}
		});
		SearchBnt.setFont(new Font("宋体", Font.PLAIN, 28));
		SearchBnt.setBounds(134, 100, 363, 70);
		add(SearchBnt);
		
		JButton Insert_ChangeBnt = new JButton("增改车辆和司机");
		Insert_ChangeBnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Insert_Change_Item();
				//TODO
			}
		});
		Insert_ChangeBnt.setFont(new Font("宋体", Font.PLAIN, 28));
		Insert_ChangeBnt.setBounds(134, 357, 363, 70);
		add(Insert_ChangeBnt);

	}
}
