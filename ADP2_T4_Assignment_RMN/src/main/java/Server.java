
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    //added code up above
    public void processClient() {
        while (true) {
            try {

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
                        dao.addStudentToDB(stud);
                        System.out.println(stud);
                        out.writeObject("success");
                        out.flush();

                    } catch (SQLException ex) {
                        out.writeObject("failed");
                        out.flush();
                    }

//                    //retreiving all students DONE
                } else if (receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("retrieve student")) {
                    try {
                        ArrayList<WorkerStudent> studentList = (ArrayList) dao.getStudentInfo();
                        Object obj = studentList;
                        System.out.println(studentList.toString());
                        out.writeObject(obj);
                        out.flush();
                    } catch (SQLException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } //student_Course table
                else if (receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("retrieve student_course")) {
                    out.writeObject("request received");
                    out.flush();
                    try {
                        boolean receive = (boolean) in.readObject();
                        if (receive == true) {
                            ArrayList<WorkerStudent> studentCourseList = (ArrayList) dao.studentsPerCourse();
                            Object obj = studentCourseList;
                            System.out.println(studentCourseList.toString());
                            out.writeObject(obj);
                            System.out.println("moo" + obj);
                            out.flush();
                        } else {
                            System.out.println("False vibes");
                        }
                    } catch (SQLException ex) {
                        System.out.println("cannot retrieve enrolled students");
                    }

                } //enrol student
                else if (receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("Enrolled")) {
                    out.writeObject("request received");
                    out.flush();
                    WorkerStudent studID = (WorkerStudent) in.readObject();
                    System.out.println("enrolled: " + studID);
                    dao.enrollStudent(studID.getStuduntID(), studID.getCourseCode());
                    out.writeObject("success");
                    out.flush();

                } //deregister
                else if (receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("deregister")) {
                    out.writeObject("request received");
                    out.flush();
                    int studID = (Integer) in.readObject();
                    dao.deregisterStudent(studID);
                    out.writeObject("success");
                    out.flush();
                    System.out.println(studID);

                } //retrieve all courses DONE
                else if (receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("retrieve all courses")) {
                    try {
                        ArrayList<WorkerCourse> courseList = (ArrayList) dao.getCourseInfo();
                        Object obj = courseList;
                        System.out.println(courseList.toString());
                        out.writeObject(obj);
                        out.flush();
                    } catch (SQLException ex) {
                        System.out.println("cannot retrieve all courses");
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
                        System.out.println("cannot retrieve available courses");
                    }
                } //add subject to subject_table done               
                //populate combobox for enrol DONE
                else if (receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("populate")) {
                    try {

                        List<String> courseCodeList = dao.getCourseCode();
                        System.out.println(courseCodeList);
                        out.writeObject(courseCodeList);
                        out.flush();
                    } catch (SQLException ex) {
                        System.out.println("Error populating");
                    }

                } //add course to db
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

                } //delete course
                else if (receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("Delete course")) {
                    out.writeObject("request received");
                    out.flush();
                    int deleteCourse = (int) in.readObject(); //course Code
                    try {
                        dao.deleteCourseFromDB(deleteCourse);
                        System.out.println(deleteCourse);
//                        out.writeObject("success");
//                        out.flush();
                    } catch (SQLException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } //Delete student
                else if (receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("Delete student")) {
                    out.writeObject("request received");
                    out.flush();
                    int delStudent = (int) in.readObject();
                    try {
                        dao.deleteStudent(delStudent);
                        System.out.println(delStudent);

                    } catch (SQLException ex) {
                        System.out.println("cannot delete student");
                    }

                } //exit program
                else if (receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("Exit")) {
                    closeConnection();
                    break;
                    //disply chosen course
                } else if (receivedObject instanceof String && ((String) receivedObject).equalsIgnoreCase("chosen course")) {
                    try {
                        String rec = (String) receivedObject;
                        out.writeObject("received");
                        out.flush();
                        String courseChosen = (String) in.readObject();

                        List<WorkerSubject> subjectCourse = dao.getAllSubjects();

                        for (WorkerSubject subject : subjectCourse) {
                            if (subject.getCourseID().equalsIgnoreCase(courseChosen)) {
                                out.writeObject(subject.toString2());
                                out.flush();
                                System.out.println(subject.toString2());
                            }
                        }
                        out.writeObject("No more content");
                        out.flush();

                    } catch (SQLException ex) {
                        System.out.println("cannot display chosen course");
                    }
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
                        System.out.println("cannot search course");
                    }

                } //search student
                else if (receivedObject instanceof Integer) {

                    List<WorkerStudent> searched = new ArrayList<>();
                    try {
                        List<WorkerStudent> searchStudent = dao.getStudentInfo();
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
                        System.out.println("cannot search student");
                    }
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
                        System.out.println("cannot add subjects");
                    }

                }

            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            } catch (ClassNotFoundException ex) {
                System.out.println("Error: " + ex);
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
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
