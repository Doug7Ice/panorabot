/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.wrk;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    private final String initialCapturesFolder = "captures";

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
            ImageIO.write(bi, "PNG", new File(initialCapturesFolder + "/" + serialPhoto + ".PNG"));
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

    public String getPath() {
        return initialCapturesFolder + currentFolder;
    }
    private int serialPhoto;
    private ItfWrkWrkOutputFile refWrk;
    private int currentFolder;
    private File outPutFile;
}
