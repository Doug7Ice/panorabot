package panorabotSrv.wrk;

import java.util.logging.Level;
import java.util.logging.Logger;
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
    private volatile boolean youHaveToSendScreenShotToDB;

    public WrkKJuniorCam(ItfWrkWrkKJuniorCam wrk) {
        super("KjuniorCam");
        this.refWrk = wrk;
        try {
            video = new VideoCapture(320, 180);
        } catch (VideoCaptureException ex) {
            Logger.getLogger(WrkKJuniorCam.class.getName()).log(Level.SEVERE, null, ex);          
        }
        this.on = false;
    }

    public void finalize() throws Throwable {
        super.finalize();
    }

	/**
	 * Recpetionne les images provenant de la camera du KJunior.
	 */
    public void run() {
        while(on){
            int[] intArr = video.getNextFrame().toPackedARGBPixels();
            MBFImage img = new MBFImage(intArr, 320, 180);
            sendWebcam(intArr);
            if (youHaveToSendScreenShotToDB){
                sendPrintScreenDB(intArr);
            }
//            BufferedImage bf = ImageUtilities.createBufferedImage(img);
//            DisplayUtilities.display(bf);
        }
    }

	/**
	 * Envoie les images au Wrk afin qu'elles soient stocke dans la DB.
	 * 
	 * @param intArr
	 */
    private void sendPrintScreenDB(int[] intArr) {
        refWrk.sendWebcam(intArr);
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
    public void sendWebcam(int[] intArr){
        refWrk.sendWebcam(intArr);
    }
    
    
}//end WrkKjuniorCam
