/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.ctrl;

import java.awt.image.BufferedImage;

/**
 *
 * @author Nathan
 */
public interface ItfCtrlWrk {
    public void resultLogin(String result);
    public void augmenterRayon();
    public void reduireRayon();
    public void afficherPopup(String message, String type);
    public int getActualRayon();
    public void afficheImage(BufferedImage img);
}
