/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openimaj_socket_server.wrk;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.openimaj.image.Image;

/**
 *
 * @author ReyL03
 */
public class WrkDB {

    public WrkDB() {
        try {
            this.dbConnection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/dblob", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(WrkDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    public void toDB(InputStream bi) {
        System.out.println("On moins, on y est...");
        Statement statement;
        int nb = 0;
        try {
            String prep = "insert t_blobfish set image = ?";
            PreparedStatement ps = (PreparedStatement) dbConnection.prepareStatement(prep);
            ps.setBlob(1, bi);
            statement = (Statement) dbConnection.createStatement();
            nb = ps.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            String erreur = "upodaaatePerson - " + ex.toString();
            System.out.println(erreur);
        }
    }

    public BufferedImage getImage(int pk) {
        BufferedImage img = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String query = "SELECT image FROM t_blobfish WHERE pk_blobfish = ?";
        try {
            pstmt = (PreparedStatement) dbConnection.prepareStatement(query);
            pstmt.setInt(1, pk);
            rs = pstmt.executeQuery();
            rs.next();
            Blob blob = rs.getBlob("image");
            // materialize BLOB onto client
            InputStream is = (ByteArrayInputStream) blob.getBinaryStream();
            img = ImageIO.read(is);
        }catch (SQLException e){
            System.out.println(e);
        }catch (IOException ex){
            System.out.println(ex);
        } finally {
            try {
                rs.close();
                pstmt.close();
                dbConnection.close();
            } catch (SQLException ex) {
                Logger.getLogger(WrkDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return img;
    }

    private Connection dbConnection;
}
