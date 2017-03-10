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
public interface ItfWrkManette {
    public void avancerRobot(int vitesse);
    public void lancerScan();
    public void stopperRobot();
    public void tournerADroite();
    public void tournerAGauche();
    public void augmenterRayon();
    public void reduireRayon();
    public void afficheMessage(String message, String type);
    public void lancerTestRayon();
}
