/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databeans;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 *
 * @author ReyL03
 */
public class ImgCapture implements Serializable{
    static final long serialVersionUID = 240220171402L;
    private int[] img;

    public ImgCapture(int[] img) {
        this.img = img;
    }

    public int[] getImg() {
        return img;
    }

    public void setImg(int[] img) {
        this.img = img;
    }
}
