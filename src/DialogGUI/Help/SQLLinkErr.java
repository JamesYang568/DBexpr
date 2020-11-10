package DialogGUI.Help;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SQLLinkErr extends JDialog {

    private final JPanel contentPanel = new JPanel();

    /**
     * Create the dialog.
     */
    public SQLLinkErr() {
        setTitle("出错了");
        setBounds(100, 100, 480, 315);
        getContentPane().setLayout(new BorderLayout());
        {
            JPanel panel = new JPanel();
            getContentPane().add(panel, BorderLayout.CENTER);
            panel.setLayout(null);
            {
                JLabel label = new JLabel("\u6570\u636E\u5E93\u8FDE\u63A5\u9519\u8BEF\uFF01");
                label.setBounds(114, 87, 241, 52);
                label.setFont(new Font("宋体", Font.BOLD, 26));
                panel.add(label);
            }

            JButton Bnt = new JButton("确定");
            Bnt.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    dispose();
                }
            });
            Bnt.setFont(new Font("宋体", Font.PLAIN, 22));
            Bnt.setBounds(161, 191, 123, 29);
            panel.add(Bnt);
        }
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
