/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frm;

import db.Consulta;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import modelo.Cuenta;
import modelo.LineaDePartida;
import modelo.PeriodoContable;
import modelo.Sesiones;


/**
 *
 * @author jaquino
 */
public class Partida extends javax.swing.JInternalFrame {

    /**
     * Creates new form Partida
     */
    List<Cuenta> lista = new ArrayList<>();
    public static Sesiones sesion;
    
    public Partida(List<Cuenta> lista, Sesiones sesion) {
        this.lista = lista;
        initComponents();
        generarTablaInicialBusqueda();
        generarTablaInicialPartida();
        this.sesion = sesion;

    }

    public void revisarTablaDePartida() {
        double sumaDebe = 0;
        double sumaHaber = 0;
        for (int i = 0; i < tablaDePartida.getRowCount(); i++) {
            Double debe = (Double) tablaDePartida.getValueAt(i, 2);
            Double haber = (Double) tablaDePartida.getValueAt(i, 3);
            sumaDebe = sumaDebe + debe;
            sumaHaber = sumaHaber + haber;
        }
        totalDebe.setText(String.valueOf(sumaDebe));
        totalHaber.setText(String.valueOf(sumaHaber));

        if (sumaDebe != sumaHaber) {
            errorPartidaDoble.setText("No se cumple partida doble");
            totalDebe.setForeground(Color.RED);
            totalHaber.setForeground(Color.RED);
            etiquetaTotal.setForeground(Color.RED);
        } else {
            errorPartidaDoble.setText("");
            totalDebe.setForeground(Color.BLACK);
            totalHaber.setForeground(Color.BLACK);
            etiquetaTotal.setForeground(Color.BLACK);
        }

    }

    public void generarTablaInicialPartida() {
        TableColumnModel tColumnModel = new DefaultTableColumnModel();
        String columnas[] = {"Código", "Nombre", "DEBE", "HABER"};
        int i = 0;
        for (String s : columnas) {
            TableColumn col = new TableColumn(i);
            col.setHeaderValue(s);
            i++;
            tColumnModel.addColumn(col);
        }
        tColumnModel.getColumn(0).setPreferredWidth(35);
        tColumnModel.getColumn(1).setPreferredWidth(250);
        tColumnModel.getColumn(2).setPreferredWidth(100);
        tColumnModel.getColumn(3).setPreferredWidth(100);

        tablaDePartida.setColumnModel(tColumnModel);

    }

