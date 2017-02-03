/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nathan
 */
public class WrkSocketStream extends Thread {

    public WrkSocketStream(String login, Socket socket, ItfWrkCtrl ctrl, WrkSocket wrkSocket) {
        this.socket = socket;
        this.ctrl = ctrl;
        this.login = login;
        this.wrkSocket = wrkSocket;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String msg = in.readLine();
                if (msg != null) {
                    ctrl.afficherMessage("message envoyé : " + msg);
                    wrkSocket.diffuseMessage(msg);
                }
            }
        } catch (IOException e) {
            ctrl.afficherMessage(login + " s'est déconnecté.");
        }
    }

    public void diffuseMessage(String msg) {
        try {
            out = new PrintWriter(socket.getOutputStream());
            out.println(msg);
        } catch (IOException ex) {
            ctrl.afficherMessage(login + " s'est déconnecté.");
        }
    }

    private ItfWrkCtrl ctrl;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String login;
    private WrkSocket wrkSocket;
}
