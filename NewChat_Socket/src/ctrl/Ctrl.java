/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import newchat.FXMLDocumentController;
import wrk.Wrk;

/**
 *
 * @author Nathan
 */
public class Ctrl {

    public Wrk getRefWrk() {
        return refWrk;
    }

    public void setRefWrk(Wrk refWrk) {
        this.refWrk = refWrk;
    }
    
    public void setRefIhm(FXMLDocumentController ihm) {
        this.refIhm = ihm;
    }
    
    public void envoyerMsg(String msg) {
        refWrk.envoyerMsg(msg);
    }
    
    public void msgAfficher(String msg) {
        refIhm.afficheMsg(msg);
    }
    
    public void fermer() {
       refWrk.fermer();
    }
    
    private Wrk refWrk;
    private FXMLDocumentController refIhm;

    
}
