package za.ac.cput.adp2_t4_assignment_rmn;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.ac.cput.adp2_t4_assignment_rmn.WorkerLogin.WorkerLogin;

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
    add student
    search student
    retrieve student
    add course
    retrieve course
    (Under Student)
    search course
    
    
    */
    
    public void processClient(){
        while(true){
            try{
                receivedObject  = in.readObject();
                
                //Authentication
                
             if(receivedObject instanceof WorkerLogin) 
             {
                 String userSearch = (String) receivedObject;
                 for (WorkerLogin worker : /*DB */){
                     if(Worker.getUsername().equalsIgnoreCase(/*DB */) && (Worker.getPassword().equalsIgnoreCase(/*DB */)){
                         out.writeObject("Success");
                         out.flush();
                     }else{
                        out.writeObject("Failed");
                         out.flush(); 
                     }
                 }
                 
                 //Add Student
                 
             }else if(receivedObject instanceof WorkerStudent)
             {
              WorkerStudent stud = (WorkerStudent) receivedObject; 
              //DB code to add to DB             
              out.writeObject("Student Added successfully");
              out.flush();
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
