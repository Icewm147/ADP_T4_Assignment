

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Raeesah Khan 219308101
 */
public class LoginGui extends JFrame {

    private static JPanel panelN, panelC, panelS;
    private static JLabel username, password, heading;
    private static JTextField usernameTxt, passwordTxt;
    private static JButton btnLogin;
    private static JComboBox cbo;

    public LoginGui() {

        panelN = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelC = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        panelS = new JPanel(new FlowLayout(FlowLayout.CENTER));

        username = new JLabel("Username: ");
        password = new JLabel("Password: ");
        heading = new JLabel("LOGIN");

        usernameTxt = new JTextField(20);
        passwordTxt = new JTextField(20);

        cbo = new JComboBox(new String[]{"Admin", "Student"});

        btnLogin = new JButton("LOGIN");

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnLogin) {

                    if (cbo.getSelectedItem() == "Student") {

                        Client run = new Client();
                        run.setTitle("Login");
                        run.setSize(300, 250);
                        run.setGuiStudent();
                        run.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        setVisible(false);
                        run.setVisible(true);
                    } else {
                        Client run = new Client();
                        run.setTitle("Login");
                        run.setSize(300, 250);
                        run.setGuiAdmin();
                        run.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        setVisible(false);
                        run.setVisible(true);
                    }

                }
            }

        });
    }

    public void setGui() {

        panelN.add(heading);
        panelC.add(cbo);
        panelC.add(username);
        panelC.add(usernameTxt);
        panelC.add(password);
        panelC.add(passwordTxt);

        panelS.add(btnLogin);

        add(panelN, BorderLayout.NORTH);
        add(panelC, BorderLayout.CENTER);
        add(panelS, BorderLayout.SOUTH);

    }

}