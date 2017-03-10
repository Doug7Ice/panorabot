/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databeans;

import java.io.Serializable;



/**
 *
 * @author ReyL03
 */
public class ImgCam implements Serializable{
    static final long serialVersionUID = 240220171400L;
    private int[] img;

    public ImgCam(int[] img) {
        this.img = img;
    }

    public int[] getImg() {
        return img;
    }

    public void setImg(int[] img) {
        this.img = img;
    }
    
    
}
