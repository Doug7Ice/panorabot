/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.wrk;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Nathan
 */
public class WrkOutputFile {
    private ItfWrkWrkOutputFile refWrk;

    public boolean saveBufferedImageToJpg(BufferedImage bi, String path) {
        try {
            File outPutFile = new File(path);
            ImageIO.write(bi, "jpg", outPutFile);
            return true;
        } catch (IOException ex) {
            System.out.println(ex);
            return false;

        }
    }
}