    public void generarTablaInicialBusqueda() {
        TableColumnModel tColumnModel = new DefaultTableColumnModel();
        String columnas[] = {"Código", "Nombre"};
        int i = 0;
        for (String s : columnas) {
            TableColumn col = new TableColumn(i);
            col.setHeaderValue(s);
            i++;
            tColumnModel.addColumn(col);
        }
        tColumnModel.getColumn(0).setPreferredWidth(20);
        tColumnModel.getColumn(1).setPreferredWidth(200);

        tablaListaCuentas.setColumnModel(tColumnModel);

        //Declaracion de sub arreglos.
        List<Cuenta> sub1 = new ArrayList<>();
        List<Cuenta> sub2 = new ArrayList<>();
        List<Cuenta> sub3 = new ArrayList<>();
        List<Cuenta> sub4 = new ArrayList<>();
        List<Cuenta> sub5 = new ArrayList<>();
        List<Cuenta> sub6 = new ArrayList<>();

        //Division de cuentas en arreglos.
        for (Cuenta x : lista) {
            if (x.getCodigo() < 10) {
                sub1.add(x);
            } else if (x.getCodigo() < 100) {
                sub2.add(x);
            } else if (x.getCodigo() < 1000) {
                sub3.add(x);
            } else if (x.getCodigo() < 10000) {
                sub4.add(x);
            } else if (x.getCodigo() < 100000) {
                sub5.add(x);
            } else if (x.getCodigo() < 1000000) {
                sub6.add(x);
            }
        }
        String T = "  "; //Tabulador :V 
        DefaultTableModel modelo = (DefaultTableModel) tablaListaCuentas.getModel();
        //Asignacion de arreglos en la tabla del catalogo.
        for (Cuenta x : sub1) { //Se imprimen las cuentas raiz ACTIVO, PASIVO... etc.
            modelo.addRow(new Object[]{x.getCodigo(), x.getNombreCuenta()});
            for (Cuenta y : sub2) { //Entre una y otra cuenta raiz, se imprimen las de segundo nivel que corresponden a cada uno.
                if (x.getCodigo() == y.getCuenta().getCodigo()) { //Hay varios ids diferentes en los siguientes arreglos.
                    //En el sub2, hay varios de 2 digitos, hay que filtrar por categoria.
                    modelo.addRow(new Object[]{y.getCodigo(), T + y.getNombreCuenta()});
                    for (Cuenta z : sub3) { //Entre cada cuenta del segundo nivel se ponen las correspondientes del tercer nivel
                        //Se continua hasta el sexto nivel, las cuentas aparecen en el orden definido por la empresa
                        if (y.getCodigo() == z.getCuenta().getCodigo()) {
                            modelo.addRow(new Object[]{z.getCodigo(), T + T + z.getNombreCuenta()});
                            for (Cuenta b : sub4) {
                                if (z.getCodigo() == b.getCuenta().getCodigo()) {
                                    modelo.addRow(new Object[]{b.getCodigo(), T + T + T + b.getNombreCuenta()});
                                    for (Cuenta c : sub5) {
                                        if (b.getCodigo() == c.getCuenta().getCodigo()) {
                                            modelo.addRow(new Object[]{c.getCodigo(), T + T + T + T + c.getNombreCuenta()});
                                            for (Cuenta d : sub6) {
                                                if (c.getCodigo() == d.getCuenta().getCodigo()) {
                                                    modelo.addRow(new Object[]{d.getCodigo(), T + T + T + T + T + d.getNombreCuenta()});
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDePartida = new javax.swing.JTable();
        etiquetaTotal = new javax.swing.JLabel();
        campoDescripcion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        AgregarCuentaAPartida = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaListaCuentas = new javax.swing.JTable();
        tfBusquedaNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        totalHaber = new javax.swing.JLabel();
        totalDebe = new javax.swing.JLabel();
        errorPartidaDoble = new javax.swing.JLabel();
        errorDescripcion = new javax.swing.JLabel();
        errorTablaPartida = new javax.swing.JLabel();
        errorAgregarAPartida = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Partida");
        setToolTipText("");

        tablaDePartida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", " Nombre", "DEBE", "HABER"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDePartida.getModel().addTableModelListener(
            new TableModelListener() 
            {
                public void tableChanged(TableModelEvent evt) 
                {
                    revisarTablaDePartida();
                }

            });
            jScrollPane1.setViewportView(tablaDePartida);

            etiquetaTotal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
            etiquetaTotal.setText("Total:");

            jLabel2.setText("Descripción:");

            jButton1.setText("Guardar");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });

            AgregarCuentaAPartida.setText("Agregar a la Partida");
            AgregarCuentaAPartida.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    AgregarCuentaAPartidaActionPerformed(evt);
                }
            });

            jButton3.setText("Eliminar Seleccionada");
            jButton3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton3ActionPerformed(evt);
                }
            });

            tablaListaCuentas.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "Codigo", "Nombre"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            tablaListaCuentas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            jScrollPane2.setViewportView(tablaListaCuentas);

            tfBusquedaNombre.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    tfBusquedaNombreKeyReleased(evt);
                }
            });

            jLabel6.setText("Busqueda por Nombre o Código:");

            totalHaber.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
            totalHaber.setText("0");

            totalDebe.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
            totalDebe.setText("0");

            errorPartidaDoble.setForeground(new java.awt.Color(255, 0, 0));

            errorDescripcion.setForeground(new java.awt.Color(255, 0, 51));

            errorTablaPartida.setForeground(new java.awt.Color(255, 0, 0));

