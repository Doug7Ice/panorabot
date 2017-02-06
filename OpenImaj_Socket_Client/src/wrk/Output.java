/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrk;

import com.sun.imageio.plugins.common.ImageUtil;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.video.Video;
import sun.nio.ch.IOUtil;

/**
 *
 * @author Nathan
 */
public class Output extends Thread {

    public Output(Socket socket, Wrk wrk, Video<MBFImage> video) {
        super("Output-Thread");
        this.wrk = wrk;
        this.socket = socket;
        this.video = video;
        this.running = false;
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        running = true;
        while (running) {

//            byte[] byteArr = video.getNextFrame().toByteImage();
            int[] intArr = video.getNextFrame().toPackedARGBPixels();
            MBFImage img = new MBFImage(intArr, 320, 180);
            BufferedImage bf = ImageUtilities.createBufferedImage(img);
//            DisplayUtilities.display(video.getNextFrame());
//            DisplayUtilities.display(bf);
            try {
                out.writeObject(intArr);
                out.flush();
            } catch (IOException ex) {
                System.out.println("erreur" + ex);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Output.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    private ObjectOutputStream out;
    private Wrk wrk;
    private Socket socket;
    private Video<MBFImage> video;
    private volatile boolean running;
}
