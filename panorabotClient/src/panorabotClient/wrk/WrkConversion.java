/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.wrk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nathan
 */
public class WrkConversion {

    private ItfWrkWrkConversion refWrk;

    /**
     * Lance le programme VisualSFM.exe en CMD et lance la création d'un modèle
     * 3d enregistré dans le fichier "C:\tempPanorabot\resultPanorabot.nvm.
     * @param pathDirImages le chemin des images utilisées pour la création du
     * modèle 3d.
     */
    public void launch2dTo3dConversion(String pathDirImages) {
        try {
            Process p = Runtime.getRuntime().exec("cmd /c C:\\VisualSFM_windows_64bit\\VisualSFM.exe sfm+pmvs " + pathDirImages + " C:\\tempPanorabot\\resultPanorabot.nvm");
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                if (!line.equals("")) {
                    System.out.println(line);
                    //refWrk.afficheMessage(line, "info");
                }
            }
        } catch (IOException ex) {
            refWrk.afficheMessage("Erreur lors du lancement de VisualSFM", "error");
        }
    }

}
