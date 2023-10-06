
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

/**
 *
 * @author Raeesah Khan 219308101
 */
public class Client extends JFrame implements ActionListener {

    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static Socket server;
    private static Object recievedObject;

    private static JPanel panelN, panelC, panelS, panelE, panelW;
    private static JLabel username, password, heading;
    private static JTextField usernameTxt, passwordTxt;
    private static JButton btnLogin;
    private static JComboBox cbo;

    private static JButton btnAddCourse, btnDelete, btnSearch, btnRetrieveStud, btnRetrieveCourse;
    private static JTextField searchTxt;

    public Client() {

        try {
            server = new Socket("127.0.0.1", 12345);
        } catch (IOException ex) {
            System.out.println("IOException >>" + ex.getMessage());
        }

        panelN = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelC = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panelS = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelE = new JPanel(new FlowLayout(FlowLayout.LEADING));
        panelW = new JPanel(new FlowLayout(FlowLayout.CENTER));

        username = new JLabel("Username: ");
        password = new JLabel("Password: ");
        heading = new JLabel("LOGIN");

        usernameTxt = new JTextField(20);
        passwordTxt = new JTextField(20);

        cbo = new JComboBox(new String[]{"Admin", "Student"});

        btnLogin = new JButton("LOGIN");
        //-----------------------------------------------------Admin  

        btnAddCourse = new JButton("Add Course");
        btnSearch = new JButton("Search");
        btnRetrieveStud = new JButton("Retrieve Student");
        btnRetrieveCourse = new JButton("Retrieve Course");
        btnDelete = new JButton("Delete stud? Course?");
        
        searchTxt = new JTextField(20);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnLogin) {

                    if (cbo.getSelectedItem() == "Student") {
                        heading.setText("Student Enrollment system");
                        panelC.setVisible(false);
                        panelW.setVisible(true);
                    } else {
                        heading.setText("Admin Access");
                        panelC.setVisible(false);
                        panelE.setVisible(true);
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

        panelE.add(btnAddCourse);
        panelE.add(btnRetrieveStud);
        panelE.add(btnRetrieveCourse);
        panelE.add(btnDelete);
        panelE.add(searchTxt);
        panelE.add(btnSearch);

        add(panelN, BorderLayout.NORTH);
        add(panelC, BorderLayout.CENTER);
        add(panelS, BorderLayout.SOUTH);
        add(panelW, BorderLayout.WEST);
        add(panelE, BorderLayout.EAST);
        panelE.setVisible(false);

    }

    public void getStreams() {
        try {
            out = new ObjectOutputStream(server.getOutputStream());
            out.flush();
            in = new ObjectInputStream(server.getInputStream());

        } catch (IOException ex) {
            System.out.println("Cannot get Streams " + ex.getMessage());
        }
    }

    private static void closeConnection() {
        try {
            out.writeObject("Server has closed");
            out.flush();
            out.close();
            in.close();
            server.close();
            System.out.println("Server has closed");
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("Server cannot close. Error " + ex.getMessage());
        }

    }

    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == btnLogin ) {
//            
//        }
//        if (e.getSource() == btnRetrieve) {
//            
//        }
//        if (e.getSource() == ) {
//            
//        }

    }

    public static void authenticationLogin() {
        WorkerLogin login = new WorkerLogin();
        String user = login.getUsername();
        String password = login.getPassword();

        try {
            WorkerLogin log = new WorkerLogin(user, password);
            out.writeObject(log);
            out.flush();

            String recievedMsg = (String) in.readObject();
            if (recievedMsg.equalsIgnoreCase("success")) {
                JOptionPane.showMessageDialog(null, "Login successful.");
            } else {
                JOptionPane.showMessageDialog(null, "Login Failed. Incorrect username or Password.");

            }

        } catch (IOException ex) {
            System.out.println("IOException" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + ex.getMessage());
        }

    }

    public static void main(String[] args) {
        Client log = new Client();
        log.getStreams();
        log.setTitle("Enrolment System");
        log.setSize(300, 250);
        log.setGui();
        log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        log.setVisible(true);
    }
}
