/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.wrk;

import com.idrsolutions.image.jpeg.JpegEncoder;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.openimaj.io.FileUtils;

/**
 *
 * @author Nathan
 */
public class WrkOutputFile {

    private final String initialCapturesFolder = "C:\\tempPanorabot";

    public WrkOutputFile() {
        currentFolder = 0;
        serialPhoto = 0;
        outPutFile = new File(initialCapturesFolder);
        boolean checkExist = true;
        while (checkExist) {
            if (new File(initialCapturesFolder + currentFolder).exists()) {
                currentFolder++;
            } else {
                checkExist = false;
            }
        }
    }

    public boolean saveBufferedImageToJpg(BufferedImage bi) {
        try {

            if (!outPutFile.exists()) {
                outPutFile.mkdir();
            }
            OutputStream os = new FileOutputStream(new File(initialCapturesFolder + "\\" + serialPhoto + ".jpg"));
            JpegEncoder encoder = new JpegEncoder();
            encoder.write(bi, os);
            os.flush();
            os.close();
            serialPhoto++;
            return true;
        } catch (IOException ex) {
            refWrk.afficheMessage("Erreur d'écriture du fichier", "error");
            return false;
        }
    }

    public void incrementCurrentFolder() {
        currentFolder += 1;
    }

    public void reinitialiserScan() {
        serialPhoto = 0;
        String[] captures = outPutFile.list();
        for (String capture : captures) {
            File currentFile = new File(outPutFile.getPath(), capture);
            currentFile.delete();
        }
    }
    
    private BufferedImage resize(BufferedImage imgToResize){
        BufferedImage outputImage = new BufferedImage(outputSizeWidth, outputSizeHeight, imgToResize.getType());
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(imgToResize, 0, 0, outputSizeWidth, outputSizeHeight, null);
        g2d.dispose();
        return outputImage;
    }

    public String getPath() {
        return outPutFile.getAbsolutePath();
    }
    private int serialPhoto;
    private ItfWrkWrkOutputFile refWrk;
    private int currentFolder;
    private File outPutFile;
    
    private final int  outputSizeWidth = 1280;
    private final int  outputSizeHeight = 720;
}
