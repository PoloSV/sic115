package modelo;

import java.util.ArrayList;

public class Cuenta {
    private TipoCuenta  tipoCuenta;
    private String      codigoCuenta;

    public String getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(String codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }
    private int         idCuenta;
    private String      nombreCuenta;
    private double      sumaDebe;
    private double      sumaHaber;
    private double      saldo;
    private boolean     ajustable;

    public Cuenta(String codigoCuenta, TipoCuenta tipoCuenta, int idCuenta, String nombreCuenta, double sumaDebe, double sumaHaber, double saldo, boolean ajustable) {
        this.tipoCuenta = tipoCuenta;
        this.idCuenta = idCuenta;
        this.nombreCuenta = nombreCuenta;
        this.sumaDebe = sumaDebe;
        this.sumaHaber = sumaHaber;
        this.saldo = saldo;
        this.ajustable = ajustable;
        this.codigoCuenta = codigoCuenta;
    }

    public Cuenta() {
    
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public double getSumaDebe() {
        return sumaDebe;
    }

    public void setSumaDebe(double sumaDebe) {
        this.sumaDebe = sumaDebe;
    }

    public double getSumaHaber() {
        return sumaHaber;
    }

    public void setSumaHaber(double sumaHaber) {
        this.sumaHaber = sumaHaber;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isAjustable() {
        return ajustable;
    }

    public void setAjustable(boolean ajustable) {
        this.ajustable = ajustable;
    }
}
