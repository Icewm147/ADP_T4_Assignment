package za.ac.cput.adp2_t4_assignment_rmn.Client;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import za.ac.cput.adp2_t4_assignment_rmn.WorkerLogin.WorkerLogin;

/**
 *
 * @author Raeesah Khan 219308101
 */
public class Client extends JFrame implements ActionListener {

    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static Socket server;
    private static Object recievedObject;

    public Client() {

        try {
            server = new Socket("127.0.0.1", 12345);
        } catch (IOException ex) {
            System.out.println("IOException >>" + ex.getMessage());
        }
        
        
        
        

    }

    public void setGuiAdmin() {

    }
    
    public void setGuiStudent(){
        
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
//        if (e.getSource() == ) {
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
        Client srs = new Client();
        LoginGui log = new LoginGui();
        log.setTitle("Login");
        log.setSize(300, 250);
        log.setGui();
        log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        log.setVisible(true);
    }
}
