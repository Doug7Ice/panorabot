/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrk;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openimaj.image.MBFImage;
import org.openimaj.video.Video;
import sun.nio.ch.IOUtil;

/**
 *
 * @author Nathan
 */
public class Output extends Thread{
    public Output(Socket socket, Wrk wrk, Video<MBFImage> video) {
        this.wrk = wrk;
        this.socket = socket;
        this.video = video;
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    @Override
    public void run(){
        while(true){
            byte[] byteArr = video.getNextFrame().toByteImage();
            try {
                out.writeObject(byteArr);
                out.flush();
            } catch (IOException ex) {
                System.out.println("erreur" + ex);
            } 
        }
    }
    
    private ObjectOutputStream out;
    private Wrk wrk;
    private Socket socket;
    private Video<MBFImage> video;
}
