/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.wrk;

import java.awt.image.BufferedImage;

/**
 *
 * @author Nathan
 */
public interface ItfWrkWrkSocket {
    public void afficheMessage(String message, String type);
    public void afficheImage(BufferedImage img);
    public void resultLogin(String result);
}
