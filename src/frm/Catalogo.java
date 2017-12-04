/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frm;

import Clases.CuentasTableModel;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import modelo.Cuenta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jaquino
 */
public class Catalogo extends javax.swing.JInternalFrame {

    CuentasTableModel cuentaTB = new CuentasTableModel();

    /**
     * Creates new form Catalogo
     */
    public Point centrar(JInternalFrame frame) {
        Dimension desktopSize = this.getDesktopPane().getSize();
        Dimension jInternalFrameSize = frame.getSize();
        Point punto = new Point((desktopSize.width - jInternalFrameSize.width) / 2, (desktopSize.height - jInternalFrameSize.height) / 2);

        return punto;
    }

    public Catalogo(List<Cuenta> lista) { //Constructor, debe crearse despues de consultar la base.
        this.lista = lista; //Se asigna la lista de la consulta al objeto local.
        initComponents();
        inicializarColumnas(); //Se genera la tabla.
    }
    //Objeto global para crear cuentas.
    public List<Cuenta> lista;

    public void deleteAllRows() {
        DefaultTableModel modelo = (DefaultTableModel) catTabla.getModel();
        modelo.getDataVector().removeAllElements();
        revalidate();

    }

    //Por pereza he agregado aqui el rellenado de la tabla xD
    public void inicializarColumnas() {
        TableColumnModel tColumnModel = new DefaultTableColumnModel();
        String columnas[] = {"Código de Cuenta", "Nombre", "Suma Debe", "Suma Haber", "Saldo"};
        int i = 0;
        for (String s : columnas) {
            TableColumn col = new TableColumn(i);
            col.setHeaderValue(s);
            i++;
            tColumnModel.addColumn(col);
        }
        tColumnModel.getColumn(1).setPreferredWidth(600);

        catTabla.setColumnModel(tColumnModel);

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
        String T = "    "; //Tabulador :V 
        DefaultTableModel modelo = (DefaultTableModel) catTabla.getModel();
        //Asignacion de arreglos en la tabla del catalogo.
        for (Cuenta x : sub1) { //Se imprimen las cuentas raiz ACTIVO, PASIVO... etc.
            modelo.addRow(new Object[]{x.getCodigo(), x.getNombreCuenta(), x.getSumaDebe(), x.getSumaHaber(), x.getSaldo()});
            for (Cuenta y : sub2) { //Entre una y otra cuenta raiz, se imprimen las de segundo nivel que corresponden a cada uno.
                if (x.getCodigo() == y.getCuenta().getCodigo()) { //Hay varios ids diferentes en los siguientes arreglos.
                    //En el sub2, hay varios de 2 digitos, hay que filtrar por categoria.
                    modelo.addRow(new Object[]{y.getCodigo(), T + y.getNombreCuenta(), y.getSumaDebe(), y.getSumaHaber(), y.getSaldo()});
                    for (Cuenta z : sub3) { //Entre cada cuenta del segundo nivel se ponen las correspondientes del tercer nivel
                        //Se continua hasta el sexto nivel, las cuentas aparecen en el orden definido por la empresa
                        if (y.getCodigo() == z.getCuenta().getCodigo()) {
                            modelo.addRow(new Object[]{z.getCodigo(), T + T + z.getNombreCuenta(), z.getSumaDebe(), z.getSumaHaber(), z.getSaldo()});
                            for (Cuenta b : sub4) {
                                if (z.getCodigo() == b.getCuenta().getCodigo()) {
                                    modelo.addRow(new Object[]{b.getCodigo(), T + T + T + b.getNombreCuenta(), b.getSumaDebe(), b.getSumaHaber(), b.getSaldo()});
                                    for (Cuenta c : sub5) {
                                        if (b.getCodigo() == c.getCuenta().getCodigo()) {
                                            modelo.addRow(new Object[]{c.getCodigo(), T + T + T + T + c.getNombreCuenta(), c.getSumaDebe(), c.getSumaHaber(), c.getSaldo()});
                                            for (Cuenta d : sub6) {
                                                if (c.getCodigo() == d.getCuenta().getCodigo()) {
                                                    modelo.addRow(new Object[]{d.getCodigo(), T + T + T + T + T + d.getNombreCuenta(), d.getSumaDebe(), d.getSumaHaber(), d.getSaldo()});
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

        jLabel1 = new javax.swing.JLabel();
        tfBusquedaNombre = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        catTabla = new javax.swing.JTable();

        setClosable(true);
        setResizable(true);

        jLabel1.setText("Busqueda:");

        tfBusquedaNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBusquedaNombreKeyReleased(evt);
            }
        });

        jButton2.setText("Agregar cuenta");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Eliminar");

        catTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(catTabla);
        if (catTabla.getColumnModel().getColumnCount() > 0) {
            catTabla.getColumnModel().getColumn(0).setMinWidth(70);
            catTabla.getColumnModel().getColumn(0).setPreferredWidth(90);
            catTabla.getColumnModel().getColumn(0).setMaxWidth(100);
            catTabla.getColumnModel().getColumn(1).setMinWidth(400);
            catTabla.getColumnModel().getColumn(1).setPreferredWidth(400);
            catTabla.getColumnModel().getColumn(1).setMaxWidth(500);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfBusquedaNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfBusquedaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        addCuenta();
//        if (addCuentaCerrada) {
//            this.dispose();
//        }

        //  deleteAllRows();
        //  inicializarColumnas();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void tfBusquedaNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBusquedaNombreKeyReleased
        String nombre = tfBusquedaNombre.getText();

        if (!tfBusquedaNombre.getText().isEmpty()) {
            nombre = nombre.toLowerCase();
            DefaultTableModel modelo = (DefaultTableModel) catTabla.getModel();
            modelo.getDataVector().removeAllElements();
            for (Cuenta i : lista) {
                if (i.getNombreCuenta().toLowerCase().contains(nombre) || String.valueOf(i.getCodigo()).contains(nombre)) {
                    modelo.addRow(new Object[]{i.getCodigo(), i.getNombreCuenta(),i.getSumaDebe(),i.getSumaHaber(),i.getSaldo()});
                }
            }
            catTabla.revalidate();
        } else {
            DefaultTableModel modelo = (DefaultTableModel) catTabla.getModel();
            modelo.getDataVector().removeAllElements();
            catTabla.revalidate();
            inicializarColumnas();

        }
    }//GEN-LAST:event_tfBusquedaNombreKeyReleased

    private void addCuenta() {

        AddCuenta abrir = new AddCuenta(this.lista);
        abrir.setLocation(centrar(abrir));
        this.getDesktopPane().add(abrir);
        abrir.show();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable catTabla;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tfBusquedaNombre;
    // End of variables declaration//GEN-END:variables
}
