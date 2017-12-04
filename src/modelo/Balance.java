/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import db.Consulta;
import java.math.BigDecimal;

/**
 *
 * @author Celina
 */
public class Balance {
    
    private int codigo;
    private Cuenta cuenta;
    private BigDecimal monto;
    
    public Balance(){
        
    }
    
    public Balance(int codigo, Cuenta cuenta, BigDecimal monto){
        this.codigo = codigo;
        this.cuenta = cuenta;
        this.monto = monto;
        
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    
    public void extraerComprobacion(){
        Consulta c = new Consulta();
        c.inicializar();
        
        c.obtener("cuenta");
        
        c.cerrarConexion();
    }
    
    public void extraerAjustes(){
        Consulta c = new Consulta();
        c.inicializar();
        
        c.obtenerYFiltrar("cuenta", "ajustables = true");
        
        c.cerrarConexion();
    }
}
