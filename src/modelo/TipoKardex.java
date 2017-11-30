package modelo;

import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;
/**
 * Existen varios tipos de Kardex
 * @author Marvin
 */
public class TipoKardex implements Serializable {
    
    private int idTipoKardex;
    private String tipoKardex;
    private Set kardexs = new HashSet(0);
    
    public static final int KARDEX_MATERIA_PRIMA = 1;
    public static final int KARDEX_PRODUCTO_EN_PROCESO = 2;
    public static final int KARDEX_PRODUCTO_TERMINADO = 3;

    public TipoKardex() {
    }

    public TipoKardex(int idTipoKardex, String tipoKardex) {
        this.idTipoKardex = idTipoKardex;
        this.tipoKardex = tipoKardex;
    }
    
    public TipoKardex(int idTipoKardex, String tipoKardex, Set kardexs) {
        this.idTipoKardex = idTipoKardex;
        this.tipoKardex = tipoKardex;
        this.kardexs = kardexs;
    }

    public int getIdTipoKardex() {
        return idTipoKardex;
    }

    public void setIdTipoKardex(int idTipoKardex) {
        this.idTipoKardex = idTipoKardex;
    }

    public String getTipoKardex() {
        return tipoKardex;
    }

    public void setTipoKardex(String tipoKardex) {
        this.tipoKardex = tipoKardex;
    }

    public Set getKardexs() {
        return kardexs;
    }

    public void setKardexs(Set kardexs) {
        this.kardexs = kardexs;
    }
    
    
    
}
