
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
            serverSocket = new ServerSocket(12345, 2);
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
                //test
                receivedObject = in.readObject();

                if (receivedObject instanceof WorkerLogin) {
                    WorkerLogin login = (WorkerLogin) receivedObject;
                    String username = login.getUsername();
                    String password = login.getPassword();
                    String accessType = login.getUserAccessType();

                    if (dao.authenticateUser(username, password, accessType)) {
                        out.writeObject("success");
                    } else {
                        out.writeObject("failure");
                    }
                    out.flush();

                } //add student DONE
                else if (receivedObject instanceof WorkerStudent) {
                    WorkerStudent stud = (WorkerStudent) receivedObject;
                    try {
                        ArrayList<WorkerStudent> student = (ArrayList) dao.getStudentInfo();
                        for (WorkerStudent students : student) {
                            dao.addStudentToDB(stud);
                            System.out.println(stud);
                            out.writeObject("success");
                            out.flush();
                        }
                    } catch (SQLException ex) {
                        out.writeObject("failed");
                        out.flush();
                    }

//                    //retreiving all students DONE
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

                } //enrol student
                else if(receivedObject instanceof String && ((String)receivedObject).equalsIgnoreCase("enrol")){
                    
                    
                }
                //retrieve all courses DONE
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

                } //retrieve Available courses DONE
                else if (receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("Available")) {
                    try {
                        List<WorkerCourse> courseList = dao.getAvailableCourses();
                        Object obj = courseList;
                        System.out.println(courseList.toString());
                        out.writeObject(obj);
                        out.flush();
                    } catch (SQLException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //add subject to subject_table done
                } else if (receivedObject instanceof WorkerSubject) {
                    WorkerSubject subject = (WorkerSubject) receivedObject;
                    try {
                        WorkerSubject sub1 = new WorkerSubject(subject.getSubjectID1(), subject.getSubjectName1(), subject.getCourseID());
                        dao.addSubject(sub1);
                        WorkerSubject sub2 = new WorkerSubject(subject.getSubjectID2(), subject.getSubjectName2(), subject.getCourseID());
                        dao.addSubject(sub2);
                        WorkerSubject sub3 = new WorkerSubject(subject.getSubjectID3(), subject.getSubjectName3(), subject.getCourseID());
                        dao.addSubject(sub3);
                        WorkerSubject sub4 = new WorkerSubject(subject.getSubjectID4(), subject.getSubjectName4(), subject.getCourseID());
                        dao.addSubject(sub4);
                        out.writeObject("Success");
                        out.flush();
                    } catch (SQLException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }//populate combobox for enrol DONE
                else if(receivedObject instanceof String && ((String)receivedObject).equalsIgnoreCase("populate")){
                    try {

                        List<String> courseCodeList = dao.getCourseCode();
                        System.out.println(courseCodeList);
                        out.writeObject(courseCodeList);
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
                        out.flush();
                    } catch (SQLException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                    
                }
                else if(receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("chosen course")){
                    try {
                        out.writeObject("received");
                        out.flush();
                        List<WorkerSubject> subjectCourse = dao.getAllSubjects();
                        String courseChosen = (String)in.readObject();
                        for(WorkerSubject subject : subjectCourse){
                            if(subject.getCourseID() == courseChosen){
                                subjectCourse.add(subject);
                                out.writeObject(subjectCourse);
                                out.flush();
                            }
                        }
                    out.flush();
                    
                    } catch (SQLException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                    
                }
                else if (receivedObject instanceof WorkerCourse) {
                    WorkerCourse course = (WorkerCourse) receivedObject;
                    try {
                        dao.addCourseToDB(course);
                        out.writeObject("Success");
                        System.out.println(course);
                        out.flush();
                    } catch (SQLException ex) {
                        out.writeObject("failed");
                        out.flush();
                    }
//                    out.writeObject("Course Added successfully");
//                    out.flush();
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

                } else if (receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("Exit")) {
                    closeConnection();
                    break;
                } //search course
                else if (receivedObject instanceof String) {
                    String search = (String) receivedObject;
                    List<WorkerCourse> searchC = new ArrayList<>();
                    try {
                        List<WorkerCourse> searchCourse = dao.getCourseInfo();
                        System.out.println("methods ran");
                        for (WorkerCourse course : searchCourse) {
                            if (course.getCourseCode().equalsIgnoreCase(search)) {
                                System.out.println("yes");
                                searchC.add(course);
                            } else {
                                System.out.println("no");
                            }
                        }
                        out.writeObject(searchC);
                        out.flush();
                        System.out.println("Course search" + searchC);
                    } catch (SQLException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } //search student
                else if (receivedObject instanceof Integer) {

                    List<WorkerStudent> searched = new ArrayList<>();
                    //List<WorkerCourse> searchC = new ArrayList<>();
                    try {
                        List<WorkerStudent> searchStudent = dao.getStudentInfo();
                        //   List<WorkerCourse> searchCourse = dao.getAllCourses();
                        int search = (Integer) receivedObject;
                        for (WorkerStudent student : searchStudent) {
                            if (student.getStuduntID() == search) {
                                searched.add(student);
                                out.writeObject(searched);
                                out.flush();
                                System.out.println("Student search: " + searched);

                            }
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
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
