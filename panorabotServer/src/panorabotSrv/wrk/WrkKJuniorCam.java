package panorabotSrv.wrk;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.video.Video;
import org.openimaj.video.capture.VideoCapture;
import org.openimaj.video.capture.VideoCaptureException;

/**
 * Sous-worker permettant de controller la camera du KJunior.
 * @author ReyL03
 * @version 1.0
 * @updated 17-fevr.-2017 14:54:37
 */
public class WrkKJuniorCam extends Thread {

    private ObjectOutputStream out;
    private volatile boolean on;
    private ItfWrkWrkKJuniorCam refWrk;
    private Video<MBFImage> video;

    public WrkKJuniorCam(ItfWrkWrkKJuniorCam wrk) {
        super("KjuniorCam");
        try {
            video = new VideoCapture(320, 180);
        } catch (VideoCaptureException ex) {
            Logger.getLogger(WrkKJuniorCam.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.refWrk = wrk;
    }

    public void finalize() throws Throwable {
        super.finalize();
    }

	/**
	 * Recpetionne les images provenant de la camera du KJunior.
	 */
    public void run() {
        this.on = true;
        while(on){
            int[] intArr = video.getNextFrame().toPackedARGBPixels();
            MBFImage img = new MBFImage(intArr, 320, 180);
            BufferedImage bf = ImageUtilities.createBufferedImage(img);
            DisplayUtilities.display(bf);
        }
    }

	/**
	 * Envoie les images au Wrk afin qu'elles soient stocke dans la DB.
	 * 
	 * @param intArr
	 */
    private void sendPrintScreen(int[] intArr) {
        try {
            out.writeObject(intArr);
            out.flush();
        } catch (IOException ex) {
            System.out.println("erreur" + ex);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(WrkKJuniorCam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
    
    
}//end WrkKjuniorCam
