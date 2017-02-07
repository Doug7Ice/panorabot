/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import org.openimaj.image.MBFImage;
import org.openimaj.video.Video;

/**
 *
 * @author Nathan
 */
public interface ItfCtrlWrk {
    public void sendVideo(Video<MBFImage> video);
    public void stopThreads();
}
