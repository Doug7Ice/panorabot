/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrk;

import ctrl.ItfCtrlWrk;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openimaj.image.MBFImage;
import org.openimaj.video.Video;

/**
 *
 * @author Nathan
 */
public class Wrk implements ItfCtrlWrk {

    @Override
    public void sendVideo(Video<MBFImage> video) {
        try {
            this.socket = new Socket("192.168.2.2", 2009);
        } catch (IOException ex) {
            System.out.println("erreur : " + ex);
        }
        this.output = new Output(socket, this, video);
        output.start();
    }

    public ItfWrkCtrl getRefCtrl() {
        return refCtrl;
    }

    public void setRefCtrl(ItfWrkCtrl refCtrl) {
        this.refCtrl = refCtrl;
    }

    @Override
    public void stopThreads() {
        if (output != null) {
            output.setRunning(false);
        }
        output = null;
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("erreur : " + ex);
        }
        System.gc();
    }

    private Socket socket;
    private ItfWrkCtrl refCtrl;
    private Output output;

}
