/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrk;

import ctrl.Ctrl;
import ctrl.ItfCtrlWrk;

/**
 *
 * @author Nathan
 */
public class Wrk implements ItfCtrlWrk {

    public Wrk(Ctrl refCtrl) {
        this.refCtrl = refCtrl;
        wrkSocket = new WrkSocket();
    }

    @Override
    public void launchSocket() {
        wrkSocket.launchSocket();
    }

    private WrkSocket wrkSocket;
    private ItfWrkCtrl refCtrl;

}
