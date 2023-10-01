package za.ac.cput.adp2_t4_assignment_rmn.Client;

import java.awt.FlowLayout;
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

    public LoginGui() {

        panelN = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelC = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        panelS = new JPanel(new FlowLayout(FlowLayout.CENTER));

        username = new JLabel("Username: ");
        password = new JLabel("Password: ");
        heading = new JLabel("LOGIN");
        
        usernameTxt = new JTextField();
        passwordTxt = new JTextField();
        
        btnLogin = new JButton("LOGIN");
        
    }

    public void setGui() {

        
        
        
        
    }
    
    
    

}
