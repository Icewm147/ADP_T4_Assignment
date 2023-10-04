

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @authors -Nicholas van der Nest (222749180) -Raeesah Khan (219) -Mogammad
 * Mas'ood Lamera (221376321)
 */
public class Server {

    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static Object receivedObject;

    public Server() {
        try {
            serverSocket = new ServerSocket(12345);
            System.out.println("Server is listening....");
            clientSocket = serverSocket.accept();
            System.out.println("Connected to client>>>");
        } catch (IOException ex) {
            System.out.println("Server cannot listen. Error: " + ex.getMessage());
        }
    }

    public void getStreams() {
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Cannot get Streams " + ex.getMessage());
        }
    }

    /*in process client,
    ----Authenticate user login
    (Under admin)
    ----add student
    ----retrieve all student 
    ----search student    
    ----add course
    retrieve all course
    (Under Student)
    search course
    *Enroll for course?
    //(accept enrollment requests from client(students), storing enrollment data in the Derby DB, Retrieving enrollment data from the DB, Authentication of admin and student users)
     */

    
    //added code up above
//    public void processClient() {
//        while (true) {
//            try {
//                receivedObject = in.readObject();
//
//                //Authentication
//                if (receivedObject instanceof WorkerLogin) {
//                    String userSearch = (String) receivedObject;
//                    for (WorkerLogin worker : /*DB */ ) {
//                        if (worker.getUsername().equalsIgnoreCase(/*DB */) && (worker.getPassword().equalsIgnoreCase(/*DB */)) {
//                            out.writeObject("Success");
//                            out.flush();
//                        } else {
//                            out.writeObject("Login Failed");
//                            out.flush();
//                        }
//                    }
//
//                    //Add Student
//                } else if (receivedObject instanceof WorkerStudent) {
//                    WorkerStudent stud = (WorkerStudent) receivedObject;
//                    //addStudentToDB(stud); <---------------------------------------------------------------check pls
//                    out.writeObject("Student Added successfully");
//                    out.flush();
//                    
//                    //retreiving all student
//                }else if(receivedObject instanceof String  && ((String)receivedObject).equalsIgnoreCase("retrieve all students")){
//                    //call Search Method
//                    //StudentDB 'allStudent' object containing StudentTable content                   
//                    out.writeObject(allStudents);
//                    out.flush();
//                    
//                   //Search student 
//                }else if(receivedObject instanceof String  && ((String)receivedObject).equalsIgnoreCase("search")){
//                    String studID = (String) receivedObject; 
//                    /*if("studID is in the db"){
//                       Object with student parameters to contain info on that student
//                       out.writeObject(Objectname);
//                    out.flush();
//                    }
//                    else{
//                    out.writeObject("The Student you are searching for does not exist");
//                    out.flush();                    
//                    }
//                    */
//                    String name = JOptionPane.showInputDialog("Please enter your name");
//                    System.out.println(name);
//                    //add course
//                }else if (receivedObject instanceof WorkerCourse) {
//                    WorkerCourse course = (WorkerCourse) receivedObject;
//                    //addCourseToDB(course);
//                    out.writeObject("Course Added successfully");
//                    out.flush();
//                    
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        }
//
//    }

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
      //  srs.processClient();

    }

}
