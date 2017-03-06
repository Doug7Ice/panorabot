package panorabotSrv.wrk;

import databeans.ImgCam;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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

    private volatile boolean on;
    private ItfWrkWrkKJuniorCam refWrk;
    private Video<MBFImage> video;
     private volatile boolean sendDB;

    public WrkKJuniorCam(ItfWrkWrkKJuniorCam wrk) {
        super("KjuniorCam");
        this.refWrk = wrk;
        this.sendDB = false;
        try {
            video = new VideoCapture(320, 180);
        } catch (VideoCaptureException ex) {
            Logger.getLogger(WrkKJuniorCam.class.getName()).log(Level.SEVERE, null, ex);          
        }
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
            sendPrintScreen(intArr);
//            BufferedImage bf = ImageUtilities.createBufferedImage(img);
//            DisplayUtilities.display(bf);
            if (sendDB){
                sendWebcam(intArr);
            }
        }
    }

	/**
	 * Envoie les images au Wrk afin qu'elles soient stocke dans la DB.
	 * 
	 * @param intArr
	 */
    private void sendPrintScreen(int[] intArr) {
        ImgCam cam = new ImgCam(intArr);
        refWrk.sendWebcam(cam);
    }

    public boolean isOn() {
        return on;
    }

    public boolean isSendDB() {
        return sendDB;
    }

    public void setSendDB(boolean sendDB) {
        this.sendDB = sendDB;
    }
   

    public void setOn(boolean on) {
        this.on = on;
    }
    public void sendWebcam(int[] intArr){
        MBFImage img = new MBFImage(intArr, 320, 180);
        BufferedImage bi = ImageUtilities.createBufferedImage(img);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bi, "png", baos);
        } catch (IOException ex) {
            Logger.getLogger(WrkKJuniorCam.class.getName()).log(Level.SEVERE, null, ex);
        }
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        refWrk.stockeImagesDB(is);
    }
    
    
}//end WrkKjuniorCam
