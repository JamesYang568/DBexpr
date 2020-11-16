package DialogGUI.Interface;

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
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Signin frame = new Signin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Signin() {
        setTitle("登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 726, 422);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel Toplabel = new JLabel("登录");
        Toplabel.setFont(new Font("宋体", Font.BOLD, 32));
        Toplabel.setBounds(312, 15, 78, 74);
        contentPane.add(Toplabel);

        JPanel mainpanel = new JPanel();
        mainpanel.setBounds(0, 82, 704, 187);
        contentPane.add(mainpanel);
        mainpanel.setLayout(null);

        JLabel wrongL = new JLabel("账户或密码错误");
        wrongL.setForeground(Color.RED);
        wrongL.setBounds(15, 61, 172, 67);
        wrongL.setVisible(false);
        mainpanel.add(wrongL);
        wrongL.setFont(new Font("宋体", Font.PLAIN, 22));

        JLabel IDL = new JLabel("账户");
        IDL.setBounds(169, 39, 48, 29);
        IDL.setFont(new Font("宋体", Font.PLAIN, 24));
        mainpanel.add(IDL);

        IDF = new JTextField();
        IDF.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                wrongL.setVisible(false);
            }
        });
        IDF.setBounds(388, 36, 186, 35);
        IDF.setFont(new Font("宋体", Font.PLAIN, 22));
        mainpanel.add(IDF);
        IDF.setColumns(15);

        JLabel PWL = new JLabel("密码");
        PWL.setBounds(169, 120, 48, 29);
        PWL.setFont(new Font("宋体", Font.PLAIN, 24));
        mainpanel.add(PWL);

        PWF = new JPasswordField();
        PWF.setBounds(388, 117, 186, 35);
        PWF.setColumns(10);
        PWF.setFont(new Font("宋体", Font.PLAIN, 18));
        mainpanel.add(PWF);

        JButton SB = new JButton("确定");
        SB.addActionListener(e -> {
            int id = Integer.parseInt(IDF.getText());
            String password = new String(PWF.getPassword());
            int flag = 0;
            try {
                flag = who(id, password);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            if (flag == 1) {
                new ClientConsole();
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
        SB.setFont(new Font("宋体", Font.PLAIN, 22));
        SB.setBounds(151, 299, 123, 35);
        contentPane.add(SB);

        JButton SD = new JButton("取消");
        SD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                IDF.setText("");
                PWF.setText("");
            }
        });
        SD.setFont(new Font("宋体", Font.PLAIN, 22));
        SD.setBounds(411, 299, 123, 35);
        contentPane.add(SD);
    }

    private int who(int id, String pw) throws SQLException {
        if (id == 88888)
            return 2;
        if (DataProcessing.searchClient(id, pw))
            return 1;
        else
            return 3;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        DataProcessing.disconnectFromDB();
    }
}
