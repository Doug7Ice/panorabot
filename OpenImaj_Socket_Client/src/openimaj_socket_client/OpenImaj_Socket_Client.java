/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openimaj_socket_client;

import ctrl.Ctrl;
import ihm.Ihm;
import wrk.Wrk;

/**
 *
 * @author Nathan
 */
public class OpenImaj_Socket_Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ctrl ctrl = new Ctrl();
        Wrk wrk = new Wrk();
        Ihm ihm = new Ihm();
        
        ctrl.setRefIhm(ihm);
        ctrl.setRefWrk(wrk);
        wrk.setRefCtrl(ctrl);
        ihm.setRefCtrl(ctrl);
        
        ihm.setVisible(true);
        ctrl.launchVideoRecord();
    }
    
}
