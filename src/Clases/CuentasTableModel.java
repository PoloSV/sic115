package Clases;


import db.CuentaDB;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import modelo.Cuenta;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jaquino
 */
public class CuentasTableModel extends AbstractTableModel{
    public ArrayList<Cuenta> cuentas = new ArrayList<>();
    @Override
    public int getRowCount() {
        return cuentas.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cuenta c = cuentas.get(rowIndex);
        Object valor = null;
//        switch(columnIndex){
//            case 0:
//                valor = c.getIdCuenta();
//                break;
//            case 1:
//                valor = c.getTipoCuenta();
//                break;
//            case 2:
//                valor = c.getCodigoCuenta();
//                break;
//            case 3:
//                valor = c.getNombreCuenta();
//                break;
//            case 4:
//                valor = c.getSumaDebe();
//                break;
//            case 5:
//                valor = c.getSumaHaber();
//                break;
//            case 6:
//                valor = c.getSaldo();
//                break;
//            case 7:
//                valor = c.isAjustable();
//                break;
//        }
        return "";
    }
    
}
