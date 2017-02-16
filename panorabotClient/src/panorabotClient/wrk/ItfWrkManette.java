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
    public void changerRayon(double rayon);
    public void lancerScan(double rayon);
    public void stopperRobot();
    public void tournerADroite();
    public void tournerAGauche();
    public void augmenterRayon();
    public void reduireRayon();
    
}
