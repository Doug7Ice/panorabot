/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.wrk;

/**
 *
 * @author Nathan
 */
public interface ItfWrkCtrl {
    public void connecter(String user, String mdp);
    public void stopperRobotDUrgence();
    public void lancerSocket();
    public void quit();
}
