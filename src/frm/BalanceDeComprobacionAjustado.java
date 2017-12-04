package frm;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import db.Consulta;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import modelo.Cuenta;
import modelo.Balance;


/**
 *
 * @author Fatima
 */
public class BalanceDeComprobacionAjustado extends javax.swing.JInternalFrame {
    List<Cuenta> lista;
    /**
     * Creates new form BalanceDeComprobacionAjustado
     */
    public BalanceDeComprobacionAjustado(List<Cuenta> lista) {
        initComponents();
        this.lista = lista;
        consultarDatos();
    }
    
    public BalanceDeComprobacionAjustado() {
        initComponents();
        
        //Balance b = new Balance();
        //b.extraerAjustes();
        consultarDatos();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        tfDeudor = new javax.swing.JTextField();
        tfAcreedor = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("BALANCE DE COMPROBACION AJUSTADO");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Deudor", "Acreedor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Total");

        tfDeudor.setEditable(false);

        tfAcreedor.setEditable(false);

        jButton1.setText("Guardar a PDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tfDeudor, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(tfAcreedor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfAcreedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfDeudor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        try {
            Document document = new Document();
            JFileChooser open = new JFileChooser();
            PdfPTable table = new PdfPTable(4);
            
            open.setFileFilter(new FileNameExtensionFilter("archivo pdf", "pdf"));
            open.showSaveDialog(this);

            PdfWriter.getInstance(document, new FileOutputStream(open.getSelectedFile() + ".pdf"));
            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Paragraph p = new Paragraph("PROPLASTIC S.A. DE C.V.");
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            p = new Paragraph("BALANCE DE COMPROBACIÓN AJUSTADO");
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            p = new Paragraph("DEL ____ AL ____ DE 20___");
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add(Chunk.NEWLINE);

            addRows(table);
            //Codigo de la tabla 

            document.add(table);
            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(EstadoDeCapital.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EstadoDeCapital.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BalanceDeComprobacionAjustado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BalanceDeComprobacionAjustado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BalanceDeComprobacionAjustado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BalanceDeComprobacionAjustado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BalanceDeComprobacionAjustado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField tfAcreedor;
    private javax.swing.JTextField tfDeudor;
    // End of variables declaration//GEN-END:variables

    private void addRows(PdfPTable table) {
        Font fontBold = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
        Font fontItalic = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.ITALIC);
        PdfPCell cell;
        //Crear el header
        cell = new PdfPCell(new Phrase("Codigo",fontBold));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Nombre",fontBold));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Deudor",fontBold));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Acreedor",fontBold));
        table.addCell(cell);
        
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            for (int j = 0; j < 4; j++) {
                String aux = String.valueOf(modelo.getValueAt(i, j));
                table.addCell(aux);
            }
        }
        
        cell = new PdfPCell(new Phrase("Total",fontItalic));
        cell.setColspan(2);
        table.addCell(cell);
        
        table.addCell(tfDeudor.getText());
        table.addCell(tfAcreedor.getText());
    }
    
    private void consultarDatos(){
        double totHaber = 0;
        double totDebe = 0;
        
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        
        Consulta c = new Consulta();
        c.inicializar();
        
        List<Cuenta> cuentas = c.obtenerYFiltrar("Cuenta","ajustable=true");
        for(Cuenta x: cuentas){
            modelo.addRow(new Object[]{x.getCodigo(),x.getNombreCuenta(),x.getSumaDebe().doubleValue(), x.getSumaHaber().doubleValue()});
            totDebe += x.getSumaDebe().doubleValue();
            totHaber += x.getSumaHaber().doubleValue();
        }
        
        tfDeudor.setText(String.valueOf(totDebe));
        tfAcreedor.setText(String.valueOf(totHaber));
        
        c.cerrarConexion();
    }
}
