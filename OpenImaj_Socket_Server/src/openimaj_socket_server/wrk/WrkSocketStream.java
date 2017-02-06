/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openimaj_socket_server.wrk;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import openimaj_socket_server.ctrl.ItfCtrlWrk;

/**
 *
 * @author ReyL03
 */
class WrkSocketStream extends Thread{
    public WrkSocketStream(Socket socket, ItfCtrlWrk ctrl, WrkSocket wrkSocket) {
        this.socket = socket;
        this.refCtrl = ctrl;
        this.wrkSocket = wrkSocket;
    }

    @Override
    public void run() {
        try {
            in = new BufferedInputStream (socket.getInputStream());
            while (true) {
                byte [] tab = new byte[Integer.MAX_VALUE];
                int unByte = in.read(tab);
            }
        } catch (IOException e) {
            refCtrl.afficheMessage("déconnection");
        }
    }

    public void diffuseMessage(String msg) {
        try {
            out = new PrintWriter(socket.getOutputStream());
            out.println(msg);
        } catch (IOException ex) {
            refCtrl.afficheMessage("déconnection");
        }
    }

    private ItfCtrlWrk refCtrl;
    private Socket socket;
    private BufferedInputStream in;
    private PrintWriter out;
    private WrkSocket wrkSocket;
}

