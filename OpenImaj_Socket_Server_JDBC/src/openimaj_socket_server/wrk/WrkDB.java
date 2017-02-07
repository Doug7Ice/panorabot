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
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private Connection dbConnection;
}
