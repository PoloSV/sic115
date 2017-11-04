package sesion;
import modelo.Usuario;
import db.Consulta;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Autenticación de Usuario
 * @author Marvin
 */
public class UserValidator {
    
    public static Usuario verificarUsuario(String username, String password) throws NoSuchAlgorithmException{
        List<Usuario> usuario = new ArrayList<>();
        
        Consulta consulta = new Consulta();
        consulta.inicializar();
        usuario = consulta.obtenerYFiltrar("Usuario", "username='"+username+"' AND"
                + " password='"+UserEncryp.md5Hash(password)+"'");
        
        consulta.cerrarConexion();
        
        if(usuario.size() == 1){
            return usuario.get(0);
        }
        return null;
    }
    
}
