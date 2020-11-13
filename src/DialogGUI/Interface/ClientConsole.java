package DialogGUI.Interface;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ClientConsole extends JFrame {

    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public ClientConsole() {
        setTitle("客户端");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 853, 625);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
    }

}
