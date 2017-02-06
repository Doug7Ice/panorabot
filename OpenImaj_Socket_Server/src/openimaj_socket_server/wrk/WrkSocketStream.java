/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openimaj_socket_server.wrk;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.imageio.ImageIO;
import openimaj_socket_server.ctrl.ItfCtrlWrk;

/**
 *
 * @author ReyL03
 */
class WrkSocketStream extends Thread {

    public WrkSocketStream(Socket socket, ItfCtrlWrk ctrl, WrkSocket wrkSocket) {
        super("WrkLireImages");
        this.socket = socket;
        this.refCtrl = ctrl;
        this.wrkSocket = wrkSocket;   
    }

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            while (true) {
                byte[] tabBytes = (byte[]) in.readObject();
                ByteArrayInputStream bais = new ByteArrayInputStream(tabBytes);
                BufferedImage bi = ImageIO.read(bais);
                afficheImage(bi);
            }
        } catch (IOException e) {
            refCtrl.afficheMessage("déconnection");
        } catch (ClassNotFoundException ex) {
            refCtrl.afficheMessage("Erreur lors de la lecture du fluc tabarnak");
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
    
    public void afficheImage(BufferedImage bi){
        refCtrl.afficheImage(bi);
    }

    private ItfCtrlWrk refCtrl;
    private Socket socket;
    private ObjectInputStream in;
    private PrintWriter out;
    private WrkSocket wrkSocket;
}
