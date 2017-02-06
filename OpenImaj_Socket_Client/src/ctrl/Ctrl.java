/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import ihm.ItfIhmCtrl;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.video.Video;
import org.openimaj.video.VideoDisplay;
import org.openimaj.video.capture.VideoCapture;
import org.openimaj.video.capture.VideoCaptureException;
import wrk.ItfWrkCtrl;

/**
 *
 * @author Nathan
 */
public class Ctrl implements ItfIhmCtrl, ItfWrkCtrl {

    public void launchVideoRecord() {
        try {
            Video<MBFImage> video;
            video = new VideoCapture(320, 180);
            VideoDisplay<MBFImage> display = refIhm.displayVideo(video);
            refWrk.sendVideo(video);
        } catch (VideoCaptureException ex) {
            Logger.getLogger(Ctrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ItfCtrlIhm getRefIhm() {
        return refIhm;
    }

    public void setRefIhm(ItfCtrlIhm refIhm) {
        this.refIhm = refIhm;
    }

    public ItfCtrlWrk getRefWrk() {
        return refWrk;
    }

    public void setRefWrk(ItfCtrlWrk refWrk) {
        this.refWrk = refWrk;
    }

    @Override
    public void quit() {
        refWrk.stopThreads();
    }

    private ItfCtrlIhm refIhm;
    private ItfCtrlWrk refWrk;

}
