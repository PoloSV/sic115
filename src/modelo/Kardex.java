package modelo;

import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;
import java.math.BigDecimal;
/**
 * Cada uno de los kardex de un periodo y de un tipo especifico
 * @author Marvin
 */
public class Kardex implements Serializable{
    
    private int idKardex;
    private BigDecimal sumaEntradas;
    private BigDecimal sumaSalidas;
    private PeriodoContable periodoContable;
    private TipoKardex tipoKardex;
    private Set lineasKardex = new HashSet(0);

    public Kardex() {
    }

    public Kardex(int idKardex, PeriodoContable periodoContable, TipoKardex tipoKardex) {
        this.idKardex = idKardex;
        this.periodoContable = periodoContable;
        this.tipoKardex = tipoKardex;
    }

    public Kardex(int idKardex, BigDecimal sumaEntradas, BigDecimal sumaSalidas, PeriodoContable periodoContable, TipoKardex tipoKardex, Set lineasKardex) {
        this.idKardex = idKardex;
        this.sumaEntradas = sumaEntradas;
        this.sumaSalidas = sumaSalidas;
        this.periodoContable = periodoContable;
        this.tipoKardex = tipoKardex;
        this.lineasKardex = lineasKardex;
    }

    public int getIdKardex() {
        return idKardex;
    }

    public void setIdKardex(int idKardex) {
        this.idKardex = idKardex;
    }

    public BigDecimal getSumaEntradas() {
        return sumaEntradas;
    }

    public void setSumaEntradas(BigDecimal sumaEntradas) {
        this.sumaEntradas = sumaEntradas;
    }

    public BigDecimal getSumaSalidas() {
        return sumaSalidas;
    }

    public void setSumaSalidas(BigDecimal sumaSalidas) {
        this.sumaSalidas = sumaSalidas;
    }

    public PeriodoContable getPeriodoContable() {
        return periodoContable;
    }

    public void setPeriodoContable(PeriodoContable periodoContable) {
        this.periodoContable = periodoContable;
    }

    public TipoKardex getTipoKardex() {
        return tipoKardex;
    }

    public void setTipoKardex(TipoKardex tipoKardex) {
        this.tipoKardex = tipoKardex;
    }

    public Set getLineasKardex() {
        return lineasKardex;
    }

    public void setLineasKardex(Set lineasKardex) {
        this.lineasKardex = lineasKardex;
    }

    
    
}
