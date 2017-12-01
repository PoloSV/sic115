/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frm;

import db.Consulta;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.text.NumberFormatter;
import modelo.Kardex;
import modelo.LineaKardex;

/**
 *
 * @author Marvin
 */
public class NuevaLineaKardexMateriaPrima extends javax.swing.JInternalFrame {

    public static final int ENTRADA = 1;
    public static final int SALIDA = 2;
    
    private int opcion;
    private Kardex kardex;
    
    /**
     * Creates new form NuevaLineaKardexMateriaPrima
     */
    public NuevaLineaKardexMateriaPrima(int opcion, Kardex kardexMatPrima) {
        this.opcion = opcion;
        this.kardex = kardexMatPrima;
        initComponents();
        setClosable(true);
        
        if(opcion == SALIDA){
            txtPrecioUnitario.setEditable(false);
            DecimalFormat df = new DecimalFormat("#.0000");
            double preci = (kardex.getSumaEntradas().doubleValue() - kardex.getSumaSalidas().doubleValue()) / (kardex.getSumaCantidadEntradas() - kardex.getSumaCantidadSalidas());
            preci = Double.parseDouble(df.format(preci));
            txtPrecioUnitario.setText(String.valueOf(preci));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        txtPrecioUnitario = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nueva Linea");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Cantidad:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Precio Unitario:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Nueva Linea");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(btnGuardar)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel5)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(btnGuardar)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String cant = txtCantidad.getText();
        String unit = txtPrecioUnitario.getText();
        
        try{
            int intCant = Integer.parseInt(cant);
            double doubleUnit = Double.parseDouble(unit);
            double monto = intCant*doubleUnit;
            LineaKardex nuevaLinea = new LineaKardex();
            nuevaLinea.setFechaLinea(new Date());
            nuevaLinea.setKardex(kardex);
            switch(opcion){
                case ENTRADA:
                    nuevaLinea.setSaliCant(0);
                    nuevaLinea.setSaliUnit(BigDecimal.valueOf(0.0));
                    nuevaLinea.setSaliMont(BigDecimal.valueOf(0.0));
                    
                    nuevaLinea.setEntraCant(intCant);
                    nuevaLinea.setEntraUnit(BigDecimal.valueOf(doubleUnit));
                    nuevaLinea.setEntraMont(BigDecimal.valueOf(monto));
                    kardex.setSumaCantidadEntradas(kardex.getSumaCantidadEntradas() + intCant);
                    nuevaLinea.setMontCant(kardex.getSumaCantidadEntradas() - kardex.getSumaCantidadSalidas());
                    double sumaMontoEntradas = kardex.getSumaEntradas().doubleValue() + monto;
                    kardex.setSumaEntradas(BigDecimal.valueOf(sumaMontoEntradas));
                    double sumaMontoSalidas = kardex.getSumaSalidas().doubleValue();
                    nuevaLinea.setMontMont(BigDecimal.valueOf(sumaMontoEntradas - sumaMontoSalidas));
                    double precioUnitario = (sumaMontoEntradas - sumaMontoSalidas)/nuevaLinea.getMontCant();
                    nuevaLinea.setMontUnit(BigDecimal.valueOf(precioUnitario));
                    
                    
                break;
                case SALIDA:
                    nuevaLinea.setEntraCant(0);
                    nuevaLinea.setEntraUnit(BigDecimal.valueOf(0.0));
                    nuevaLinea.setEntraMont(BigDecimal.valueOf(0.0));
                    
                    nuevaLinea.setSaliCant(intCant);
                    nuevaLinea.setSaliUnit(BigDecimal.valueOf(doubleUnit));
                    nuevaLinea.setSaliMont(BigDecimal.valueOf(monto));
                    kardex.setSumaCantidadSalidas(kardex.getSumaCantidadSalidas() + intCant);
                    nuevaLinea.setMontCant(kardex.getSumaCantidadEntradas() - kardex.getSumaCantidadSalidas());
                    double sumaMontoSalida = kardex.getSumaSalidas().doubleValue() + monto;
                    kardex.setSumaSalidas(BigDecimal.valueOf(sumaMontoSalida));
                    double sumaMontoEntrada = kardex.getSumaEntradas().doubleValue();
                    nuevaLinea.setMontMont(BigDecimal.valueOf(sumaMontoEntrada - sumaMontoSalida));
                    double precioUnit = (sumaMontoEntrada - sumaMontoSalida)/nuevaLinea.getMontCant();
                    nuevaLinea.setMontUnit(BigDecimal.valueOf(precioUnit));
                break;
            }
            Consulta c = new Consulta();
            c.inicializar();
            
            c.actualizar(kardex);
            c.cerrarConexion();
            
            Consulta d = new Consulta();
            d.inicializar();
            d.guardar(nuevaLinea);
            
            d.cerrarConexion();
            
            JOptionPane.showMessageDialog(this, "Guardado Con exito");
            txtCantidad.setText("");
            if(opcion == ENTRADA){
                txtPrecioUnitario.setText("");
            }        
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Ingrese Numeros Validos", "Error en Datos", JOptionPane.ERROR_MESSAGE);
        }
        
        
        
    }//GEN-LAST:event_btnGuardarActionPerformed
   
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtPrecioUnitario;
    // End of variables declaration//GEN-END:variables
}
