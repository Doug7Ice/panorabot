package panorabotClient.wrk;

import com.idrsolutions.image.jpeg.JpegEncoder;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
/**
 * Classe permettant la gestion des fichiers.
 * @author Nathan Canzali
 */
public class WrkOutputFile {

    private final String initialCapturesFolder = "C:\\tempPanorabot";

    /**
     * Constructeur de la classe WrkOutputFile.
     */
    public WrkOutputFile() {
        serialPhoto = 0;
        outPutFile = new File(initialCapturesFolder);
        if (!outPutFile.exists()) {
            outPutFile.mkdir();
        }
    }

    /**
     * Enregistre une BufferedImage en JPEG (jpg). L'enregistrement se fait dans
     * le répertoire "C:\tempPanorabot"
     * @param bi la BufferedImage en jpg.
     * @return un boolean true si l'enregistrement s'est bien effectué.
     */
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

    /**
     * Supprime tous les fichiers dans le répertoire de scan.
     */
    public void reinitialiserScan() {
        serialPhoto = 0;
        String[] captures = outPutFile.list();
        for (String capture : captures) {
            File currentFile = new File(outPutFile.getPath(), capture);
            currentFile.delete();
        }
    }
    
    /**
     * Retourne le chemin du répertoire où sont stockées les images du scan en
     * String.
     * @return le chemin du répertoire où sont stockées les images du scan.
     */
    public String getPath() {
        return outPutFile.getAbsolutePath();
    }
    private int serialPhoto;
    private ItfWrkWrkOutputFile refWrk;
    private File outPutFile;
}
