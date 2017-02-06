/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openimaj_socket_server.ctrl;

import java.awt.image.BufferedImage;
import openimaj_socket_server.ihm.ItfIhmCtrl;
import openimaj_socket_server.wrk.ItfWrkCtrl;


/**
 *
 * @author ReyL03
 */
public class Ctrl implements ItfCtrlIhm, ItfCtrlWrk{

    public Ctrl() {
    }
    
    @Override
    public void start() {
        launchSocket();
    }
    
    public void launchSocket(){
        refWrk.launchSocket();
    }

     @Override
    public void afficheMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public void afficheImage(BufferedImage a) {
        refIhm.afficheImage(a);
    }
    
    
    public ItfIhmCtrl getRefIhm() {
        return refIhm;
    }

    public void setRefIhm(ItfIhmCtrl refIhm) {
        this.refIhm = refIhm;
    }

    public ItfWrkCtrl getRefWrk() {
        return refWrk;
    }

    public void setRefWrk(ItfWrkCtrl refWrk) {
        this.refWrk = refWrk;
    }
    
    private ItfIhmCtrl refIhm;
    private ItfWrkCtrl refWrk;

   

}
