
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raeesah Khan 219308101
 */
public class Client extends JFrame {

    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static Socket server;
    private static Object recievedObject;
    private static Object displays;

    private Font font1, font2, font3;

    private static JPanel panelN, panelC, panelS, panelE, panelW, panelJ;
    private static JLabel username, password, heading;
    private static JTextField usernameTxt, passwordTxt;
    private static JButton btnLogin, btnLogout;
    private static JComboBox cbo, cbo1, cbo2;

    private static JButton btnAddCourse, btnAddStud, btnDelete, btnSearch, btnRetrieveStud, btnRetrieveCourse;
    private static JTextField searchTxt;

    private static JButton btnEnroll, btnViewCourse;

    private static JLabel courseDes, courseCode, paneHeading, space1, space2, space3, space6;
    private static JTextField courseDesTxt, courseCodeTxt;

    private static JLabel studId, studName, studLastName, paneHeading2, space0, space4, space5;
    private static JTextField studIdTxt, studNameTxt, studLastNameTxt;

    private static DefaultTableModel tableModel;
    private static JTable table;
    private static JScrollPane scrollPane;

    public Client() {

        try {
            server = new Socket("127.0.0.1", 12345);
        } catch (IOException ex) {
            System.out.println("IOException >>" + ex.getMessage());
        }

        font1 = new Font("Arial", Font.BOLD, 18);
        font2 = new Font("Arial", Font.BOLD, 16);
        font3 = new Font("Arial", Font.PLAIN, 16);

        panelN = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelN.setBackground(Color.LIGHT_GRAY);

        panelC = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelS = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelE = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //    panelE = new JPanel(new GridLayout(5,6));
        panelW = new JPanel(new GridLayout(4, 2));
        panelJ = new JPanel(new GridLayout(4, 2));

        username = new JLabel("Username: ");
        password = new JLabel("Password: ");
        heading = new JLabel("LOGIN");
        heading.setFont(font1);

        usernameTxt = new JTextField(20);
        passwordTxt = new JTextField(20);

        cbo = new JComboBox(new String[]{"Admin", "Student"});
        cbo1 = new JComboBox(new String[]{"raeesah", "khan"});
        cbo2 = new JComboBox(new String[]{"inm", "adt"});

        btnLogin = new JButton("LOGIN");
        btnLogout = new JButton("LOGOUT");
        //-----------------------------------------------------Admin  

        btnAddCourse = new JButton("Add Course");
        btnSearch = new JButton("Search");
        btnRetrieveStud = new JButton("Retrieve Student");
        btnRetrieveCourse = new JButton("Retrieve Course");
        btnDelete = new JButton("Delete stud? Course?");
        btnAddStud = new JButton("Add student");

        searchTxt = new JTextField(20);

        //-----------------------------------------------------Student
        btnEnroll = new JButton("Enroll In Course");
        btnViewCourse = new JButton("View Available Courses");

        //-----------------------------------------------------JOptionPane
        courseDes = new JLabel("Course Description: ");
        courseCode = new JLabel("Course Code: ");
        paneHeading = new JLabel("Add a Course: ");

        space1 = new JLabel();
        space2 = new JLabel();
        space3 = new JLabel();

        courseDesTxt = new JTextField(20);
        courseCodeTxt = new JTextField(20);

        //-----------------------------------------------------JOptionPane 2 add student
        studId = new JLabel("Student ID: ");
        studName = new JLabel("Student Name: ");
        studLastName = new JLabel("Student Last Name: ");
        paneHeading2 = new JLabel("Add Student");
        space0 = new JLabel();
        space4 = new JLabel();
        space5 = new JLabel();
        space6 = new JLabel();

        studIdTxt = new JTextField(20);
        studNameTxt = new JTextField(20);
        studLastNameTxt = new JTextField(20);

        //-------------------------------------------- table
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnLogin) {

                    if (cbo.getSelectedItem() == "Student") {
                        heading.setText("Student Enrollment system");
                        panelN.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
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
                        btnAddStud.setVisible(true);
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

        btnEnroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnEnroll) {

                    int result = JOptionPane.showOptionDialog(null, panelJ,
                            "Add a Course",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                            new String[]{"Add", "Cancel"}, "Yes");
                    if (result == JOptionPane.YES_OPTION) {

                        panelJ.setVisible(false);
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

                        AddCourse();

                        panelJ.setVisible(false);
                    }

                }

            }

        });

    }

    public void setGui() {

        panelN.setBorder(BorderFactory.createEmptyBorder(20, 20, 350, 20));
        panelC.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelE.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelW.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelS.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelN.add(heading);

        panelN.add(cbo);
        panelN.add(space6);
        panelN.add(username);
        panelN.add(usernameTxt);
        panelN.add(password);
        panelN.add(passwordTxt);

        panelC.add(btnAddCourse);
        panelC.add(btnAddStud);
        panelC.add(btnRetrieveStud);
        panelC.add(btnRetrieveCourse);
        panelC.add(btnDelete);
        panelC.add(searchTxt);
        panelC.add(btnSearch);

        panelC.add(btnEnroll);
        panelC.add(btnViewCourse);

        panelS.add(btnLogin);
        panelS.add(btnLogout);

        panelJ.add(paneHeading);
        panelJ.add(space1);
        panelJ.add(courseCode);
        panelJ.add(courseCodeTxt);
        panelJ.add(courseDes);
        panelJ.add(courseDesTxt);

        panelJ.setVisible(false);

        panelW.add(paneHeading2);
        panelW.add(space0);
        panelW.add(studId);
        panelW.add(studIdTxt);
        panelW.add(studName);
        panelW.add(studNameTxt);
        panelW.add(studLastName);
        panelW.add(studLastNameTxt);

        panelW.setVisible(false);

        panelC.add(new JScrollPane(table));
        scrollPane.setVisible(false);
        table.setVisible(false);
        add(panelN, BorderLayout.NORTH);
        add(panelC, BorderLayout.CENTER);
        add(panelS, BorderLayout.SOUTH);
        add(panelW, BorderLayout.WEST);
        add(panelJ, BorderLayout.EAST);

        panelE.setVisible(false);

        btnAddCourse.setVisible(false);
        btnAddStud.setVisible(false);
        btnSearch.setVisible(false);
        btnRetrieveStud.setVisible(false);
        btnRetrieveCourse.setVisible(false);
        btnDelete.setVisible(false);
        searchTxt.setVisible(false);

        btnEnroll.setVisible(false);
        btnViewCourse.setVisible(false);

        scrollPane.setVisible(false);

        btnAddStud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnAddStud) {

                    panelW.setVisible(true);
                    int result = JOptionPane.showOptionDialog(null, panelW,
                            "Add a Course",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                            new String[]{"Add", "Cancel"}, "Yes");
                    if (result == JOptionPane.YES_OPTION) {
                        AddStud();
                        panelW.setVisible(false);
                    }

                }

            }

        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnDelete) {
                    int result = JOptionPane.showOptionDialog(null, "Choose which catagory that you would like to delete from. ",
                            "Add a Course",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                            new String[]{"Student", "Course"}, "Yes");
                    if (result == JOptionPane.YES_OPTION) {
                        //------------------ Delete student
                        int result1 = JOptionPane.showOptionDialog(null, cbo1,
                                "Delete Student",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                new String[]{"Delete", "Cancel"}, "Yes");
                        if (result1 == JOptionPane.YES_OPTION) {
                            int answer1 = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this student? ");
                            if (answer1 == JOptionPane.YES_OPTION) {
                                System.out.println("Student deleted");
                            }

                        }

                    } else {
                        //------------------ Delete Course
                        int result1 = JOptionPane.showOptionDialog(null, cbo2,
                                "Delete Student",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                new String[]{"Delete", "Cancel"}, "Yes");
                        if (result1 == JOptionPane.YES_OPTION) {
                            int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this course? ");
                            if (answer == JOptionPane.YES_OPTION) {
                                System.out.println("Course deleted");
                            }

                        }

                    }

                }
            }

        });

        btnRetrieveStud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnRetrieveStud) {
                    table.setVisible(true);
                    scrollPane.setVisible(true);
                    retrieveStud();
                }

            }

        });

        btnRetrieveCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnRetrieveCourse) {
                    retrieveCourse();
                }

            }

        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnSearch) {
                    searchStud();
                }

            }

        });

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

    public void AddStud() {
        String id = studIdTxt.getText();
        String name = studNameTxt.getText();
        String lastN = studLastNameTxt.getText();

        try {
            WorkerStudent add = new WorkerStudent(id, name, lastN);
            out.writeObject(add);
            out.flush();

            String recievedMsg = (String) in.readObject();
            if (recievedMsg.equalsIgnoreCase("success")) {
                JOptionPane.showMessageDialog(null, "Course has been added.");
            }

        } catch (IOException ex) {
            System.out.println("IOException" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + ex.getMessage());
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
                JOptionPane.showMessageDialog(null, "Course has been added.");
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

    public static void retrieveStud() {
        clearTable();
        tableModel.addColumn("Student Id");
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");

        try {
            out.writeObject("retrieve student");
            out.flush();

            ArrayList<WorkerStudent> display = (ArrayList<WorkerStudent>) in.readObject();
            for (int i = 0; i < display.size(); i++) {
                WorkerStudent workerStudent = display.get(i);

                ArrayList<Object> arrCon = converter(workerStudent);
                Object[] arrConArray = arrCon.toArray();

                tableModel.addRow(arrConArray);
            }

        } catch (IOException ex) {
            System.out.println("IOException" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + ex.getMessage());

//            ArrayList<WorkerStudent> display = (ArrayList) in.readObject();
//            tableModel.addRow(new Object[]{display});
        }
    }

    public static void retrieveCourse() {
        clearTable();
        tableModel.addColumn("Course Code");
        tableModel.addColumn("Course Description");
        tableModel.addColumn("Availability");

        try {
            out.writeObject("retrieve Course");
            out.flush();

        } catch (IOException ex) {
            System.out.println("IOException" + ex.getMessage());
        }
    }

    public static void searchStud() {

    }

    public static void deleteStud() {

    }

    public static void deleteCourse() {

    }

    private static void clearTable() {
        int rowCount = tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        DefaultTableModel tableM = (DefaultTableModel) table.getModel();
        tableModel.setColumnCount(0);
    }

////    public static ArrayList<Object> converter(ArrayList<WorkerStudent> list) {
////        ArrayList<Object> arrConvert = new ArrayList<>();
////        for (WorkerStudent obj : list) {
////            arrConvert.add(obj.getStuduntID());
////            arrConvert.add(obj.getStudentFirstName());
////            arrConvert.add(obj.getStudentLastName());
////        }
////        return arrConvert;
////    }
    public static ArrayList<Object> converter(WorkerStudent workerStudent) {
        ArrayList<Object> arrConvert = new ArrayList<>();
        arrConvert.add(workerStudent.getStuduntID());
        arrConvert.add(workerStudent.getStudentFirstName());
        arrConvert.add(workerStudent.getStudentLastName());
        return arrConvert;
    }

// public static ArrayList<Object> converter(Object obj){
//        ArrayList<Object> arrConvert= new ArrayList<>();
//        if(obj instanceof WorkerStudent){
//           // Object display= (WorkerStudent) obj;
//            arrConvert.add(((WorkerStudent) obj).getStuduntID());
//            arrConvert.add(((WorkerStudent) obj).getStudentFirstName());
//            arrConvert.add(((WorkerStudent) obj).getStudentLastName());
//        }
//        return arrConvert;
//    }
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
