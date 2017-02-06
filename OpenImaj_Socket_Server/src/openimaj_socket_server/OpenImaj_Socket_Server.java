/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openimaj_socket_server;

import openimaj_socket_server.ctrl.Ctrl;
import openimaj_socket_server.ihm.Ihm;
import openimaj_socket_server.wrk.Wrk;

/**
 *
 * @author ReyL03
 */
public class OpenImaj_Socket_Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ctrl ctrl = new Ctrl();
        Ihm ihm = new Ihm();
        Wrk wrk = new Wrk();
        
        ctrl.setRefIhm(ihm);
        ctrl.setRefWrk(wrk);
        ihm.setRefCtrl(ctrl);
    }
    
}
