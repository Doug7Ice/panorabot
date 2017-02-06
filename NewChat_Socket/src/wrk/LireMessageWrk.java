/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Nathan
 */
public class LireMessageWrk extends Thread {

    public LireMessageWrk(Socket socket, Wrk wrk, String string) {
        super(string);
        try {
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));;
        } catch (UnknownHostException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        this.wrk = wrk;
    }

    @Override
    public void run() {
        isReading = true;
        while (isReading) {
            try {

                String message = in.readLine();
                wrk.showMessage(message);
                System.out.println(message);

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    public boolean isIsReading() {
        return isReading;
    }

    public void setIsReading(boolean isReading) {
        this.isReading = isReading;
    }

    private BufferedReader in;
    private volatile boolean isReading;
    private Wrk wrk;
}
