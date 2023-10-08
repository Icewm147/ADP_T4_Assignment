
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.*;

/**
 *
 * @authors -Nicholas van der Nest (222749180) -Raeesah Khan (219) -Mogammad
 * Mas'ood Lamera (221376321)
 */
public class Server {

    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static Object receivedObject;
    private static DAO dao;

    public Server() {
        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Server is listening....");
            clientSocket = serverSocket.accept();
            System.out.println("Connected to client>>>");
        } catch (IOException ex) {
            System.out.println("Server cannot listen. Error: " + ex.getMessage());
        }

        dao = new DAO();
    }

    public void getStreams() {
        try {
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush();

        } catch (IOException ex) {
            System.out.println("Cannot get Streams " + ex.getMessage());
        }
    }

    /*in process client,
    ----Authenticate user login
    (Under admin)
    ----add student       Working
    ----retrieve all student 
    ----search student    
    ----add course        Working
    ----retrieve all course
    (Under Student)
    search course
    *Enroll for course?
    //(accept enrollment requests from client(students), storing enrollment data in the Derby DB, Retrieving enrollment data from the DB, Authentication of admin and student users)
     */
    //added code up above
    public void processClient() {
        while (true) {
            try {
                receivedObject = in.readObject();

                //Authentication
                if (receivedObject instanceof WorkerLogin) {
//                    String userAuth = (String) receivedObject;                    
//                    dao.adminAuthentication();
//                    for (WorkerLogin worker : /*DB */ ) {
//                        if (worker.getUsername().equalsIgnoreCase(/*DB */) && (worker.getPassword().equalsIgnoreCase(/*DB */))      {
//                            out.writeObject("Success");
//                            out.flush();
//                        } else {
//                            out.writeObject("Login Failed");
//                            out.flush();
//                        }
//                    }
                    //add student DONE
                } else if (receivedObject instanceof WorkerStudent) {
                    WorkerStudent stud = (WorkerStudent) receivedObject;
                    try {
                        ArrayList<WorkerStudent> student = (ArrayList) dao.getStudentInfo();
                        for (WorkerStudent students : student) {
                            if (students.getStuduntID().equals(stud)) {
                                System.out.println("false");
                            } else {
                                System.out.println("true");
                                dao.addStudentToDB(stud);
                                System.out.println(stud);
                                out.writeObject("success");
                                out.flush();

                            }
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    } 
//                    catch (SQLException ex) {
//                        JOptionPane.showMessageDialog(null, "Student Already Added");
//                    }

                    //retreiving all students
                } else if (receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("retrieve student")) {
                    try {
                        List<WorkerStudent> studentList = dao.getStudentInfo();
                        Object obj = studentList;
                        System.out.println(studentList.toString());
                        out.writeObject(obj);
                        out.flush();
                    } catch (SQLException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } //retrieve all courses
                else if (receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("retrieve all courses")) {
                    try {
                        List<WorkerCourse> courseList = dao.getCourseInfo();
                        Object obj = courseList;
                        System.out.println(courseList.toString());
                        out.writeObject(obj);
                        out.flush();
                    } catch (SQLException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } //Search student 
                else if (receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("search")) {
                    String studID = (String) receivedObject;
                    try {
                        Object search = dao.getStudentInfo();

//                    if(studID.equalsIgnoreCase(objSearch)){
//                       Object with student parameters to contain info on that student
//                       out.writeObject(Objectname);
//                    out.flush();
//                    }
//                    else{
//                    out.writeObject("The Student you are searching for does not exist");
//                    out.flush();                    
//                    }
                    } catch (SQLException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    String name = JOptionPane.showInputDialog("Please enter your name");
                    System.out.println(name);
                    //add course DONE
                } else if (receivedObject instanceof WorkerCourse) {
                    WorkerCourse course = (WorkerCourse) receivedObject;
                    try {
                        dao.addCourseToDB(course);
                        out.writeObject("Success");
                        System.out.println(course);
                        out.flush();
                    } catch (SQLException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.writeObject("Course Added successfully");
                    out.flush();
                } //Search for course
                else if (receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("Search")) {
                    String course = (String) receivedObject;

                    //DB Search method: searchCourse(course);
                } //delete course
                else if (receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("Delete course")) {
                    int deleteCourse = (int) receivedObject;
                    try {
                        dao.deleteCourseFromDB(deleteCourse);
                        out.writeObject(deleteCourse + " Has been deleted");
                        out.flush();
                    } catch (SQLException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } //Delete student
                else if (receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("Delete student")) {
                    int delStudent = (int) receivedObject;
                    try {
                        dao.deleteStudent(delStudent);
                        out.writeObject(delStudent + " Has been deleted");
                        out.flush();
                    } catch (SQLException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                else if(receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("Exit"))
                {
                    closeConnection();
                }

            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private static void closeConnection() {
        try {
            out.writeObject("Server has closed");
            out.flush();
            out.close();
            in.close();
            clientSocket.close();
            System.out.println("Server has closed");
            System.exit(0);

        } catch (IOException ex) {
            System.out.println("Server cannot close. Error " + ex.getMessage());

        }
    }

    public static void main(String[] args) {
        Server srs = new Server();
        srs.getStreams();
        srs.processClient();

    }

}
