/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openimaj_socket_server.ctrl;

import java.awt.image.BufferedImage;

/**
 *
 * @author ReyL03
 */
public interface ItfCtrlIhm {
    public void start();
    public void launchSocket();
    public void close();
    public BufferedImage afficheImageDB(int pkDb);
}
