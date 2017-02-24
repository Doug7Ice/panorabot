/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotSrv.wrk;

import java.net.Socket;

/**
 *
 * @author rosalina
 */
public interface ItfWrkWrkSocket {

    public void afficheStatutClient(boolean client);

    public void lauchWrkInput(Socket socket);
    public void lauchWrkInputLogin(Socket socketLogin);
    public void lauchWrkOutput(Socket socketCommandsAndWebcam,Socket socketImgFromBD,Socket socketLogin);

    public void affichePopupError(String error);

    public void showWebcam();
}
