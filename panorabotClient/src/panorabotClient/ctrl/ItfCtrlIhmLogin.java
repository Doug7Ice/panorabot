/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.ctrl;

/**
 *
 * @author Nathan
 */
public interface ItfCtrlIhmLogin {
    public boolean connecter(String user, String mdp);

    public void lancerSocket();
}