
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Raeesah Khan 219308101
 */
public class Client extends JFrame {

    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static Socket server;
    private static Object recievedObject;

    private static JPanel panelN, panelC, panelS, panelE, panelW, panelJ;
    private static JLabel username, password, heading;
    private static JTextField usernameTxt, passwordTxt;
    private static JButton btnLogin;
    private static JComboBox cbo;

    private static JButton btnAddCourse, btnDelete, btnSearch, btnRetrieveStud, btnRetrieveCourse;
    private static JTextField searchTxt;

    private static JButton btnEnroll, btnViewCourse;

    private static JLabel courseDes, courseCode, paneHeading, space1, space2, space3;
    private static JTextField courseDesTxt, courseCodeTxt;

    public Client() {

        try {
            server = new Socket("127.0.0.1", 12345);
        } catch (IOException ex) {
            System.out.println("IOException >>" + ex.getMessage());
        }

        panelN = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelC = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelS = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelE = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //    panelE = new JPanel(new GridLayout(5,6));
        panelW = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelJ = new JPanel(new GridLayout(4, 2));

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

        //-----------------------------------------------------Student
        btnEnroll = new JButton("Enroll In Course");
        btnViewCourse = new JButton("View Available Courses");

        //-----------------------------------------------------JOptionPane
        courseDes = new JLabel("Course Description: ");
        courseDes.setAlignmentX(CENTER_ALIGNMENT);
        courseCode = new JLabel("Course Code: ");
        paneHeading = new JLabel("Add a Course: ");

        space1 = new JLabel();
        space2 = new JLabel();
        space3 = new JLabel();

        courseDesTxt = new JTextField(20);
        courseCodeTxt = new JTextField(20);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnLogin) {

                    if (cbo.getSelectedItem() == "Student") {
                        heading.setText("Student Enrollment system");

                        btnEnroll.setVisible(true);
                        btnViewCourse.setVisible(true);

                        cbo.setVisible(false);
                        username.setVisible(false);
                        usernameTxt.setVisible(false);
                        password.setVisible(false);
                        passwordTxt.setVisible(false);

                        panelS.setVisible(false);
                    } else {
                        heading.setText("Admin Access");

                        btnAddCourse.setVisible(true);
                        btnSearch.setVisible(true);
                        btnRetrieveStud.setVisible(true);
                        btnRetrieveCourse.setVisible(true);
                        btnDelete.setVisible(true);
                        searchTxt.setVisible(true);

                        cbo.setVisible(false);
                        username.setVisible(false);
                        usernameTxt.setVisible(false);
                        password.setVisible(false);
                        passwordTxt.setVisible(false);

                        panelS.setVisible(false);
                    }

                }
            }

        });

        btnAddCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnAddCourse) {

                    panelJ.setVisible(true);
                    int result = JOptionPane.showOptionDialog(null, panelJ,
                            "Add a Course",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                            new String[]{"Add", "Cancel"}, "Yes");
                    if (result == JOptionPane.YES_OPTION) {

                        String code = courseCodeTxt.getText();
                        String description = courseDesTxt.getText();
                        
                        
                        
                        
                        
                        System.out.println(code + " " + description);

                        panelJ.setVisible(false);
                    }

                }

            }

        });

        btnEnroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnEnroll) {

                    int result = JOptionPane.showOptionDialog(null, panelJ,
                            "Add a Course",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                            new String[]{"Add", "Cancel"}, "Yes");
                    if (result == JOptionPane.YES_OPTION) {

                        AddCourse();

                       
                        panelJ.setVisible(false);
                    }

                }
            }

        });

    }

    public void setGui() {

        panelN.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelC.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelE.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelW.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelS.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelN.add(heading);

        panelC.add(cbo);
        panelC.add(username);
        panelC.add(usernameTxt);
        panelC.add(password);
        panelC.add(passwordTxt);

        panelC.add(btnAddCourse);
        panelC.add(btnRetrieveStud);
        panelC.add(btnRetrieveCourse);
        panelC.add(btnDelete);
        panelC.add(searchTxt);
        panelC.add(btnSearch);

        panelC.add(btnEnroll);
        panelC.add(btnViewCourse);

        panelS.add(btnLogin);

        panelJ.add(paneHeading);
        panelJ.add(space1);
        panelJ.add(courseCode);
        panelJ.add(courseCodeTxt);
        panelJ.add(courseDes);
        panelJ.add(courseDesTxt);

        panelJ.setVisible(false);

        add(panelN, BorderLayout.NORTH);
        add(panelC, BorderLayout.CENTER);
        add(panelS, BorderLayout.SOUTH);
        add(panelW, BorderLayout.WEST);
        add(panelJ, BorderLayout.EAST);

        panelE.setVisible(false);

        btnAddCourse.setVisible(false);
        btnSearch.setVisible(false);
        btnRetrieveStud.setVisible(false);
        btnRetrieveCourse.setVisible(false);
        btnDelete.setVisible(false);
        searchTxt.setVisible(false);

        btnEnroll.setVisible(false);
        btnViewCourse.setVisible(false);

    }

    public void getStreams() {
        try {
            out = new ObjectOutputStream(server.getOutputStream());
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

    public void AddCourse() {
        String description = courseDesTxt.getText();
        String code = courseCodeTxt.getText();

        try {
            WorkerCourse add = new WorkerCourse(code, description);
            out.writeObject(add);
            out.flush();

            String recievedMsg = (String) in.readObject();
            if (recievedMsg.equalsIgnoreCase("success")) {
                JOptionPane.showMessageDialog(null, "Login successful.");
            }

        } catch (IOException ex) {
            System.out.println("IOException" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + ex.getMessage());
        }

    }

    public static void authenticationLogin() {
        WorkerLogin login = new WorkerLogin();
        String user = login.getUsername();
        String passwords = login.getPassword();
        String type = cbo.getSelectedItem().toString();

        try {
            WorkerLogin log = new WorkerLogin(user, passwords, type);
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
        log.setSize(700, 450);
        log.setGui();
        log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        log.setLocationRelativeTo(null);
        log.setVisible(true);
    }
}
