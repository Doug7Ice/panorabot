/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.wrk;

import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import panorabotClient.ctrl.ItfCtrlWrk;

/**
 *
 * @author Nathan
 */
public class Wrk implements ItfWrkCtrl, ItfWrkManette, ItfWrkWrkConversion, ItfWrkWrkSocket, ItfWrkWrkOutputFile {

    public Wrk() {
        refWrkSocket = new WrkSocket(this);
        refWrkConversion = new WrkConversion();
        refWrkOutputFile = new WrkOutputFile();
        isRobotTurning = false;
    }

    @Override
    public void connecter(String user, String mdp) {
        refWrkSocket.login(user, mdp);
    }

    //Methods implemented from ItfWrkCtrl
    @Override
    public void stopperRobotDUrgence() {
        refWrkSocket.stopperRobotTCP();
    }

    //Methods implemented from ItfWrkManette
    @Override
    public void avancerRobot(int vitesse) {
        if (!isRobotTurning) {
            refWrkSocket.avancerRobotTCP(vitesse);
        }
    }

    @Override
    public void stopperRobot() {
        refWrkSocket.stopperRobotTCP();
        isRobotTurning = false;
    }

    @Override
    public void tournerADroite() {
        refWrkSocket.tournerADroiteTCP();
        isRobotTurning = true;
    }

    @Override
    public void tournerAGauche() {
        refWrkSocket.tournerAGaucheTCP();
        isRobotTurning = true;
    }

    @Override
    public void changerRayon(double rayon) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void lancerScan() {
        refWrkSocket.lancerScanTCP(refCtrl.getActualRayon());
    }
    
    @Override
    public void augmenterRayon() {
        refCtrl.augmenterRayon();
    }
    
    @Override
    public void reduireRayon() {
        refCtrl.reduireRayon();
    }
    
     @Override
    public void afficheImage(BufferedImage img) {
        refCtrl.afficheImage(img);
    }
    
    @Override
    public void quit() {
        if (refWrkSocket != null) {
            try {
                refWrkSocket.quit();
                refWrkSocket.join();
                refWrkSocket = null;
            } catch (InterruptedException ex) {
                refCtrl.afficherPopup("Erreur de thread", "error");
            }
        }
        System.gc();
        
    }


    @Override
    public void lancerSocket() {
        refWrkSocket.connecterSocket();
        refWrkSocket.start();
        refWrkManette = new WrkManette(this);
    }
    
    @Override
    public void afficheMessage(String message, String type) {
       refCtrl.afficherPopup(message, type);
    }
    
    @Override
    public void resultLogin(String result) {
        refCtrl.resultLogin(result);
    }


    //Setters and Getters
    public ItfCtrlWrk getCtrl() {
        return refCtrl;
    }

    public void setCtrl(ItfCtrlWrk ctrl) {
        this.refCtrl = ctrl;
    }

    //Private variables
    private ItfCtrlWrk refCtrl;
    private WrkManette refWrkManette;
    private WrkSocket refWrkSocket;
    private WrkConversion refWrkConversion;
    private WrkOutputFile refWrkOutputFile;
    private boolean isRobotTurning;

    

   

    




    
    
}
