/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import org.openimaj.image.MBFImage;
import org.openimaj.video.Video;
import org.openimaj.video.VideoDisplay;

/**
 *
 * @author Nathan
 */
public interface ItfCtrlIhm {
    public VideoDisplay<MBFImage> displayVideo(Video<MBFImage> video);
}
