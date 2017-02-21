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

    public void lauchWrkInput(Socket socketSendCamAndReceive,Socket socketSQLogin);

    public void lauchWrkOutput(Socket socketSendCamAndReceive,Socket socketSendImgFromDB);

    public void affichePopupError(String error);

    public void showWebcam();

    public void closeWebcam();
    
    public void closeWrkInput();
}
