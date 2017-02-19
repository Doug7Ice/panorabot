/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotSrv.wrk;

import java.io.InputStream;

/**
 *
 * @author rosalina
 */
public interface ItfWrkWrkKJuniorCam {
    public void stockeImagesDB(InputStream stream);
    public void affichePopupError(String error);
}
