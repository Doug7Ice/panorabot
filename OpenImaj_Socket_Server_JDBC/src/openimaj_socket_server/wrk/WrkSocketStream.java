/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openimaj_socket_server.wrk;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import openimaj_socket_server.ctrl.ItfCtrlWrk;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;

/**
 *
 * @author ReyL03
 */
class WrkSocketStream extends Thread {

    public WrkSocketStream(Socket socket, ItfCtrlWrk ctrl, WrkSocket wrkSocket, Wrk wrk) {
        super("WrkLireImages");
        this.socket = socket;
        this.refCtrl = ctrl;
        this.wrkSocket = wrkSocket;
        this.refWrk = wrk;
    }

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            read = true;
            while (read) {
                int[] tabInt = (int[]) in.readObject();
//                byte[] tabBytes = (byte[]) in.readObject();
//                ByteArrayInputStream bais = new ByteArrayInputStream(tabBytes);           
//                BufferedImage bi = ImageIO.read(bais);
                MBFImage i = new MBFImage(tabInt, 320, 180);
                BufferedImage bi = ImageUtilities.createBufferedImage(i);
                afficheImage(bi);
                toDB(bi);
            }
        } catch (IOException e) {
            refCtrl.afficheMessage("déconnection");
        } catch (ClassNotFoundException ex) {
            refCtrl.afficheMessage("Erreur lors de la lecture du flux tabarnak");
        }
    }

    public void toDB(BufferedImage bi) {
        refWrk.toDB(bi);
    }

    public void diffuseMessage(String msg) {
        try {
            out = new PrintWriter(socket.getOutputStream());
            out.println(msg);
        } catch (IOException ex) {
            refCtrl.afficheMessage("déconnection");
        }
    }

    public void afficheImage(BufferedImage bi) {
        refCtrl.afficheImage(bi);
    }

    public void setRead(boolean a) {
        read = a;
    }

    private ItfCtrlWrk refCtrl;
    private Socket socket;
    private ObjectInputStream in;
    private PrintWriter out;
    private WrkSocket wrkSocket;
    private Wrk refWrk;
    private volatile boolean read;
}
