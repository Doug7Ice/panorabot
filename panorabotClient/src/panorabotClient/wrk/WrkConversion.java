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

    public void launch2dTo3dConversionDEPRECATED(String pathDirImages) {
        try {
            Process p = Runtime.getRuntime().exec("VisualSFM\\VisualSFM.exe sfm+pmvs captures result\\result.nvm");
            p.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                if (line != null & !line.equals("")) {
                    System.out.println(line);
                    refWrk.afficheMessage(line, "info");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(WrkConversion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(WrkConversion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void launch2dTo3dConversion(String pathDirImages) {
        try {
            //ProcessBuilder pb = new ProcessBuilder("cmd /c C:\\VisualSFM_windows_64bit\\VisualSFM.exe sfm+pmvs C:\\tempPanorabot C:\\tempPanorabot\\resultPanorabot.nvm");
            //ProcessBuilder pb = new ProcessBuilder("ipconfig");
            //pb.redirectErrorStream(true);
            //Process p = pb.start();
            Process p = Runtime.getRuntime().exec("cmd /c C:\\VisualSFM_windows_64bit\\VisualSFM.exe sfm+pmvs C:\\tempPanorabot C:\\tempPanorabot\\resultPanorabot.nvm");
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                if (!line.equals("")) {
                    System.out.println(line);
                    //refWrk.afficheMessage(line, "info");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(WrkConversion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
