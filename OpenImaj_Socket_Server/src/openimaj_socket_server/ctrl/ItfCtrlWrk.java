/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openimaj_socket_server.ctrl;

import java.awt.image.BufferedImage;
import org.openimaj.image.MBFImage;

/**
 *
 * @author ReyL03
 */
public interface ItfCtrlWrk {
    public void afficheMessage(String msg);
    public void afficheImage(BufferedImage a);
    public void afficheImage(MBFImage a);
}
