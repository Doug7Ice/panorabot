package panorabotSrv.wrk;

import databeans.ImgCapture;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;

/**
 * Wrk permettant de controller la BD.
 *
 * @author ReyL03
 * @version 1.0
 * @updated 17-fevr.-2017 14:54:37
 */
public class WrkDB {

    private Connection dbConnection;
    private ItfWrkWrkDB refWrk;
    private String usernameConnecte;
    private int nbCapture;

    public WrkDB() {
        try {
            this.dbConnection = (Connection) DriverManager.getConnection("jdbc:mysql://canzalin.emf-informatique.ch:3306/canzalin_PanoraBot_DB", "canzalin_panorabotAdmin", "Emf12345?");
        } catch (SQLException ex) {
            Logger.getLogger(WrkDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    public void finalize() throws Throwable {

    }

    /**
     * Permet d'ajouter un log dans la base de donnees.
     *
     * @param log log
     */
    public void addLog(String log) {
    }

    /**
     * Receptionne les images d'une capture dans la BD. La capture peut etre
     * trouvee grace au numero de l'User ainsi que du Scan.
     *
     * @param pkUser
     * @param pkScan pkScan
     * @return ImgCapture
     */
    public ArrayList<ImgCapture> getImages(int pkUser, int pkScan) {
        ArrayList<ImgCapture> lesPhotos = new ArrayList<ImgCapture>();
        ResultSet rs = null;
        com.mysql.jdbc.PreparedStatement pstmt = null;
        String query = "SELECT * FROM T_Photo WHERE FK_Capture = ?";
        try {
            pstmt = (com.mysql.jdbc.PreparedStatement) dbConnection.prepareStatement(query);
            pstmt.setInt(1, this.nbCapture);
            rs = pstmt.executeQuery();          
            while (rs.next()) {
                Blob blob = rs.getBlob("photo");
                BufferedImage img = null;
                InputStream is = (ByteArrayInputStream) blob.getBinaryStream();
                img = ImageIO.read(is);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(img, "png", baos);
                byte[] imageData = baos.toByteArray();

//                InputStream a = new ByteArrayInputStream(imageData);
//                BufferedImage bf = ImageIO.read(a);
//                DisplayUtilities.display(bf);

                lesPhotos.add(new ImgCapture(imageData));

//                int bloblength = (int) blob.length();
//                byte[] arrayPhotosBytes = blob.getBytes(1, bloblength);
//                IntBuffer intBuf
//                        = ByteBuffer.wrap(arrayPhotosBytes)
//                        .order(ByteOrder.BIG_ENDIAN)
//                        .asIntBuffer();
//                int[] arrayPhotos = new int[intBuf.remaining()];
//                intBuf.get(arrayPhotos);
//                lesPhotos.add(new ImgCapture(arrayPhotos));               
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (IOException ex) {
            Logger.getLogger(WrkDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            //rs.close();
            //pstmt.close();
            //dbConnection.close();
        }
        return lesPhotos;
    }

    /**
     * Envoie dans la BD un Blob.
     *
     * @param stream stream
     */
    public boolean put(InputStream stream) {
        return false;
    }

    public int userConnection(String username, String password) {
        int ok = 3;
        String passwordDB = null;
        Statement statement;
        ResultSet rs = null;
        try {
            if (!dbConnection.isClosed()) {
                String prep = "select pwd from T_User where username = ? and pwd = ?";
                PreparedStatement ps = (PreparedStatement) dbConnection.prepareStatement(prep);
                ps.setString(1, username);
                ps.setString(2, password);
                statement = (Statement) dbConnection.createStatement();
                rs = ps.executeQuery();
                rs.next();
                if (rs.getRow() == 0) {
                    ok = 0;
                }
                if (ok != 0) {
                    passwordDB = rs.getString("pwd");
                    statement.close();
                    if (passwordDB.equals(password)) {
                        ok = 1;
                        this.usernameConnecte = username;
                    } else {
                        ok = 0;
                    }
                }
            } else {
                ok = 3;
            }
        } catch (SQLException ex) {
            ok = 3;
            Logger.getLogger(WrkDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ok;
    }

    public void putCapture() {
        com.mysql.jdbc.Statement statement;
        int nb = 0;
        try {
            statement = (com.mysql.jdbc.Statement) dbConnection.createStatement();

            String prepCapture = "INSERT INTO T_Capture (date,FK_User) VALUES (?,?)";
            //com.mysql.jdbc.PreparedStatement psCapture = (com.mysql.jdbc.PreparedStatement) dbConnection.prepareStatement(prepCapture);
            PreparedStatement psCapture = dbConnection.prepareStatement(prepCapture, Statement.RETURN_GENERATED_KEYS);
            java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
            psCapture.setDate(1, date);
            psCapture.setInt(2, 1);
            psCapture.executeUpdate();
            ResultSet keys = psCapture.getGeneratedKeys();
            keys.next();
            //nbCapture = keys.getInt(1);
            
            nbCapture = getLastPK();

            statement.close();
        } catch (SQLException ex) {
            String erreur = "erreur - " + ex.toString();
            System.out.println(erreur);
        }
    }

    public int getLastPK() throws SQLException {
        ResultSet rs = null;
        Statement statement = (com.mysql.jdbc.Statement) dbConnection.createStatement();       
        rs = statement.executeQuery("select max(pk_capture) as pkMAX from T_Capture");
        rs.next();
        String lastid = rs.getString("pkMAX");
        
        return Integer.parseInt(lastid);
    }

    public String getUsernameConnecte() {
        return usernameConnecte;
    }

    public void putPhoto(InputStream bi) {
        int nb = 0;
        com.mysql.jdbc.Statement statement;
        try {
            statement = (com.mysql.jdbc.Statement) dbConnection.createStatement();
            String prepPhoto = "INSERT INTO T_Photo (photo,FK_Capture) VALUES (?,?)";
            com.mysql.jdbc.PreparedStatement psPhoto;
            psPhoto = (com.mysql.jdbc.PreparedStatement) dbConnection.prepareStatement(prepPhoto);
            psPhoto.setBlob(1, bi);
            psPhoto.setInt(2, nbCapture);
            nb = psPhoto.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(WrkDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        try {
            this.dbConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(WrkDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}//end WrkDB
