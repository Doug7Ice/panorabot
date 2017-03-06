/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotSrv.wrk;

/**
 *
 * @author ReyL03
 */
public interface ItfWrkWrkInput {
    public void afficheMessageConsole(String msg);
    public void bougeLeRobot(String commande);
    public void affichePopupError(String error);
    public void lanceCapture(double rayon);
    public void checkLogin(String username, String password);
    public void sendTxtClient(String txt);
}
