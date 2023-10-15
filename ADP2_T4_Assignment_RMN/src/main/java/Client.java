
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

    private Font font1, font2, font3, font4;

    private static JPanel panelN, panelC, panelS, panelE, panelW, panelJ, panelP, panelL, panelA, panelStud, panelDelStud, panelDelC, panelSC, panelK, panelO;
    private static JLabel username, password, heading;
    private static JTextField usernameTxt;
    private static JPasswordField passwordTxt;
    private static JButton btnLogin, btnLogout;
    private static JComboBox cbo, cbo3;
    private static JComboBox<String> cbo1, cbo2, cbo4 = new JComboBox<>();

    private static JButton btnAddCourse, btnAddStud, btnDelete, btnSearchStud, btnSearchCourse, btnRetrieveStud, btnRetrieveCourse, btnAddSubject;
    private static JTextField searchTxtStud, searchTxtCourse;

    private static JButton btnEnrol, btnViewCourse;

    private static JLabel courseDes, courseCode, paneHeading, panelHeading2, space1, space2, space3, space6, space7;
    private static JTextField courseDesTxt, courseCodeTxt;

    private static JLabel studId, studName, studLastName, paneHeading2, paneHeading3, space0, space4, space5, space8;
    private static JTextField studIdTxt, studNameTxt, studLastNameTxt;

    private static DefaultTableModel tableModel;
    private static JTable table;
    private static JScrollPane scrollPane;

    private static JTextArea textArea1, textArea;
    private static JCheckBox checkBox = new JCheckBox();

    private static JLabel lblSubjectID1, lblSubject1, lblSubjectID2, lblSubject2, lblSubjectID3, lblSubject3, lblSubjectID4, lblSubject4;
    private static JTextField subjectIDTxt1, subjectTxt1, subjectIDTxt2, subjectTxt2, subjectIDTxt3, subjectTxt3, subjectIDTxt4, subjectTxt4;

    private static boolean log;

    private static JLabel chooseCourse;

    public Client() {

        try {
            server = new Socket("127.0.0.1", 12345);
        } catch (IOException ex) {
            System.out.println("IOException >>" + ex.getMessage());
        }

        font1 = new Font("Arial", Font.BOLD, 20);
        font2 = new Font("Arial", Font.BOLD, 16);
        font3 = new Font("Arial", Font.PLAIN, 16);
        font4 = new Font("Arial", Font.PLAIN, 14);

        panelN = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelN.setBackground(Color.LIGHT_GRAY);

        panelC = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelS = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelE = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSC = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelW = new JPanel(new GridLayout(4, 2));
        panelJ = new JPanel(new GridLayout(6, 2));
        panelK = new JPanel(new GridLayout(18, 2));
        panelO = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panelDelStud = new JPanel(new GridLayout(2, 1));
        panelDelC = new JPanel(new GridLayout(2, 2));

        panelP = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelL = new JPanel(new GridLayout(6, 1));
        panelA = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        panelStud = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //----------------------------------------------------- TextField   
        heading = new JLabel("LOGIN");
        heading.setFont(font1);
        username = new JLabel("Username: ");
        username.setFont(font2);
        password = new JLabel("Password: ");
        password.setFont(font2);

        //----------------------------------------------------- TextField 
        usernameTxt = new JTextField(20);
        usernameTxt.setFont(font3);
        passwordTxt = new JPasswordField(20);
        passwordTxt.setFont(font3);
        //----------------------------------------------------- ComboBox  

        cbo = new JComboBox(new String[]{"Admin", "Student"});
        cbo.setFont(font2);
        cbo.setPreferredSize(new Dimension(200, 35));

        cbo1 = new JComboBox(new String[]{"raeesah", "khan"});
        cbo2 = new JComboBox(new String[]{"inm", "adt"});

        cbo3 = new JComboBox(new String[]{"Filter", "Student"});
        cbo3.setFont(font2);
        cbo3.setPreferredSize(new Dimension(200, 35));
        //-----------------------------------------------------Buttons
        btnLogin = new JButton("LOGIN");
        btnLogin.setFont(font2);
        btnLogin.setPreferredSize(new Dimension(200, 40));

        btnLogout = new JButton("LOGOUT");
        btnLogout.setFont(font2);
        btnLogout.setPreferredSize(new Dimension(200, 40));

        //-----------------------------------------------------Admin  
        btnAddCourse = new JButton("Add Course");
        btnAddCourse.setFont(font4);

        btnSearchStud = new JButton("Search");
        btnSearchStud.setFont(font4);

        btnSearchCourse = new JButton("Search");
        btnSearchCourse.setFont(font4);

        btnRetrieveStud = new JButton("Retrieve Student");
        btnRetrieveStud.setFont(font4);

        btnRetrieveCourse = new JButton("Retrieve Course");
        btnRetrieveCourse.setFont(font4);

        btnDelete = new JButton("Delete Student/Course");
        btnDelete.setFont(font4);

        btnAddStud = new JButton("Add student");
        btnAddStud.setFont(font4);

        searchTxtStud = new JTextField(20);
        searchTxtCourse = new JTextField(20);
        //-----------------------------------------------------Student
        btnEnrol = new JButton("Enroll In Course");
        btnEnrol.setFont(font4);
        btnViewCourse = new JButton("View Available Courses");
        btnViewCourse.setFont(font4);

        //----------------------------------------------------- JOptionPane
        courseDes = new JLabel("Course Description: ");
        courseCode = new JLabel("Course Code: ");
        paneHeading = new JLabel("Add a Course: ");
        space1 = new JLabel();
        space2 = new JLabel();
        space3 = new JLabel();
        space7 = new JLabel();
        space8 = new JLabel();

        courseDesTxt = new JTextField(20);
        courseCodeTxt = new JTextField(20);

        //----------------------------------------------------- JOptionPane add subject panelK
        panelHeading2 = new JLabel("Add Subjects:");
        lblSubjectID1 = new JLabel("1) Subject ID: ");
        lblSubject1 = new JLabel("Subject 1: ");

        lblSubjectID2 = new JLabel("2) Subject ID: ");
        lblSubject2 = new JLabel("Subject 2: ");

        lblSubjectID3 = new JLabel("3) Subject ID: ");
        lblSubject3 = new JLabel("Subject 3: ");

        lblSubjectID4 = new JLabel("4) Subject ID: ");
        lblSubject4 = new JLabel("Subject 4: ");

        subjectIDTxt1 = new JTextField(20);
        subjectTxt1 = new JTextField(20);

        subjectIDTxt2 = new JTextField(20);
        subjectTxt2 = new JTextField(20);

        subjectIDTxt3 = new JTextField(20);
        subjectTxt3 = new JTextField(20);

        subjectIDTxt4 = new JTextField(20);
        subjectTxt4 = new JTextField(20);

        //----------------------------------------------------- JOptionPane add student
        studId = new JLabel("Student ID: ");
        studName = new JLabel("Student Name: ");
        studLastName = new JLabel("Student Last Name: ");
        paneHeading2 = new JLabel("Add Student");
        space0 = new JLabel();
        space4 = new JLabel();
        space5 = new JLabel();
        space6 = new JLabel("Course Availablity: ");

        studIdTxt = new JTextField(20);
        studNameTxt = new JTextField(20);
        studLastNameTxt = new JTextField(20);

        //-------------------------------------------- table
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        textArea = new JTextArea(5, 10);
        textArea1 = new JTextArea(5, 10);

        //-------------------------------------------- enrol cbobox
        chooseCourse = new JLabel("Choose a course: ");
        chooseCourse.setFont(font2);
        cbo4 = new JComboBox<>();
        cbo4.setFont(font2);
        cbo4.setPreferredSize(new Dimension(200, 35));

    }

    public void setGui() {

        panelN.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelC.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelW.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelS.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        table.setPreferredScrollableViewportSize(new Dimension(600, 120));
        panelN.add(heading);

        panelP.add(scrollPane);

        panelL.add(cbo);
        panelL.add(space6);
        panelL.add(username);
        panelL.add(usernameTxt);
        panelL.add(password);
        panelL.add(passwordTxt);

        panelA.add(btnAddCourse);
        panelA.add(btnAddStud);
        panelA.add(btnRetrieveStud);
        panelA.add(btnRetrieveCourse);
        panelA.add(btnDelete);

        panelE.add(searchTxtStud);
        panelE.add(btnSearchStud);

        panelSC.add(searchTxtCourse);
        panelSC.add(btnSearchCourse);

        panelStud.add(btnEnrol);
        panelStud.add(btnViewCourse);

        panelC.add(panelL);
        panelC.add(panelA);
        panelC.add(panelE);
        panelC.add(panelSC);
        panelC.add(panelStud);
        panelC.add(panelO);
        panelC.add(panelP);
        panelC.add(panelK);

        // panelS.add(new JScrollPane(table));
        panelS.add(btnLogin);
        panelS.add(btnLogout);

        //----------------------------- Add a course JOptionPane
        panelJ.add(paneHeading);
        panelJ.add(space1);
        panelJ.add(courseCode);
        panelJ.add(courseCodeTxt);
        panelJ.add(courseDes);
        panelJ.add(courseDesTxt);
        panelJ.add(space6);
        panelJ.add(checkBox);

        panelK.add(panelHeading2);
        panelK.add(space7);
        panelK.add(lblSubjectID1);
        panelK.add(subjectIDTxt1);
        panelK.add(lblSubject1);
        panelK.add(subjectTxt1);

        panelK.add(lblSubjectID2);
        panelK.add(subjectIDTxt2);
        panelK.add(lblSubject2);
        panelK.add(subjectTxt2);

        panelK.add(lblSubjectID3);
        panelK.add(subjectIDTxt3);
        panelK.add(lblSubject3);
        panelK.add(subjectTxt3);

        panelK.add(lblSubjectID4);
        panelK.add(subjectIDTxt4);
        panelK.add(lblSubject4);
        panelK.add(subjectTxt4);

        panelJ.setVisible(false);
        panelK.setVisible(false);
        //----------------------------- Add a Student JOptionPane
        panelW.add(paneHeading2);
        panelW.add(space0);
        panelW.add(studId);
        panelW.add(studIdTxt);
        panelW.add(studName);
        panelW.add(studNameTxt);
        panelW.add(studLastName);
        panelW.add(studLastNameTxt);

        panelW.setVisible(false);
        //----------------------------- choose a course JOptionPane
        panelO.add(chooseCourse);
        //panelO.add(space8);
        panelO.add(cbo4);

        panelO.setVisible(false);

        panelDelStud.add(cbo1);
        panelDelStud.add(textArea);

        panelDelC.add(cbo2);
        panelDelC.add(textArea1);

        add(panelN, BorderLayout.NORTH);
        add(panelC, BorderLayout.CENTER);
        add(panelS, BorderLayout.SOUTH);
        add(panelW, BorderLayout.WEST);
        add(panelJ, BorderLayout.EAST);

        panelE.setVisible(false);
        panelSC.setVisible(false);
        panelP.setVisible(false);
        panelA.setVisible(false);
        panelStud.setVisible(false);

        btnLogout.setVisible(false);

        String combo = cbo1.getSelectedItem().toString();
        textArea.setText(combo);

        //-------------------------------------------ActionListeners
//        searchTxt.addFocusListener(new FocusListener(){
//            @Override
//            public void focusGained(FocusEvent e) {
//                searchTxt.setText("");
//                
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//              searchTxt.setText("Search by Student ID");
//              
//            }
//
//        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnLogin) {
                    String user = usernameTxt.getText(); // Get the username from your UI input field
                    String password = String.valueOf(passwordTxt.getPassword()); // Get the password from your UI input field
                    String userAccessType = cbo.getSelectedItem().toString(); // Get the user access type from your UI combo box

                    // Call the authenticationLogin method from the Client class
                    //  Client.authenticationLogin(user, password, userAccessType);
                    if (cbo.getSelectedItem() == "Student") {

                        authenticationLogin(user, password, userAccessType);
                        if (log == true) {
                            retrieveCourse();
                            heading.setText("Student Enrollment system");

                            panelStud.setVisible(true);
                            panelL.setVisible(false);
                            panelP.setVisible(true);
                            btnLogin.setVisible(false);
                            btnLogout.setVisible(true);
                        }
                    } else {

                        authenticationLogin(user, password, userAccessType);
                        if (log == true) {
                            heading.setText("Admin Access");

                            panelA.setVisible(true);
                            panelL.setVisible(false);

                            panelE.setVisible(false);
                            btnLogin.setVisible(false);
                            btnLogout.setVisible(true);
                        }
                    }

                }
            }
        }
        );

        btnEnrol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (e.getSource() == btnEnrol) {
                    panelO.setVisible(true);
                    clearTable();
                    populateCbo4();

//                    int result = JOptionPane.showOptionDialog(null, panelO,
//                            "Add a Course",
//                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
//                            new String[]{"View", "Cancel"}, "Yes");
//                    if (result == JOptionPane.YES_OPTION) {
//
//                        panelO.setVisible(false);
//                    }
                }
            }

        }
        );
        btnAddCourse.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (e.getSource() == btnAddCourse) {

                    panelJ.setVisible(true);

                    int result = JOptionPane.showOptionDialog(null, panelJ,
                            "Add a Course",
                            JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                            new String[]{"Add", "Cancel"}, "Yes");

                    if (result == JOptionPane.YES_OPTION) {

                        if (courseCodeTxt.getText().isEmpty() || courseDesTxt.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid Course.");
                            courseCodeTxt.setText("");
                            courseDesTxt.setText("");
                            courseCodeTxt.requestFocus();
                            return;
                        }
                        AddCourse();
                        panelK.setVisible(true);
                        int result2 = JOptionPane.showOptionDialog(null, panelK,
                                "Add a Subject",
                                JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                                new String[]{"save"}, "Yes");

                        if (result2 == JOptionPane.YES_OPTION) {
                            AddSubject();

                        }

                    }
                }

            }

        }
        );

        btnLogout.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (e.getSource() == btnLogout) {

                    closeConnection();
                }
            }

        }
        );

        btnAddStud.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (e.getSource() == btnAddStud) {

                    panelW.setVisible(true);
                    int result = JOptionPane.showOptionDialog(null, panelW,
                            "Add a Student",
                            JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                            new String[]{"Add", "Cancel"}, "Yes");
                    if (result == JOptionPane.YES_OPTION) {
                        if (studIdTxt.getText().isEmpty() || studNameTxt.getText().isEmpty() || studLastNameTxt.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid Student.");
                            studIdTxt.setText("");
                            studNameTxt.setText("");
                            studLastNameTxt.setText("");
                            studIdTxt.requestFocus();
                            return;
                        }
                        AddStud();
                        studIdTxt.setText("");
                        studNameTxt.setText("");
                        studLastNameTxt.setText("");
                        studIdTxt.requestFocus();
                        panelW.setVisible(false);
                    }

                }

            }

        }
        );

        btnDelete.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (e.getSource() == btnDelete) {
                    int result = JOptionPane.showOptionDialog(null, "Choose which catagory that you would like to delete from. ",
                            "Add a Course",
                            JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                            new String[]{"Student", "Course"}, "Yes");
                    if (result == JOptionPane.YES_OPTION) {
                        //------------------ Delete student
                        int result1 = JOptionPane.showOptionDialog(null, panelDelStud,
                                "Delete Student",
                                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                                new String[]{"Delete", "Cancel"}, "Yes");

                        if (result1 == JOptionPane.YES_OPTION) {
                            int answer1 = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this student? ");
                            if (answer1 == JOptionPane.YES_OPTION) {

                                deleteStud();
                                System.out.println("Student deleted");
                            }

                        }

                    } else {
                        //------------------ Delete Course
                        int result1 = JOptionPane.showOptionDialog(null, panelDelC,
                                "Delete Course",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                new String[]{"Delete", "Cancel"}, "Yes");

                        String combo = cbo2.getSelectedItem().toString();
                        textArea1.append(combo);

                        if (result1 == JOptionPane.YES_OPTION) {
                            int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this course? ");
                            if (answer == JOptionPane.YES_OPTION) {

                                deleteCourse();
                                System.out.println("Course deleted");
                            }

                        }

                    }

                }
            }

        }
        );

        btnRetrieveStud.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (e.getSource() == btnRetrieveStud) {

                    searchTxtStud.setText("student");
                    panelE.setVisible(true);
                    panelSC.setVisible(false);
                    panelP.setVisible(true);
                    retrieveStud();
                }

            }

        }
        );

        btnRetrieveCourse.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (e.getSource() == btnRetrieveCourse) {

                    searchTxtCourse.setText("course");
                    panelSC.setVisible(true);
                    panelE.setVisible(false);
                    panelP.setVisible(true);
                    retrieveCourse();
                }

            }

        }
        );

        btnSearchStud.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (e.getSource() == btnSearchStud) {
                    searchStud();
                    searchTxtStud.setText("");
                }

            }

        }
        );
        btnSearchCourse.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (e.getSource() == btnSearchCourse) {
                    searchCourse();
                    searchTxtCourse.setText("");
                }

            }

        }
        );

        btnViewCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnViewCourse) {
                    btnViewCourse.setBackground(Color.GREEN);
                    panelO.setVisible(false);
                    retrieveAvailableCourse();
                }
            }

        }
        );

        cbo4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == cbo4) {
                    chosenCourse();
                }
            }

        }
        );

    }

    public void enrolStud() {

    }

    public void chosenCourse() {
//
//        String send = "chosen course";
//         String chosenC = cbo4.getSelectedItem().toString();
        try {

             String chosenC = cbo4.getSelectedItem().toString();
            out.writeObject("chosen course");
            out.flush();
            out.writeObject(chosenC);
            out.flush();

// Read the response
            String receivedMsg = (String) in.readObject();
            if (receivedMsg.equals("received")) {
                ArrayList<WorkerSubject> display = (ArrayList<WorkerSubject>) in.readObject();
                // Process the received data
                for (int i = 0; i < display.size(); i++) {
                    WorkerSubject workerSubject = display.get(i);
                    ArrayList<Object> arrCon = converter4(workerSubject);
                    Object[] arrConArray = arrCon.toArray();
                    tableModel.addRow(arrConArray);
                    System.out.println(arrConArray.toString());
                }
            }

//
//            out.writeObject(send);
//            out.flush();
//            out.writeObject(chosenC);
//            out.flush();
//
////            
//            ArrayList<WorkerSubject> display = (ArrayList<WorkerSubject>) in.readObject();
//            for (int i = 0; i < display.size(); i++) {
//                WorkerSubject workerSubject = display.get(i);
//
//                ArrayList<Object> arrCon = converter4(workerSubject);
//                Object[] arrConArray = arrCon.toArray();
//
//                tableModel.addRow(arrConArray);
//                System.out.println(arrConArray.toString());
//
//            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void populateCbo4() {

        try {
            out.writeObject("populate");
            out.flush();

            ArrayList<String> populate = (ArrayList) in.readObject();
            System.out.println(populate);
            for (String pop : populate) {
                cbo4.addItem(pop);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error getting in the courses you have requested");
        } catch (ClassNotFoundException ex) {
           JOptionPane.showMessageDialog(null,"error finding class");
        }

    }

    public void AddStud() {
        String id = studIdTxt.getText();
        String name = studNameTxt.getText();
        String lastN = studLastNameTxt.getText();

        try {
            WorkerStudent add = new WorkerStudent(Integer.parseInt(id), name, lastN);
            out.writeObject(add);
            out.flush();

            String recievedMsg = (String) in.readObject();
            if (recievedMsg.equalsIgnoreCase("success")) {
                JOptionPane.showMessageDialog(null, "Student has been added.");
            }
            if (recievedMsg.equalsIgnoreCase("failed")) {
                JOptionPane.showMessageDialog(null, "Student has already been added.");
            }

        } catch (IOException ex) {
            System.out.println("IOException" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + ex.getMessage());
        }

    }

    public void AddSubject() {

        String subID1 = subjectIDTxt1.getText();
        String subName1 = subjectTxt1.getText();
        String subID2 = subjectIDTxt2.getText();
        String subName2 = subjectTxt2.getText();
        String subID3 = subjectIDTxt3.getText();
        String subName3 = subjectTxt3.getText();
        String subID4 = subjectIDTxt4.getText();
        String subName4 = subjectTxt4.getText();
        String subCourse = courseCodeTxt.getText();

        try {
            WorkerSubject add = new WorkerSubject(subID1, subName1, subID2, subName2, subID3, subName3, subID4, subName4, subCourse);
            System.out.println(add);
            out.writeObject(add);
            out.flush();

            String recievedMsg = (String) in.readObject();
            if (recievedMsg.equalsIgnoreCase("success")) {
                JOptionPane.showMessageDialog(null, "Subject has been added.");
                subjectIDTxt1.setText("");
                subjectTxt1.setText("");
                subjectIDTxt2.setText("");
                subjectTxt2.setText("");
                subjectIDTxt3.setText("");
                subjectTxt3.setText("");
                subjectIDTxt4.setText("");
                subjectTxt4.setText("");
                subjectIDTxt1.requestFocus();

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
        boolean available = checkBox.isSelected();

        try {
            WorkerCourse add = new WorkerCourse(code, description, available);
            out.writeObject(add);
            out.flush();

            String recievedMsg = (String) in.readObject();
            if (recievedMsg.equalsIgnoreCase("success")) {
                JOptionPane.showMessageDialog(null, "Course has been added.");
                courseDesTxt.setText("");
                checkBox.setSelected(false);
            }
//            if (recievedMsg.equalsIgnoreCase("failed")) {
//
//                JOptionPane.showMessageDialog(null, "This course has already been added.");
//            }

        } catch (IOException ex) {
            System.out.println("IOException" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + ex.getMessage());
        }

    }

    //test worked out
    public static void authenticationLogin(String username, String password, String userAccessType) {
        try {

            WorkerLogin login = new WorkerLogin(username, password, userAccessType);
            out.writeObject(login);
            out.flush();

            String receivedMsg = (String) in.readObject();

            if (receivedMsg.equalsIgnoreCase("success")) {
                log = true;
                JOptionPane.showMessageDialog(null, "Login successful.");
            }

            if (receivedMsg.equalsIgnoreCase("failure")) {
                log = false;
                JOptionPane.showMessageDialog(null, "Login Failed. Incorrect username or password.");
                usernameTxt.setText("");
                passwordTxt.setText("");
                usernameTxt.requestFocus();

            }

        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException: " + ex.getMessage());
        }
    }

    public static void retrieveStud() {
        clearTable();
        tableModel.addColumn("Student ID");
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

        }
    }

    public static void retrieveCourse() {
        clearTable();
        tableModel.addColumn("Course Code");
        tableModel.addColumn("Course Description");
        tableModel.addColumn("Availability");

        try {
            out.writeObject("retrieve all courses");
            out.flush();

            ArrayList<WorkerCourse> display = (ArrayList<WorkerCourse>) in.readObject();
            for (int i = 0; i < display.size(); i++) {
                WorkerCourse workerCourse = display.get(i);

                ArrayList<Object> arrCon = converter2(workerCourse);
                Object[] arrConArray = arrCon.toArray();

                tableModel.addRow(arrConArray);

            }

        } catch (IOException ex) {
            System.out.println("IOException" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + ex.getMessage());
        }
    }

    public static void searchStud() {
        clearTable();
        tableModel.addColumn("Student ID");
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");

        int search = Integer.parseInt(searchTxtStud.getText());

        try {
            out.writeObject(search);
            out.flush();

            ArrayList<WorkerStudent> display = (ArrayList<WorkerStudent>) in.readObject();

            for (int i = 0; i < display.size(); i++) {
                WorkerStudent workerStudent = display.get(i);

                ArrayList<Object> arrCon = converter(workerStudent);
                Object[] arrConArray = arrCon.toArray();

                tableModel.addRow(arrConArray);
                System.out.println(arrConArray.toString());
            }

        } catch (IOException ex) {
            System.out.println("Error in IO");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error in ClassNotFound");
        }

    }

    public static void searchCourse() {
        clearTable();
        tableModel.addColumn("Course Code");
        tableModel.addColumn("Course Description");
        tableModel.addColumn("Availability");

        String search = searchTxtCourse.getText();

        try {

            out.writeObject(search);
            out.flush();
            System.out.println("called the method");
            ArrayList<WorkerCourse> displayCourse = (ArrayList<WorkerCourse>) in.readObject();

            for (int j = 0; j < displayCourse.size(); j++) {
                WorkerCourse workerCourse = displayCourse.get(j);
                ArrayList<Object> arrCon2 = converter2(workerCourse);
                Object[] arrCon2Obj = arrCon2.toArray();

                tableModel.addRow(arrCon2Obj);
                System.out.println(arrCon2Obj.toString());
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void retrieveAvailableCourse() {
        clearTable();
        tableModel.addColumn("Course Code");
        tableModel.addColumn("Course Description");
        tableModel.addColumn("Availability");

        try {
            out.writeObject("Available");
            out.flush();

            ArrayList<WorkerCourse> display = (ArrayList<WorkerCourse>) in.readObject();
            for (int i = 0; i < display.size(); i++) {
                WorkerCourse workerCourse = display.get(i);

                ArrayList<Object> arrCon = converter2(workerCourse);
                Object[] arrConArray = arrCon.toArray();

                tableModel.addRow(arrConArray);

            }

        } catch (IOException ex) {
            System.out.println("IOException" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + ex.getMessage());
        }
    }

    public static void deleteStud() {
//        String combo = cbo1.getSelectedItem().toString();
//        textArea.append(combo);
    }

    public static void deleteCourse() {
        String combo = cbo2.getSelectedItem().toString();
        textArea1.append(combo);
    }

    private static void clearTable() {
        int rowCount = tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        DefaultTableModel tableM = (DefaultTableModel) table.getModel();
        tableModel.setColumnCount(0);
    }

    public static ArrayList<Object> converter(WorkerStudent workerStudent) {
        ArrayList<Object> arrConvert = new ArrayList<>();
        arrConvert.add(workerStudent.getStuduntID());
        arrConvert.add(workerStudent.getStudentFirstName());
        arrConvert.add(workerStudent.getStudentLastName());
        return arrConvert;
    }

    public static ArrayList<Object> converter3(WorkerStudent workerStudent) {
        ArrayList<Object> arrConvert = new ArrayList<>();
        arrConvert.add(workerStudent.getStuduntID());
        arrConvert.add(workerStudent.getStudentFirstName());
        arrConvert.add(workerStudent.getStudentLastName());
        return arrConvert;
    }

    public static ArrayList<Object> converter2(WorkerCourse workerCourse) {
        ArrayList<Object> arrConvert = new ArrayList<>();
        arrConvert.add(workerCourse.getCourseCode());
        arrConvert.add(workerCourse.getCourseDescription());
        arrConvert.add(workerCourse.isAvailable());
        return arrConvert;
    }

    public static ArrayList<Object> converter4(WorkerSubject workerSubject) {
        ArrayList<Object> arrConvert = new ArrayList<>();
        arrConvert.add(workerSubject.getSubjectID1());
        arrConvert.add(workerSubject.getSubjectName1());
        arrConvert.add(workerSubject.getCourseID());
        return arrConvert;
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
            out.writeObject("Exit");
            out.close();
            in.close();
            server.close();
            System.out.println("Server has closed");
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("Server cannot close. Error " + ex.getMessage());
        }

    }

    public static void main(String[] args) {
        Client log = new Client();
        log.getStreams();
        log.setTitle("Enrolment System");
        log.setSize(730, 480);
        log.setGui();
        log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        log.setLocationRelativeTo(null);
        log.setVisible(true);
    }
}
