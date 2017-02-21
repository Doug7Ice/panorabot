/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.ihm;

import java.awt.image.BufferedImage;

/**
 *
 * @author Nathan
 */
public interface ItfIhmRobotCtrl {

    public void augmenterRayon();
    public void reduireRayon();
    public void afficherPopup(String message, String type);
    public int getActualRayon();
    public void afficheImage(BufferedImage img);
    
}
