package modelo;

import java.util.HashSet;
import java.util.Set;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * Una linea de detalle de un Kardex
 * @author Marvin
 */
public class LineaKardex implements Serializable {
    
    private int idLineaKardex;
    private Kardex kardex;
    private Date fechaLinea;
    private int entraCant;
    private BigDecimal entraUnit;
    private BigDecimal entraMont;
    private int saliCant;
    private BigDecimal saliUnit;
    private BigDecimal saliMont;
    private int montCant;
    private BigDecimal montUnit;
    private BigDecimal montMont;

    public LineaKardex() {
    }

    public LineaKardex(int idLineaKardex, Kardex kardex, Date fechaLinea) {
        this.idLineaKardex = idLineaKardex;
        this.kardex = kardex;
        this.fechaLinea = fechaLinea;
    }

    public LineaKardex(int idLineaKardex, Kardex kardex, Date fechaLinea, int entraCant, BigDecimal entraUnit, BigDecimal entraMont, int saliCant, BigDecimal saliUnit, BigDecimal saliMont, int montCant, BigDecimal montUnit, BigDecimal montMont) {
        this.idLineaKardex = idLineaKardex;
        this.kardex = kardex;
        this.fechaLinea = fechaLinea;
        this.entraCant = entraCant;
        this.entraUnit = entraUnit;
        this.entraMont = entraMont;
        this.saliCant = saliCant;
        this.saliUnit = saliUnit;
        this.saliMont = saliMont;
        this.montCant = montCant;
        this.montUnit = montUnit;
        this.montMont = montMont;
    }

    public int getIdLineaKardex() {
        return idLineaKardex;
    }

    public void setIdLineaKardex(int idLineaKardex) {
        this.idLineaKardex = idLineaKardex;
    }

    public Kardex getKardex() {
        return kardex;
    }

    public void setKardex(Kardex kardex) {
        this.kardex = kardex;
    }

    public Date getFechaLinea() {
        return fechaLinea;
    }

    public void setFechaLinea(Date fechaLinea) {
        this.fechaLinea = fechaLinea;
    }

    public int getEntraCant() {
        return entraCant;
    }

    public void setEntraCant(int entraCant) {
        this.entraCant = entraCant;
    }

    public BigDecimal getEntraUnit() {
        return entraUnit;
    }

    public void setEntraUnit(BigDecimal entraUnit) {
        this.entraUnit = entraUnit;
    }

    public BigDecimal getEntraMont() {
        return entraMont;
    }

    public void setEntraMont(BigDecimal entraMont) {
        this.entraMont = entraMont;
    }

    public int getSaliCant() {
        return saliCant;
    }

    public void setSaliCant(int saliCant) {
        this.saliCant = saliCant;
    }

    public BigDecimal getSaliUnit() {
        return saliUnit;
    }

    public void setSaliUnit(BigDecimal saliUnit) {
        this.saliUnit = saliUnit;
    }

    public BigDecimal getSaliMont() {
        return saliMont;
    }

    public void setSaliMont(BigDecimal saliMont) {
        this.saliMont = saliMont;
    }

    public int getMontCant() {
        return montCant;
    }

    public void setMontCant(int montCant) {
        this.montCant = montCant;
    }

    public BigDecimal getMontUnit() {
        return montUnit;
    }

    public void setMontUnit(BigDecimal montUnit) {
        this.montUnit = montUnit;
    }

    public BigDecimal getMontMont() {
        return montMont;
    }

    public void setMontMont(BigDecimal montMont) {
        this.montMont = montMont;
    }

    
    
}
