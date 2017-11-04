package sesion;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utilidades de Hash para Campos de texto MD5
 * @author Marvin
 */
public class UserEncryp {
    
    /**
     * Retorna el hash de una cadena
     * @param password
     * @return String La cadena encriptada usando MD5
     * @throws NoSuchAlgorithmException 
     */
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
}
