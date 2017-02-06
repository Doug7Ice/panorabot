/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadstests;

import Thread.MonThreadExtends;
import Thread.MonThreadImplements;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nathan
 */
public class ThreadsTests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Bienvenue");
        Thread t1 = new MonThreadExtends("Thread Extends");
        Thread t2 = new Thread(new MonThreadImplements(),
                "Thread Implements");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
            t1 = null;
            t2 = null;
        } catch (InterruptedException ex) {
            System.out.println("Une erreur d'interruption"
                    + " a été rencontrée "  + ex);
        }
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadsTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
