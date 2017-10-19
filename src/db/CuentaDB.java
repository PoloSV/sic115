package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cuenta;
import modelo.TipoCuenta;

public class CuentaDB {
    public ArrayList<Cuenta> cuentas = new ArrayList<>();
    
    public TipoCuenta getTipoCuenta(int id){
        TipoCuenta tc = null;
        String sentenciaSQL = "SELECT * FROM tipo_cuenta WHERE id_tipo = ?";
        try {
            PreparedStatement pS = Conexion.getConexion().prepareStatement(sentenciaSQL);
            pS.setInt(1, id);
            ResultSet rs = pS.executeQuery();
            while(rs.next()){
                tc = new TipoCuenta(rs.getInt("id_tipo"),rs.getString("nombre_tipo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CuentaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tc;
    }
    
    public void getFromDB(){
//        try {
//            Statement sta = Conexion.getConexion().createStatement();
//            ResultSet rs = sta.executeQuery("SELECT id_cuenta, id_tipo, codigo, nombre_cuenta,suma_debe, suma_haber,saldo, ajustable FROM cuenta");
//            while(rs.next()){
//                Cuenta c = new Cuenta();
//                c.setIdCuenta(rs.getInt("id_cuenta"));
//                c.setCodigoCuenta(rs.getString("codigo"));
//                c.setNombreCuenta(rs.getString("nombre_cuenta"));
//                c.setTipoCuenta(getTipoCuenta(rs.getInt("id_tipo")));
//                c.setSumaDebe(rs.getDouble("suma_debe"));
//                c.setSumaHaber(rs.getDouble("suma_haber"));
//                c.setSaldo(rs.getDouble("saldo"));
//                c.setAjustable(rs.getBoolean("ajustable"));
//                cuentas.add(c);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(CuentaDB.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    public ArrayList<Cuenta> getCuentas(){
        if(cuentas.isEmpty()) getFromDB();
        return cuentas;
    }
//    public boolean addCuenta(Cuenta c) throws SQLException{
//        String sentenciaSQL = "INSERT INTO cuenta VALUES id_tipo = ?, codigo = ?, nombre_cuenta = ?, suma_debe = ?, suma_haber = ?, saldo = ?, ajustable = ?";
//        PreparedStatement pS = Conexion.getConexion().prepareStatement(sentenciaSQL);
//        pS.setInt(1, c.getTipoCuenta().getIdTipo());
//        pS.setString(2, c.getCodigoCuenta());
//        pS.setString(3, c.getNombreCuenta());
//        pS.setDouble(4, c.getSumaDebe());
//        pS.setDouble(5, c.getSumaHaber());
//        pS.setDouble(6, c.getSaldo());
//        pS.setBoolean(7, c.isAjustable());
//        pS.execute();
//        return true;
//    }
}
