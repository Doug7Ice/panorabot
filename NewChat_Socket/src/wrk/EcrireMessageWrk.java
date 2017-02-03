/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrk;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
/**
 *
 * @author Nathan
 */
public class EcrireMessageWrk {

    public EcrireMessageWrk(Socket socket, Wrk wrk) {
        this.wrk = wrk;
        this.socket = socket;
        try {
            this.out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        setComputerName();
    }

    public void writeMessage(String message) {
        
        System.out.println("Votre message :");
        
        out.println(message);
        out.flush();

    }

    public void setComputerName() {
        //InetAddress.getLocalHost().getHostName();
        name = System.getProperty("user.name");
    }

    private Wrk wrk;
    private Socket socket;
    private PrintWriter out;
    private String name;
    private Scanner sc = null;
}
