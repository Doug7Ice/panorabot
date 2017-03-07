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

    public void launch2dTo3dConversion(String pathDirImages) {
        try {
            Process p = Runtime.getRuntime().exec("VisualSFM\\VisualSFM.exe sfm+pmvs 'captures' 'result\\result.nvm'");
            p.waitFor();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            String line = "";
            while ((line = reader.readLine()) != null) {
                refWrk.afficheMessage(line, "info");
            }
        } catch (IOException ex) {
            Logger.getLogger(WrkConversion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(WrkConversion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
