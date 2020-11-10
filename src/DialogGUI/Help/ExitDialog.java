package DialogGUI.Help;

import javax.swing.*;
import java.awt.*;

public class ExitDialog extends JDialog implements Runnable{

	private final JPanel contentPanel = new JPanel();

    public ExitDialog() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

	/**
	 * Create the dialog.
	 */
    public void run() {
		setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());{
            JPanel panel = new JPanel();
            getContentPane().add(panel, BorderLayout.CENTER);
            panel.setLayout(null);

            JLabel label = new JLabel("\u611F\u8C22\u60A8\u7684\u4F7F\u7528\uFF01");
            label.setFont(new Font("宋体", Font.BOLD, 24));
            label.setBounds(132, 102, 200, 36);
            panel.add(label);
        }
	}

}
