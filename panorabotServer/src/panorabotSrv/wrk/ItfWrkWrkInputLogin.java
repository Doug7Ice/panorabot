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
public interface ItfWrkWrkInputLogin {

    public void afficheMessageConsole(String msg);

    public void affichePopupError(String error);
    
    public void sendTxtClient(String txt);

    public boolean checkLogin(String string, String string0);

}
