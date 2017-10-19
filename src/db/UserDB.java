package db;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Usuario;
public class UserDB {
    public static String md5Hash(String password) throws NoSuchAlgorithmException{
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(password.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
        while(hashtext.length() < 32 ){
            hashtext = "0"+hashtext;
        }
        return hashtext;
    }
    public ArrayList<Usuario> users = new ArrayList<>();
//    public static Usuario login(String username, String password){
//        Usuario u = null;
//        String sentenciaSQL = "SELECT * FROM users WHERE username = ? AND password = ?";
//        try{
//            PreparedStatement ps = Conexion.getConexion().prepareStatement(sentenciaSQL);
//            ps.setString(1,username);
//            ps.setString(2, md5Hash(password));
//            ResultSet rs = ps.executeQuery();
//            while(rs.next()){
//                u = new Usuario();
//                u.setIdUser(rs.getInt("idUser"));
//                u.setUsername(rs.getString("username"));
//                u.setPassword(rs.getString("password"));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return u;
//    }
    
}
