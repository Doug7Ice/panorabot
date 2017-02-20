/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.wrk;

import panorabotClient.ctrl.ItfCtrlWrk;

/**
 *
 * @author Nathan
 */
public class Wrk implements ItfWrkCtrl, ItfWrkManette, ItfWrkWrkConversion, ItfWrkWrkSocket, ItfWrkWrkOutputFile {

    public Wrk() {

        refWrkConversion = new WrkConversion();
        refWrkOutputFile = new WrkOutputFile();
        isRobotTurning = false;
    }

    @Override
    public boolean connecter(String user, String mdp) {
        return false;
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
    public void lancerScan(double rayon) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void lancerSocket() {
        refWrkSocket = new WrkSocket(this);
        refWrkSocket.start();
        refWrkManette = new WrkManette(this);
    }
    
    @Override
    public void afficherErreur(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    //Setters and Getters
    public ItfCtrlWrk getCtrl() {
        return refCtrl;
    }

    public void setCtrl(ItfCtrlWrk ctrl) {
        this.refCtrl = ctrl;
    }

    private ItfCtrlWrk refCtrl;
    private WrkManette refWrkManette;
    private WrkSocket refWrkSocket;
    private WrkConversion refWrkConversion;
    private WrkOutputFile refWrkOutputFile;
    private boolean isRobotTurning;

    
    
}