            errorAgregarAPartida.setForeground(new java.awt.Color(255, 0, 0));

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                                        .addComponent(tfBusquedaNombre)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(AgregarCuentaAPartida)
                                            .addGap(18, 18, 18)
                                            .addComponent(errorAgregarAPartida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(207, 207, 207)
                                                    .addComponent(etiquetaTotal)
                                                    .addGap(120, 120, 120)
                                                    .addComponent(totalDebe, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(errorPartidaDoble, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(totalHaber, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(jScrollPane1))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(errorTablaPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton3)))
                            .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(errorDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(campoDescripcion)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton1)
                                    .addGap(13, 13, 13))))))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)
                        .addComponent(jLabel2))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(errorDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfBusquedaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton3)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(errorTablaPartida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(10, 10, 10)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(AgregarCuentaAPartida)
                                    .addComponent(errorAgregarAPartida, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(etiquetaTotal)))
                        .addGroup(layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(totalHaber, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(totalDebe, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(errorPartidaDoble, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            DefaultTableModel modelo = (DefaultTableModel) tablaDePartida.getModel();
            if (modelo.getRowCount() > 0) {
                modelo.getDataVector().remove(tablaDePartida.getSelectedRow());
                tablaDePartida.revalidate();
                revisarTablaDePartida();
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tfBusquedaNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBusquedaNombreKeyReleased
        String nombre = tfBusquedaNombre.getText();

        if (!tfBusquedaNombre.getText().isEmpty()) {
            nombre = nombre.toLowerCase();
            DefaultTableModel modelo = (DefaultTableModel) tablaListaCuentas.getModel();
            modelo.getDataVector().removeAllElements();
            for (Cuenta i : lista) {
                if (i.getNombreCuenta().toLowerCase().contains(nombre) || String.valueOf(i.getCodigo()).contains(nombre)) {
                    modelo.addRow(new Object[]{i.getCodigo(), i.getNombreCuenta()});
                }
            }
            tablaListaCuentas.revalidate();
        } else {
            DefaultTableModel modelo = (DefaultTableModel) tablaListaCuentas.getModel();
            modelo.getDataVector().removeAllElements();
            tablaListaCuentas.revalidate();
            generarTablaInicialBusqueda();

        }


    }//GEN-LAST:event_tfBusquedaNombreKeyReleased

    private void AgregarCuentaAPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarCuentaAPartidaActionPerformed
        // TODO add your handling code here:
        String elementoNombre = "";
        String elementoCodigo = "";
        
        DefaultTableModel modelo = (DefaultTableModel) tablaDePartida.getModel();
        try {
            int filaSeleccionada = tablaListaCuentas.getSelectedRow();

            elementoNombre = (String) tablaListaCuentas.getValueAt(filaSeleccionada, 1);
            elementoCodigo = String.valueOf(tablaListaCuentas.getValueAt(filaSeleccionada, 0));

            elementoNombre = elementoNombre.trim();
            elementoCodigo = elementoCodigo.trim();
            boolean cuentaExistente = false;
            for (int i = 0; i < modelo.getRowCount(); i++) {
                String aux = String.valueOf(modelo.getValueAt(i, 0));
                if (elementoCodigo.equals(aux)) {
                    cuentaExistente = true;
                }
            }
            if (!cuentaExistente) {
                for (Cuenta x : lista) {
                    if (x.getNombreCuenta().equals(elementoNombre) && String.valueOf(x.getCodigo()).equals(elementoCodigo)) {
                        modelo.addRow(new Object[]{x.getCodigo(), x.getNombreCuenta(), 0.0, 0.0});
                    }
                }
                errorAgregarAPartida.setText("");
            }else{
                errorAgregarAPartida.setText("Ya agregada.");
            }

        } catch (ArrayIndexOutOfBoundsException e) {

        }
    }//GEN-LAST:event_AgregarCuentaAPartidaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(tablaDePartida.getRowCount() == 0){
            errorTablaPartida.setText("No deje la partida sin contenido/");
        }else if(campoDescripcion.getText().isEmpty()){
            errorDescripcion.setText("Descripcion es obligatoria.");
        }else if(totalDebe.getText().equals("0.0")){
            errorTablaPartida.setText("Agregue datos a las cuentas.");
        }else if(!errorPartidaDoble.getText().equals("")){
            errorPartidaDoble.setText("Asegurese de cumplir partida doble.");
        } else {
            errorTablaPartida.setText("");
            errorDescripcion.setText("");
            errorPartidaDoble.setText("");
            
        Consulta con = new Consulta();
        con.inicializar();
        Integer idPeriodo = con.getLastId("modelo.PeriodoContable", "idPeriodo");
        List<PeriodoContable> periodo = con.obtenerYFiltrar("modelo.PeriodoContable", "idPeriodo = "+idPeriodo);
        Date fecha = new Date();    
        modelo.Partida nuevaPartida = new modelo.Partida();
        nuevaPartida.setDescripcion(campoDescripcion.getText().trim());
       // nuevaPartida.setIdPartida(1);
        nuevaPartida.setFecha(fecha);

        nuevaPartida.setUsuario(sesion.getUsuario());
        nuevaPartida.setPeriodoContable(periodo.get(0));
        con.guardar(nuevaPartida);
        con.cerrarConexion();
        LineaDePartida linea = new LineaDePartida();
       
        for(int i=0; i<tablaDePartida.getRowCount(); i++){
            con.inicializar();
            //Obteniendo datos de la tabla
           Integer codigo = (Integer) tablaDePartida.getValueAt(i, 0);
           String nombre = (String) tablaDePartida.getValueAt(i, 1);
           List<Cuenta> arreglo =  con.obtenerYFiltrar("modelo.Cuenta", "idCuenta ="+codigo);
           Cuenta cuenta = arreglo.get(0);
           BigDecimal debe = new BigDecimal((Double) tablaDePartida.getValueAt(i, 2));
           BigDecimal haber = new BigDecimal((Double) tablaDePartida.getValueAt(i, 3));
           
           //Obteniendo valores de la Cuenta para la suma acumulada.
           Double debeCuenta = cuenta.getSumaDebe().doubleValue();
           Double haberCuenta = cuenta.getSumaHaber().doubleValue();
           
           //Asignando valores a la cuenta correspondiente, sumatoria acumulada.
           BigDecimal debeTotalCuenta = new BigDecimal(debeCuenta + debe.doubleValue());
           BigDecimal haberTotalCuenta = new BigDecimal(haberCuenta + haber.doubleValue());
           cuenta.setSumaDebe(debeTotalCuenta);
           cuenta.setSumaHaber(haberTotalCuenta);
           con.actualizar(cuenta);
           con.cerrarConexion();
           con.inicializar();
           //Guardando la linea de partida. Una por iteracion.
           linea.setIdLinea(codigo);
           linea.setCuenta(cuenta);
           linea.setDebe(debe);
           linea.setHaber(haber);
           linea.setPartida(nuevaPartida);
           con.guardar(linea);
          con.cerrarConexion();
        }
        errorTablaPartida.setText("Guardado con exito. Puede crear otra partida o salir.");
       

        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    public Point centrar(JInternalFrame frame) {
        Dimension desktopSize = this.getDesktopPane().getSize();
        Dimension jInternalFrameSize = frame.getSize();
        Point punto = new Point((desktopSize.width - jInternalFrameSize.width) / 2, (desktopSize.height - jInternalFrameSize.height) / 2);

        return punto;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AgregarCuentaAPartida;
    private javax.swing.JTextField campoDescripcion;
    private javax.swing.JLabel errorAgregarAPartida;
    private javax.swing.JLabel errorDescripcion;
    private javax.swing.JLabel errorPartidaDoble;
    private javax.swing.JLabel errorTablaPartida;
    private javax.swing.JLabel etiquetaTotal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaDePartida;
    private javax.swing.JTable tablaListaCuentas;
    private javax.swing.JTextField tfBusquedaNombre;
    private javax.swing.JLabel totalDebe;
    private javax.swing.JLabel totalHaber;
    // End of variables declaration//GEN-END:variables
}
