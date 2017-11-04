package sesion;
import modelo.Usuario;
import db.Consulta;
import modelo.Sesiones;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Autenticación de Usuario
 * @author Marvin
 */
public class UserValidator {
    
    public static Sesiones verificarUsuario(String username, String password) throws NoSuchAlgorithmException{
        List<Usuario> usuario = new ArrayList<>();
        
        Consulta consulta = new Consulta();
        consulta.inicializar();
        
        usuario = consulta.obtenerYFiltrar("Usuario", "username='"+username+"' AND"
                + " password='"+UserEncryp.md5Hash(password)+"'");
        
        if(usuario.size() == 1){
            Usuario u = usuario.get(0);
            Sesiones s = crearSesion(u);
            consulta.guardar(s);
            consulta.cerrarConexion();
            return s;
        }
        consulta.cerrarConexion();
        return null;
    }
    
    /**
     * Crear una instancia de una nueva sesion de usuario
     * @return Sesiones La nueva sesion creada
     * @throws NoSuchAlgorithmException 
     */
    private static Sesiones crearSesion(Usuario user) throws NoSuchAlgorithmException{
        Date hoy = new Date();
        Sesiones sesion = new Sesiones();
        sesion.setUsuario(user);
        sesion.setInicio(hoy);
        sesion.setActiva(true);
        sesion.setKeySesion(UserEncryp.md5Hash(hoy.toString()));
        return sesion;
    }
    
}
