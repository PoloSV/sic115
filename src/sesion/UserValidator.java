package sesion;
import modelo.Usuario;
import db.Consulta;
import frm.Principal;
import modelo.Sesiones;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
 * Autenticación de Usuario
 * @author Marvin
 */
public class UserValidator {
    
    /**
     * Verificación de Usuarios
     * @param username El nombre de usuario a loguear
     * @param password La contraseña del usuario
     * @return Una instancia de una Sesion en caso de tener exito o null
     * @throws NoSuchAlgorithmException 
     */
    public static Sesiones verificarUsuario(String username, String password) throws NoSuchAlgorithmException{
        List<Usuario> usuario;
        
        Consulta consulta = new Consulta();
        consulta.inicializar();
        
        usuario = consulta.obtenerYFiltrar("Usuario", "username='"+username+"' AND"
                + " password='"+UserEncryp.md5Hash(password)+"'");
        
        if(usuario.size() == 1){
            Usuario u = usuario.get(0);
            if(u.getActive()){
                Sesiones s = crearSesion(u);
                consulta.guardar(s);
                consulta.cerrarConexion();
                return s;
            }            
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
    
    /**
     * Usar siempre para mantener actualizado el estado de la sesion
     * @return boolean Es valida o no lo es la sesion actual
     */
    public static boolean isSesionValida(){
        Consulta c = new Consulta();
        c.inicializar();
        c.refrescar(Principal.sesion);
        c.cerrarConexion();
        return Principal.sesion.getActiva() && Principal.sesion.getUsuario().getActive();
    }
    
}
