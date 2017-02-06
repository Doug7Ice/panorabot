/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openimaj_socket_server.wrk;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import openimaj_socket_server.ctrl.ItfCtrlWrk;

/**
 *
 * @author ReyL03
 */
public class WrkSocket extends Thread{

    public WrkSocket(ServerSocket socketServer, ItfCtrlWrk ctrl) {
        this.socketServer = socketServer;
        this.refCtrl = ctrl;
    }
    
    public void run() {
        try {
            while (true) {
                
                socket = socketServer.accept(); // Un client se connecte on l'accepte
                System.out.println("L'utilisateur numéro est connecté !");
                WrkSocketStream socketUser = new WrkSocketStream(socket, refCtrl, this);
                threadStream = socketUser;
                threadStream.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private ServerSocket socketServer;
    private Socket socket;
    private ItfCtrlWrk refCtrl;
    private WrkSocketStream socketUser;
    private Thread threadStream;
}
