/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import newchat_socket_server.ItfIhmCtrl;
import wrk.ItfWrkCtrl;
import wrk.Wrk;

/**
 *
 * @author Nathan
 */
public class Ctrl implements ItfIhmCtrl, ItfWrkCtrl{

    public Ctrl() {
        this.refWrk = new Wrk(this);
    }
    
    public void lauchSocket(){
        
    }

    //SETTERS AND GETTERS
    public ItfCtrlIhm getRefIhm() {
        return refIhm;
    }

    @Override
    public void setRefIhm(ItfCtrlIhm refIhm) {
        this.refIhm = refIhm;
    }

    public ItfCtrlWrk getRefWrk() {
        return refWrk;
    }

    public void setRefWrk(ItfCtrlWrk refWrk) {
        this.refWrk = refWrk;
    }
    
    
    
    
    private ItfCtrlIhm refIhm;
    private ItfCtrlWrk refWrk;
}
