/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openimaj_socket_server.wrk;

import java.awt.image.BufferedImage;
import java.io.InputStream;

/**
 *
 * @author ReyL03
 */
public interface ItfWrkCtrl {

    public void launchSocket();
    public void close();
    public void toDB(InputStream bi);
    public BufferedImage getImage(int pk);
}
